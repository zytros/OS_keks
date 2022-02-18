package assembly;


import assembly.enums.ArgType;
import assembly.enums.Operation;

import java.io.File;

public class AssemblySimulator {
    File file = new File("");

    long[] data = new long[100];
    InstructionStream instructionStream;
    public static void main(String[] args) {
        Memory mem = new Memory(1024);
        DataRegs regs = new DataRegs();
        AssemblyRunner assemblyRunner = new AssemblyRunner(regs, mem);
        assemblyRunner.execute(new Instruction(Operation.mov,new Argument(ArgType.immediate, 100), new Argument(ArgType.indirect1, 500)));  //mov $100 ($500)
        assemblyRunner.execute(new Instruction(Operation.mov,new Argument(ArgType.immediate, 50), new Argument(ArgType.register, 0)));      //mov $50 %rax
        assemblyRunner.execute(new Instruction(Operation.shl,new Argument(ArgType.indirect1, 500), new Argument(ArgType.immediate, 2)));  //shl ($500) 2
        System.out.println(mem.memory[500]);
    }

    public void run(){

    }
}
