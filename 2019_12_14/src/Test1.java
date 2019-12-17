class Node {
    int val;
    Node next;

    Node(int val) {
        this.val = val;
        this.next = null;
    }
}
class MyLinkedList {

    Node head;
    int length = 0;

    /** Initialize your data structure here. */
    public MyLinkedList() {
        head = null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        Node tmp = head;
        if(index < 0 || index >= length)
            return -1;
        for(int i = 0; i < index; i++) {
            tmp = tmp.next;
        }
        return tmp.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node node = new Node(val);
        if(head == null) {
            head = node;
        } else {
            node.next = head;
            head = node;
        }
        length++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node = new Node(val);
        if(head == null) {
            head = node;
            length++;
            return;
        }
        Node tmp = head;
        while(tmp.next != null) {
            tmp = tmp.next;
        }
        tmp.next = node;
        length++;
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index == length) {
            addAtTail(val);
            return;
        } else if (index > length) {
            return;
        } else if (index <= 0) {
            addAtHead(val);
        } else {
            Node tmp = head;
            Node node = new Node(val);
            for(int i = 1; i < index; i++) {
                tmp = tmp.next;
            }
            node.next = tmp.next;
            tmp.next = node;
            length++;
        }

    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        Node tmp = head;
        if(index < 0 || index >= length)
            return ;
        if(index == 0) {
            head = head.next;
            return;
        }
        for(int i = 1; i < index; i++) {
            tmp = tmp.next;
        }
        if(length == 1) {
            this.head = null;
        } else {
            tmp.next = tmp.next.next;
        }
        length--;
    }
}
public class Test1 {
    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.addAtHead(1);
        list.addAtTail(3);
        list.addAtIndex(1,2);
        System.out.println(list.get(1));
        list.deleteAtIndex(0);
        System.out.println(list.get(0));

    }
}
