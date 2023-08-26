package com.example.consumer.service.impl;

import com.example.userinterface.service.DemoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;


//provider实现接口中的服务
@DubboService
public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello " + name;
    }
}
