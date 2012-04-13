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
        
        for (Statement s : statements) {
            parentFunction.parentProgram.debug("STACK: " + parentFunction.stack.toString());
            parentFunction.parentProgram.debug(s.toString());
            s.run();
        }
        
        parentFunction.parentProgram.indent(-4);
        return new sStack();
    }
    
    public void add(Statement s) {
        statements.add(s);
    }
}
