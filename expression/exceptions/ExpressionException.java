package expression.exceptions;

public class ExpressionException extends RuntimeException {
    public ExpressionException() {
        super();
    }

    public ExpressionException(final String message) {
        super(message);
    }

    public ExpressionException(final String message, final Exception e) {
        super(message, e);
    }
}
