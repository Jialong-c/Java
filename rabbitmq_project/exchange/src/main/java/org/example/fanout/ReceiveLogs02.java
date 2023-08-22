package org.example.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.CancelCallback;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import org.example.utils.RabbitMqUtils;

/**
 * 消息接收
 */
public class ReceiveLogs02 {
    //交换机的名称
    private static final String EXCHANGE_NAME = "logs";
    public static void main(String[] args) throws Exception {

        Channel channel = RabbitMqUtils.getChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, BuiltinExchangeType.FANOUT);
        /**
         * 生成一个临时的队列，队列的名称是随机的
         * 当消费者断开和该队列的连接时，队列自动删除
         */
        String queue = channel.queueDeclare().getQueue();
        /**
         * 绑定交换机与队列
         */
        channel.queueBind(queue,EXCHANGE_NAME,"");
        System.out.println("ReceiveLogs02等待接收消息,把接收到的消息打印在屏幕.....");
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody());
            System.out.println("ReceiveLogs02控制台打印接收到的消息："+message);
        };
        channel.basicConsume(queue,true,deliverCallback, (CancelCallback) null);
    }
}
