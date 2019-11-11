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

    private ListNode searchPrev(int key) {
        ListNode prev = this.head;
        while (prev.next != null) {
            if (prev.next.data == key) {
                return prev;
            }
            prev = prev.next;
        }
        return null;
    }

    public void remove(int key) {
        if (this.head.data == key) {
            head = head.next;
        } else {
            ListNode prev = searchPrev(key);
            if (prev != null) {
                prev.next = prev.next.next;
            } else {
                System.out.println("error");
            }
        }
    }

    //以x为基准分割链表
    public ListNode partition(int x) {
        ListNode bs = null;
        ListNode be = null;
        ListNode as = null;
        ListNode ae = null;
        ListNode cur = this.head;
        while (cur != null) {
            if (cur.data < x) {
                //是不是第一次插入
                if (bs == null) {
                    bs = cur;
                    be = bs;
                } else {
                    be.next = cur;
                    be = be.next;
                }
            } else {
                //是不是第一次插入
                if (as == null) {
                    as = cur;
                    ae = as;
                } else {
                    ae.next = cur;
                    ae = ae.next;
                }
            }
            cur = cur.next;
        }
        if(ae != null){
            ae.next = null;
        }
        if(bs != null) {
            be.next = as;
        } else {
            bs = as;
        }
        return bs;
    }

    public ListNode deleteDuplication(){
        ListNode cur = this.head;
        ListNode newHead = new ListNode(-1);
        ListNode tmp = newHead;
        while (cur != null) {
            //重复的节点
            if(cur.next != null && cur.data == cur.next.data) {
                while (cur.next != null && cur.next.data == cur.data)
                    cur = cur.next;
                cur = cur.next;
            }else {
                tmp.next = cur;
                tmp = tmp.next;
                cur = cur.next;
            }
        }
        tmp.next = null;
        return newHead.next;
    }

    public boolean chkPalindrome() {
        //1、找到单链表的中间节点
        ListNode fast = this.head;
        ListNode slow = this.head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        //2、反转单链表
        ListNode cur = slow.next;
        while (cur != null) {
            ListNode curNext = cur.next;
            cur.next = slow;
            slow = cur;
            cur = curNext;
        }
        //3、fast/slow往前    head往后走

    }


    /*反转单链表
    public ListNode reverseList() {
        ListNode prev = null;
        ListNode newHead = null;
        ListNode cur = this.head;
        while (cur != null) {
            ListNode curNext = cur.next;
            if(curNext == null) {
                newHead = cur;
            }
            cur.next = prev;
            prev = cur;
            cur = curNext;
        }
        return newHead;
    }
    public void display2(ListNode newHead){
        if(newHead == null) {
            return;
        }
        ListNode cur = newHead;
        while (cur != null) {
            System.out.print(cur.data+" ");
            cur = cur.next;
        }
        System.out.println();
    }

    public ListNode middleNode() {
        /*int len = getLength()/2;
        ListNode cur = this.head;
        for (int i = 0; i < len; i++) {
            cur = cur.next;
        }
        return cur;*./
        ListNode fast = this.head;
        ListNode slow = this.head;
        while (fast!=null && fast.next!=null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }
    //
    public ListNode findKthToTail1(int k) {
        if(k <= 0 ||k > getLength()) {
            return null;
        }
        ListNode fast = this.head;
        ListNode slow = this.head;
        while (k-1 > 0) {
            fast = fast.next;
            k--;
        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }
    public ListNode findKthToTail(int k) {
        if(k <= 0) {
            return null;
        }
        ListNode fast = this.head;
        ListNode slow = this.head;
        while (k-1 > 0) {
            if(fast.next != null) {
                fast = fast.next;
                k--;
            }else {
                System.out.println("没有这个节点");
                return null;
            }

        }
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }
        return slow;
    }

     */
}
