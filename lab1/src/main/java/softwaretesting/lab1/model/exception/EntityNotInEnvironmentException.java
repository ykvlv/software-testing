package softwaretesting.lab1.model.exception;

public class EntityNotInEnvironmentException extends RuntimeException {
    public EntityNotInEnvironmentException(String message) {
        super(message);
        System.out.println(message);
    }
}
