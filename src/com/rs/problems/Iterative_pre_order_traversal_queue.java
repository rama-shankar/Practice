package com.rs.problems;

import com.rs.help.TreeNode;
import com.rs.help.U;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Iterative_pre_order_traversal_queue {
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
    Queue<TreeNode> q1 = new LinkedList<TreeNode>();
    Queue<TreeNode> q2 = new LinkedList<TreeNode>();
    Queue<TreeNode> q3 = new LinkedList<TreeNode>();
    q1.add(root);
    while (!q1.isEmpty()){
      TreeNode node = q1.remove();
      if(node != null){
        res.add(node.data);
        q2.add(node.left);
        q2.add(node.right);
      }
      while (!q1.isEmpty()){
        q2.add(q1.remove());
      }
      q3 = q2;
      q2 = q1;
      q1 = q3;

    }
    return res;
  }

  public static void main(String[] args){
      Iterative_pre_order_traversal_queue o = new Iterative_pre_order_traversal_queue();
      System.out.println(o.preOrder(U.DEFAULT_BI_TREE));
  }
}