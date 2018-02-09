package com.example.demo.service;

import java.util.Map;

/**
 * <pre>
 * desc:
 * Author:chenlu
 * Email:chenlu@ddjf.com.cn
 * Date:11:37 2018/2/8
 * </pre>
 */
public interface DemoService {
    Map getDemoName(String demoName);
    int getCount(Integer number,String param);
}
