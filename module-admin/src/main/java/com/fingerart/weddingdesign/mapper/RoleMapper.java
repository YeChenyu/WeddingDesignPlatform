package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TResource;
import com.fingerart.weddingdesign.model.UmsMenu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台角色管理自定义Dao
 * Created by macro on 2020/2/2.
 */
public interface RoleMapper {
    /**
     * 根据后台用户ID获取菜单
     */
    List<UmsMenu> getMenuList(@Param("adminId") Long adminId);
    /**
     * 根据角色ID获取菜单
     */
    List<UmsMenu> getMenuListByRoleId(@Param("roleId") Long roleId);
    /**
     * 根据角色ID获取资源
     */
    List<TResource> getResourceListByRoleId(@Param("roleId") Long roleId);
}
