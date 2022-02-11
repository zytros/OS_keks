package assembly;

import java.math.BigInteger;

public class AssemblyRunner {
    DataRegs regs = new DataRegs();
    //Memory memory;
    public void execute(Instruction instr){

        long data1 = 18189;
        long data2 = 1784537534;
        long dest = 574;

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
                //TODO
                break;
            case lea:
                //TODO
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
                break;
        }
    }

    void setFlags(BigInteger bres, long res){
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
    }
}
