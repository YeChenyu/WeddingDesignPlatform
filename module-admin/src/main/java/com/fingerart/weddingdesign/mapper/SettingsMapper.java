package com.fingerart.weddingdesign.mapper;

import com.fingerart.weddingdesign.entity.TSettings;
import org.apache.ibatis.annotations.MapKey;

import java.util.Map;

public interface SettingsMapper {

    @MapKey("itemName")
    Map<String, TSettings> selectSettingMapByGroup(int group);
}
