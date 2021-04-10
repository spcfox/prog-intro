package expression.exceptions;

import expression.Add;
import expression.FullExpression;

public class CheckedAdd extends Add {
    public CheckedAdd(FullExpression operand1, FullExpression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected int calculate(int x, int y) {
        return CheckedMathUtils.add(x, y);
    }

    @Override
    protected double calculate(double x, double y) {
        throw new UnsupportedOperationException("CheckedSubtract does not supports operations with doubles");
    }
}

