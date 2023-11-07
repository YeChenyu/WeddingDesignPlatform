package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TCaseCateRelation;
import com.fingerart.weddingdesign.entity.TCaseCateRelationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TCaseCateRelationMapper {
    long countByExample(TCaseCateRelationExample example);

    int deleteByExample(TCaseCateRelationExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TCaseCateRelation row);

    int insertSelective(TCaseCateRelation row);

    List<TCaseCateRelation> selectByExample(TCaseCateRelationExample example);

    TCaseCateRelation selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TCaseCateRelation row, @Param("example") TCaseCateRelationExample example);

    int updateByExample(@Param("row") TCaseCateRelation row, @Param("example") TCaseCateRelationExample example);

    int updateByPrimaryKeySelective(TCaseCateRelation row);

    int updateByPrimaryKey(TCaseCateRelation row);
}