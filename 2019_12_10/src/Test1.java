class MyListQueue {
    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
        }
    }
    public Node front;
    public Node rear;
    public int usedSize = 0;

    public boolean isEmpty() {
        if(usedSize == 0)
            return true;
        return false;
    }
    //入队
    public void offer(int data) {
        if (isEmpty()) {
            this.rear = new MyListQueue.Node(data);
            this.front = rear;
        } else {
            this.rear.next = new MyListQueue.Node(data);
            rear = rear.next;
        }
        usedSize++;
    }

}
public class Test1 {
}
