package ms.usermanagement.infrastructure.consumer;

public abstract class Consumer<T> {
    public abstract void onMessage(T message);
}
