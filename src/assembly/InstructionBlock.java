package assembly;

public class InstructionBlock {
    String name;    //"Label"
    Instruction[] instr;
    int length;

    public InstructionBlock(String name, Instruction[] instr) {
        this.name = name;
        this.instr = instr;
        this.length = instr.length;
    }
}
