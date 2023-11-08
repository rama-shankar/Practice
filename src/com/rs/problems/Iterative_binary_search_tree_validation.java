package com.rs.problems;

import com.rs.help.TreeNode;
import com.rs.help.U;

import java.util.Deque;
import java.util.LinkedList;

public class Iterative_binary_search_tree_validation {
    /**
     * Checks if the passed root tree node belongs to
     * a valid binary search tree.
     *
     * @param root Root TreeNode of the binary tree.
     * @return True if the passed node is the root of a valid BST.
     */
    public Boolean isValidBst(TreeNode root) {
        Deque<TreeNode> nodes = new LinkedList<>();
        int leftVal = Integer.MIN_VALUE;
        while(root != null || !nodes.isEmpty()){
            while(root != null){
                nodes.addLast(root);
                root = root.left;
            }

            root = nodes.removeLast();
            if(root.data < leftVal){
                return false;
            }

            leftVal = root.data;
            root = root.right;
        }

        return true;
    }

    public static void main(String[] args) {
        Iterative_binary_search_tree_validation o = new Iterative_binary_search_tree_validation();
        System.out.println(o.isValidBst(U.toTreeNode("10 5 15 4 6 14 16")));
    }

}