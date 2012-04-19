import java.util.ArrayList;

public class Statement {
    protected String type;
    
    public Statement() {
    
    }
    public void run(Function parent) {
        System.out.println(" - statement ");
    }

}

class sReturn extends Statement {
    sStack arguments;
    
    public sReturn(sStack args) {
        arguments = args;
    }
    
    public String toString() {
        return "RETURN " + arguments;
    }
    
    public void run(Function parent) {
        // if proper sequence of stack locations
        sStack ret = new sStack();
        for (Argument a : arguments.stack) {
            if (a instanceof aInteger) {
                ret.push(parent.stack.getLast((Integer)a.run()));
            } else if (a instanceof FunctionCall) {
                ret.push(a);
            } else if (a instanceof OperatorCall) {
                ret.push(a);
            } else {
                System.out.println(" ### return Unknown argument type: " + a.toString());
            }
        }
        
        parent.hasReturned = true;
        parent.returnList = ret;
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
        boolean ran = true;
        for (Argument a : arguments.stack) {
            if (a instanceof aInteger) {
                parent.stack.push(a);
            } else if (a instanceof FunctionCall) {
                ran = parent.call(a.getName(), parent.stack);
                if (!ran) {
                    parent.stack.push(a);
                }
            } else if (a instanceof OperatorCall) {
                if (OperatorCall.canRun(parent.stack)) {
                    parent.call(a.getName(), parent.stack);
                } else {
                    parent.stack.push(a);
                }
            } else if (a instanceof MemoryCall) {
                if (((MemoryCall)a).getType().equals("to")) {
                    if (((MemoryCall)a).getIndex() == -1)
                    {
                        if (parent.stack.size() >= 2 && parent.stack.getLast(0) instanceof aInteger) {
                            parent.parentProgram.setMemory((Integer)(parent.stack.getLast(0).run()), parent.stack.getLast(1));
                            parent.stack.popLast(2);
                        } else {
                            parent.stack.push(a);
                        }
                    } else {
                        if (parent.stack.size() >= 1) {
                            parent.parentProgram.setMemory(((MemoryCall)a).getIndex(), parent.stack.getLast(0));
                            parent.stack.popLast(1);
                        } else {
                            parent.stack.push(a);
                        }
                    }
                } else {
                    if (((MemoryCall)a).getIndex() == -1)
                    {
                        if (parent.stack.size() >= 1 && parent.stack.getLast(0) instanceof aInteger) {
                            Argument arg = parent.parentProgram.getMemory((Integer)(parent.stack.getLast(0).run()));
                            parent.stack.popLast(1);
                            sStack s = new sStack(); s.push(arg);
                            new sPush(s).run(parent);
                        } else {
                            parent.stack.push(a);
                        }
                    } else {
                        Argument arg = parent.parentProgram.getMemory(((MemoryCall)a).getIndex());
                        sStack s = new sStack(); s.push(arg);
                        new sPush(s).run(parent);
                    }
                }
            } else {
                System.out.println(" ### push Unknown argument type: " + a.toString());
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
        return "WHILE " + expr;
    }
    
    public void run(Function parent) {
        while (expr.run(parent)) {
            trueBlock.run(parent);
        }
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
        return "IF " + expr;
    }
    
    public void run(Function parent) {
        if (expr.run(parent)) {
            parent.parentProgram.debug("Running true statements.");
            trueBlock.run(parent);
        } else {
            parent.parentProgram.debug("Running false statements.");
            falseBlock.run(parent);
        }
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
