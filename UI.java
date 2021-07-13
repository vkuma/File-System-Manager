import java.util.Scanner;


public class UI {


    /**
     * memory manager to handle with the memory array and freeblock array.
     */
    private MemManager manager;


    /**
     * Simple console UI to invoke commands from the MemManager
     * @param manager
     */
    public UI(MemManager manager) {

        this.manager = manager;

        Scanner scanner = new Scanner(System.in);
        String command;
        System.out.println("----Please Enter Command: type \'help\' for command list");
        
        while (scanner.hasNext()) {
            command = scanner.nextLine();

            if (command.startsWith("insert")) {
                String[] content = command.split("\\s");
                if(content.length < 3) {
                    System.out.println("please enter all arguments");
                    continue;
                }
                commandInsert(content[1], Integer.parseInt(content[2]));
            } else if(command.startsWith("remove")) {
                String[] content = command.split("\\s");
                if(content.length < 2) {
                    System.out.println("please enter all arguments");
                    continue;
                }
                commandRemove(content[1]);
            } else if (command.startsWith("read")) {
                String[] content = command.split("\\s");
                if(content.length < 2) {
                    System.out.println("please enter all arguments");
                    continue;
                }
                commandRead(content[1]);
            } else if (command.startsWith("printll")) {
                commandPrintll();
            } else if (command.startsWith("printmemory")) {
                commandPrintmemory();
            } else if (command.startsWith("help")) {
                System.out.println(
                    """
                    Commands:--
                    \n\t> insert [file name] [size in KB]
                    \n\t> remove [file name]
                    \n\t> read [file name]
                    \n\t> printll --to print the Doubly LinkedList
                    \n\t> printmemory --to print memory array
                    \n\t> exit --to exit the application
                    """);
            } else if (command.startsWith("exit")) {
                scanner.close();
                break;
            }  else if (command.startsWith("test")) {
                commandTest();
                
            } else {
                System.out.println("Please enter valid command");
            }
        }
    }

    public void commandTest() {
        manager.test("file1");
    }

    /**
     * Insert file with size in KB
     * @param file
     * @param size
     */
    public void commandInsert(String file, int size) {
        if (manager.insert(file, size)) {
            manager.printing();
        } else {
            System.out.println("Insert failed, not enough space!");
        }
        
    }

    /**
     * Remove file based on fileID
     * @param file
     */
    public void commandRemove(String file) {
        if (manager.remove(file)) {
            manager.printing();
        } else {
            System.out.println("Remove failed, \'"+file+"\'' not found!");
        }
        
    }

    /**
     * read file based on fileID
     * @param file
     */
    public void commandRead(String file) {
        if (!manager.read(file)) {
            System.out.println("Read failed, \'"+file+"\'' not found!");
        }
    }

    /**
     * print linked list 
     */
    public void commandPrintll() {
        manager.printll();
    }

    /**
     * print memory pool
     */
    public void commandPrintmemory() {
        manager.printmemory();
    }

}