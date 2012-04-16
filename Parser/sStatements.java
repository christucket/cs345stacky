import java.util.ArrayList;

class sReturn extends Statement {
    sStack arguments;
    
    public sReturn(sStack args) {
        arguments = args;
    }
    
    public String toString() {
        return "RETURN ";
    }
    
    public void run(Function parent) {
        System.out.println(" - return ");
    }
}

class sPush extends Statement {
    sStack arguments;
    
    public sPush(sStack args) {
        arguments = args;
    }
    
    public String toString() {
        return "PUSH " + arguments;
    }
    
    public void run(Function parent) {
        for (Argument a : arguments.stack) {
            if (a instanceof aInteger) {
                parent.stack.push(a);
            } else if (a instanceof FunctionCall) {
                parent.call(a.getName(), parent.stack);
            } else if (a instanceof OperatorCall) {
                if (OperatorCall.canRun(parent.stack)) {
                    parent.call(a.getName(), parent.stack);
                } else {
                    parent.stack.push(a);
                }
            }
        }
    }
}

class sWhile extends Statement {
    Expression expr;
    Block trueBlock;
    
    public sWhile(Expression e, Block tb) {
        expr = e;
        trueBlock = tb;
    }
    
    public String toString() {
        return "WHILE " ;
    }
    
    public void run(Function parent) {
        System.out.println(" - while ");
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
    
    public String toString() {
        return "IF ";
    }
    
    public void run(Function parent) {
        System.out.println(" - if ");
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
