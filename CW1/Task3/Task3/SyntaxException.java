// Do not modify the code below except for replacing the "..."!  Don't
// add anything (including "public" declarations), don't remove
// anything (including "public" declarations). Don't wrap it in a
// package, don't make it an innner class of some other class.  If
// your IDE suggsts to change anything below, ignore your IDE. You are
// welcome to add new classes! Please put them into separate files.

import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Field;

class SyntaxException extends Exception {
    public String msg;
    public SyntaxException ( String _msg ) { msg = _msg; } }

class Task3Exception extends Exception {
    public String msg;
    public Task3Exception ( String _msg ) { msg = _msg; } }

interface Parser {
    public Block parse ( List<Token> input ) throws SyntaxException, 
    Task3Exception; }

class Task3 {
    public static Parser create () { return new Parser(){
            public Block parse(List<Token> input){
                Block block = new Block(new ArrayList<Exp>());
                List<Exp> exps = new ArrayList<Exp>();
                while(!input.isEmpty()){
                    block = new Block(exps);
                    Token currToken = input.get(0);
                    if(currToken instanceof T_Skip){
                        exps.add(new Skip());
                    } else if(currToken instanceof T_Integer) {
                        //Field[] fields = currToken.getFields();
                        //Field field = currToken.getField("n");
                        //exps.add(new IntLiteral(currToken.get(n)));
                    } else {
                        exps.add(new BlockExp(block));
                    }
                    IntLiteral integer = new IntLiteral(0);
                    Skip skip = new Skip();
                    BlockExp blockexp = new BlockExp(block);
                    
                    input.remove(0);
                    parse(input);
                }
                return block;
            }
        }
        ;} 
}

