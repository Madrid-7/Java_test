public class Main {
    public static void main(String[] args) {
        BothSidesList list1 = new BothSidesList();
        list1.addLast(1);
        list1.addLast(3);
        list1.addLast(5);
        list1.display();
        list1.addIndex(0,0);
        list1.display();
        list1.addIndex(4,6);
        list1.display();
        list1.addIndex(2,2);
        list1.display();
        list1.remove(6);
        list1.remove(0);
        list1.display();
        list1.addFirst(1);
        list1.removeAll(1);
        list1.display();
    }
}
