package ms.usermanagement.infrastructure.gateway.messaging;

public abstract class Message<M> {
    public abstract void send(M message);
}
