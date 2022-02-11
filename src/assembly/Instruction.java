package assembly;

public class Instruction {
    Operation op;
    Argument arg1;
    Argument arg2;

    public Instruction(Operation op, Argument arg1, Argument arg2) {
        this.op = op;
        this.arg1 = arg1;
        this.arg2 = arg2;
    }
}
