import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());
        Node head = null;
        Node tail = null;
        for (int i = 0; i < n; i++) {
            int data = scanner.nextInt();
            Node newNode = new Node(data);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail = tail.next = newNode;
            }
        }
        int delete = scanner.nextInt();
        Node nodeP = head;
        Node nodeBefore = null;
        while (nodeP != null) {
            if (nodeP.data == delete) {
                if (nodeP == head) {
                    nodeP = head = head.next;
                } else {
                    nodeBefore.next = nodeP.next;
                    nodeP = nodeP.next;
                }
                n--;
            } else {
                nodeBefore = nodeP;
                nodeP=nodeP.next;
            }
        }
        System.out.println(n);
        for (Node node = head; node != null; node = node.next) {
            System.out.print(node.data + " ");

        }
    }
}

class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
    }
}