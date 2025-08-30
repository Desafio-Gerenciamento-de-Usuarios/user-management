package ms.usermanagement.application.usecase;

public abstract class VoidUseCase<I> {
    public abstract void execute(I input);

    public abstract void validate(I input);
}
