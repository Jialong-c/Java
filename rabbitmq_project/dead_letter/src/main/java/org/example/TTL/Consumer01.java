package org.example.TTL;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.example.utils.RabbitMqUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Consumer01 {

    //普通交换机名称
    private static final String NORMAL_EXCHANGE = "normal_exchange";
    //死信交换机名称
    private static final String DEAD_EXCHANGE = "dead_exchange";

    public static void main(String[] args) throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);
        channel.exchangeDeclare(DEAD_EXCHANGE,BuiltinExchangeType.DIRECT);

        //声明普通队列
        String normalQueue = "normal-queue";
        HashMap<String, Object> map = new HashMap<>();
        //正常队列设置死信交换机
        map.put("x-dead-letter-exchange", DEAD_EXCHANGE);
        //正常队列设置死信 routing-key 参数 key 是固定值
        map.put("x-dead-letter-routing-key", "lisi");
        channel.queueDeclare(normalQueue,false,false,false,map);

        //声明死信队列
        String deadQueue="dead-queue";
        channel.queueDeclare(deadQueue,false,false,false,null);

        channel.queueBind(normalQueue,NORMAL_EXCHANGE,"zhangsan");
        channel.queueBind(deadQueue,DEAD_EXCHANGE,"lisi");

        System.out.println("等待接收消息.....");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Consumer01接收到消息:"+message);
        };
        channel.basicConsume(normalQueue,true,deliverCallback, (CancelCallback) null);
    }
}
