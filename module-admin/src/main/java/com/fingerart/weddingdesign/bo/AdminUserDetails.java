package com.fingerart.weddingdesign.bo;

import com.fingerart.weddingdesign.entity.TAdmin;
import com.fingerart.weddingdesign.entity.TResource;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * SpringSecurity需要的用户信息封装类
 * Created by macro on 2018/4/26.
 */
public class AdminUserDetails implements UserDetails {
    //后台用户
    private final TAdmin tAdmin;
    //拥有资源列表
    private final List<TResource> resourceList;

    public AdminUserDetails(TAdmin tAdmin,List<TResource> resourceList) {
        this.tAdmin = tAdmin;
        this.resourceList = resourceList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //返回当前用户的角色
        return resourceList.stream()
                .map(role ->new SimpleGrantedAuthority(role.getId()+":"+role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return tAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return tAdmin.getUsername();
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
        return tAdmin.getStatus().equals(1);
    }
}
