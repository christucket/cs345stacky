import java.util.ArrayList;

public class Expression {
    ArrayList<Argument> arguments;
    
    public Expression(ArrayList<Argument> args) {
        arguments = args;
    }
    
    public String toString() {
        return "EXPRESSION: " + arguments.toString();
    }
}
