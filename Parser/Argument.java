public class Argument {
    protected int type;
    
    // 1 - aInteger
    // 2 - Function / function
    // 3 - aStackLocation
    // 4 - aOperator
    // 5 - aCondition
    // 6 - FunctionCall / function call
    
    public Argument(int t) {
        type = t;
    }
    
    public Argument(Object o) {
        
    }
}

class aInteger extends Argument {
    int number;
    
    public aInteger(int n) {
        super(1);
        number = n;
    }
    
    public String toString() {
        return "INTEGER: " + number;
    }
}


class aStackLocation extends Argument {
    int number;
    
    public aStackLocation(int n) {
        super(3);
        number = n;
    }
    
    public String toString() {
        return "STACKLOCATION: " + number;
    }
}

class aOperator extends Argument {
    String operator;
    
    public aOperator(String op) {
        super(4);
        operator = op;
    }
    
    public String toString() {
        return "OPERATOR: " + operator;
    }
}

class aCondition extends Argument {
    String condition;
    
    public aCondition(String c) {
        super(5);
        condition = c;
    }
    
    public String toString() {
        return "CONDITION: " + condition;
    }
}


