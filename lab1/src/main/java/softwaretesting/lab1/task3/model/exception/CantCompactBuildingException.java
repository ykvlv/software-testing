package softwaretesting.lab1.task3.model.exception;

public class CantCompactBuildingException extends RuntimeException {
    public CantCompactBuildingException(String message) {
        super(message);
        System.err.println(message);
    }
}
