package assembly;

public class AssemblyRunner {
    DataRegs regs;
    public void execute(Instruction instr){

        long data1 = 0;
        long data2 = 0;
        long dest = 0;

        switch (instr.op){
            case add:
                dest = data1 + data2;
                break;
            case sub:
                dest = data1 - data2;
                break;
            case mul:
                dest = data1 * data2;
                break;
            case div:
                dest = data1 / data2;
                break;
            case and:
                dest = data1 & data2;
                break;
            case or:
                dest = data1 & data2
                break;
            case xor:

                break;
            case not:

                break;
            case sar:

                break;
            case sal:

                break;
            case shr:

                break;
            case shl:

                break;
            case mov:

                break;
            case lea:

                break;
            case push:

                break;
            case pop:

                break;
            case cmp:

                break;
            case call:

                break;
            case ret:

                break;
            case jcc:

                break;
        }
    }
}
