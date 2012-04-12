import java.util.ArrayList;

public class Function extends Argument {
    private Program parentProgram;
    private String name;
    private int params;
    private Block block;
    
    
    public Function(String n, int p, Block b) {
        super(2);
        name = n;
        if (n.equals("")) name = this.hashCode() + "";
        params = p;
        block = b;
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return "FUNCTION: " + name + " " + params;
    }
}

class FunctionCall extends Argument {
    private String name;
    
    public FunctionCall(String n) {
        super(6);
        name = n;
    }
    
    public String toString() {
        return "FUNCTIONCALL: " + name;
    }
}
