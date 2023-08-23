package com.example.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.CustomExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class DelayedQueueConfig {

    public static final String DELAYED_QUEUE_NAME = "delayed.queue";
    public static final String DELAYED_EXCHANGE_NAME = "delayed.exchange";
    public static final String DELAYED_ROUTING_KEY = "delayed.routingkey";

    //声明交换机
    @Bean("delayedExchange")
    public CustomExchange delayedExchange(){
        Map<String, Object> args = new HashMap<>();
        args.put("x-delayed-type","direct");
        return new CustomExchange(DELAYED_EXCHANGE_NAME,"x-delayed-message",false,false,args);
    }

    //声明队列
    @Bean("delayedQueue")
    public Queue delayedQueue(){
        return new Queue(DELAYED_QUEUE_NAME,false,false,false,null);
    }

    @Bean
    public Binding bindingDelayedQueue(@Qualifier("delayedQueue") Queue queue,@Qualifier("delayedExchange") CustomExchange delayedExchange) {
        return BindingBuilder.bind(queue).to(delayedExchange).with(DELAYED_ROUTING_KEY).noargs();
    }
}
