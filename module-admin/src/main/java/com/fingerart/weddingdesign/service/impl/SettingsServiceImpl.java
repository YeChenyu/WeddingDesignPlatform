package com.fingerart.weddingdesign.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fingerart.weddingdesign.config.WechatConfig;
import com.fingerart.weddingdesign.entity.TSettings;
import com.fingerart.weddingdesign.entity.TSettingsExample;
import com.fingerart.weddingdesign.mapper.SettingsMapper;
import com.fingerart.weddingdesign.mapper.TSettingsMapper;
import com.fingerart.weddingdesign.service.SettingsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class SettingsServiceImpl implements SettingsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TAdminServiceImpl.class);

    @Autowired
    private SettingsMapper settingsMapper;
    @Autowired
    private TSettingsMapper tSettingsMapper;

    @Autowired
    private WechatConfig wechatConfig;

    @Override
    public boolean updateSetting(TSettings setting) {
        return tSettingsMapper.updateByPrimaryKey(setting) >= 0;
    }

    @Override
    public Map<String, TSettings> fetchSettingMapByGroup(int group) {
        return settingsMapper.selectSettingMapByGroup(group);
    }

    @Override
    public TSettings fetchSettingByName(String name) {
        TSettingsExample selectExample = new TSettingsExample();
        selectExample.setDistinct(true);
        TSettingsExample.Criteria criteria = selectExample.createCriteria();
        criteria.andItemNameEqualTo(name);
        List<TSettings> settingsList = tSettingsMapper.selectByExample(selectExample);
        return settingsList.size() > 0 ? settingsList.get(0) : null;
    }

    @Override
    public boolean checkExistByName(String name) {
        TSettingsExample selectExample = new TSettingsExample();
        TSettingsExample.Criteria criteria = selectExample.createCriteria();
        criteria.andItemNameEqualTo(name);
        return tSettingsMapper.countByExample(selectExample) > 0;
    }

    private boolean checkAccessToken(TSettings tokenSetting, TSettings expiresSetting) {
        LOGGER.info("Start to executed checkAccessToken");
        try {
            if (ObjectUtils.isEmpty(tokenSetting) || ObjectUtils.isEmpty(expiresSetting)) {
                return false;
            }
            if (ObjectUtils.isEmpty(tokenSetting.getUpdateTime())) {
                return false;
            }
            int expiresIn = Integer.parseInt(expiresSetting.getItemValue());
            Timestamp now = new Timestamp(System.currentTimeMillis() - expiresIn * 1000);
            Date updateAt = tokenSetting.getUpdateTime();
            LOGGER.info("checkAccessToken now=" + now.getTime() + ", updateAt=" + updateAt.getTime());
            if (now.after(updateAt)) {
                return false;
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            LOGGER.info("End to executed checkAccessToken");
        }
    }

    @Override
    public String fetchWechatTokenSetting() {
        try {
            TSettings tokenSetting = fetchSettingByName(SettingsService.NAME_WECHAT_ACCESSTOKEN);
            TSettings expiresSetting = fetchSettingByName(SettingsService.NAME_WECHAT_EXPIRESIN);
            // 校验 AccessToken 是否过期
            if(checkAccessToken(tokenSetting, expiresSetting)) {
                return tokenSetting.getItemValue();
            }
            // 获取 Access Token
            String appId = wechatConfig.getWechatPublicAppId();
            String appSecret = wechatConfig.getWechatPublicAppSecret();
            LOGGER.info("WX_TOKEN appId="+ appId+ ", appSecret="+ appSecret);
            String WX_TOKEN_URL =  "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=" + appId + "&secret=" + appSecret;

            String serverResult = null;
            HttpURLConnection conn = null;
            try {
                URL serverUrl = new URL(WX_TOKEN_URL);
                conn = (HttpURLConnection) serverUrl.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Content-type", "application/json");
                //必须设置false，否则会自动redirect到重定向后的地址
                conn.setInstanceFollowRedirects(false);
                conn.connect();
                serverResult = getReturn(conn);
                LOGGER.debug("WX_TOKEN="+ serverResult);
            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.error(e.getMessage());
            }finally {
                if(conn != null) {
                    conn.disconnect();
                    conn = null;
                }
            }
            if(StringUtils.isEmpty(serverResult)){
                LOGGER.error("WX_TOKEN fetch failed");
                return null;
            }
            LOGGER.info("WX_TOKEN return success: "+ serverResult);
            JSONObject wxToken = JSON.parseObject(serverResult);
            String accessToken = wxToken.getString("access_token");
            int expiresIn = wxToken.getIntValue("expires_in");
            LOGGER.info("WX_TOKEN accessToken="+ accessToken+ ", expiresIn="+ expiresIn);
            if(ObjectUtils.isEmpty(accessToken) || ObjectUtils.isEmpty(expiresIn)) {
                LOGGER.error("WX_TOKEN fetch failed");
                return null;
            }
            // 更新 Access Token/ExpiresIn
            if(tokenSetting == null) {
                tokenSetting = new TSettings();
            }
            Timestamp now = new Timestamp(System.currentTimeMillis());
            tokenSetting.setItemValue(accessToken);
            tokenSetting.setUpdateTime(now);
            expiresSetting.setItemValue(String.valueOf(expiresIn));
            expiresSetting.setUpdateTime(now);
            updateSetting(tokenSetting);
            updateSetting(expiresSetting);
            return accessToken;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            LOGGER.info("End to executed fetch wechat access token");
        }
    }


    public String getReturn(HttpURLConnection connection) {
        StringBuffer buffer = new StringBuffer();
        //将返回的输入流转换成字符串
        try (InputStream inputStream = connection.getInputStream();
             InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);) {
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            String result = buffer.toString();
            return result;
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }

}
