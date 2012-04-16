import java.util.ArrayList;

public class Block {
    public Function parentFunction;
    private ArrayList<Statement> statements;
    
    public Block() {
         statements = new ArrayList<Statement>();
    }
    
    public sStack run(Function parent) {
        parentFunction = parent;
        
        parentFunction.parentProgram.indent(4);
        
        parentFunction.parentProgram.debug(parent.getName() + " STACK: " + parentFunction.stack.toString());
        for (Statement s : statements) {
            if (parent.hasReturned) break;
            parentFunction.parentProgram.debug(s.toString());
            s.run(parentFunction);
            parentFunction.parentProgram.debug(parent.getName() + " STACK: " + parentFunction.stack.toString());
        }
        
        parentFunction.parentProgram.indent(-4);
        
        return new sStack();
    }
    
    public void add(Statement s) {
        statements.add(s);
    }
}
