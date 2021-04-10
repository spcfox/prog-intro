package expression.exceptions;

import expression.FullExpression;
import expression.UnaryOperation;

public class CheckedSqrt extends UnaryOperation {
    public CheckedSqrt(FullExpression operand) {
        super(operand);
    }

    @Override
    protected String getOperationSymbol() {
        return "sqrt";
    }

    @Override
    protected int calculate(int x) {
        if (x < 0) {
            throw new NegativeSqrtExpression();
        }

        return (int) Math.sqrt(x);
    }

    @Override
    protected double calculate(double x) {
        throw new UnsupportedOperationException("CheckedAbs does not supports operations with doubles");
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public boolean isAssociative() {
        return false;
    }

    @Override
    public boolean isContinuous() {
        return false;
    }
}
