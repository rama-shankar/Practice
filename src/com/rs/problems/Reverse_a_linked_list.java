package com.rs.problems;

import com.rs.help.ListNode;
import com.rs.help.U;

public class Reverse_a_linked_list {
  /**
   * Reverses the passed singly linked list.
   *
   * @param head Head ListNode of the list.
   * @return Head ListNode of the reversed list.
   */
  public ListNode reverseList(ListNode head) {
    if(head == null || head.next == null){
      return head;
    }
    ListNode node = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return node;
  }
  public static void main(String[] args){ 
      Reverse_a_linked_list o = new Reverse_a_linked_list();
      System.out.println(o.reverseList(U.DEFAULT_LINK_LIST));
  }
}