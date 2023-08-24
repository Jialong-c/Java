package com.example.rabbitmq.controller;

import com.example.rabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 发送消息，测试确认
 */
@RestController
@Slf4j
@RequestMapping("/confirm")
public class ProducerController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    public String sendMsg(@PathVariable String message){

        CorrelationData correlationData=new CorrelationData("1");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,ConfirmConfig.CONFIRM_ROUTING_KEY,message+ConfirmConfig.CONFIRM_ROUTING_KEY,correlationData);
        log.info("发送消息内容:{}",message+ConfirmConfig.CONFIRM_ROUTING_KEY);

        CorrelationData correlationData2=new CorrelationData("2");
        rabbitTemplate.convertAndSend(ConfirmConfig.CONFIRM_EXCHANGE_NAME,ConfirmConfig.CONFIRM_ROUTING_KEY+"2",message+ConfirmConfig.CONFIRM_ROUTING_KEY+"2",correlationData2);
        log.info("发送消息内容:{}",message+ConfirmConfig.CONFIRM_ROUTING_KEY+"2");

        return "success";
    }
}
