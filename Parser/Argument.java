public class Argument {
    public Argument() {
    }
    
    public Argument(Object o) {
        
    }
    
    public Object run() {return null;}
    
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
        return "INTEGER: " + number;
    }
}


class aStackLocation extends Argument {
    int number;
    
    public aStackLocation(int n) {
        number = n;
    }
    
    public String toString() {
        return "STACKLOCATION: " + number;
    }
}

class FunctionCall extends Argument {
    private String name;
    
    public FunctionCall(String n) {
        name = n;
    }
    
    public String toString() {
        return "FUNCTIONCALL: " + name;
    }
}


