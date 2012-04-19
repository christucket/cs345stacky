import java.util.ArrayList;

public class Function {
    public Program parentProgram;
    private String name;
    private int params;
    private Block block;
    
    sStack stack;
    sStack returnList;
    boolean hasReturned;
    
    
    public Function(String n, int p, Block b) {
        if (n.equals("")) n = this.hashCode() + "";
        
        name = n;
        params = p;
        block = b;
        
        parentProgram = null;
        stack = new sStack();
        returnList = new sStack();
    }
    
    public boolean call(FunctionCall fc, sStack s) {
        return call(fc.getName(), s);
    }
    
    public boolean call(String fc, sStack s) {
        s = parentProgram.runFunction(fc, s);
        if (s == null) return false;
        
        parentProgram.debug("Returning " + s + " to STACK[" + stack.hashCode() % 1000 + "]: " + stack);
        //System.out.println("Trying to add " + s);
        new sPush(s).run(this);
        parentProgram.debug(name + "[" + this.hashCode() % 1000 + "] STACK[" + stack.hashCode() % 1000 + "]: " + stack);
        return true;
    }
    
    public String getName() {
        return name;
    }
    
    public int getParams() {
        return params;
    }
    
    public Function getCopy() {
        return new Function(name, params, block);
    }
    
    public sStack run(Program parent, sStack start) {
        stack = start;
        returnList = new sStack();
        hasReturned = false;
        
        parentProgram = parent;
        stack = start;
        
        //parentProgram.indent(4);
        parentProgram.debug("Running: " + name + "[" + this.hashCode() % 1000 + "] with STACK[" + stack.hashCode() % 1000 + "]");
        block.run(this);
        
        //parentProgram.indent(-4);
        return returnList;
    }
    
    public boolean canRun() {
        return (stack.size() >= params);
    }
    
    public String toString() {
        return "FUNCTION: " + name + " " + params;
    }
}

class aOperator extends Function {
    String operator;
    
    public aOperator(String op) {
        super(op, 2, new Block());
        operator = op;
    }
    
    public sStack run(Program parent, sStack start) {
        stack = start;
        returnList = new sStack();
        
        int result = 0;
        int second = ((aInteger)stack.getLast(0)).run();
        int first = ((aInteger)stack.getLast(1)).run();
        
        if (operator.equals("+")) {
            result = first + second;
        } else if (operator.equals("-")) {
            result = first - second;
        } else if (operator.equals("/")) {
            result = first / second;
        } else if (operator.equals("*")) {
            result = first * second;
        }
        
        returnList.push(new aInteger(result));
        return returnList;
    }
    
    public boolean canRun() {
        boolean ret = false;
        
        System.out.println(getName() + " - " + stack.size() + " > 1");
        if (stack.size() > 1) {
            ret = ( stack.getLast(0) instanceof aInteger && stack.getLast(1) instanceof aInteger );
        }
        
        return ret;
    }
    
    public String toString() {
        return "OPERATOR: " + operator;
    }
}

class aCondition extends Function {
    String condition;
    
    public aCondition(String c) {
        super(c, 2, new Block());
        condition = c;
    }
    
    public sStack run(Program parent, sStack start) {
        boolean result = false;
        stack = start;
        returnList = new sStack();
        
        int second = ((aInteger)stack.getLast(0)).run();
        int first = ((aInteger)stack.getLast(1)).run();
        
        if (condition.equals("==")) {
            result = first == second;
        } else if (condition.equals("!=")) {
            result = first != second;
        } else if (condition.equals(">=")) {
            result = first >= second;
        } else if (condition.equals(">")) {
            result = first > second;
        } else if (condition.equals("<=")) {
            result = first <= second;
        } else if (condition.equals("<")) {
            result = first < second;
        }
        
        if (result) {
            returnList.push(new aInteger(1));
        } else {
            returnList.push(new aInteger(0));
        }
        
        return returnList;
    }
    
    public boolean canRun() {
        boolean ret = false;
        
        if (stack.size() > 1) {
            ret = ( stack.getLast(0) instanceof aInteger && stack.getLast(1) instanceof aInteger );
        }
        
        return ret;
    }
    
    public String toString() {
        return "CONDITION: " + condition;
    }
}
