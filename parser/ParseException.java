package parser;

public class ParseException extends Exception {
    public ParseException() {
        super();
    }

    public ParseException(final String message) {
        super(message);
    }

    public ParseException(final String message, Exception e) {
        super(message, e);
    }
}
