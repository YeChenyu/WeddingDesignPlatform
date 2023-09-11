package com.fingerart.weddingdesign.config;

import com.aliyun.oss.OSSClient;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 微信相关配置
 * Created by macro on 2018/5/17.
 */
@Getter
@Configuration
public class WechatConfig {

    @Value("${wechat.public.appId}")
    private String wechatPublicAppId;
    @Value("${wechat.public.appSecret}")
    private String wechatPublicAppSecret;

    @Value("${wechat.mini.appId}")
    private String wechatMiniAppId;
    @Value("${wechat.mini.appSecret}")
    private String wechatMiniAppSecret;

}
