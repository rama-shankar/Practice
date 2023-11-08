package com.rs.problems;

import com.rs.help.TreeNode;
import com.rs.help.U;

import java.util.Deque;
import java.util.LinkedList;

public class Iteratively_find_a_node_in_a_binary_tree {
    /**
     * Checks if the binary tree contains the target value.
     *
     * @param root Root TreeNode of the binary tree.
     * @param n    Target integer to search for.
     * @return True if the target value is found, false otherwise.
     */
    public Boolean findNode(TreeNode root, int n) {
        if(root == null) return false;

        Deque<TreeNode> nodes = new LinkedList<TreeNode>();
        nodes.add(root);
        while(!nodes.isEmpty()){
            TreeNode node = nodes.remove();
            if(node.data == n){
                return true;
            }
            if(node.left != null){
                nodes.add(node.left);
            }
            if(node.right != null){
                nodes.add(node.right);
            }
        }


        return false;
    }
    public static void main(String[] args){ 
        Iteratively_find_a_node_in_a_binary_tree o = new Iteratively_find_a_node_in_a_binary_tree();
        System.out.println(o.findNode(U.DEFAULT_BI_TREE, 8));
    }
}