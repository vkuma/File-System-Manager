import java.util.ArrayList;
import java.util.Stack;


public class MemManager {
    // data files
    /**
     * memory pool array
     */
    private MemoryArray pool;
    /**
     * the freeblock list to track memory pool.
     */
    private FreeblockArray  list;

    /**
     * the initial block size.
     */
    private int blockSize;


    /**
     * Constructor to init the drivesize and blocksize to divide it
     * @param blockSize
     * @param driveSize
     */
    public MemManager(int blockSize, int driveSize) {
        // initialize the pool array.
        pool = new MemoryArray(blockSize, driveSize);

        // initialize the freeblock list, the position is initially 0.
        list = new FreeblockArray();

        this.blockSize = blockSize;

    }

    /**
     * Takes in the file size and creates a stack of chunks with the file size
     * broken into equal parts and the reaminder left
     * 
     * @param size
     * @return Stack<Integer> stack of equally sized blocks/chunks of data with a remainder
     */
    public Stack<Integer> blockgroups (int size) {
        Stack<Integer> stack = new Stack<Integer>();
        int equalGroups = size/blockSize;
        int remainder = size%blockSize;
        
        for (int i=0; i<equalGroups; i++) {
            stack.push(blockSize);
        }
        if(remainder>0) {stack.push(remainder);}
        
        return stack;
    }

    /**
     * Insert file using fileID and size
     * @param file
     * @param size
     * @return
     */
    public boolean insert(String file, int size) {
        boolean flag = false;
        // separate file size into equal chunks and the remainder into the last block
        Stack<Integer> chunks = blockgroups(size);

        // check if there is availible memory for the file od this size
        if (checkBlocksAvailible(chunks.size())) {
            ArrayList<Integer> blockArray = new ArrayList<Integer>();
            int poolSize = pool.getblockTotal();
            
            for(int i=0; i<poolSize; i++) {
                if(!chunks.isEmpty()) {
                    if (!list.checkMemUsed(i)) {
                        pool.store(chunks.pop(), i);
                        blockArray.add(i);
                    }
                } else {
                    list.addFirst(size, blockArray, file);
                    break;
                }
                
            }
            flag = true;
        }
        return flag;
    }

    /**
     * remove file based on fileID
     * @param file
     * @return
     */
    public boolean remove(String file) {
        boolean flag = false;
        // check if file exists before attempting to remove it
        if (list.checkNodeByfileName(file)) {
            ArrayList<Integer> blockArray = list.getblockArray(file);

            for (int i=0; i< blockArray.size(); i++) {
                // clear each individual block
                pool.clear(blockArray.get(i));
            }

            list.deleteNodeByfileName(file);
            flag = true;
        }
        return flag;
        
    }

    /**
     * check is there is enough memory block availible to store
     * @param blocksNeeded
     * @return
     */
    public boolean checkBlocksAvailible(int blocksNeeded) {
        int blockTotal = pool.getblockTotal();
        int blocksUsedTotal = list.getMemUsedTotal();
        return ( blocksNeeded<=(blockTotal - blocksUsedTotal) );
    }

    public void test(String file) {
        insert(file, 85);
        list.test(file);
    }

    /**
     * General print method I use to keep track after every insert/remove method
     */
    public void printing() {
        System.out.println( pool.print() );
        System.out.println( list.print() );
        // System.out.println( list.printNode("file1") );
    }

    /**
     * read file based on fileID
     * @param file
     * @return
     */
    public boolean read(String file) {
        boolean flag = false;
        if (list.checkNodeByfileName(file)) {
            System.out.println(list.readNodeByfileName(file));
            flag = true;
        }
        return flag;
    }

    /**
     * print linked list
     */
    public void printll() {
        System.out.println( 
            """
            Doubly Linked List:-
            ds==Data Size
            i==Data block Array(in reference to memory pool)
            name==file name
            """);
        System.out.println(list.print());
    }

    /**
     * print memory pool/array
     */
    public void printmemory() {
        System.out.println("Memory pool shows as integer array:-");
        System.out.println(pool.print());
    }

    public static void main(String a[]){
        MemManager attempt = new MemManager(30, 1024);
        attempt.insert("file1", 85);
        attempt.insert("file2", 73);
        attempt.printing();
        attempt.remove("file1");
        attempt.printing();
        attempt.insert("file3", 100);
        attempt.printing();
        attempt.remove("file2");
        attempt.printing();

        
    }
    
}