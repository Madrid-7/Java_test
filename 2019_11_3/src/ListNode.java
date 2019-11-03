 class ListNode {
    public int data;
    public ListNode next;
    public ListNode(int data) {
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
        ListNode node = new ListNode(data);
        if (this.head == null) {
            this.head = node;
        } else {
            node.next = this.head;
            this.head = node;
        }
    }

    public void addLast(int data) {
        ListNode node = new ListNode(data);
        if (this.head == null) {
            this.head =node;
        } else {
            ListNode prev = this.head;
            while (prev.next != null) {
                prev = prev.next;
            }
            prev.next = node;
        }
    }

    public void printList() {
        ListNode prve = this.head;
        while (prve != null) {
            System.out.print(prve.data);
            prve = prve.next;
        }
        System.out.println();
    }

    private ListNode searchIndex(int index) {
        ListNode prev = this.head;
        for (int i = 0; i < index - 1; i++) {
            if(prev == null) {
                return null;
            }
            prev = prev.next;
        }
        return prev;
    }
    public boolean addIndex(int index, int data) {
        if(index == 0) {
            addFirst(data);
            return true;
        } else {
            ListNode prev = searchIndex(index);
            if(prev == null) {
                return false;
            }
            ListNode node = new ListNode(data);
            node.next = prev.next;
            prev.next = node;
            return true;
        }
    }

    private ListNode searchPrev(int key) {
        ListNode prev = this.head;
        while (prev.next != null) {
            if(prev.next.data == key) {
                return prev;
            }
            prev = prev.next;
        }
        return null;
    }
    //删除第一次出现关键字为key的节点
    public void remove(int key) {
        if(this.head.data == key) {
            head = head.next;
        } else {
            ListNode prev = searchPrev(key);
            if(prev!=null) {
                prev.next = prev.next.next;
            } else {
                System.out.println("error");
            }
        }

        //1、删除的节点如果是头结点
        //2、找到删除的节点的前驱  如果找不到  返回null
        //3、进行删除
    }

    public void removeAll(int key) {
        while (this.head != null && this.head.data == key) {
            head = head.next;
        }
        ListNode prve = searchPrev(key);
        while (prve != null) {
            prve.next = prve.next.next;
            prve = searchPrev(key);
        }
    }
}
