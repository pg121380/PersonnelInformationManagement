package pub.liyf.exception;

public class PersonNotFoundException extends Exception {
    public PersonNotFoundException() {
    }

    public PersonNotFoundException(String message) {
        super(message);
    }
}
