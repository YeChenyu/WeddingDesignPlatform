package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TCase;
import com.fingerart.weddingdesign.entity.TCaseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCaseMapper {
    long countByExample(TCaseExample example);

    int deleteByExample(TCaseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCase row);

    int insertSelective(TCase row);

    List<TCase> selectByExample(TCaseExample example);

    TCase selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TCase row, @Param("example") TCaseExample example);

    int updateByExample(@Param("row") TCase row, @Param("example") TCaseExample example);

    int updateByPrimaryKeySelective(TCase row);

    int updateByPrimaryKey(TCase row);
}