package com.example.workqueue;

import com.example.utils.RabbitMqUtils;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;

public class Worker02 {
    public static final String QUEUE_NAME="hello";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        DeliverCallback deliverCallback=(consumerTag, delivery)->{
            String message= new String(delivery.getBody());
            System.out.println("接收到消息:"+message);
        };

        CancelCallback cancelCallback=(consumerTag)->{
            System.out.println("消息消费被中断");
        };
        System.out.println("C2消费者启动等待消费......");
        channel.basicConsume(QUEUE_NAME,true, deliverCallback,cancelCallback);
    }
}
