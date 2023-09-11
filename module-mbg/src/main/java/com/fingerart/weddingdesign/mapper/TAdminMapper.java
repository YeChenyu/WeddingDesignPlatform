package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TAdmin;
import com.fingerart.weddingdesign.entity.TAdminExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TAdminMapper {
    long countByExample(TAdminExample example);

    int deleteByExample(TAdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TAdmin row);

    int insertSelective(TAdmin row);

    List<TAdmin> selectByExample(TAdminExample example);

    TAdmin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TAdmin row, @Param("example") TAdminExample example);

    int updateByExample(@Param("row") TAdmin row, @Param("example") TAdminExample example);

    int updateByPrimaryKeySelective(TAdmin row);

    int updateByPrimaryKey(TAdmin row);
}