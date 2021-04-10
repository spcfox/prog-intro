package expression;

import java.util.Objects;

public abstract class UnaryOperation implements Operation {
    private final FullExpression operand;

    public UnaryOperation(FullExpression operand) {
        this.operand = operand;
    }

    @Override
    public int evaluate(int variable) {
        return calculate(operand.evaluate(variable));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(operand.evaluate(x, y, z));
    }

    @Override
    public double evaluate(double variable) {
        return calculate(operand.evaluate(variable));
    }

    protected abstract int calculate(int x);

    protected abstract double calculate(double x);

    @Override
    public String toString() {
        return "(" + getOperationSymbol() + operand + ")";
    }

    protected abstract String getOperationSymbol();

    @Override
    public String toMiniString() {
        if (operand instanceof Operation && ((Operation) operand).getPriority() < getPriority()
                || operand instanceof UnaryOperation) {
            return getOperationSymbol() + "(" + operand.toMiniString() + ")";
        } else {
            return getOperationSymbol() + operand.toMiniString();
        }
    }

    @Override
    public boolean isAssociative() {
        return true;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        UnaryOperation that = (UnaryOperation) obj;
        return operand.equals(that.operand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand, getClass());
    }
}
