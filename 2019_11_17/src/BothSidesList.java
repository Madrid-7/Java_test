class ListNode {
    int data;
    ListNode next;
    ListNode last;
    ListNode (int data)
    {
        this.data = data;
    }
}

class BothSidesList {
    public ListNode head;
    public ListNode last;

    //头插法
    public void addFirst(int data) {
        ListNode tmp = new ListNode(data);
        if (this.head == null) {
            this.head = tmp;
            this.last = tmp;
        } else {
            tmp.next = this.head;
            this.head.last = tmp;
            this.head = tmp;
        }
    }

    //尾插法
    public void addLast(int data) {
        ListNode tmp = new ListNode(data);
        if (this.head == null) {
            this.head = tmp;
            this.last = tmp;
        } else {
            this.last.next = tmp;
            tmp.last = this.last;
            this.last = tmp;
        }
    }

    public void display() {
        ListNode tmp = this.head;
        while (tmp != null) {
            System.out.print(tmp.data +" ");
            tmp = tmp.next;
        }
        System.out.print("\n");
    }

    //得到单链表的长度
    public int size() {
        int count = 0;
        ListNode tmp = this.head;
        while (tmp != null) {
            tmp = tmp.next;
            count++;
        }
        return count;
    }

    //任意位置插入,第一个数据节点为0号下标
    public boolean addIndex(int index,int data) {
        int len = size();
        if (index < 0 && index > len) {
            return false;
        } else if (index == 0) {
            addFirst(data);
            return true;
        } else if (index == len) {
            addLast(data);
            return true;
        }
        ListNode cur = this.head;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        ListNode tmp = new ListNode(data);
        tmp.last = cur.last;
        tmp.next = cur;
        cur.last.next = tmp;
        cur.last = tmp;
        return true;
    }

    //删除第一次出现关键字为key的节点
    public void remove(int key) {
        ListNode tmp = this.head;
        while (tmp != null) {
            if (tmp.data == key) {
                if (tmp == this.head) {
                    this.head = this.head.next;
                    this.head.last = null;
                } else if (tmp == this.last) {
                    this.last = this.last.last;
                    this.last.next = null;
                } else {
                    tmp.last.next = tmp.next;
                    tmp.next.last = tmp.last;
                }
                return;
            }
            tmp = tmp.next;
        }
    }

    public void removeAll(int key) {
        ListNode tmp = this.head;
        while (tmp != null) {
            if (tmp.data == key) {
                if (tmp == this.head) {
                    this.head = this.head.next;
                    this.head.last = null;
                } else if (tmp == this.last) {
                    this.last = this.last.last;
                    this.last.next = null;
                } else {
                    tmp.next.last = tmp.last;
                    tmp.last.next = tmp.next;

                }
            }
            tmp = tmp.next;
        }
    }

}
