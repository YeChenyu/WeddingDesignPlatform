package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TAdminRole;
import com.fingerart.weddingdesign.entity.TAdminRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAdminRoleMapper {
    long countByExample(TAdminRoleExample example);

    int deleteByExample(TAdminRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAdminRole row);

    int insertSelective(TAdminRole row);

    List<TAdminRole> selectByExample(TAdminRoleExample example);

    TAdminRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TAdminRole row, @Param("example") TAdminRoleExample example);

    int updateByExample(@Param("row") TAdminRole row, @Param("example") TAdminRoleExample example);

    int updateByPrimaryKeySelective(TAdminRole row);

    int updateByPrimaryKey(TAdminRole row);
}