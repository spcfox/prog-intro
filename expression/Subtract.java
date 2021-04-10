package expression;

public class Subtract extends BinaryOperation {
    public Subtract(FullExpression operand1, FullExpression operand2) {
        super(operand1, operand2);
    }

    @Override
    protected String getOperationSymbol() {
        return "-";
    }

    @Override
    protected int calculate(int x, int y) {
        return x - y;
    }

    @Override
    protected double calculate(double x, double y) {
        return x - y;
    }

    @Override
    public int getPriority() {
        return 10;
    }

    @Override
    public boolean isAssociative() {
        return false;
    }

    @Override
    public boolean isContinuous() {
        return true;
    }
}
