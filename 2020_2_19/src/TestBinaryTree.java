
public class TestBinaryTree {

    static class Node {
        public int key;
        public Node left;
        public Node right;

        public Node(int key) {
            this.key = key;
        }
    }

    public Node root = null;

    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
        } else {
            Node cur = root;
            Node parent = null;
            while (cur != null) {
                parent = cur;
                if (cur.key > key) {
                    cur = cur.left;
                } else if (cur.key < key) {
                    cur = cur.right;
                }
            }
            if (parent.key > key) {
                parent.left = new Node(key);
            } else if (parent.key < key) {
                parent.right = new Node(key);
            }
        }
    }

    //查找   key
    public Node search(int key) {
        Node cur = root;
        while (cur != null) {
            if(cur.key > key) {
                cur = cur.left;
            } else if (cur.key < key) {
                cur = cur.right;
            } else {
                return cur;
            }
        }
        return null;
    }

    public void remove(int key) {
        Node cur = root;
        Node parent = null;
        while (cur != null) {
            if(cur.key == key) {
                //找到了要删除的节点
                removeNode(parent,cur);
                return;
            }else if(cur.key < key) {
                parent = cur;
                cur = cur.right;
            }else {
                parent = cur;
                cur = cur.left;
            }
        }
    }
    public void removeNode(Node parent,Node cur){
        if (cur.left == null) {
            if (cur == root) {
                root = cur.right;
            } else if (cur == parent.left) {
                parent.left = cur.right;
            } else {
                parent.right = cur.right;
            }
        } else if (cur.right == null) {
            if (cur == root) {
                root = cur.left;
            } else if (cur == parent.left) {
                parent.left = cur.left;
            } else {
                parent.right = cur.left;
            }
        } else {
            Node goatParent = cur;
            Node goat = cur.right;
            while (goat.left != null) {
                goatParent = goat;
                goat = goat.left;
            }
            cur.key = goat.key;
            //cur.value = goat.value;
            if (goat == goatParent.left) {
                goatParent.left = goat.right;
            } else {
                goatParent.right = goat.right;
            }
        }
    }
}
