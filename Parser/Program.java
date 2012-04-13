import java.util.ArrayList;

public class Program {
    private String name;
    private ArrayList<Function> functions;
    private int tabs = 0;
    
    public Program() {
        functions = new ArrayList<Function>();
    }
    
    public void add(Function f) {
        functions.add(f);
    }
    
    public void run() {
        Function f = getFunction("main");
        if (f == null) System.out.println("Couldn't find function main");
        
        f.run(this);
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
