package com.example.rabbitmq.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import static com.example.rabbitmq.config.DelayedQueueConfig.DELAYED_EXCHANGE_NAME;
import static com.example.rabbitmq.config.DelayedQueueConfig.DELAYED_ROUTING_KEY;

/**
 * 发送消息
 */
@RestController
@RequestMapping("/ttl")
@Slf4j
@Tag(name = "SendMsgController",description = "TTL相关接口")
public class SendMsgController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/sendMsg/{message}")
    @Operation(summary = "发送消息")
    @Parameter(name = "message",description = "消息")
    public String sendMsg(@PathVariable String message){
        log.info("当前时间： {},发送一条信息给两个 TTL 队列:{}", new Date(), message);
        rabbitTemplate.convertAndSend("X","XA","消息来自 ttl 为 10S 的队列: "+message);
        rabbitTemplate.convertAndSend("X", "XB", "消息来自 ttl 为 40S 的队列: "+message);
        return "success";
    }

    @GetMapping("sendExpirationMsg/{message}/{ttlTime}")
    @Operation(summary = "发送定时消息")
    @Parameters({
            @Parameter(name="message", description = "消息"),
            @Parameter(name = "ttlTime",description = "过期时间")
            })
    public String sendExpirationMsg(@PathVariable String message,@PathVariable String ttlTime) {
        rabbitTemplate.convertAndSend("X", "XC", message, correlationData ->{
            correlationData.getMessageProperties().setExpiration(ttlTime);
            return correlationData;
        });
        log.info("当前时间： {},发送一条时长{}毫秒 TTL 信息给队列 C:{}", new Date(),ttlTime, message);
        return "success";
    }

    @GetMapping("sendDelayMsg/{message}/{delayTime}")
    @Operation(summary = "发送延迟消息")
    @Parameters({
            @Parameter(name="message", description = "消息"),
            @Parameter(name = "delayTime",description = "延迟时间")
    })
    public String sendDelayMsg(@PathVariable String message,@PathVariable Integer delayTime) {
        rabbitTemplate.convertAndSend(DELAYED_EXCHANGE_NAME, DELAYED_ROUTING_KEY, message, correlationData ->{
            correlationData.getMessageProperties().setDelay(delayTime);
            return correlationData;
        });
        log.info(" 当前时间：{}, 发送一条延迟{}毫秒的信息给队列delayed.queue:{}", new Date(),delayTime, message);
        return "success";
    }
}
