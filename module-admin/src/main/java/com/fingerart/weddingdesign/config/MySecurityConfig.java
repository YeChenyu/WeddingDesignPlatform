package com.fingerart.weddingdesign.config;

import com.fingerart.weddingdesign.entity.TResource;
import com.fingerart.weddingdesign.component.DynamicSecurityService;
import com.fingerart.weddingdesign.service.TAdminService;
import com.fingerart.weddingdesign.service.TResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * module-security模块相关配置
 * Created by macro on 2019/11/9.
 */
@Configuration
public class MySecurityConfig {

    @Autowired
    private TAdminService adminService;
    @Autowired
    private TResourceService resourceService;

    @Bean
    public UserDetailsService userDetailsService() {
        //获取登录用户信息
        return username -> adminService.loadUserByUsername(username);
    }

    @Bean
    public DynamicSecurityService dynamicSecurityService() {
        return new DynamicSecurityService() {
            @Override
            public Map<String, ConfigAttribute> loadDataSource() {
                Map<String, ConfigAttribute> map = new ConcurrentHashMap<>();
                List<TResource> resourceList = resourceService.listAll();
                for (TResource resource : resourceList) {
                    map.put(resource.getUrl(), new org.springframework.security.access.SecurityConfig(resource.getId() + ":" + resource.getName()));
                }
                return map;
            }
        };
    }
}
