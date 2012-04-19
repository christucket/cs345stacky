import java.util.ArrayList;
import java.util.HashMap;

public class Program {
    private String name;
    private ArrayList<Function> functions;
    private int tabs = 0;
    private String line = " ";
    private HashMap<Integer, Argument> memory;
    
    public Program() {
        functions = new ArrayList<Function>();
        functions.add(new aOperator("+"));
        functions.add(new aOperator("-"));
        functions.add(new aOperator("*"));
        functions.add(new aOperator("/"));
        
        functions.add(new aCondition("=="));
        functions.add(new aCondition("!="));
        functions.add(new aCondition(">="));
        functions.add(new aCondition(">"));
        functions.add(new aCondition("<="));
        functions.add(new aCondition("<"));
        
        memory = new HashMap<Integer, Argument>();
    }
    
    public void add(Function f) {
        functions.add(f);
    }
    
    public void run() {
        Function f = getFunction("main");
        if (f == null) System.out.println("Couldn't find function main");
        
        f.run(this, new sStack());
    }
    
    public void runFunction(FunctionCall fc, sStack s) {
        runFunction(fc.getName(), s);
    }
    
    public sStack runFunction(String fc, sStack s) {
        Function f = getFunction(fc);
        sStack callWith = new sStack();
        sStack ret = null;
        
        debug("Call " + fc);
        
        if (f.getParams() <= s.size()) {
            callWith = s.splitLast(f.getParams());
            s.popLast(f.getParams());
            
            // if f is a function, call a copy of that function. it prevents multiple pushing in recursive calls.
            if (! (f instanceof aOperator || f instanceof aCondition)) {
                f = f.getCopy();
            }
            
            ret = f.run(this, callWith);
        } else {
            debug(" -- can't yet, pushing to stack.");
        }
        
        return ret;
    }
    
    
    public Function getFunction(String name) {
        for (Function f : functions) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        
        return null;
    }
    
    
    public Argument getMemory(int index) {   
        if (memory.containsKey(index)) {
            return memory.get(index);
        }
        
        return new aInteger(0);
    }
    
    public void setMemory(int index, Argument arg) {
        memory.put(index, arg);
    }
    
    public void debug(String text) {
        System.out.println(tabs() + text);
    }
    
    public void indent(int amt) {
        tabs += amt;
    }
    
    private String tabs() {
        String ret = "";
        
        for (int i = 0; i < tabs; i++) {
            if (i % 4 == 0) ret += line; else ret += " ";
        }
        
        return ret;
    }
}
