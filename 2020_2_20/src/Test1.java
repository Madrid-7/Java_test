import java.util.*;

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

public class Test1 {

//2.复制带随机指针的链表
    public Node copyRandomList(Node head) {
        if(head == null) {
            return head;
        }
        Node cur = head;

        Map<Node,Node> map = new HashMap<>();
        while(cur != null) {
            Node node = new Node(cur.val);
            map.put(cur,node);
            cur = cur.next;
        }
        //
        cur = head;
        while(cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

//3.宝石与石头
    public int numJewelsInStones(String J, String S) {
        Set<Character> Jset = new HashSet();
        for (char j: J.toCharArray())
            Jset.add(j);

        int ans = 0;
        for (char s: S.toCharArray())
            if (Jset.contains(s))
                ans++;
        return ans;
    }

//4.旧键盘
    public void oldKbord() {
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        String str2 = sc.nextLine();
        char[] arr1 = str1.toUpperCase().toCharArray();
        char[] arr2 = str2.toUpperCase().toCharArray();

        Set<Character> broken = new HashSet<>();
        Set<Character> set = new HashSet<>();

        for (char ch : arr2) {
            set.add(ch);
        }
        for (char ch : arr1) {
            if(!set.contains(ch) && !broken.contains(ch)) {
                broken.add(ch);
                System.out.print(ch);
            }
        }
    }


}
