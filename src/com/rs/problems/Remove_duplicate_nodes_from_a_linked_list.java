package com.rs.problems;

import com.rs.help.ListNode;
import com.rs.help.U;

import java.util.HashSet;
import java.util.Set;

public class Remove_duplicate_nodes_from_a_linked_list {
  /**
   * Removes duplicates from the passed linked list and
   * returns the head of the modified list.
   *
   * @param head Head of the singly linked list.
   * @return Head ListNode of the modified linked list.
   */
  public ListNode removeDuplicates1(ListNode head) {
    if(head == null || head.next == null){
      return head;
    }
    Set<Integer> uniqueNodes = new HashSet<Integer>();
    ListNode cHead = head, pHead = null;
    while(cHead != null){
      if(uniqueNodes.contains(cHead.data)){
        pHead.next = cHead.next;
        cHead = pHead.next;
      }else{
        uniqueNodes.add(cHead.data);
        pHead = cHead;
        cHead = cHead.next;
      }
      
    }
    
    return head;
  }

  public ListNode removeDuplicates(ListNode head) {
    if(head == null || head.next == null){
      return head;
    }
    return head;
  }

  public static void main(String[] args){ 
      Remove_duplicate_nodes_from_a_linked_list o = new Remove_duplicate_nodes_from_a_linked_list();
      System.out.println(o.removeDuplicates(U.toListNode("1,1,3,4,4")));
  }
}