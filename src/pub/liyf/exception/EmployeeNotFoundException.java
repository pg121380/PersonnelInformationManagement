package pub.liyf.exception;

public class EmployeeNotFoundException extends PersonNotFoundException {
    public EmployeeNotFoundException() {
    }

    public EmployeeNotFoundException(String message) {
        super(message);
    }
}
