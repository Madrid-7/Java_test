public class Main {
    public static void main(String[] args) {
        List list1 = new List();
        list1.addFirst(2);
        list1.addFirst(2);
        list1.addFirst(1);
        list1.addFirst(3);
        list1.addFirst(4);
        list1.addFirst(4);
        list1.printList();
//        list1.head = list1.partition(4);
        list1.head = list1.deleteDuplication();
        list1.printList();
    }
}
