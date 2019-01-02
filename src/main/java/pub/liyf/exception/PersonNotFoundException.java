package pub.liyf.exception;

public class PersonNotFoundException extends PersonOpException {
    public PersonNotFoundException() {
    }

    public PersonNotFoundException(String message) {
        super(message);
    }
}
