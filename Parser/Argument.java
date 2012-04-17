public class Argument {

    public Argument() {
    
    }
    
    public Argument(Object o) {
        
    }
    
    public Object run() {return null;}   
    
    public String getName() {
        return "NONE: ARGUMENTWTF";
    } 
}

class aInteger extends Argument {
    int number;
    
    public aInteger(int n) {
        number = n;
    }
    
    public Integer run() { return number; };
    
    public Integer toInteger() {
        return number;
    }
    
    public String toString() {
        return "INT: " + number;
    }
}


class aStackLocation extends Argument {
    int number;
    
    public aStackLocation(int n) {
        number = n;
    }
    
    public String toString() {
        return "STACKLOC: " + number;
    }
}

class FunctionCall extends Argument {
    private String name;
    
    public FunctionCall(String n) {
        name = n;
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return "FUNCCALL: " + name;
    }
}

class OperatorCall extends Argument {
    private String name;
    
    public OperatorCall(String n) {
        name = n;
    }
    
    public String getName() {
        return name;
    }
    
    public String toString() {
        return "OPCALL: " + name;
    }
    
    public static boolean canRun(sStack s) {
        boolean ret = false;
        
        if (s.size() > 1) {
            ret = ( s.getLast(0) instanceof aInteger && s.getLast(1) instanceof aInteger );
        }
        
        return ret;
    }
}


