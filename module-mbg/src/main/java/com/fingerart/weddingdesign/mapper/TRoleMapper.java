package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TRole;
import com.fingerart.weddingdesign.entity.TRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TRoleMapper {
    long countByExample(TRoleExample example);

    int deleteByExample(TRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TRole row);

    int insertSelective(TRole row);

    List<TRole> selectByExample(TRoleExample example);

    TRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TRole row, @Param("example") TRoleExample example);

    int updateByExample(@Param("row") TRole row, @Param("example") TRoleExample example);

    int updateByPrimaryKeySelective(TRole row);

    int updateByPrimaryKey(TRole row);
}