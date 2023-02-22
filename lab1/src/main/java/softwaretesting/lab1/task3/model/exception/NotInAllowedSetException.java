package softwaretesting.lab1.task3.model.exception;

public class NotInAllowedSetException extends RuntimeException{
    public NotInAllowedSetException(String message) {
        super(message);
        System.err.println(message);
    }
}
