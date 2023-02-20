package softwaretesting.lab1.model.exception;

public class NotInAllowedSetException extends RuntimeException{
    public NotInAllowedSetException(String message) {
        super(message);
        System.out.println(message);
    }
}
