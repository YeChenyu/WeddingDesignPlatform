package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TCaseCategory;
import com.fingerart.weddingdesign.entity.TCaseCategoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCaseCategoryMapper {
    long countByExample(TCaseCategoryExample example);

    int deleteByExample(TCaseCategoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCaseCategory row);

    int insertSelective(TCaseCategory row);

    List<TCaseCategory> selectByExample(TCaseCategoryExample example);

    TCaseCategory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TCaseCategory row, @Param("example") TCaseCategoryExample example);

    int updateByExample(@Param("row") TCaseCategory row, @Param("example") TCaseCategoryExample example);

    int updateByPrimaryKeySelective(TCaseCategory row);

    int updateByPrimaryKey(TCaseCategory row);
}