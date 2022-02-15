package assembly;

import assembly.enums.ArgType;
import assembly.enums.ConditionCode;

import java.math.BigInteger;

public class AssemblyRunner {
    DataRegs regs = new DataRegs();
    Memory memory;
    public void execute(Instruction instr){

        long data1 = 18189;
        long data2 = 1784537534;
        long dest = 574;


        //TODO



        BigInteger a = BigInteger.valueOf(data1);
        BigInteger b = BigInteger.valueOf(data2);
        BigInteger bres;

        switch (instr.op){
            case add:
                dest = data1 + data2;
                bres = a.add(b);
                setFlags(bres, dest);
                break;
            case sub:
                dest = data1 - data2;
                bres = a.subtract(b);
                setFlags(bres, dest);
                break;
            case mul:
                dest = data1 * data2;
                bres = a.multiply(b);
                setFlags(bres, dest);
                break;
            case div:
                dest = data1 / data2;
                bres = a.divide(b);
                setFlags(bres, dest);
                break;
            case and:
                dest = data1 & data2;
                break;
            case or:
                dest = data1 | data2;
                break;
            case xor:
                dest = data1 ^ data2;
                break;
            case not:
                dest = ~data1;
                break;
            case sar:
                dest = data1 >> data2;
                bres = a.divide(BigInteger.valueOf((long)Math.pow(2,(double)data2)));
                setFlags(bres, dest);
                break;
            case shr:
                dest = data1 >>> data2;
                bres = a.divide(BigInteger.valueOf((long)Math.pow(2,(double)data2)));
                setFlags(bres, dest);
                break;
            case shl:
                dest = data1 << data2;
                bres = a.pow((int)data2);
                setFlags(bres, dest);
                break;
            case mov:
                dest = data1;
                break;
            case lea:
                dest = getData(instr.arg1);
                break;
            case push:
                //TODO
                break;
            case pop:
                //TODO
                break;
            case cmp:
                long res = data1 - data2;
                bres = a.subtract(b);
                if(res == 0){
                    regs.ZF = true;
                }else{
                    regs.ZF = false;
                }if(res < 0){
                    regs.SF = true;
                }else{
                    regs.SF = false;
                }
                int cmp1 = bres.compareTo(BigInteger.valueOf(Long.MIN_VALUE));
                int cmp2 = bres.compareTo(BigInteger.valueOf(Long.MAX_VALUE));
                if(cmp1 == -1 || cmp2 == 1){
                    regs.OF = true;
                }else {
                    regs.OF = false;
                }
                break;
            case call:
                //TODO
                break;
            case ret:
                //TODO
                break;
            case jcc:
                //TODO
                if (getConditionRes(ConditionCode.eq)){
                    regs.isp = data2;
                }
                break;
        }
    }

    void setFlags(BigInteger bres, long res){
        regs.ZF = res == 0;
        regs.SF = res < 0;
        int cmp1 = bres.compareTo(BigInteger.valueOf(Long.MIN_VALUE));
        int cmp2 = bres.compareTo(BigInteger.valueOf(Long.MAX_VALUE));
        regs.OF = cmp1 == -1 || cmp2 == 1;
    }

    long getData(Argument arg){
        return 0L;
    }

    void saveData(long val, Argument arg){
        switch (arg.argType){

        }
    }

    boolean getConditionRes(ConditionCode cc){
        switch (cc){
            case eq:
                if(regs.ZF){
                    return true;
                }else{
                    return false;
                }
            case neq:
                if(!regs.ZF){
                    return true;
                }else {
                    return false;
                }
            case lt:
                if (regs.SF ^ regs.OF){
                    return true;
                }else{
                    return false;
                }
            case le:
                if (regs.SF ^ regs.OF || regs.ZF){
                    return true;
                }else{
                    return false;
                }
            case gt:
                if (!(regs.SF ^ regs.OF || regs.ZF)){
                    return true;
                }else {
                    return false;
                }
            case ge:
                if (!(regs.SF ^ regs.OF)){
                    return true;
                }else {
                    return false;
                }
        }
        return false;

    }

    ConditionCode getCCFromArg(Argument arg){
        if (arg.argType == ArgType.condition){
            switch ((int) arg.data){
                case 1:
                    return ConditionCode.eq;
                case 2:
                    return ConditionCode.neq;
                case 3:
                    return ConditionCode.lt;
                case 4:
                    return ConditionCode.le;
                case 5:
                    return ConditionCode.gt;
                case 6:
                    return ConditionCode.ge;

            }
        }
        return null;
    }

    long getAddress(Argument arg){
        switch (arg.argType){
            case register:
                return readRegister(arg.data);
            case immediate:
                return arg.data;
            case indirect1:
                return memory.memory[(int)arg.data];
            case indirect2:
                return memory.memory[(int) readRegister(arg.data)];
            case indirect3:
                return memory.memory[(int)arg.data + (int) readRegister(arg.data2)];
            case condition:
                break;
        }

        return 0;
    }

    long readRegister(long regID){
        switch ((int) regID){
            case 0:
                return regs.rax;
            case 1:
                return regs.rbx;
            case 2:
                return regs.rcx;
            case 3:
                return regs.rdx;
            case 4:
                return regs.rsi;
            case 5:
                return regs.rdi;
            case 6:
                return regs.rbp;
            case 7:
                return regs.rsp;
            case 8:
                return regs.r8;
            case 9:
                return regs.r9;
            case 10:
                return regs.r10;
            case 11:
                return regs.r11;
            case 12:
                return regs.r12;
            case 13:
                return regs.r13;
            case 14:
                return regs.r14;
            case 15:
                return regs.r15;
            case 16:
                return regs.isp;
        }
        return 0;
    }
}
