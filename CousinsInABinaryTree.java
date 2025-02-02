import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

// TC: O(n) can be the worst TC if cousins are leaf nodes
// SC: O(n) as Queue is used to store and pop nodes.
public class CousinsInABinaryTree {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);

        System.out.println(isCousins(root, 4, 3)); // false

        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.right = new TreeNode(4);
        root1.right.right = new TreeNode(5);

        System.out.println(isCousins(root1, 5, 4)); // true
    }

    public static boolean isCousins(TreeNode root, int x, int y) {
        if (root == null)
            return false;
        boolean x_found = false;
        boolean y_found = false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int level = queue.size();
            for (int i = 0; i < level; i++) {
                TreeNode current = queue.poll();
                if (current.left != null && current.right != null) {
                    if (current.left.val == x && current.right.val == y
                            || current.right.val == x && current.left.val == y) {
                        return false;
                    }
                }
                if (current.val == x)
                    x_found = true;
                if (current.val == y)
                    y_found = true;
                if (current.left != null)
                    queue.offer(current.left);
                if (current.right != null)
                    queue.offer(current.right);
            }
            if (x_found == true && y_found == true)
                return true;
            if (x_found == true || y_found == true)
                return false;
        }
        return true;
    }
}