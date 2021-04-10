package parser;

public class ParseNumberException extends ParseException {
    public ParseNumberException() {
        super();
    }

    public ParseNumberException(final String message) {
        super(message);
    }

    public ParseNumberException(final String message, Exception e) {
        super(message, e);
    }
}
