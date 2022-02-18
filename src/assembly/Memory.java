package assembly;

public class Memory {
    int size;
    long[] memory;

    public Memory(int size) {
        this.size = size;
        memory = new long[size];
    }
}
