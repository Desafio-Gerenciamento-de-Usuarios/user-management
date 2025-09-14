package ms.usermanagement.infrastructure.facede;

import lombok.AllArgsConstructor;
import ms.usermanagement.application.facades.MessageFacade;
import ms.usermanagement.application.messages.NotifyUserMessage;
import ms.usermanagement.infrastructure.gateway.messaging.producer.NotifyProducer;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MessageFacadeImpl implements MessageFacade {

    private final NotifyProducer notifyProducer;

    @Override
    public void sendMessage(NotifyUserMessage message) {
        notifyProducer.send(message);
    }
}
