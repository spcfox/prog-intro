package expression;

public interface Operation extends FullExpression {
    int getPriority();
    boolean isAssociative();
    boolean isContinuous();
}
