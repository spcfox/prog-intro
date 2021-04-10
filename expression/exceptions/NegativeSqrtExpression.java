package expression.exceptions;

public class NegativeSqrtExpression extends ExpressionException {
    public NegativeSqrtExpression() {
        super();
    }

    public NegativeSqrtExpression(final String message) {
        super(message);
    }

    public NegativeSqrtExpression(final String message, final Exception e) {
        super(message, e);
    }
}
