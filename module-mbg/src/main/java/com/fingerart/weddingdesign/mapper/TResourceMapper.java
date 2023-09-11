package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TResource;
import com.fingerart.weddingdesign.entity.TResourceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TResourceMapper {
    long countByExample(TResourceExample example);

    int deleteByExample(TResourceExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TResource row);

    int insertSelective(TResource row);

    List<TResource> selectByExample(TResourceExample example);

    TResource selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TResource row, @Param("example") TResourceExample example);

    int updateByExample(@Param("row") TResource row, @Param("example") TResourceExample example);

    int updateByPrimaryKeySelective(TResource row);

    int updateByPrimaryKey(TResource row);
}