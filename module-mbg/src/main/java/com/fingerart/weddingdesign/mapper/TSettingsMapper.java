package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TSettings;
import com.fingerart.weddingdesign.entity.TSettingsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TSettingsMapper {
    long countByExample(TSettingsExample example);

    int deleteByExample(TSettingsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TSettings row);

    int insertSelective(TSettings row);

    List<TSettings> selectByExample(TSettingsExample example);

    TSettings selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TSettings row, @Param("example") TSettingsExample example);

    int updateByExample(@Param("row") TSettings row, @Param("example") TSettingsExample example);

    int updateByPrimaryKeySelective(TSettings row);

    int updateByPrimaryKey(TSettings row);
}