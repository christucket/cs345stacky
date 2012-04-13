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
        return stack.get(stack.size() - i);
    }
    
    public sStack splitLast(int n) {
        sStack newStack = new sStack();
        
        for (int i = stack.size() - n; i < stack.size(); i++) {
            newStack.push(getLast(i));
        }
        
        return newStack;
    }
    
    public String toString() {
        String ret = "[";
        for (Argument a : stack) {
            ret += a.toString() + ", ";
        }
        
        return ret + "]";
    }
}
