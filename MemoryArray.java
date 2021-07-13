
import java.util.Arrays;


public class MemoryArray {
    
    private int[] pool;
    
    private int blockSize;

    private int driveSize;

    private int blockTotal;

    // ----------------------------------------------------------
    /**
     * Create a new MemoryPool object and initial with blockSize.
     *
     * @param blockSize of the pool array
     * @param driveSize of whole pool
     */
    public MemoryArray(int blockSize, int driveSize) {
        this.blockSize = blockSize;
        this.driveSize = driveSize;
        this.blockTotal = driveSize/blockSize;
        pool = new int[this.blockTotal];
    }


    public int getblockSize() {
        return blockSize;
    }

    public int getdriveSize() {
        return driveSize;
    }

    public int getblockTotal() {
        return blockTotal;
    }

    public int[] getpool() {
        return pool;
    }

    public void store(int data, int position) {
        pool[position] = data;
    }

    public void clear(int position) {
        pool[position] = 0;
    }
    
    public int read(int position) {
        return pool[position];
    }

    public String print() {
        return Arrays.toString(pool);
    }


}