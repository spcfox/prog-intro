package expression.exceptions;

import expression.FullExpression;
import expression.UnaryOperation;

public class CheckedAbs extends UnaryOperation {
    public CheckedAbs(FullExpression operand) {
        super(operand);
    }

    @Override
    protected String getOperationSymbol() {
        return "abs";
    }

    @Override
    protected int calculate(int x) {
        return CheckedMathUtils.abs(x);
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
