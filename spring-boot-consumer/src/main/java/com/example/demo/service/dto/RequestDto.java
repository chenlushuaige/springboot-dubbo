package com.example.demo.service.dto;

import java.util.Map;

/**
 * <pre>
 * Desc: 参数JSON的DTO
 * Author:chenlu
 * Email:chenlu@ddjf.com.cn
 * Date:14:40 2018/2/8
 * </pre>
 */
public class RequestDto {
    private String interfaceName;
    private String methodName;
    private Map methodParams;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Map getMethodParams() {
        return methodParams;
    }

    public void setMethodParam(Map methodParams) {
        this.methodParams = methodParams;
    }
}

