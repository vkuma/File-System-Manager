import java.util.*;
import java.util.Arrays;
 
import java.util.NoSuchElementException;
 
/**
 * FreeblockArray is a Doubly Linked List used to keep track of the used memory
 * related to the MemoryArray
 */
public class FreeblockArray {
 
    private Node head;
    private Node tail;
    private int size;

    // keeps a list of all the blocks in MemoryArray that are currently in use
    private ArrayList<Integer> usedMem;
     
    /**
     * constructor
     */
    public FreeblockArray() {
        size = 0;
        usedMem = new ArrayList<Integer>();
    }

    /**
     * The node class used for the Doubly Linked List of the FreeblockArray class
     * has the fields for the following reason
     * file - name of each file
     * dataSize = total data size of each file
     * blockArray - used to store the indicies that are inuse to store the file contents
     */
    private class Node {
        String file;
        int  dataSize;
        ArrayList<Integer>  blockArray;

        Node next;
        Node prev;
 
        /**
         * Constructor for node of each file
         * @param dataSize
         * @param blockArray
         * @param file
         * @param next
         * @param prev
         */
        public Node(int dataSize, ArrayList<Integer> blockArray, String file, Node next, Node prev) {
            this.dataSize = dataSize;
            this.blockArray = blockArray;
            this.file = file;
            
            this.next = next;
            this.prev = prev;
        }

        /**
         * basic toString method for each node
         */
        public String toString() {
            String str = "";
            str = "(" +dataSize+", "+ blockArray.toString() + "," + file + ")";
    
            return str;
        }

    }
    /**
     * returns the size of the linked list
     * @return size
     */
    public int size() { return size; }
     
    /**
     * return whether the list is empty or not
     * @return bool
     */
    public boolean isEmpty() { return size == 0; }


    /**
     * Check if element i is in the ArrayList usedMem
     * @param i
     * @return
     */
    public boolean checkMemUsed(int i) { 
        if (usedMem.isEmpty() || !usedMem.contains(i)) {
            return false;
        } else {
            return true;
        }
    }

    public int getMemUsedTotal() { 
        return usedMem.size();
    }
     
    /**
     * add node at the beginning of the linked list
     * @param dataSize
     * @param blockArray
     * @param file
     */
    public void addFirst(int dataSize, ArrayList<Integer> blockArray, String file) {
        Node tmp = new Node(dataSize, blockArray, file, head, null);
        
        if(head != null ) {head.prev = tmp;}
        head = tmp;
        if(tail == null) { tail = tmp;}
        size++;
        updateMem(blockArray, true);
        System.out.println("adding: "+file);
    }

    /**
     * update the blockArray of the class when a node is either removed or added
     * @param blockArray
     * @param add
     */
    public void updateMem(ArrayList<Integer> blockArray, boolean add) {
        // if null init, then add
        if (usedMem == null) { 
            usedMem = new ArrayList<Integer>();
            usedMem.addAll(blockArray);
        } 
        
        // check if node is being removed or added then update appropriately
        if (add) {
            usedMem.addAll(blockArray);
        } else {
            usedMem.removeAll(blockArray);
        }
    }

    /**
     * add node at the end of the list
     * @param dataSize
     * @param blockArray
     * @param file
     */
    public void addLast(int dataSize, ArrayList<Integer> blockArray, String file) {
         
        Node tmp = new Node(dataSize, blockArray, file, null, tail);
        if(tail != null) {tail.next = tmp;}
        tail = tmp;
        if(head == null) { head = tmp;}
        size++;
        updateMem(blockArray, true);
        System.out.println("adding: "+file);
    }
     
    /**
     * this method walks forward through the linked list
     */
    public void iterateForward(){
         
        System.out.println("iterating forward..");
        Node tmp = head;
        while(tmp != null){
            System.out.println(tmp.file);
            tmp = tmp.next;
        }
    }
     
    /**
     * this method walks backward through the linked list
     */
    public void iterateBackward(){
         
        System.out.println("iterating backword..");
        Node tmp = tail;
        while(tmp != null){
            System.out.println(tmp.file);
            tmp = tmp.prev;
        }
    }
     
    /**
     * this method removes element from the start of the linked list
     * @return
     */
    public void removeFirst() {
        if (size == 0) throw new NoSuchElementException();
        Node tmp = head;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            head = head.next;
            head.prev = null;
            size--;
            
            System.out.println("deleted: "+tmp.file);
        }
        updateMem(tmp.blockArray, false);
        tmp = null;
    }
     
    /**
     * this method removes element from the end of the linked list
     */
    public void removeLast() {
        if (size == 0) throw new NoSuchElementException();
        Node tmp = tail;
        if (size == 1) {
            head = null;
            tail = null;
        } else {
            tail = tail.prev;
            tail.next = null;
            size--;
            
            System.out.println("deleted: "+tmp.file);
        }
        updateMem(tmp.blockArray, false);
        tmp = null;
    }

    /**
     * Check if node with file name exists
     * @param file
     * @return
     */
    public boolean checkNodeByfileName(String file) {
        if (size == 0) throw new NoSuchElementException();
        boolean flag = false;
        Node tmp = head;
        while(tmp != null){
            if (tmp.file.equals(file)) {
                flag = true;
                break;
            }
            tmp = tmp.next;
        }
        return flag;
    }

    /**
     * Given the file name return node
     * @param file
     * @return Node
     */
    public Node getNodeByfileName(String file) {
        if (size == 0) throw new NoSuchElementException();
        Node rNode = null;
        Node tmp = head;
        while(tmp != null){
            if (tmp.file.equals(file)) {
                rNode = tmp;
                break;
            }
            tmp = tmp.next;
        }
        return rNode;
    }

    /**
     * Delete node in list given the node as a param
     * @param del
     */
    public void deleteNode(Node del) {
        if (size == 0) throw new NoSuchElementException();

        // If node to be deleted is head node
        if (head == del) {
            removeFirst();
        } else if (tail == del) {   //if node to be deleted is tail node
            removeLast();
        } else {                    //if node is not head/tail
            Node tmp = head;
            while(tmp != del) {
                tmp = tmp.next;
            }
            tmp.prev.next = tmp.next;
            if (tmp.next != null) {
                tmp.next.prev = tmp.prev;
            }   
            updateMem(tmp.blockArray, false);     
            tmp = null;        
        }
    }

    /**
     * me for testing
     * @param file
     */
    public void test(String file) {
        ArrayList<Integer> test = getNodeByfileName(file).blockArray;
        System.out.println(test.size());
    }

    /**
     * Delete node from list given the file name
     * @param file
     */
    public void deleteNodeByfileName(String file) {
        Node del = getNodeByfileName(file);
        deleteNode(del);
    }

    /**
     * Given file name return string with node characteristics
     * @param file
     * @return
     */
    public String readNodeByfileName(String file) {
        Node red = getNodeByfileName(file);
        StringBuilder str = new StringBuilder();
        str.append(
            """
            Doubly Linked List:-
            \nds==Data Size
            \ni==Data block Array(in reference to memory pool)
            \nname==file name
            \n(ds, i, name)\n
            """);
        str.append(red.toString());
        return str.toString();
    }

    /**
     * Get method for node filed blovkArray
     * @param file
     * @return
     */
    public ArrayList<Integer> getblockArray(String file) {
        return getNodeByfileName(file).blockArray;
    }

    /**
     * get method for node field dataSize
     * @param file
     * @return
     */
    public int getdataSize(String file) {
        return getNodeByfileName(file).dataSize;
    }
 

    /**
     * print method for linked list
     * @return
     */
    public String print() {
        StringBuilder str = new StringBuilder();
        Node tmp = head;
        while(tmp != null){
            int  ds = tmp.dataSize;
            String i = tmp.blockArray.toString();
            String file = tmp.file;
            
            str.append("{ds:"+ds+" | i:"+i+" | name:"+file+"}");
            if (tmp.next != null) {str.append(" <--> ");}
            
            tmp = tmp.next;
        }

        return str.toString();
    }

    /**
     * print method for node based on file name
     * @param file
     * @return
     */
    public String printNode(String file) {
        return getNodeByfileName(file).toString();
    }
     
    public static void main(String a[]){
        ArrayList<Integer> a1 = new ArrayList<Integer>(Arrays.asList(1));
        ArrayList<Integer> a2 = new ArrayList<Integer>(Arrays.asList(2));
        ArrayList<Integer> a3 = new ArrayList<Integer>(Arrays.asList(3));
        ArrayList<Integer> a4 = new ArrayList<Integer>(Arrays.asList(4));
         
        FreeblockArray dll = new FreeblockArray();
        dll.addFirst(10, a1, "one");
        dll.addFirst(34, a2, "two");
        dll.addLast(56, a3, "three");
        dll.addLast(364, a4, "four");
        dll.iterateForward();
        dll.removeFirst();
        dll.removeLast();
        dll.iterateBackward();
    }
}