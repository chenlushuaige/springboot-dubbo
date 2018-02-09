package com.example.demo.service.controller;

import com.example.demo.service.dto.RequestDto;
import com.example.demo.service.generator.DubboServiceFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 * Desc: 校验的正确性要求参数个数符合服务的方法参数的个数
 * Author:chenlu
 * Email:chenlu@ddjf.com.cn
 * Date:17:53 2018/2/7
 * </pre>
 */

@RestController
public class SampleController {
    @Autowired
    DubboServiceFactory dubboServiceFactory;

    @RequestMapping(path = "/home", method = RequestMethod.POST)
    public Object home(@RequestBody RequestDto requestDto) throws ClassNotFoundException {
        Map maps = requestDto.getMethodParams();
        List<Map<String, Object>> paramInfos = new ArrayList<>();
        if (maps == null) {
            return dubboServiceFactory.genericInvoke(requestDto.getInterfaceName(), requestDto.getMethodName(), paramInfos);
        }

        /**
         * 解析方法参数 methodParams
         */
        int size = maps.size();
        int i = 0;
        if (maps != null) {
            for (i = 1; maps.get("param_" + i) != null; i++) {
                Map map = new HashMap();
                map.put("ParamType", maps.get("ParamType_" + i));
                map.put("param", maps.get("param_" + i));
                paramInfos.add(map);
            }
        }

        /**
         * 参数顺序校验，应对param_1,param_2,param_6这种情况，
         * 这些对参数的校验到时候封装一个Util类;
         */

        if (i - size != 1) {
            return "参数序列错误";
        }

        return dubboServiceFactory.genericInvoke(requestDto.getInterfaceName(), requestDto.getMethodName(), paramInfos);
        //return null;
    }

}
