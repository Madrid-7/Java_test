class ListNode {
    public int data;
    public ListNode next;
    public ListNode (int data) {
        this.data = data;
        this.next = null;
    }
}

class List {
    public ListNode head;
    public List() {
        this.head = null;
    }

    public void addFirst(int data) {
        if(this.head == null) {
            this.head = new ListNode(data);
        } else {
            ListNode node = new ListNode(data);
            node.next = this.head;
            this.head = node;
        }
    }

    public void printList() {
        ListNode now = this.head;
        while (now != null) {
            System.out.print(now.data + " ");
            now = now.next;
        }
        System.out.println();
    }
}

class ListMake {
    public ListNode mergeTwoLists (ListNode headA, ListNode headB) {
        ListNode newHead = new ListNode(-1);
        ListNode tmp = newHead;
        while (headA != null && headB != null) {
            if(headA.data > headB.data) {
                tmp.next = headB;
                headB = headB.next;
                tmp = tmp.next;
            } else {
                tmp.next = headA;
                headA = headA.next;
                tmp = tmp.next;
            }
        }
        if(headA == null) {
            tmp.next = headB;
        } else {
            tmp.next = headA;
        }
        return newHead.next;
    }
    public void print(ListNode tmp) {
        while (tmp != null) {
            System.out.print(tmp.data +" ");
            tmp = tmp.next;
        }
        System.out.println();
    }
}