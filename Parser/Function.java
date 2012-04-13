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
        
        stack = new sStack();
        returnList = new sStack();
    }
    
    public String getName() {
        return name;
    }
    
    public int getParams() {
        return params;
    }
    
    public sStack run(Program parent) {
        parentProgram = parent;
        
        parentProgram.indent(4);
        parentProgram.debug("Running [" + name + "]");
        returnList = block.run(this);
        
        parentProgram.indent(-4);
        return returnList;
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
    
    public sStack run() {
        int result = 0;
        int first = ((aInteger)stack.getLast(0)).run();
        int second = ((aInteger)stack.getLast(1)).run();
        
        if (operator.equals("+")) {
            result = first + second;
        }
        
        returnList.push(new aInteger(result));
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
        return "OPERATOR: " + operator;
    }
}

class aCondition extends Function {
    String condition;
    
    public aCondition(String c) {
        super(c, 2, new Block());
        condition = c;
    }
    
    public sStack run() {
        boolean result = false;
        
        int first = ((aInteger)stack.getLast(0)).run();
        int second = ((aInteger)stack.getLast(1)).run();
        
        if (condition.equals("==")) {
            result = first == second;
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
