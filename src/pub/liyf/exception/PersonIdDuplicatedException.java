package pub.liyf.exception;

public class PersonIdDuplicatedException extends Exception {
    public PersonIdDuplicatedException() {
    }

    public PersonIdDuplicatedException(String message) {
        super(message);
    }
}
