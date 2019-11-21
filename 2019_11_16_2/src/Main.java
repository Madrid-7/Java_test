public class Main {
    public static void main(String args[]) {
        List list1 = new List();
        List list2 = new List();
        list1.addFirst(9);
        list1.addFirst(7);
        list1.addFirst(5);
        list1.addFirst(3);
        list1.addFirst(1);
        list1.printList();
        list2.addFirst(8);
        list2.addFirst(6);
        list2.addFirst(4);
        list2.addFirst(2);
        list2.printList();
        ListMake listMake = new ListMake();
        ListNode node = listMake.mergeTwoLists(list1.head, list2.head);
        listMake.print(node);
    }
}
