package expression;

import java.util.Objects;

public abstract class BinaryOperation implements Operation {
    private final FullExpression operand1;
    private final FullExpression operand2;

    public BinaryOperation(FullExpression operand1, FullExpression operand2) {
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    @Override
    public int evaluate(int variable) {
        return calculate(operand1.evaluate(variable), operand2.evaluate(variable));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return calculate(operand1.evaluate(x, y, z), operand2.evaluate(x, y, z));
    }

    @Override
    public double evaluate(double variable) {
        return calculate(operand1.evaluate(variable), operand2.evaluate(variable));
    }

    protected abstract int calculate(int x, int y);

    protected abstract double calculate(double x, double y);

    @Override
    public String toString() {
        return "(" + operand1 + " " + getOperationSymbol() + " " + operand2 + ")";
    }

    protected abstract String getOperationSymbol();

    @Override
    public String toMiniString() {
        String result = addBracketsIf(
                operand1.toMiniString(),
                operand1 instanceof Operation
                        && ((Operation) operand1).getPriority() < this.getPriority()
        );

        result += " " + getOperationSymbol() + " ";

        result += addBracketsIf(
                operand2.toMiniString(),
                operand2 instanceof Operation && (
                        ((Operation) operand2).getPriority() < this.getPriority()
                        || ((Operation) operand2).getPriority() == this.getPriority()
                        && (!this.isAssociative() || !((Operation) operand2).isContinuous())
                )
        );

        return result;
    }

    private String addBracketsIf(String string, boolean addBrackets) {
        if (addBrackets) {
            return "(" + string + ")";
        }
        return string;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BinaryOperation that = (BinaryOperation) obj;
        return operand1.equals(that.operand1) && operand2.equals(that.operand2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operand1, operand2, getClass());
    }
}
