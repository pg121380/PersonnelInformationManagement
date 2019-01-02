package pub.liyf.exception;

public class PersonIdDuplicatedException extends PersonOpException {
    public PersonIdDuplicatedException() {
    }

    public PersonIdDuplicatedException(String message) {
        super(message);
    }
}
