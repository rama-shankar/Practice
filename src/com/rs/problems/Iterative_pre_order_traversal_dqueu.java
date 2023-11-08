package com.rs.problems;

import com.rs.help.TreeNode;
import com.rs.help.U;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Iterative_pre_order_traversal_dqueu {
  /**
   * Returns a list of integers depicting the data
   * of the nodes of the input tree, traversed with
   * pre-order iterative traversal.
   *
   * @param root Root TreeNode.
   * @return List of node data.
   */
  public List<Integer> preOrder(TreeNode root) {
    List<Integer> res = new ArrayList<>();
    if(root == null){
      return  res;
    }
    Deque<TreeNode> q = new LinkedList<TreeNode>();
    q.add(root);
    while (!q.isEmpty()){
      TreeNode node = q.removeLast();
      if(node != null){
        res.add(node.data);
        q.add(node.right);
        q.add(node.left);
      }
    }
    return res;
  }

  public static void main(String[] args){
      Iterative_pre_order_traversal_dqueu o = new Iterative_pre_order_traversal_dqueu();
      System.out.println(o.preOrder(U.DEFAULT_BI_TREE));
  }
}