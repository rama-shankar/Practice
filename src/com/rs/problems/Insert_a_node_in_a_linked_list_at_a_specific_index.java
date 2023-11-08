package com.rs.problems;

import com.rs.help.ListNode;
import com.rs.help.U;

public class Insert_a_node_in_a_linked_list_at_a_specific_index {
  /**
   * Inserts a new ListNode with data n at the specified i and
   * returns the head of the modified list.
   *
   * @param head  Head of the singly linked list.
   * @param n     Integer to set on the data property of the inserted node.
   * @param i Zero indexed integer position to insert the new node.
   * @return Head ListNode of the modified linked list.
   */
  public ListNode insertAtIndex(ListNode head, int n, int i) {
    if(head != null && i == 0){
      ListNode node = new ListNode(n);
      node.next = head;
      return node;
    }

    if(head == null || i == 0){
      return new ListNode(n);
    }
    
    ListNode iNode = insertAtIndex(head.next, n, i - 1);
    if(head.next != iNode){
      iNode.next = head.next;
      head.next = iNode;
    }
    
    return head;
  }
  
  public static void main(String[] args){ 
      Insert_a_node_in_a_linked_list_at_a_specific_index o = new Insert_a_node_in_a_linked_list_at_a_specific_index();
      System.out.println(o.insertAtIndex(U.DEFAULT_LINK_LIST, 10, 1));
  }
}