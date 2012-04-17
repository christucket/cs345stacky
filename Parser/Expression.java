import java.util.ArrayList;

public class Expression {
    sStack arguments;
    
    public Expression(sStack args) {
        arguments = args;
    }
    
    public String toString() {
        return "EXPRESSION: " + arguments.toString();
    }
    
    public boolean run(Function parent) {
        sStack s = new sStack();
        sStack ret = new sStack();
        
        for (Argument a : arguments.stack) {
            if (a instanceof OperatorCall) {
                Function f = parent.parentProgram.getFunction(a.getName());
                ret = f.run(parent.parentProgram, s);
                s.popLast(2);
                s.push(ret.getLast(0));
            } else {
                s.push(parent.stack.getLast((Integer)a.run()));
            }
        }
        
        return ((Integer)s.getLast(0).run() > 0);
    }
}
