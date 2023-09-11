package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TUser;
import com.fingerart.weddingdesign.entity.TUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TUserMapper {
    long countByExample(TUserExample example);

    int deleteByExample(TUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TUser row);

    int insertSelective(TUser row);

    List<TUser> selectByExample(TUserExample example);

    TUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TUser row, @Param("example") TUserExample example);

    int updateByExample(@Param("row") TUser row, @Param("example") TUserExample example);

    int updateByPrimaryKeySelective(TUser row);

    int updateByPrimaryKey(TUser row);
}