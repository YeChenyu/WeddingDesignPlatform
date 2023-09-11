package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TAdminRoleRelation;
import com.fingerart.weddingdesign.entity.TAdminRoleRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAdminRoleRelationMapper {
    long countByExample(TAdminRoleRelationExample example);

    int deleteByExample(TAdminRoleRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAdminRoleRelation row);

    int insertSelective(TAdminRoleRelation row);

    List<TAdminRoleRelation> selectByExample(TAdminRoleRelationExample example);

    TAdminRoleRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TAdminRoleRelation row, @Param("example") TAdminRoleRelationExample example);

    int updateByExample(@Param("row") TAdminRoleRelation row, @Param("example") TAdminRoleRelationExample example);

    int updateByPrimaryKeySelective(TAdminRoleRelation row);

    int updateByPrimaryKey(TAdminRoleRelation row);
}