import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Teque {
    private static Node first = null;
    private static Node last = null;
    private static int size = 0;
    Teque(){}

    public static void main(String[] args) {
        try {
            File file = new File(args[0]);
            Scanner scanner = new Scanner(file);
            int commandNumber = scanner.nextInt();
            scanner.nextLine();

            for (int i = 0; i < commandNumber; i++) {
                String[] lin = scanner.nextLine().trim().split(" ");
                String command = lin[0];
                int n = Integer.parseInt(lin[1]);
                if (command.equals("get")) {
                    System.out.println(get(n).get());
                } else if (command.equals("push_front")) {
                    Node no = new Node(n);
                    push_front(no);
                } else if (command.equals("push_back")) {
                    Node no = new Node(n);
                    push_back(no);
                } else if (command.equals("push_middle")){
                    Node no = new Node(n);
                    push_middle(no);
                }

            }
        } catch(FileNotFoundException e) {
            System.out.println(e);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("File name required");
        }

    }

    static class Node {
        private Node next;
        private int data;
        Node(int d){
            data = d;
        }
        public void setNext(Node n){
            next = n;
        }

        public int get(){
            return data;
        }

        public Node getNext(){
            return next;
        }
    }

    public static Node get(int n){
        Node current = first;
        for (int i = 0; i<n; i++) {
            current = current.getNext();
        }
        return current;
    }

    public static void push_front(Node n){
        if (last == null) {
            last = n;
        }
        if (size > 0) {
            n.setNext(first);
        }
        first = n;

        size++;
    }

    public static void push_back(Node n){
        if (size == 0) {
            first = n;
        } else {
            last.setNext(n);
        }
        last = n;
        size++;
    }

    public static void push_middle(Node n){
        if (size == 0) {
            first = n;
            last = n;
        } else if (size == 1) {
            last = n;
        }

        int place = (size+1)/2;
        Node currentMiddle = get(place-1);
        n.setNext(currentMiddle.getNext());
        currentMiddle.setNext(n);

        size++;
    }
}
