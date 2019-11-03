public class Main {
    public static void main(String[] args) {
        List list = new List();
        list.addFirst(2);
        list.addFirst(2);
        list.addFirst(2);
        list.printList();
        list.addIndex(2,5);
        list.removeAll(2);
        list.printList();
    }
}
