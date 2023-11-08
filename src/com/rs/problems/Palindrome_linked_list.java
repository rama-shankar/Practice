package com.rs.problems;

import com.rs.help.ListNode;
import com.rs.help.U;

public class Palindrome_linked_list {
  /**
   * Checks if the input list is a palindrome
   *
   * @param head Head ListNode of the list.
   * @return True if the list is a palindrome.
   */
  public Boolean isPalindrome(ListNode head) {
    ListNode midHead = midNode(head);
    ListNode rev = reverseList(midHead);
    
    while(head != null && rev != null){
      if(head.data != rev.data){
        return false;
      } 
      head = head.next;
      rev = rev.next;
    }
    return true;
    
  }
  
  ListNode midNode(ListNode head){
    ListNode head2x = head;
    while(head2x != null && head2x.next != null){
      head = head.next;
      head2x = head2x.next.next;
    }
    return head;
  }
  
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
      Palindrome_linked_list o = new Palindrome_linked_list();
      System.out.println(o.isPalindrome(U.toListNode("1,2,2,1")));
  }

}