// Do not modify the code below except for replacing the "..."!  Don't
// add anything (including "public" declarations), don't remove
// anything (including "public" declarations). Don't wrap it in a
// package, don't make it an innner class of some other class.  If
// your IDE suggsts to change anything below, ignore your IDE. You are
// welcome to add new classes! Please put them into separate files.

import java.util.List;

class Task3 {
    public static Codegen create () throws CodegenException { 
        return new Codegen()
        {
            public String codegen ( Program p ) throws CodegenException{
                List<Declaration> decls = p.decls;
                String program = "";
                for(int i = 0; i < decls.size(); i++){
                    genDecl gendecl = new genDecl();
                    program += gendecl.genDecl(decls.get(i));
                }
                program += "\tli $v0 10\n\tsyscall";
                return program;
            }
        };
    };
} 