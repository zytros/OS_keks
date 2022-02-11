package assembly;

import java.util.ArrayList;

public class InstructionStream {
    Instruction[] instructions;
    long length;

    public InstructionStream(ArrayList<Instruction> instr){
        length = instr.size();
        instructions = new Instruction[(int) length];
        for(int i = 0; i < length; i++){
            instructions[i] = instr.get(i);
        }
    }
}
