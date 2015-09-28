package leetcode;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if (root == null) return res;

        StringBuilder sb = new StringBuilder();

        binaryTreePathsHelper(root, res, sb);
        return res;
    }
    
    public void binaryTreePathsHelper(TreeNode root, List<String> res, StringBuilder sb) {
        
        if (root.left == null && root.right == null) {
            sb.append(root.val);
            res.add(sb.toString());
            return;
        }
        
        sb.append(root.val);
        sb.append("->");
        
        if (root.left != null) {
            binaryTreePathsHelper(root.left, res, new StringBuilder(sb));
        }
        if (root.right != null) {
            binaryTreePathsHelper(root.right, res, new StringBuilder(sb));
        }
    }
}
