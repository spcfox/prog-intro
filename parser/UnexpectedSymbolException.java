package parser;

public class UnexpectedSymbolException extends ParseException {
    public UnexpectedSymbolException() {
        super();
    }

    public UnexpectedSymbolException(final String message) {
        super(message);
    }

    public UnexpectedSymbolException(final String message, Exception e) {
        super(message, e);
    }
}
