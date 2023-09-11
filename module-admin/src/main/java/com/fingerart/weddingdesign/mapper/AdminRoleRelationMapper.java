package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TAdminRoleRelation;
import com.fingerart.weddingdesign.entity.TResource;
import com.fingerart.weddingdesign.entity.TRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 后台用户与角色关系管理自定义Dao
 * Created by macro on 2018/10/8.
 */
public interface AdminRoleRelationMapper {
    /**
     * 批量插入用户角色关系
     */
    int insertList(@Param("list") List<TAdminRoleRelation> adminRoleRelationList);

    /**
     * 获取用于所有角色
     */
    List<TRole> getRoleList(@Param("adminId") Long adminId);

    /**
     * 获取用户所有可访问资源
     */
    List<TResource> getResourceList(@Param("adminId") Long adminId);

    /**
     * 获取资源相关用户ID列表
     */
    List<Long> getAdminIdList(@Param("resourceId") Long resourceId);
}
