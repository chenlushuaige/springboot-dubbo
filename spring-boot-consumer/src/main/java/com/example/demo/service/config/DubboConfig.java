package com.example.demo.service.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * <pre>
 * Desc:
 * Author:chenlu
 * Email:chenlu@ddjf.com.cn
 * Date:10:36 2018/2/9
 * </pre>
 */
@Configuration
public class DubboConfig {

    @Value("${spring.dubbo.application.name}")
    private String name;
    @Value("${spring.dubbo.registry.address}")
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }



}
