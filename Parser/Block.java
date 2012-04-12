import java.util.ArrayList;

public class Block {
    private ArrayList<Statement> statements;
    
    public Block() {
         statements = new ArrayList<Statement>();
    }
    
    public void run() {
        
    }
    
    public void add(Statement s) {
        statements.add(s);
    }
}
