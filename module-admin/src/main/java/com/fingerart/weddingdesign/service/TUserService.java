package com.fingerart.weddingdesign.service;

import com.fingerart.weddingdesign.dto.UmsAdminParam;
import com.fingerart.weddingdesign.dto.UpdateAdminPasswordParam;
import com.fingerart.weddingdesign.entity.TAdmin;
import com.fingerart.weddingdesign.entity.TResource;
import com.fingerart.weddingdesign.entity.TRole;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 小程序用户管理Service
 * Created by yecy on 2023/8/07.
 */
public interface TUserService {

    /**
     * 根据用户名获取后台管理员
     */
    TAdmin getAdminByUsername(String username);

    /**
     * 登录功能
     * @param username 用户名
     * @param password 密码
     * @return 生成的JWT的token
     */
    String login(String username,String password);

    /**
     * 刷新token的功能
     * @param oldToken 旧的token
     */
    String refreshToken(String oldToken);

    /**
     * 根据用户id获取用户
     */
    TAdmin getItem(Long id);

    /**
     * 根据用户名或昵称分页查询用户
     */
    List<TAdmin> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 修改指定用户信息
     */
    int update(Long id, TAdmin admin);

    /**
     * 删除指定用户
     */
    int delete(Long id);
}
