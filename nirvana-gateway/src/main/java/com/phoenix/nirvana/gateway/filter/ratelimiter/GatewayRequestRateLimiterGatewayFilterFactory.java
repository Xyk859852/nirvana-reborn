package com.phoenix.nirvana.gateway.filter.ratelimiter;

import com.phoenix.nirvana.common.util.json.JsonUtils;
import com.phoenix.nirvana.common.vo.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.RequestRateLimiterGatewayFilterFactory;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RateLimiter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;
import java.util.Map;

@Slf4j
@Component
public class GatewayRequestRateLimiterGatewayFilterFactory extends RequestRateLimiterGatewayFilterFactory {

    private final Integer code = 1429;

    private final String message = "请求频繁请稍后请求。。。";

    private final RateLimiter defaultRateLimiter;

    private final KeyResolver defaultKeyResolver;


    public GatewayRequestRateLimiterGatewayFilterFactory(RateLimiter defaultRateLimiter, @Qualifier("userKeyResolver") KeyResolver defaultKeyResolver) {
        super(defaultRateLimiter, defaultKeyResolver);
        this.defaultRateLimiter = defaultRateLimiter;
        this.defaultKeyResolver = defaultKeyResolver;
    }

    @Override
    public GatewayFilter apply(Config config) {
        KeyResolver resolver = getOrDefault(config.getKeyResolver(), defaultKeyResolver);
        RateLimiter<Object> limiter = getOrDefault(config.getRateLimiter(), defaultRateLimiter);
        return (exchange, chain) -> resolver.resolve(exchange).flatMap(key -> {
            String routeId = config.getRouteId();
            if (routeId == null) {
                Route route = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
                routeId = route.getId();
            }
            String finalRouteId = routeId;
            return limiter.isAllowed(routeId, key).flatMap(response -> {
                for (Map.Entry<String, String> header : response.getHeaders().entrySet()) {
                    exchange.getResponse().getHeaders().add(header.getKey(), header.getValue());
                }
                if (response.isAllowed()) {
                    return chain.filter(exchange);
                }
                ServerHttpResponse httpResponse = exchange.getResponse();
                log.warn("已限流: 目标服务：{}，请求路径：{}", finalRouteId, exchange.getRequest().getPath());
                //修改code为500
                httpResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
                if (!httpResponse.getHeaders().containsKey("Content-Type")) {
                    httpResponse.getHeaders().add("Content-Type", "application/json");
                }
                //此处无法触发全局异常处理，手动返回
                DataBuffer buffer = httpResponse.bufferFactory().wrap(JsonUtils.toJsonString(CommonResult.error(code, message)).getBytes(StandardCharsets.UTF_8));
                return httpResponse.writeWith(Mono.just(buffer));
            });
        });
    }

    private <T> T getOrDefault(T configValue, T defaultValue) {
        return (configValue != null) ? configValue : defaultValue;
    }
}
