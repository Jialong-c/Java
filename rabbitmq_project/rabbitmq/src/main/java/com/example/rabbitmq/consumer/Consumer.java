package com.example.rabbitmq.consumer;

import com.example.rabbitmq.config.ConfirmConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 接收消息
 */
@Component
@Slf4j
public class Consumer {

    @RabbitListener(queues = ConfirmConfig.CONFIRM_QUEUE_NAME)
    public void receiveConfirmMessage(Message message){
        String msg = new String(message.getBody());
        log.info("接受到队列 confirm.queue 消息:{}",msg);
    }
}
