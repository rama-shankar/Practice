package com.ib.linklist;

public class ReverseLinkedList {

	public ListNode reverseList(ListNode A) {
		ListNode it = A;
		ListNode prev = null;
		ListNode next = A.next;
		while(it != null) {
			it.next = prev;
			A = it;
			prev = it;
			it = next;
			if(next != null) {
				next = next.next;				
			}
			
		}
		return A;
	}

	public static void main(String[] args) {
		ListNode a1 = new ListNode(1);
		ListNode a2 = new ListNode(2);
		ListNode c1 = new ListNode(7);
		ListNode c2 = new ListNode(8);
		ListNode c3 = new ListNode(9);
		a1.next = a2;
		a2.next = c1;
		c1.next = c2;
		c2.next = c3;
		//ListNode.print(a1);
		 ReverseLinkedList rr = new ReverseLinkedList();
		 ListNode.print( rr.reverseList(a1));

	}
}
