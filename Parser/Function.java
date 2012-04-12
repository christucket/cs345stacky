import java.util.ArrayList;

public class Function {
    private Program parentProgram;
    private String name;
    private int params;
    private Block block;
    
    ArrayList<Argument> stack;
    ArrayList<Argument> returnList;
    
    
    public Function(String n, int p, Block b) {
        if (n.equals("")) n = this.hashCode() + "";
        
        name = n;
        params = p;
        block = b;
        
        stack = new ArrayList<Argument>();
        returnList = new ArrayList<Argument>();
    }
    
    public Argument getLast(int i) {
        // get last arguments in the stack, 0 is the last.
        return stack.get(stack.size() - i);
    }
    
    public String getName() {
        return name;
    }
    
    public int getParams() {
        return params;
    }
    
    public ArrayList<Argument> run() {
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
    
    public ArrayList<Argument> run() {
        int result = 0;
        int first = ((aInteger)getLast(0)).run();
        int second = ((aInteger)getLast(1)).run();
        
        if (operator.equals("+")) {
            result = first + second;
        }
        
        returnList.append(new aInteger(result));
        return returnList;
    }
    
    public boolean canRun() {
        return ( getLast(0) instanceof aInteger && getLast(1) instanceof aInteger );
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
    
    public ArrayList<Argument> run() {
        return returnList;
    }
    
    
    public String toString() {
        return "CONDITION: " + condition;
    }
}
