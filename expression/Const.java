package expression;

import java.util.Objects;

public class Const implements FullExpression {
    private final Number value;

    public Const(int value) {
        this.value = value;
    }

    public Const(double value) {
        this.value = value;
    }

    @Override
    public int evaluate(int variable) {
        return value.intValue();
    }

    @Override
    public double evaluate(double variable) {
        return value.doubleValue();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public String toMiniString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return value.equals(((Const) obj).value);
    }

    @Override
    public int hashCode() {
         return Objects.hash(value);
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return evaluate(0);
    }
}
