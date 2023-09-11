package com.fingerart.weddingdesign.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis相关配置
 * Created by macro on 2019/4/8.
 */
@Configuration
@EnableTransactionManagement
@MapperScan({"com.fingerart.weddingdesign.mapper","com.fingerart.weddingdesign.dao"})
public class MyBatisConfig {
}
