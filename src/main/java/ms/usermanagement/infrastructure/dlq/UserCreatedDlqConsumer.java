package ms.usermanagement.infrastructure.dlq;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import ms.usermanagement.application.messages.UserCreatedMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Log4j2
@Component
@RequiredArgsConstructor
public class UserCreatedDlqConsumer extends DlqConsumer<UserCreatedMessage> {


    @Override
    @RabbitListener(queues = "${amqp.dlq.user-created-dlq}")
    public void onDlqMessage(UserCreatedMessage message) {
        log.error("Falha persistente ao criar usuário, enviando alerta: {}", message.id());
    }
}
