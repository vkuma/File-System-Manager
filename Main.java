import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) 
        throws IOException,
        IllegalArgumentException,
        IllegalAccessException,
        InvocationTargetException
    {
   
        Scanner sc = new Scanner(System.in);  // Create a Scanner object
        
        System.out.println("Enter drive size in MB");
        int temp_driveSize = sc.nextInt();  // Read user input
        
        System.out.println("Enter block size in KB");
        int temp_blockSize = sc.nextInt();  // Read user input

        int driveSize = temp_driveSize*1024;
        int blockSize = temp_blockSize;

        System.out.println("SETTING DRIVE SIZE.... "+driveSize);
        System.out.println("SETTING BLOCK SIZE.... "+blockSize);
        
        // create memory manager object and initialize it with blockSize
        MemManager manager = new MemManager(blockSize, driveSize);

        // create client object and initialize it
        @SuppressWarnings("unused")
        UI client = new UI(manager);
        sc.close();
    }
}
