package com.example.ack;

import com.example.utils.RabbitMqUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;

import java.util.UUID;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

/**
 * 发布确认模式
 * 1.单个确认
 * 2.批量确认
 * 3.异步批量确认
 */
public class ConfirmMessage {

    //发送消息的个数
    public static final int MESSAGE_COUNT=1000;

    public static void main(String[] args) throws Exception {
        //1.单个确认 耗时 568 ms
        //ConfirmMessage.publishMessageSingle();
        //2.批量确认 耗时 76 ms
        //ConfirmMessage.publishMessageBatch();
        //3.异步批量确认 耗时 65 ms
        ConfirmMessage.publishMessageAsync();
    }

    //1.单个确认
    public static void publishMessageSingle() throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare("",false,false,false,null);
        //开启发布确认
        channel.confirmSelect();
        long begin = System.currentTimeMillis();
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message=i+"";
            channel.basicPublish("",queueName,null,message.getBytes());
            //单个消息进行发布确认
            boolean confirms = channel.waitForConfirms();
            if(confirms){
                System.out.printf("消息%s发送成功\n",message);
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("发布%d个单独确认消息，耗时 %d ms",MESSAGE_COUNT,end-begin);
    }

    //2.批量确认
    public static void publishMessageBatch() throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare("",false,false,false,null);
        //开启发布确认
        channel.confirmSelect();
        long begin = System.currentTimeMillis();
        //批量确认消息大小
        int batchSize=100;
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message=i+"";
            channel.basicPublish("",queueName,null,message.getBytes());
            //判断达到100条消息的时候批量确认一次
            if((i+1)%batchSize==0){
                boolean confirms = channel.waitForConfirms();
                if(confirms){
                    System.out.printf("第%d-%d条消息发送成功\n",(i-99),(i+1));
                }
            }
        }
        long end = System.currentTimeMillis();
        System.out.printf("发布%d个批量确认消息，耗时 %d ms",MESSAGE_COUNT,end-begin);
    }

    //异步发布确认
    public static void publishMessageAsync() throws Exception {
        Channel channel = RabbitMqUtils.getChannel();
        String queueName = UUID.randomUUID().toString();
        channel.queueDeclare("",false,false,false,null);
        //开启发布确认
        channel.confirmSelect();
        /**
         * 线程安全有序的一个哈希表，适用于高并发的情况
         * 1.轻松的将序号与消息进行关联
         * 2.轻松批量删除条目 只要给到序列号
         * 3.支持并发访问
         */
        ConcurrentSkipListMap<Long,String> outstandingConfirms=new ConcurrentSkipListMap<>();



        /**
         * 确认收到消息的一个回调
         * 1.消息序列号
         * 2.true 可以确认小于等于当前序列号的消息
         * false 确认当前序列号消息
         */
        ConfirmCallback ackCallback = (sequenceNumber, multiple) -> {
            if (multiple) {
                //返回的是小于等于当前序列号的未确认消息 是一个 map
                ConcurrentNavigableMap<Long, String> confirmed =outstandingConfirms.headMap(sequenceNumber, true);
                //清除该部分未确认消息
                confirmed.clear();
            }else{
                //只清除当前序列号的消息
                outstandingConfirms.remove(sequenceNumber);
            }
        };
        ConfirmCallback nackCallback = (sequenceNumber, multiple) -> {
            String message = outstandingConfirms.get(sequenceNumber);
            System.out.println("发布的消息"+message+"未被确认，序列号"+sequenceNumber);
        };
        /**
         * 添加一个异步确认的监听器
         * 1.确认收到消息的回调
         * 2.未收到消息的回调
         */
        channel.addConfirmListener(ackCallback,nackCallback);

        long begin = System.currentTimeMillis();
        //批量发布消息
        for (int i = 0; i < MESSAGE_COUNT; i++) {
            String message=i+"";
            channel.basicPublish("",queueName,null,message.getBytes());
            /**
             * channel.getNextPublishSeqNo()获取下一个消息的序列号
             * 通过序列号与消息体进行一个关联
             * 全部都是未确认的消息体
             */
            outstandingConfirms.put(channel.getNextPublishSeqNo(), message);
        }
        long end = System.currentTimeMillis();
        System.out.printf("发布%d个异步发布确认消息，耗时 %d ms",MESSAGE_COUNT,end-begin);
    }
}
