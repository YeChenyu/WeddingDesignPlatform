package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TResourceCategory;
import com.fingerart.weddingdesign.entity.TResourceCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TResourceCategoryMapper {
    long countByExample(TResourceCategoryExample example);

    int deleteByExample(TResourceCategoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TResourceCategory row);

    int insertSelective(TResourceCategory row);

    List<TResourceCategory> selectByExample(TResourceCategoryExample example);

    TResourceCategory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TResourceCategory row, @Param("example") TResourceCategoryExample example);

    int updateByExample(@Param("row") TResourceCategory row, @Param("example") TResourceCategoryExample example);

    int updateByPrimaryKeySelective(TResourceCategory row);

    int updateByPrimaryKey(TResourceCategory row);
}