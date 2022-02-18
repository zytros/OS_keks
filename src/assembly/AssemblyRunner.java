package assembly;

import assembly.enums.ArgType;
import assembly.enums.ConditionCode;

import java.math.BigInteger;

public class AssemblyRunner {
    DataRegs regs;
    Memory memory;

    public AssemblyRunner(DataRegs regs, Memory memory) {
        this.regs = regs;
        this.memory = memory;
    }

    public void execute(Instruction instr){

        boolean save = true;

        long data1 = getData(instr.arg1);
        long data2 = getData(instr.arg2);
        long dest = 0L;

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
                saveData(dest, instr.arg1);
                save = false;
                break;
            case mov:
                dest = data1;
                break;
            case lea:
                dest = lea(instr.arg1);
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
        if (save) {
            saveData(dest, instr.arg2);
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
        switch (arg.argType){
            case immediate:
            case condition:
                return arg.data1;
            case register:
                return readRegister(arg.data1);
            case indirect1:
                return memory.memory[(int)arg.data1];
            case indirect2:
                return memory.memory[(int) readRegister(arg.data1)];
            case indirect3:
                return memory.memory[(int)arg.data1 + (int) readRegister(arg.data2)];
            case noarg:
                return 0L;
        }
        return 0L;
    }

    void saveData(long val, Argument arg){
        switch (arg.argType){
            case register:
                saveToReg(val, arg.data1);
                break;
            case indirect1:
            case indirect2:
            case indirect3:
                memory.memory[(int)lea(arg)] = val;
                break;
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
            switch ((int) arg.data1){
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

    long lea(Argument arg){
        switch (arg.argType){
            case indirect1:
                return arg.data1;
            case indirect2:
                return readRegister(arg.data1);
            case indirect3:
                return arg.data1 + readRegister(arg.data2);
        }
        return 0L;
    }

    void saveToReg(long val, long reg){
        switch ((int) reg){
            case 0:
                regs.rax = val;
            case 1:
                regs.rbx = val;
            case 2:
                regs.rcx = val;
            case 3:
                regs.rdx = val;
            case 4:
                regs.rsi = val;
            case 5:
                regs.rdi = val;
            case 6:
                regs.rbp = val;
            case 7:
                regs.rsp = val;
            case 8:
                regs.r8 = val;
            case 9:
                regs.r9 = val;
            case 10:
                regs.r10 = val;
            case 11:
                regs.r11 = val;
            case 12:
                regs.r12 = val;
            case 13:
                regs.r13 = val;
            case 14:
                regs.r14 = val;
            case 15:
                regs.r15 = val;
            case 16:
                regs.isp = val;
        }
    }
}
