package ms.usermanagement.infrastructure.dlq;

public abstract class DlqConsumer<T> {
    public abstract void onDlqMessage(T message);
}
