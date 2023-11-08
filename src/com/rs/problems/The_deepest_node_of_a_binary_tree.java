package com.rs.problems;

import com.rs.help.TreeNode;
import com.rs.help.U;

import java.util.Deque;
import java.util.LinkedList;

public class The_deepest_node_of_a_binary_tree {
  /**
   * Finds and returns the deepest node of the
   * binary tree.
   *
   * @param root Root TreeNode of the tree.
   * @return Integer data of the deepest node.
   */
  public int deepestNode(TreeNode root) {
    if(root == null) return -1;
    
    if(root.left == null && root.right == null) return root.data;
    
    int leftNode = deepestNode(root.left);
    int right = deepestNode(root.right);
    
    if(right == -1) return leftNode;
    
    return right;
  }
  public static void main(String[] args){ 
      The_deepest_node_of_a_binary_tree o = new The_deepest_node_of_a_binary_tree();
     System.out.println(U.toTreeNode("1 2 3 # # 4 5 6 # 8 # 9 # # # 11 # 12"));
      System.out.println(o.deepestNode(U.toTreeNode("1 2 3 # # 4 5 6 # 8 # 9 # # # 11 # 12")));
  }
}