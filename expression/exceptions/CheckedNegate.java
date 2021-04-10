package expression.exceptions;

import expression.Negate;
import expression.FullExpression;

public class CheckedNegate extends Negate {
    public CheckedNegate(FullExpression operand) {
        super(operand);
    }

    @Override
    protected int calculate(int x) {
        return CheckedMathUtils.negate(x);
    }
}
