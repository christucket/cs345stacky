import java.util.ArrayList;

public class Block {
    private ArrayList<Statement> statements;
    
    public Block() {
         statements = new ArrayList<Statement>();
    }
    
    public sStack run(Function parent) {
        parent.parentProgram.indent(4);
        
        parent.parentProgram.debug(parent.getName() + "[" + parent.hashCode() % 1000 + "] STACK[" + parent.stack.hashCode() % 1000 + "]: " + parent.stack.toString());
        for (Statement s : statements) {
            if (parent.hasReturned) break;
            parent.parentProgram.debug(s.toString());
            s.run(parent);
            parent.parentProgram.debug(parent.getName() + "[" + parent.hashCode() % 1000 + "] STACK[" + parent.stack.hashCode() % 1000 + "]: " + parent.stack.toString());
        }
        
        parent.parentProgram.indent(-4);
        
        return new sStack();
    }
    
    public void add(Statement s) {
        statements.add(s);
    }
}
