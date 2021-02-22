package com.phoenix.nirvana.admin.security.bo;

import com.phoenix.nirvana.admin.web.api.bo.OnlineUserBO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Accessors(chain = true)
public class SecurityUserBO implements UserDetails {

    private OnlineUserBO onlineUserBO;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return onlineUserBO.getPassWord();
    }

    @Override
    public String getUsername() {
        return onlineUserBO.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !onlineUserBO.getIsEnable();
    }
}
