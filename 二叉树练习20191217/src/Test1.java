
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Test1 {

    //二叉树先序非递归
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.empty()) {
            while (p != null) {
                stack.push(p);
                list.add(p.val);
                p = p.left;
            }
            p = stack.pop();
            p = p.right;
        }
        return list;
    }


    //二叉树后序非递归
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        TreeNode q = null;
        while (p != null || !stack.empty()) {
            while (p != null) {
                stack.push(p);
                p = p.left;
            }
            p = stack.peek();
            if(p.right == null || p.right == q) {
                p = stack.pop();
                list.add(p.val);
                q = p;
                p = null;
            } else {
                p = p.right;
            }
        }
        return list;
    }
}


