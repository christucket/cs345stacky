
public class Condition {
    Expression left; Expression right;
    String operator;
    
    public Condition(String op, Expression l, Expression r) {
        operator = op;
        left = l;
        right = r;
    }
}
