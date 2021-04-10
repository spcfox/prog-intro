package expression;

public class Flip extends UnaryOperation {
    public Flip(FullExpression operand) {
        super(operand);
    }

    @Override
    protected String getOperationSymbol() {
        return "flip";
    }

    @Override
    protected int calculate(int x) {
        return Integer.reverse(x) >>> Integer.numberOfLeadingZeros(x);
    }

    @Override
    protected double calculate(double x) {
        throw new UnsupportedOperationException("Flip does not support double");
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
