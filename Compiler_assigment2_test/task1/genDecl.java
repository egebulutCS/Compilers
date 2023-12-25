
/**
 * Write a description of class Gens here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class genDecl
{
    int entryid = 0;
    
    public String genDecl(Declaration d)
    {
        if(d.body instanceof Invoke){
            int sizeAR = (2+d.numOfArgs)*4;
            return d.id + "_entry:\n\tmove $fp $sp\n\tsw $ra 0($sp)\n\taddiu $sp $sp -4\n" + genExp(d.body) + "\n\tlw $ra 4($sp)\n\taddiu $sp $sp "+sizeAR+"\n\tlw $fp 0($sp)\n\tjr $ra\n";
        } else {
            return d.id + "_entry:\n" + genExp(d.body);
        }
    }

    public String genExp(Exp E)
    {
        if(E instanceof IntLiteral){
            IntLiteral l = (IntLiteral) E;
            return "\tli $a0 " + l.n + "\n";
        }
        if(E instanceof Variable){
            Variable v = (Variable) E;
            int offset = 4 * v.x;
            return "\tlw $a0 " + offset + "($fp)\n";
        }
        if(E instanceof If){
            If i = (If) E;
            if(i.comp instanceof Equals){
                String elseBranch = "elseBranch"+newLabel();
                String thenBranch = "thenBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(i.l) + "\n\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(i.r) + "\n\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tbeq $a0 $t1 " + thenBranch + "\n" + elseBranch + ":\n" + genExp(i.elseBody) + "\n\tb " + exitLabel + "\n" + thenBranch + ":\n" + genExp(i.thenBody) + "\n" + exitLabel + ":";
            }
            if(i.comp instanceof Less){
                String elseBranch = "elseBranch"+newLabel();
                String thenBranch = "thenBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(i.l) + "\n\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(i.r) + "\n\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tblt $a0 $t1 " + thenBranch + "\n" + elseBranch + ":\n" + genExp(i.elseBody) + "\n\tb " + exitLabel + "\n" + thenBranch + ":\n" + genExp(i.thenBody) + "\n" + exitLabel + ":";
            }
            if(i.comp instanceof LessEq){
                String elseBranch = "elseBranch"+newLabel();
                String thenBranch = "thenBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(i.l) + "\n\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(i.r) + "\n\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tble $a0 $t1 " + thenBranch + "\n" + elseBranch + ":\n" + genExp(i.elseBody) + "\n\tb " + exitLabel + "\n" + thenBranch + ":\n" + genExp(i.thenBody) + "\n" + exitLabel + ":";
            }
            if(i.comp instanceof Greater){
                String elseBranch = "elseBranch"+newLabel();
                String thenBranch = "thenBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(i.l) + "\n\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(i.r) + "\n\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tbgt $a0 $t1 " + thenBranch + "\n" + elseBranch + ":\n" + genExp(i.elseBody) + "\n\tb " + exitLabel + "\n" + thenBranch + ":\n" + genExp(i.thenBody) + "\n" + exitLabel + ":";
            }
            if(i.comp instanceof GreaterEq){
                String elseBranch = "elseBranch"+newLabel();
                String thenBranch = "thenBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(i.l) + "\n\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(i.r) + "\n\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tbge $a0 $t1 " + thenBranch + "\n" + elseBranch + ":\n" + genExp(i.elseBody) + "\n\tb " + exitLabel + "\n" + thenBranch + ":\n" + genExp(i.thenBody) + "\n" + exitLabel + ":";
            }
        }
        if(E instanceof Binexp){
            Binexp bin = (Binexp) E;
            if(bin.binop instanceof Plus){
                return genExp(bin.l) + "\n\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(bin.r) + "\n\tlw $t1 4($sp)\n\tadd $a0 $t1 $a0\n\taddiu $sp $sp 4";
            }
            if(bin.binop instanceof Minus){
                return genExp(bin.l) + "\n\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(bin.r) + "\n\tlw $t1 4($sp)\n\tsub $a0 $t1 $a0\n\taddiu $sp $sp 4";
            }
            if(bin.binop instanceof Times){
                return genExp(bin.l) + "\n\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(bin.r) + "\n\tlw $t1 4($sp)\n\tmult $a0 $t1 $a0\n\taddiu $sp $sp 4";
            }
            if(bin.binop instanceof Div){
                return genExp(bin.l) + "\n\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(bin.r) + "\n\tlw $t1 4($sp)\n\tdiv $a0 $t1\n\taddiu $sp $sp 4\n\tmflo $a0";
            }
        }
        if(E instanceof Invoke){
            Invoke inv = (Invoke) E;
            String code = "\tsw $fp 0($sp)\n\taddiu $sp $sp -4\n";
            for(int i = inv.args.size(); i > 0; i--){
                code += genExp(inv.args.get(i-1)) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n";
            }
            code += "\tjal "+inv.name+"_entry";
            return code;
        } 
        if(E instanceof Assign){
            Assign assign = (Assign) E;
            int offset = 4 * assign.x;
            return genExp(assign.e) + "\tlw $a0 " + offset + "($fp)\n";
        }
        if(E instanceof Skip){
            return "";
        }
        if(E instanceof Seq){
            
        }
        if(E instanceof While){
            While loop = (While) E;
            if(loop.comp instanceof Equals){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(loop.l) + "\n\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(loop.r) + loop + ":\n" + genExp(loop.body) + "\n\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tbeq $a0 $t1 " + exitLabel + "\n" + exitLabel + ":";
            }
            if(loop.comp instanceof Less){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(loop.l) + "\n\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(loop.r) + loop + ":\n" + genExp(loop.body) + "\n\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tbt $a0 $t1 " + exitLabel + "\n" + exitLabel + ":";
            }
            if(loop.comp instanceof LessEq){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(loop.l) + "\n\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(loop.r) + loop + ":\n" + genExp(loop.body) + "\n\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tble $a0 $t1 " + exitLabel + "\n" + exitLabel + ":";
            }
            if(loop.comp instanceof Greater){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(loop.l) + "\n\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(loop.r) + loop + ":\n" + genExp(loop.body) + "\n\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tbgt $a0 $t1 " + exitLabel + "\n" + exitLabel + ":";
            }
            if(loop.comp instanceof GreaterEq){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(loop.l) + "\n\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(loop.r) + loop + ":\n" + genExp(loop.body) + "\n\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tbge $a0 $t1 " + exitLabel + "\n" + exitLabel + ":";
            }
        }
        if(E instanceof RepeatUntil){
            
        }
        else {
            return "";
        }
        return "";
    }
    
    public String newLabel(){
        String str = ""+entryid;
        entryid++;
        return str;
    }
}
