package expression.exceptions;

import expression.BinaryOperation;
import expression.FullExpression;

public class CheckedLcm extends BinaryOperation {
    public CheckedLcm(FullExpression operand1, FullExpression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected String getOperationSymbol() {
        return "lcm";
    }

    @Override
    protected int calculate(int x, int y) {
        return CheckedMathUtils.lcm(x, y);
    }

    @Override
    protected double calculate(double x, double y) {
        throw new UnsupportedOperationException("CheckedLcm does not supports operations with doubles");
    }

    @Override
    public int getPriority() {
        return 5;
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
