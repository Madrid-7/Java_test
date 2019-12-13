class Node {

    public Node prev;
    public int data;
    public Node next;

    public Node(Node prev, int data, Node next){
        this.prev = prev;
        this.data = data;
        this.next = next;
    }
}

public class MyLinkedList implements MyList {

    private Node first;
    private Node last;
    private int size;

    private void linkLast(int data) {
        Node l = last;
        Node newNode = new Node(l, data, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    private void rangeCheck(int index) throws BoxIndexOutOfBoundsException {
        if(index<0 || index>=size)
        {
            throw new BoxIndexOutOfBoundsException("Index"+index+",Size"+size);
        }
    }

    private Node findNode(int index){
        Node targetNode;
        //判断index范围是在链表前半部分还是后半部分
        if(index < (size >> 1)){
            //从前往后找比较快
            targetNode=first;
            for(int i=0;i<=index;i++){
                targetNode = targetNode.next;
            }
        }else{
            //从后往前找比较快
            targetNode=last;
            for(int i=size-1;i>=index;i--){
                targetNode = targetNode.prev;
            }
        }
        return targetNode;
    }
    private int unLink(Node targetNode){
        int oldValue = targetNode.data;
        Node prevNode = targetNode.prev;
        Node nextNode = targetNode.next;

        if(prevNode==null){
            first = nextNode;
        }else{
            prevNode.next = nextNode;
            targetNode.prev = null;
        }

        if(nextNode==null){
            last = prevNode;
        }else{
            nextNode.prev = prevNode;
            targetNode.next = null;
        }
        size--;

        return oldValue;
    }

    @Override

    public boolean add(int element) {
        this.linkLast(element);
        return true;
    }
    @Override
    public int get(int index) {
        try {
            this.rangeCheck(index);
        } catch (BoxIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        Node targetNode = this.findNode(index);
        return targetNode.data;
    }
    @Override
    public int remove(int index) {
        try {
            this.rangeCheck(index);
        } catch (BoxIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        Node targetNode = this.findNode(index);
        int oldValue = this.unLink(targetNode);
        return oldValue;
    }
    @Override
    public int size() {
        return size;
    }

    @Override
    public void show() {
        Node node=first;
        while(node.next!=null){
            System.out.print(node.data+"  ");
            node=node.next;
        }
        System.out.println(node.data+"  ");
    }

}
