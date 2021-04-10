package parser;

public class UnexpectedEndException extends ParseException {
    public UnexpectedEndException() {
        super();
    }

    public UnexpectedEndException(final String message) {
        super(message);
    }

    public UnexpectedEndException(final String message, Exception e) {
        super(message, e);
    }
}
