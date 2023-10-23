public class TimeTravelExpression 
    extends SparkExpression 
{
    public enum Operator
    {
        AS_OF_TIMESTAMP("AS OF TIMESTAMP"),
        AS_OF_VERSION("AS OF VERSION")
        /**/;

        private final String value;

        Operator(String value)
        {
            this.value = value;
        }

        public String getValue()
        {
            return value;
        }
    }

    private final Operator operator;
    private final SparkExpression left;
    private final SparkExpression right;

    public TimeTravelExpression(Operator operator, SparkExpression left, SparkExpression right)
    {
        requireNonNull(operator, "operator is null");
        requireNonNull(left, "left is null");
        requireNonNull(right, "right is null");

        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public Operator getOperator()
    {
        return operator;
    }

    public SparkExpression getLeft()
    {
        return left;
    }

    public SparkExpression getRight()
    {
        return right;
    }

    @Override
    public <R, C> R accept(SparkExpressionTreeVisitor<R, C> visitor, C context)
    {
        return visitor.visitTimeTravelExpression(this, context);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TimeTravelExpression that = (TimeTravelExpression) o;
        return operator == that.operator &&
                Objects.equals(left, that.left) &&
                Objects.equals(right, that.right);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(operator, left, right);
    }

    @Override
    public String toString()
    {
        return toStringHelper(this)
                .add("operator", operator)
                .add("left", left)
                .add("right", right)
                .toString();
    }
    
}
