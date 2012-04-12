import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Stacky {

    public static void main(String args[]) throws ParseException, IOException {
        // If there is a command line argument, interpret it as a stdin.
        InputStream inStream = System.in;
        if (args.length > 0 ) {
            inStream = new FileInputStream(args[0]);
        }

        Parser parser = new Parser(inStream);
        Program program = parser.Program();
        
        
        System.out.println("\n\n\n\n");
        
        //program.run();
        
        
        /*
        StaticTypeCheck staticTypeCheck = new StaticTypeCheck();
        
        staticTypeCheck.checkProgram(prog);
        
        prog.display();
        
        if (staticTypeCheck.getErrLogger().hasErrors()) {
            System.err.println("ABORTING EXECUTION DUE TO THE ERRORS");
        } else {
            Interpreter interpreter = new Interpreter(false);
            interpreter.runProgram(prog);
        }
        */
        
        /*
        System.out.println("\nBegin type checking . . .");
        System.out.println("\nType map:");
        TypeMap map = StaticTypeCheck.typing(prog.globals, prog.functions);
        map.display();
        StaticTypeCheck.V(prog);
        Semantics semantics = new Semantics( );
        State state = semantics.M(prog);
        System.out.println("\nFinal State");
        state.display( );
        */
    }

}
