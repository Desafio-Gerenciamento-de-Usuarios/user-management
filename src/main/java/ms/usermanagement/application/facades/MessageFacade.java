package ms.usermanagement.application.facades;

import ms.usermanagement.application.messages.NotifyUserMessage;

public interface MessageFacade {
    void sendMessage(NotifyUserMessage message);
}
