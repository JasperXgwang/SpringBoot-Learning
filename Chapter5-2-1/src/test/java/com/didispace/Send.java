package com.didispace;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

/**
 * User: jasperxgwang
 * Date: 2018-1-12 16:54
 */
public class Send {
    //队列名称
    private final static String QUEUE_NAME = "hello";

    public static void main(String[] argv) throws java.io.IOException, InterruptedException {
        /**
         * 创建连接连接到MabbitMQ
         */
        ConnectionFactory factory = new ConnectionFactory();
        //设置MabbitMQ所在主机ip或者主机名
        factory.setHost("172.16.187.143");
        //创建一个连接
        Connection connection = factory.newConnection();
        //创建一个频道
        Channel channel = connection.createChannel();
        //指定一个队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        for (int i = 0; i < 100; i++) {
            //发送的消息
            String message = "hello world!" + i;
            //往队列中发出一条消息
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("Sent '" + message + "'");
            Thread.sleep(500);
        }
        //关闭频道和连接
        channel.close();
        connection.close();
    }
}
