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
}
