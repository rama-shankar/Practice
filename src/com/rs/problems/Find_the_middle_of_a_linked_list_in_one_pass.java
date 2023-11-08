package com.rs.problems;

import com.rs.help.ListNode;
import com.rs.help.U;

public class Find_the_middle_of_a_linked_list_in_one_pass {
  /**
   * Finds the middle node of the linked list in one pass and returns
   * the data contained within.
   *
   * @param head Head of the singly linked list.
   * @return Data contained within the middle node of the linked list.
   */
  public int findMiddle(ListNode head) {
    if(head == null) return -1;
    ListNode head2X = head;
    
    while (head2X != null && head2X.next != null){
      head = head.next;
      head2X = head2X.next.next;
    }
    
    return head.data;
  }
  
  public static void main(String[] args){ 
      Find_the_middle_of_a_linked_list_in_one_pass o = new Find_the_middle_of_a_linked_list_in_one_pass();
      System.out.println(o.findMiddle(U.DEFAULT_LINK_LIST));
  }
}