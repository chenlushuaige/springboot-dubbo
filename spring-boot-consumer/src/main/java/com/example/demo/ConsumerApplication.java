package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 访问地址：http://localhost:11000/core-consumer/home
 * 访问的JSON格式
 *      {
 *          "interfaceName": "com.example.demo.service.DemoService",
 *          "methodName": "getCount",
 *          "methodParams":
 *                      {
 *                          "param_1":"231",
 *                          "param_2":"FFFFFFF",
 *                          "param_3":"dsdadad",
 *                                  ...
 *                                  ...
 *                                  ...
 *                                  ...
 *                                  ...
 *                                  ...
 *                      }
 *      }
 */

@SpringBootApplication
@ComponentScan("com.example.demo.*")
public class ConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
    }
}
