package expression;

public class Low extends UnaryOperation {
    public Low(FullExpression operand) {
        super(operand);
    }

    @Override
    protected String getOperationSymbol() {
        return "low";
    }

    @Override
    protected int calculate(int x) {
        return Integer.lowestOneBit(x);
    }

    @Override
    protected double calculate(double x) {
        throw new UnsupportedOperationException("Low does not support double");
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
