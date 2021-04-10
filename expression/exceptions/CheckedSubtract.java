package expression.exceptions;

import expression.Subtract;
import expression.FullExpression;

public class CheckedSubtract extends Subtract {
    public CheckedSubtract(FullExpression operand1, FullExpression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected int calculate(int x, int y) {
        return CheckedMathUtils.subtract(x, y);
    }

    @Override
    protected double calculate(double x, double y) {
        throw new UnsupportedOperationException("CheckedAdd does not supports operations with doubles");
    }
}
