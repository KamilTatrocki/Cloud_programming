package org.example.core;



import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.example.Secret;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQManager {

    private static final Logger logger = LoggerFactory.getLogger(RabbitMQManager.class);
    private static RabbitMQManager instance;
    private Connection connection;
    private Channel channel;

    private RabbitMQManager() {
        ConnectionFactory factory = new ConnectionFactory();

        try {
            try{
                factory.setUri(Secret.secret);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            this.connection = factory.newConnection();
            this.channel = connection.createChannel();
        } catch (IOException | TimeoutException e) {
            logger.error("Error during RabbitMQ initialization", e);
        }
    }

    public static synchronized RabbitMQManager getInstance() {
        if (instance == null) {
            instance = new RabbitMQManager();
        }
        return instance;
    }

    public Channel getChannel() {
        return channel;
    }

    public void close() throws IOException, TimeoutException {
        if (channel != null) channel.close();
        if (connection != null) connection.close();
    }
}
