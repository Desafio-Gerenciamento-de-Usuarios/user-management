package ms.usermanagement.application.usecase;

public abstract class UseCase<I, O> {
    public abstract O execute(I input);

    public abstract void validate(I input);
}
