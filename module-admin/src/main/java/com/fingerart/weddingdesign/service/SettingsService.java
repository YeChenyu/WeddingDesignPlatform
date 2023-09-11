package com.fingerart.weddingdesign.service;


import com.fingerart.weddingdesign.entity.TSettings;

import java.util.Map;


public interface SettingsService {

    public final static int GROUP_WECHAT = 2;
    public final static int GROUP_SPLASH = 2;

    public final static String NAME_WECHAT_ACCESSTOKEN = "wechat_accesstoken";
    public final static String NAME_WECHAT_EXPIRESIN = "wechat_expires_in";
    public final static String NAME_SPLASH_ENABLE = "splash_enable";
    public final static String NAME_SPLASH_QRCODE = "splash_qr_code";
    public final static String NAME_SPLASH_ENV_VERSION = "splash_env_version";
    public final static String NAME_SPLASH_PAGE = "splash_page";
    public final static String NAME_SPLASH_PARAM = "splash_param";

    Map<String, TSettings> fetchSettingMapByGroup(int group);

    TSettings fetchSettingByName(String name);

    boolean updateSetting(TSettings setting);

    boolean checkExistByName(String name);
    
    String fetchWechatTokenSetting();

}
