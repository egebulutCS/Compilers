
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
            return "\tli $a" + entryid + " " + l.n + "\n";
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
                return genExp(i.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(i.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tbeq $a0 $t1 " + thenBranch + "\n" + elseBranch + ":\n" + genExp(i.elseBody) + "\tb " + exitLabel + "\n" + thenBranch + ":\n" + genExp(i.thenBody) + exitLabel + ":";
            }
            if(i.comp instanceof Less){
                String elseBranch = "elseBranch"+newLabel();
                String thenBranch = "thenBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(i.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(i.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tblt $t1 $a0 " + thenBranch + "\n" + elseBranch + ":\n" + genExp(i.elseBody) + "\tb " + exitLabel + "\n" + thenBranch + ":\n" + genExp(i.thenBody) + exitLabel + ":";
            }
            if(i.comp instanceof LessEq){
                String elseBranch = "elseBranch"+newLabel();
                String thenBranch = "thenBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(i.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(i.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tble $t1 $a0 " + thenBranch + "\n" + elseBranch + ":\n" + genExp(i.elseBody) + "\tb " + exitLabel + "\n" + thenBranch + ":\n" + genExp(i.thenBody) + exitLabel + ":";
            }
            if(i.comp instanceof Greater){
                String elseBranch = "elseBranch"+newLabel();
                String thenBranch = "thenBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(i.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(i.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tbgt $t1 $a0 " + thenBranch + "\n" + elseBranch + ":\n" + genExp(i.elseBody) + "\tb " + exitLabel + "\n" + thenBranch + ":\n" + genExp(i.thenBody) + exitLabel + ":";
            }
            if(i.comp instanceof GreaterEq){
                String elseBranch = "elseBranch"+newLabel();
                String thenBranch = "thenBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(i.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(i.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n\tbge $t1 $a0 " + thenBranch + "\n" + elseBranch + ":\n" + genExp(i.elseBody) + "\tb " + exitLabel + "\n" + thenBranch + ":\n" + genExp(i.thenBody) + exitLabel + ":";
            }
        }
        if(E instanceof Binexp){
            Binexp bin = (Binexp) E;
            if(bin.binop instanceof Plus){
                return genExp(bin.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(bin.r) + "\tlw $t1 4($sp)\n\tadd $a0 $t1 $a0\n\taddiu $sp $sp 4";
            }
            if(bin.binop instanceof Minus){
                return genExp(bin.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(bin.r) + "\tlw $t1 4($sp)\n\tsub $a0 $t1 $a0\n\taddiu $sp $sp 4";
            }
            if(bin.binop instanceof Times){
                return genExp(bin.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(bin.r) + "\tlw $t1 4($sp)\n\tmul $a0 $t1 $a0\n\taddiu $sp $sp 4";
            }
            if(bin.binop instanceof Div){
                return genExp(bin.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(bin.r) + "\tlw $t1 4($sp)\n\tdiv $t1 $a0\n\taddiu $sp $sp 4\n\tmflo $a0";
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
            return genExp(assign.e) + "\tsw $a0 " + offset + "($fp)\n";
        }
        if(E instanceof Skip){
            return "\tnop\n";
        }
        if(E instanceof Seq){
            Seq seq = (Seq) E;
            return genExp(seq.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(seq.r) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n";
        }
        if(E instanceof While){
            While loop = (While) E;
            if(loop.comp instanceof Equals){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(loop.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(loop.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n" + loopBranch + ":\n" + "\tbne $a0 $t1 " + exitLabel + "\n" + genExp(loop.body) + "\tb " + loopBranch + "\n" + exitLabel + ":";
            }
            if(loop.comp instanceof Less){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(loop.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(loop.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n" + loopBranch + ":\n" + "\tbge $t1 $a0 " + exitLabel + "\n" + genExp(loop.body) + "\tb " + loopBranch + "\n" + exitLabel + ":";
            }
            if(loop.comp instanceof LessEq){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(loop.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(loop.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n" + loopBranch + ":\n" + "\tbgt $t1 $a0 " + exitLabel + "\n" + genExp(loop.body) + "\tb " + loopBranch + "\n" + exitLabel + ":";
            }
            if(loop.comp instanceof Greater){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(loop.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(loop.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n" + loopBranch + ":\n" + "\tble $t1 $a0 " + exitLabel + "\n" + genExp(loop.body) + "\tb " + loopBranch + "\n" + exitLabel + ":";
            }
            if(loop.comp instanceof GreaterEq){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(loop.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(loop.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n" + loopBranch + ":\n" + "\tblt $t1 $a0 " + exitLabel + "\n" + genExp(loop.body) + "\tb " + loopBranch + "\n" + exitLabel + ":";
            }
        }
        if(E instanceof RepeatUntil){
            RepeatUntil r = (RepeatUntil) E;
            if(r.comp instanceof Equals){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(r.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(r.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n" + loopBranch + ":\n" + genExp(r.body) + "\tbeq $a0 $t1 " + exitLabel + "\n\tb " + loopBranch + "\n" + exitLabel + ":";
            }
            if(r.comp instanceof Less){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(r.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(r.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n" + loopBranch + ":\n" + genExp(r.body) + "\tblt $t1 $a0 " + exitLabel + "\n\tb " + loopBranch + "\n" + exitLabel + ":";
            }
            if(r.comp instanceof LessEq){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(r.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(r.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n" + loopBranch + ":\n" + genExp(r.body) + "\tble $t1 $a0 " + exitLabel + "\n\tb " + loopBranch + "\n" + exitLabel + ":";
            }
            if(r.comp instanceof Greater){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(r.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(r.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n" + loopBranch + ":\n" + genExp(r.body) + "\tbgt $t1 $a0 " + exitLabel + "\n\tb " + loopBranch + "\n" + exitLabel + ":";
            }
            if(r.comp instanceof GreaterEq){
                String loopBranch = "loopBranch"+newLabel();
                String exitLabel = "exitLabel"+newLabel();
                return genExp(r.l) + "\tsw $a0 0($sp)\n\taddiu $sp $sp -4\n" + genExp(r.r) + "\tlw $t1 4($sp)\n\taddiu $sp $sp 4\n" + loopBranch + ":\n" + genExp(r.body) + "\tbge $t1 $a0 " + exitLabel + "\n\tb " + loopBranch + "\n" + exitLabel + ":";
            }
        }
        if(E instanceof Break){
            return "\tbreak\n";
        }
        if(E instanceof Continue){
            return "\tjr $ra\n";
        }
        else {
            return "";
        }
    }

    public String newLabel(){
        String str = ""+entryid;
        entryid++;
        return str;
    }
}
