package expression;

public class Multiply extends BinaryOperation {
    public Multiply(FullExpression operand1, FullExpression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected String getOperationSymbol() {
        return "*";
    }

    @Override
    protected int calculate(int x, int y) {
        return x * y;
    }

    @Override
    protected double calculate(double x, double y) {
        return x * y;
    }

    @Override
    public int getPriority() {
        return 20;
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
