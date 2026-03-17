package org.example.core;

import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public abstract class BaseConnector<T> {

    protected final Logger logger;
    protected final String queueName;
    protected final Channel channel;
    protected final Class<T> eventClass;

    protected BaseConnector(Class<T> eventClass) throws IOException {
        this.logger = LoggerFactory.getLogger(this.getClass());
        this.eventClass = eventClass;
        this.queueName = eventClass.getSimpleName();
        this.channel = RabbitMQManager.getInstance().getChannel();

//        logMethodCall("BaseConnectorConstructor", queueName);
        this.channel.queueDeclare(queueName, false, false, false, null);
    }

    protected void logMethodCall(String methodName, Object... args) {
        StringBuilder sb = new StringBuilder();
        sb.append("Method: ").append(methodName).append(" | Args: ");
        for (Object arg : args) {
            sb.append(arg).append(" ");
        }
        logger.info(sb.toString());
    }
}
