package ms.usermanagement.infrastructure.gateway.messaging.producer;

import ms.usermanagement.application.messages.NotifyUserMessage;
import ms.usermanagement.infrastructure.gateway.messaging.Message;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotifyProducer extends Message<NotifyUserMessage> {

    private final AmqpTemplate amqpTemplate;
    private final String queueName;

    public NotifyProducer(AmqpTemplate amqpTemplate, @Value("${amqp.queue.notify-user}") String queueName) {
        this.amqpTemplate = amqpTemplate;
        this.queueName = queueName;
    }

    @Override
    public void send(NotifyUserMessage message) {
        this.amqpTemplate.convertAndSend(this.queueName, message);
    }
}
