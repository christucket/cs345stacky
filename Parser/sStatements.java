import java.util.ArrayList;

class sCall extends Statement {
    ArrayList<Argument> arguments;
    
    public sCall(ArrayList<Argument> args) {
        arguments = args;
    }
}

class sReturn extends Statement {
    ArrayList<Argument> arguments;
    
    public sReturn(ArrayList<Argument> args) {
        arguments = args;
    }
}

class sPush extends Statement {
    ArrayList<Argument> arguments;
    
    public sPush(ArrayList<Argument> args) {
        arguments = args;
    }
}

class sWhile extends Statement {
    Expression expr;
    Block trueBlock;
    
    public sWhile(Expression e, Block tb) {
        expr = e;
        trueBlock = tb;
    }
}

class sIf extends Statement {
    Expression expr;
    Block trueBlock;
    Block falseBlock;
    
    public sIf(Expression e, Block tb, Block fb) {
        expr = e;
        trueBlock = tb;
        falseBlock = fb;
    }
}




/*

    private ArrayList<Object> returnStatement() {
        if (parentFunction.getSize() <= maxArgs) {
            parentFunction.reportError("[ERROR] Invalid number of arguments in return " + args + " stack: " + parentFunction.strStack());
        }
        if (parentFunction.halt()) return null;
         
        ArrayList<Object> ret = parentFunction.replaceArgs(args);
        System.out.println(parentFunction.getIndent() + "RETURNING: " + args + " -> " + ret);
        return ret;
    }
    
    private void whileStatement() {
        while (expression.isTrue()) {
            parentFunction.indent();
            for (Statement s : statements) {    
                if (parentFunction.halt()) return;
                s.run();
                System.out.println(parentFunction.getIndent() + "[" + parentFunction.getName() + "] stack: " + parentFunction.strStack());
            }
            parentFunction.dedent();
        }
    }
    
*/
