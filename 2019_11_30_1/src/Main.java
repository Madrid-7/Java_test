public class Main {
    public static void main(String[] args) {
        List list1 = new List();
        list1.addFirst(5);
        list1.addFirst(4);
        list1.addFirst(3);
        list1.addFirst(2);
        list1.addFirst(1);
        list1.printList();
        list1.reverseList();
        list1.printList();
    }
}
