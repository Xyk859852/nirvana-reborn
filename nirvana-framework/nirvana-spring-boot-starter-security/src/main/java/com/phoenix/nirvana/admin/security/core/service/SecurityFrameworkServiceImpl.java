package com.phoenix.nirvana.admin.security.core.service;

import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.phoenix.nirvana.admin.security.core.bo.LoginUser;
import com.phoenix.nirvana.admin.security.core.utils.SecurityFrameworkUtils;
import com.phoenix.nirvana.common.core.KeyValue;
import com.phoenix.nirvana.common.util.cache.CacheUtils;
import com.phoenix.nirvana.service.system.rpc.auth.permission.SysPermissionRpc;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static com.phoenix.nirvana.admin.security.core.utils.SecurityFrameworkUtils.getLoginUserId;


/**
 * 默认的 {@link SecurityFrameworkService} 实现类
 *
 * @author xuyongkang
 */
@AllArgsConstructor
public class SecurityFrameworkServiceImpl implements SecurityFrameworkService {

    private final SysPermissionRpc permissionRpc;

    /**
     * 针对 {@link #hasAnyRoles(String...)} 的缓存
     */
    private final LoadingCache<KeyValue<Long, List<String>>, Boolean> hasAnyRolesCache = CacheUtils.buildAsyncReloadingCache(
            Duration.ofMinutes(1L), // 过期时间 1 分钟
            new CacheLoader<KeyValue<Long, List<String>>, Boolean>() {

                @Override
                public Boolean load(KeyValue<Long, List<String>> key) {
                    return permissionRpc.hasAnyRoles(key.getKey(), key.getValue().toArray(new String[0])).getCheckedData();
                }

            });

    /**
     * 针对 {@link #hasAnyPermissions(String...)} 的缓存
     */
    private final LoadingCache<KeyValue<Long, List<String>>, Boolean> hasAnyPermissionsCache = CacheUtils.buildAsyncReloadingCache(
            Duration.ofMinutes(1L), // 过期时间 1 分钟
            new CacheLoader<KeyValue<Long, List<String>>, Boolean>() {

                @Override
                public Boolean load(KeyValue<Long, List<String>> key) {
                    return permissionRpc.hasAnyPermissions(key.getKey(), key.getValue().toArray(new String[0])).getCheckedData();
                }

            });

    @Override
    public boolean hasPermission(String permission) {
        return hasAnyPermissions(permission);
    }

    @Override
    @SneakyThrows
    public boolean hasAnyPermissions(String... permissions) {
        return hasAnyPermissionsCache.get(new KeyValue<>(getLoginUserId(), Arrays.asList(permissions)));
    }

    @Override
    public boolean hasRole(String role) {
        return hasAnyRoles(role);
    }

    @Override
    @SneakyThrows
    public boolean hasAnyRoles(String... roles) {
        return hasAnyRolesCache.get(new KeyValue<>(getLoginUserId(), Arrays.asList(roles)));
    }

    @Override
    public boolean hasScope(String scope) {
        return hasAnyScopes(scope);
    }

    @Override
    public boolean hasAnyScopes(String... scope) {
        LoginUser user = SecurityFrameworkUtils.getLoginUser();
        if (user == null) {
            return false;
        }
        return true;
    }

}
