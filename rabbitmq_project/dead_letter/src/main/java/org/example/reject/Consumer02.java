package org.example.reject;

import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.example.utils.RabbitMqUtils;

public class Consumer02 {
    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();

        String deadQueue = "dead-queue";
        System.out.println("等待接收消息.....");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Consumer02接收到消息:"+message);
        };
        channel.basicConsume(deadQueue,true,deliverCallback, (CancelCallback) null);
    }
}
