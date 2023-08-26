package com.example.consumer.service.impl;

import com.example.userinterface.service.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService(version = "2.0")
public class DemoServiceImpl2 implements DemoService {

    @Override
    public String sayHello(String name) {
        return "Hello " + name+" version:2.0";
    }

}
