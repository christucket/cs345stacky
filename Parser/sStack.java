import java.util.ArrayList;

public class sStack {

    private ArrayList<Argument> stack;

    public sStack() {
    
    }
    
    public int size() {
        return stack.size();
    }
    
    public void push(Argument arg) {
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
