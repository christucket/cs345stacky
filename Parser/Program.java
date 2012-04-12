import java.util.ArrayList;

public class Program {
    private String name;
    private ArrayList<Function> functions;
    
    public Program() {
        functions = new ArrayList<Function>();
    }
    
    public void add(Function f) {
        functions.add(f);
    }
    
    public void run() {
        Function f = getFunction("main");
        if (f == null) System.out.println("Couldn't find function main");
        
        f.run();
    }
    
    public Function getFunction(String name) {
        for (Function f : functions) {
            if (f.getName().equals(name)) {
                return f;
            }
        }
        
        return null;
    }
}
