import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;


//Definition for a binary tree node.
class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

class Solution {

    //二叉树层序遍历（广度优先）
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if(root == null) {
            return new ArrayList<>();
        }
        queue.offer(root);
        while(!queue.isEmpty()) {
            int count = queue.size();
            List<Integer> list1 = new ArrayList<>();
            for(int i = 0; i < count; i++) {
                TreeNode p = queue.poll();
                list1.add(p.val);
                if(p.left != null) {
                    queue.offer(p.left);
                }
                if(p.right != null) {
                    queue.offer(p.right);
                }
            }
            list.add(list1);
        }
        return list;
    }
}


public class Test2 {
}
