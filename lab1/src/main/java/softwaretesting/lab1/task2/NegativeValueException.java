package softwaretesting.lab1.task2;

public class NegativeValueException extends RuntimeException {
    public NegativeValueException(String message) {
        super(message);
        System.err.println(message);
    }
}
