package pub.liyf.exception;

public class StudentNotFoundException extends PersonNotFoundException{
    public StudentNotFoundException() {
    }

    public StudentNotFoundException(String message) {
        super(message);
    }
}
