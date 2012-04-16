import java.util.ArrayList;
import java.util.Iterator;

public class sStack {
    public ArrayList<Argument> stack;

    public sStack() {
        stack = new ArrayList<Argument>();
    }
    
    public int size() {
        return stack.size();
    }
    
    public void push(Argument arg) {
        if (arg != null)
            stack.add(arg);
    }
    
    
    public Argument getLast(int i) {
        // get last arguments in the stack, 0 is the last.
        return stack.get(stack.size() - i - 1);
    }
    
    public sStack splitLast(int n) {
        sStack newStack = new sStack();
        
        for (int i = n - 1; i >= 0; i--) {
            newStack.push(getLast(i));
        }
        
        return newStack;
    }
    
    public void popLast(int n) {
        stack.subList(stack.size() - n, stack.size()).clear();
    }
    
    public String toString() {
        String ret = "[";
        
        for (Argument a : stack) {
            ret += a.toString() + ", ";
        }
        
        if (ret.length() > 2) ret = ret.substring(0, ret.length() - 2);
        return ret + "]";
    }
}
