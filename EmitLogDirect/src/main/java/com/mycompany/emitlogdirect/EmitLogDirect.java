package com.mycompany.emitlogdirect;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class EmitLogDirect {
    private final static String EXCHANGE_NAME = "direct_logs";
    
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        
        try (Connection connection = factory.newConnection()) {
            Channel channel = connection.createChannel();
            
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            
            String bindingKey1 = "urgent";
            String bindingKey2 = "normal";
            
            String severity = "[SERVER SAYS] It's gon' be me, you, and the dance floor. - Chirs Brown, 2007";
            String msg = "[SERVER SAYS] And it's all because of you. - Ne-Yo, 2007";
            
            channel.basicPublish(EXCHANGE_NAME, bindingKey1, null, severity.getBytes());
            
            System.out.println("[SERVER SAYS] >>urgent<< Message Sended!");
            
            channel.basicPublish(EXCHANGE_NAME, bindingKey2, null, msg.getBytes());
            
            System.out.println("[SERVER SAYS] >>normal<< Message Sended!");
            
        }
    }
}
