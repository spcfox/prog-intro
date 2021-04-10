package expression.exceptions;

import expression.Divide;
import expression.FullExpression;

public class CheckedDivide extends Divide {
    public CheckedDivide(FullExpression operand1, FullExpression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected int calculate(int x, int y) {
        if (y == 0) {
            throw new DivisionByZeroException();
        }
        if (x == Integer.MIN_VALUE && y == -1) {
            throw new OverflowException();
        }
        return x / y;
    }

    @Override
    protected double calculate(double x, double y) {
        throw new UnsupportedOperationException("CheckedDivide does not supports operations with doubles");
    }
}
