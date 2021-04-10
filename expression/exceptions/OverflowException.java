package expression.exceptions;

public class OverflowException extends ExpressionException {
    public OverflowException() {
        super();
    }

    public OverflowException(final String message) {
        super(message);
    }

    public OverflowException(final String message, final Exception e) {
        super(message, e);
    }
}
