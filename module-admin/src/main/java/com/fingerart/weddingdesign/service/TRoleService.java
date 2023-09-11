package com.fingerart.weddingdesign.service;

import com.fingerart.weddingdesign.entity.TResource;
import com.fingerart.weddingdesign.entity.TRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 后台角色管理Service
 * Created by macro on 2018/9/30.
 */
public interface TRoleService {
    /**
     * 添加角色
     */
    int create(TRole role);

    /**
     * 修改角色信息
     */
    int update(Long id, TRole role);

    /**
     * 批量删除角色
     */
    int delete(List<Long> ids);

    /**
     * 获取所有角色列表
     */
    List<TRole> list();

    /**
     * 分页获取角色列表
     */
    List<TRole> list(String keyword, Integer pageSize, Integer pageNum);

    /**
     * 获取角色相关资源
     */
    List<TResource> listResource(Long roleId);

}
