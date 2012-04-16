import java.util.ArrayList;

public class Function {
    public Program parentProgram;
    private String name;
    private int params;
    private Block block;
    
    sStack stack;
    sStack returnList;
    
    
    public Function(String n, int p, Block b) {
        if (n.equals("")) n = this.hashCode() + "";
        
        name = n;
        params = p;
        block = b;
        
        parentProgram = null;
        stack = new sStack();
        returnList = new sStack();
    }
    
    public void call(FunctionCall fc, sStack s) {
        call(fc.getName(), s);
    }
    
    public void call(String fc, sStack s) {
        s = parentProgram.runFunction(fc, s);
        //System.out.println("Trying to add " + s);
        new sPush(s).run(this);
        parentProgram.debug(name + " STACK: " + stack);
    }
    
    public String getName() {
        return name;
    }
    
    public int getParams() {
        return params;
    }
    
    public sStack run(Program parent, sStack start) {
        returnList = new sStack();
        
        parentProgram = parent;
        stack = start;
        
        parentProgram.indent(4);
        parentProgram.debug("Running [" + name + "]");
        returnList = block.run(this);
        
        parentProgram.indent(-4);
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
        int first = ((aInteger)stack.getLast(0)).run();
        int second = ((aInteger)stack.getLast(1)).run();
        
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
        
        int first = ((aInteger)stack.getLast(0)).run();
        int second = ((aInteger)stack.getLast(1)).run();
        
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
