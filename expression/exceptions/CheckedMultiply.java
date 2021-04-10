package expression.exceptions;

import expression.Multiply;
import expression.FullExpression;

public class CheckedMultiply extends Multiply {
    public CheckedMultiply(FullExpression operand1, FullExpression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected int calculate(int x, int y) {
        return CheckedMathUtils.multiply(x, y);
    }

    @Override
    protected double calculate(double x, double y) {
        throw new UnsupportedOperationException("CheckedMultiply does not supports operations with doubles");
    }
}
