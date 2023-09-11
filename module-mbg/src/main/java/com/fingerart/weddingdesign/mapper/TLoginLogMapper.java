package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TLoginLog;
import com.fingerart.weddingdesign.entity.TLoginLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TLoginLogMapper {
    long countByExample(TLoginLogExample example);

    int deleteByExample(TLoginLogExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TLoginLog row);

    int insertSelective(TLoginLog row);

    List<TLoginLog> selectByExample(TLoginLogExample example);

    TLoginLog selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TLoginLog row, @Param("example") TLoginLogExample example);

    int updateByExample(@Param("row") TLoginLog row, @Param("example") TLoginLogExample example);

    int updateByPrimaryKeySelective(TLoginLog row);

    int updateByPrimaryKey(TLoginLog row);
}