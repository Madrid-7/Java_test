class ListNode {
    int data;
    ListNode next;

    public ListNode(int data) {
        this.data = data;
        this.next = null;
    }
}

 class List {
    ListNode head;

    public void addFirst(int data) {
        ListNode tmp = new ListNode(data);
        if(this.head == null) {
            this.head = tmp;
        } else {
            tmp.next = this.head;
            this.head = tmp;
        }
    }

    public void printList() {
        ListNode tmp = this.head;
        while (tmp != null) {
            System.out.print(tmp.data+" ");
            tmp = tmp.next;
        }
        System.out.println();
    }

    public ListNode reverseList() {
        ListNode tmp = this.head;
        this.head = null;
        while (tmp != null) {
            addFirst(tmp.data);
            tmp = tmp.next;
        }
        return this.head;
    }
}
