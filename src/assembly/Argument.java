package assembly;

public class Argument {
    ArgType argType;
    long data;
    long data2;
    long data3;
    long data4;

    public Argument(ArgType argType, long data){
        this.argType = argType;
        this.data = data;

    }

    public Argument(ArgType argType, long data, long data2) {
        this.argType = argType;
        this.data = data;
        this.data2 = data2;
    }

    public Argument(ArgType argType, long data, long data2, long data3) {
        this.argType = argType;
        this.data = data;
        this.data2 = data2;
        this.data3 = data3;
    }

    public Argument(ArgType argType, long data, long data2, long data3, long data4) {
        this.argType = argType;
        this.data = data;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
    }
}
