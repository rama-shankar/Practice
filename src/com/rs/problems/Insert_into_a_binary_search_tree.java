package com.rs.problems;

import com.rs.help.TreeNode;
import com.rs.help.U;

public class Insert_into_a_binary_search_tree {
    /**
     * Inserts a new node with data = n into the
     * passed binary search tree.
     *
     * @param root Root TreeNode of the binary search tree.
     * @param n    Integer to set the `data` of the new node to.
     * @return Root TreeNode of the modified tree.
     */
    public TreeNode insert(TreeNode root, int n) {
        if (root == null) return new TreeNode(n);
        if (root.data < n) {
            root.right = insert(root.right, n);
        } else {
            root.left = insert(root.left, n);
        }
        return root;
    }

    public TreeNode insertIterative(TreeNode root, int n) {
        if(root == null) return new TreeNode(n);
        TreeNode iRoot = root;
        while (iRoot != null) {
            if (iRoot.data < n) {
                if(iRoot.right == null){
                    iRoot.right = new TreeNode(n);
                    break;
                }else{
                    iRoot = iRoot.right;
                }

            } else {
                if(iRoot.left == null){
                    iRoot.left = new TreeNode(n);
                    break;
                }else{
                    iRoot = iRoot.left;
                }
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Insert_into_a_binary_search_tree o = new Insert_into_a_binary_search_tree();
        System.out.println(o.insertIterative(U.toTreeNode("4 2 8 # # 5 10"), 6).toTree());
    }
}