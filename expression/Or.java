package expression;

public class Or extends BinaryOperation {
    public Or(FullExpression operand1, FullExpression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected String getOperationSymbol() {
        return "|";
    }

    @Override
    protected int calculate(int x, int y) {
        return x | y;
    }

    @Override
    protected double calculate(double x, double y) {
        throw new UnsupportedOperationException("OR does not support double");
    }

    @Override
    public int getPriority() {
        return 3;
    }

    @Override
    public boolean isAssociative() {
        return true;
    }

    @Override
    public boolean isContinuous() {
        return true;
    }
}
