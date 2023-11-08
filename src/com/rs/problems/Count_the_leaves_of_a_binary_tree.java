package com.rs.problems;

import com.rs.help.TreeNode;
import com.rs.help.U;

public class Count_the_leaves_of_a_binary_tree {
  /**
   * Returns the number of leaf nodes in the binary
   * tree.
   *
   * @param root Root TreeNode.
   * @return Number of leaf nodes.
   */
  public int numLeaves(TreeNode root) {
    if(root == null){
      return 0;
    }
    if(root.left == null && root.right == null){
      return 1 ;
    }
    return numLeaves(root.left) + numLeaves(root.right);
  }

 /* public int numLeaves(TreeNode root) {
    if (root == null) return 0;
    else if (root.left == null && root.right == null) return 1;
    else return numLeaves(root.left) + numLeaves(root.right);
  }*/

  public static void main(String[] args){ 
      Count_the_leaves_of_a_binary_tree o = new Count_the_leaves_of_a_binary_tree();
      System.out.println(o.numLeaves(U.DEFAULT_BI_TREE));
  }
}