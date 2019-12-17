import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.List;
/*
class Solution1 {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if(root == null) {
            return list;
        }

        List<Integer> list1 = inorderTraversal(root.left);
        list.addAll(list1);

        System.out.print(root.val + " ");
        list.add(root.val);

        List<Integer> list2 = inorderTraversal(root.right);
        list.addAll(list2);

        return list;
    }
}
public class Main {
}
*/