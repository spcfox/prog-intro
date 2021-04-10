package expression;

public class Negate extends UnaryOperation {
    public Negate(FullExpression operand) {
        super(operand);
    }

    @Override
    protected String getOperationSymbol() {
        return "-";
    }

    @Override
    protected int calculate(int x) {
        return -x;
    }

    @Override
    protected double calculate(double x) {
        return -x;
    }

    @Override
    public int getPriority() {
        return 0;
    }

    @Override
    public boolean isContinuous() {
        return true;
    }
}
