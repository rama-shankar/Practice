package com.ib.linklist;

public class ReverseLinkedList {

	 private int listLenth(ListNode a) {
		int l = 0;		
		while(a != null) {
			l++;
			a = a.next;
		}
		return l;
	}
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
	public int lPalin(ListNode A) {
	int l = listLenth(A);
	if(l < 2) return 1;
		
		int h = l % 2 == 0 ? l /2 :( l /2 ) + 1;
	
		ListNode n1 = A;
		for(int i = 0 ; i < h ; i++) {
			n1 = n1.next;
		}
		
		
		n1 = reverseList(n1);
		
		while (n1 != null ) {
			if(A.val != n1.val) return 0;
			A = A.next;
			n1 = n1.next;
			
		}
		return 1;
    }
}
