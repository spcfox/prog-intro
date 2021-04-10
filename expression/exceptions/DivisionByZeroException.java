package expression.exceptions;

public class DivisionByZeroException extends ExpressionException {
    public DivisionByZeroException() {
        super();
    }

    public DivisionByZeroException(final String message) {
        super(message);
    }

    public DivisionByZeroException(final String message, final Exception e) {
        super(message, e);
    }
}
