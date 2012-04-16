import java.util.ArrayList;

public class Program {
    private String name;
    private ArrayList<Function> functions;
    private int tabs = 0;
    
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
        debug("Call " + fc);
        Function f = getFunction(fc);
        sStack callWith = new sStack();
        sStack ret = null;
        
        if (f.getParams() <= s.size()) {
            callWith = s.splitLast(f.getParams());
            s.popLast(f.getParams());
            ret = f.run(this, callWith);
        } else {
            System.out.println("Can't run " + f + " yet");
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
    
    
    
    public void debug(String text) {
        System.out.println(tabs() + text);
    }
    
    public void indent(int amt) {
        tabs += amt;
    }
    
    private String tabs() {
        String ret = "";
        
        for (int i = 0; i < tabs; i++) ret += " ";
        
        return ret;
    }
}
