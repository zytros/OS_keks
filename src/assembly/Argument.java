package assembly;

import assembly.enums.ArgType;

public class Argument {
    ArgType argType;
    long data1;
    long data2;
    long data3;
    long data4;

    public Argument(ArgType argType, long data1) {
        this.argType = argType;
        this.data1 = data1;
    }

    public Argument(ArgType argType, long data1, long data2) {
        this.argType = argType;
        this.data1 = data1;
        this.data2 = data2;
    }

    public Argument(ArgType argType, long data1, long data2, long data3) {
        this.argType = argType;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
    }

    public Argument(ArgType argType, long data1, long data2, long data3, long data4) {
        this.argType = argType;
        this.data1 = data1;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
    }
}
