package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TRoleResourceRelation;
import com.fingerart.weddingdesign.entity.TRoleResourceRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoleResourceRelationMapper {
    long countByExample(TRoleResourceRelationExample example);

    int deleteByExample(TRoleResourceRelationExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TRoleResourceRelation row);

    int insertSelective(TRoleResourceRelation row);

    List<TRoleResourceRelation> selectByExample(TRoleResourceRelationExample example);

    TRoleResourceRelation selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TRoleResourceRelation row, @Param("example") TRoleResourceRelationExample example);

    int updateByExample(@Param("row") TRoleResourceRelation row, @Param("example") TRoleResourceRelationExample example);

    int updateByPrimaryKeySelective(TRoleResourceRelation row);

    int updateByPrimaryKey(TRoleResourceRelation row);
}