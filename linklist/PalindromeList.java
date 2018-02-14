package com.ib.linklist;

public class PalindromeList {
	
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
		
		int h = l % 2 == 0 ? l /2 :( l /2 ) + 1;
		
		ListNode n1 = null;
		for(int i = 0 ; i < h ; i++) {
			n1 = A.next;
		}
		
		
		n1 = reverseList(n1);
		
		while (n1 != null ) {
			if(A.val != n1.val) return 0;
			A = A.next;
			n1 = n1.next;
			
		}
		return 1;
    }
	public static void main(String[] args) {
		ListNode c1 = new ListNode(1);
		ListNode c2 = new ListNode(2);
		ListNode c3 = new ListNode(3);
		
		c1.next = c2;
		c2.next = c3;
		ListNode.print(c1);
		PalindromeList pp = new PalindromeList();
		System.out.println("rr" + pp.lPalin(c1));

	}
	
	


}
