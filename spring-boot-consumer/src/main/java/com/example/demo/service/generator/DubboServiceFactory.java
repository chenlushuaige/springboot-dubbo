package com.example.demo.service.generator;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ReferenceConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.utils.ReferenceConfigCache;
import com.alibaba.dubbo.rpc.service.GenericService;
import com.example.demo.service.config.DubboConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;


/**
 * <pre>
 * Desc:
 * Author:chenlu
 * Email:chenlu@ddjf.com.cn
 * Date:15:22 2018/2/8
 * </pre>
 */

@Service
public class DubboServiceFactory {

    private ApplicationConfig application = new ApplicationConfig();

    private RegistryConfig registry = new RegistryConfig();

    @Autowired
    DubboConfig dubboConfig;

    public Object genericInvoke(String interfaceClass, String methodName, List<Map<String, Object>> parameters) throws ClassNotFoundException {

        ReferenceConfig<GenericService> reference = new ReferenceConfig<>();
        application.setName(dubboConfig.getName());
        registry.setAddress(dubboConfig.getAddress());
        reference.setApplication(application);
        reference.setRegistry(registry);
        reference.setInterface(interfaceClass); // 接口名
        reference.setGeneric(true); // 声明为泛化接口

        /*ReferenceConfig实例很重，封装了与注册中心的连接以及与提供者的连接，
        需要缓存，否则重复生成ReferenceConfig可能造成性能问题并且会有内存和连接泄漏。
        API方式编程时，容易忽略此问题。
        这里使用dubbo内置的简单缓存工具类进行缓存*/


        //GenericService genericService = reference.get();//当时用postman测试，每访问一次，消耗的内存就增加好几兆，吓到宝宝了,所以不要用这种方式


        ReferenceConfigCache cache = ReferenceConfigCache.getCache();
        GenericService genericService = cache.get(reference);
        // 用com.alibaba.dubbo.rpc.service.GenericService可以替代所有接口引用

        int len = parameters.size();

        Object[] invokeParams = new Object[len];
        for (int i = 0; i < len; i++) {
            invokeParams[i] = parameters.get(i).get("param");
        }

        /**
         * 获取方法所有参数类型
         */
        Class<?> testClass = Class.forName(interfaceClass);
        Method[] methods = testClass.getDeclaredMethods();
        Class<?>[] invokeParamClass = null;
        for (Method method : methods) {
            if (methodName.equals(method.getName())) {
                invokeParamClass = method.getParameterTypes();
                break;
            }
        }

        if (invokeParamClass == null) {
            return "方法不存在";
        }
        if (invokeParamClass.length != len) {
            return "方法参数错误";
        }

        String[] invokeParamTyeps = new String[invokeParamClass.length];
        for (int i = 0; i < invokeParamClass.length; i++) {
            invokeParamTyeps[i] = invokeParamClass[i].getName();
        }

        return genericService.$invoke(methodName, invokeParamTyeps, invokeParams);

    }

}




