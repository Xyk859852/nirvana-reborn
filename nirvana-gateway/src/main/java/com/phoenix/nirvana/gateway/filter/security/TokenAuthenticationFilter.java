package com.phoenix.nirvana.gateway.filter.security;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.phoenix.nirvana.common.core.KeyValue;
import com.phoenix.nirvana.common.util.cache.CacheUtils;
import com.phoenix.nirvana.common.util.json.JsonUtils;
import com.phoenix.nirvana.common.vo.CommonResult;
import com.phoenix.nirvana.gateway.util.SecurityFrameworkUtils;
import com.phoenix.nirvana.gateway.util.WebFrameworkUtils;
import com.phoenix.nirvana.service.system.rpc.admin.OAuth2TokenApi;
import com.phoenix.nirvana.service.system.rpc.admin.domain.bo.OnlineUserBO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.function.Function;

/**
 * 网关路由鉴权
 */
@Slf4j
@Component
public class TokenAuthenticationFilter implements GlobalFilter {

    @Autowired
    SecurityProperties securityProperties;

    /**
     * CommonResult<OAuth2AccessTokenCheckRespDTO> 对应的 TypeReference 结果，用于解析 checkToken 的结果
     */
    private static final TypeReference<CommonResult<OnlineUserBO>> CHECK_RESULT_TYPE_REFERENCE
            = new TypeReference<CommonResult<OnlineUserBO>>() {
    };

    /**
     * 登录用户的本地缓存
     * <p>
     * key1：多租户的编号
     * key2：访问令牌
     */
    private final LoadingCache<KeyValue<Long, String>, OnlineUserBO> loginUserCache = CacheUtils.buildAsyncReloadingCache(Duration.ofSeconds(10),
            new CacheLoader<KeyValue<Long, String>, OnlineUserBO>() {

                @Override
                public OnlineUserBO load(KeyValue<Long, String> token) {
                    String body = checkAccessToken(token.getKey(), token.getValue()).block();
                    return buildUser(body);
                }

            });

    /**
     * 空的 LoginUser 的结果
     * <p>
     * TODO 芋艿：用于解决 getLoginUser 返回 Mono.empty() 的时候，会导致后续的 flatMap 无法进行处理的问题。先暂时这么解决，寻找更优解 ing
     */
    private static final OnlineUserBO LOGIN_USER_EMPTY = new OnlineUserBO();

    private final WebClient webClient;

    public TokenAuthenticationFilter(ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        // Q：为什么不使用 OAuth2TokenApi 进行调用？
        // A1：Spring Cloud OpenFeign 官方未内置 Reactive 的支持 https://docs.spring.io/spring-cloud-openfeign/docs/current/reference/html/#reactive-support
        // A2：校验 Token 的 API 需要使用到 header[tenant-id] 传递租户编号，暂时不想编写 RequestInterceptor 实现
        // 因此，这里采用 WebClient，通过 lbFunction 实现负载均衡
        this.webClient = WebClient.builder().filter(lbFunction).build();
    }


    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //删除请求头的用户
        SecurityFrameworkUtils.removeLoginUser(exchange);
        // 情况一，如果没有 Token 令牌，则直接继续 filter
        String token = SecurityFrameworkUtils.obtainAuthorization(exchange);
        if (StrUtil.isEmpty(token)) {
            return chain.filter(exchange);
        }
        return getLoginUser(exchange, token).defaultIfEmpty(LOGIN_USER_EMPTY).flatMap(user -> {
            // 1. 无用户，直接 filter 继续请求
            if (user == LOGIN_USER_EMPTY) {
                return chain.filter(exchange);
            }

            // 2.1 有用户，则设置登录用户
            SecurityFrameworkUtils.setLoginUser(exchange, user);
            // 2.2 将 user 并设置到 login-user 的请求头，使用 json 存储值
            ServerWebExchange newExchange = exchange.mutate()
                    .request(builder -> SecurityFrameworkUtils.setLoginUserHeader(builder, user)).build();
            return chain.filter(newExchange);
        });
    }

    private Mono<OnlineUserBO> getLoginUser(ServerWebExchange exchange, String token) {
        // 从缓存中，获取 LoginUser
        Long tenantId = WebFrameworkUtils.getTenantId(exchange);
        KeyValue<Long, String> cacheKey = new KeyValue<Long, String>().setKey(tenantId).setValue(token);
        OnlineUserBO localUser = loginUserCache.getIfPresent(cacheKey);
        if (localUser != null) {
            return Mono.just(localUser);
        }

        // 缓存不存在，则请求远程服务
        return checkAccessToken(tenantId, token).flatMap((Function<String, Mono<OnlineUserBO>>) body -> {
            OnlineUserBO remoteUser = buildUser(body);
            if (remoteUser != null) {
                // 非空，则进行缓存
                loginUserCache.put(cacheKey, remoteUser);
                return Mono.just(remoteUser);
            }
            return Mono.empty();
        });
    }

    private Mono<String> checkAccessToken(Long tenantId, String token) {
        return webClient.get()
                .uri(OAuth2TokenApi.URL_CHECK, uriBuilder -> uriBuilder.queryParam("token", token).build())
//                .headers(httpHeaders -> WebFrameworkUtils.setTenantIdHeader(tenantId, httpHeaders)) // 设置租户的 Header
                .retrieve().bodyToMono(String.class);
    }

    private OnlineUserBO buildUser(String body) {
        CommonResult<OnlineUserBO> result = JsonUtils.parseObject(body, CHECK_RESULT_TYPE_REFERENCE);
        if (result == null || result.isError()) {
            return null;
        }
        // 创建登录用户
        return result.getData();
    }

}
