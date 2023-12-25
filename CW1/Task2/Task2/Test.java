import java.util.ArrayList;
import java.util.List;


public class Test
{
    private Main m;
    public Test(){
        m = new Main();
        m.g();
        //m.f();
        
        LexicalException le = new LexicalException ( "" );
        Task2Exception te = new Task2Exception ( "" );
        
        try {
            Lexer l = Task2.create ();
            List<Token> print = l.lex("def f(x,y,z) = { if x == y then { z } else { 0 } }");
            for (Token n:print){
                System.out.println(n.toString());
            }
        }catch ( Exception e ) {System.out.println("Exception returned"+e);}
        System.out.println("\n\n\n\n\n");
        try {
            Lexer l = Task2.create ();
            List<Token> print = l.lex(";;{{{}}{{{ {{}}}} }}}}}}}}10 10 if if then then then else");
            for (Token n:print){
                System.out.println(n.toString());
            }
        }catch ( Exception e ) {System.out.println("Exception returned"+e);}
        System.out.println("\n\n\n\n\n");
        try {
            Lexer l = Task2.create ();
            List<Token> print = l.lex("65if");
            for (Token n:print){
                System.out.println(n.toString());
            }
        }catch ( Exception e ) {System.out.println("Exception returned"+e);}
    }
}
