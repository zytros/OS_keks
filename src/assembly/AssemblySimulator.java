package assembly;


import assembly.enums.ArgType;
import assembly.enums.Operation;

import java.io.File;

public class AssemblySimulator {
    File file = new File("");

    long[] data = new long[100];
    InstructionStream instructionStream;
    public static void main(String[] args) {
        System.out.println(Operation.add);
        AssemblyRunner assemblyRunner = new AssemblyRunner();
        assemblyRunner.execute(new Instruction(Operation.cmp,new Argument(ArgType.immediate, 0L), new Argument(ArgType.immediate, 0L)));
    }

    public void run(){

    }
}
