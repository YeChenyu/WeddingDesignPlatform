package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TDevice;
import com.fingerart.weddingdesign.entity.TDeviceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TDeviceMapper {
    long countByExample(TDeviceExample example);

    int deleteByExample(TDeviceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(TDevice row);

    int insertSelective(TDevice row);

    List<TDevice> selectByExample(TDeviceExample example);

    TDevice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") TDevice row, @Param("example") TDeviceExample example);

    int updateByExample(@Param("row") TDevice row, @Param("example") TDeviceExample example);

    int updateByPrimaryKeySelective(TDevice row);

    int updateByPrimaryKey(TDevice row);
}