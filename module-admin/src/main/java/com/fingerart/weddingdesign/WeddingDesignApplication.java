package com.fingerart.weddingdesign;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动入口
 * Created by macro on 2018/4/26.
 */
@MapperScan("com.fingerart.weddingdesign.mapper")
@SpringBootApplication
public class WeddingDesignApplication {
    public static void main(String[] args) {
        SpringApplication.run(WeddingDesignApplication.class, args);
    }
}
