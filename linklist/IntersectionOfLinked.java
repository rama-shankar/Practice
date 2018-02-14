package com.ib.linklist;


public class IntersectionOfLinked {
	public ListNode getIntersectionNode(ListNode a, ListNode b) {
		int a_l = listLenth(a);
		int b_l = listLenth(b);
		int d = a_l - b_l;
		if(d < 0) {
			ListNode t = a;
			a = b;
			b = t;
			d = b_l - a_l;
		}
		for (int i = 0; i < d; i++) {
			a = a.next;
		}

		while (a != null && b != null) {
			if(a == b) return a;
			a = a.next;
			b = b.next;
			
		}
		return null;
	}
	
	private int listLenth(ListNode a) {
		int l = 0;		
		while(a != null) {
			l++;
			a = a.next;
		}
		return l;
	}
	public static void main(String[] args) {
		ListNode a1  = new ListNode(1);
		ListNode a2  = new ListNode(2);
		a1.next = a2;
		
		
		ListNode c1  = new ListNode(7);
		ListNode c2  = new ListNode(8);
		ListNode c3  = new ListNode(9);
		c1.next = c2;
		c2.next = c3;			
		
		ListNode b1  = new ListNode(4);
		ListNode b2  = new ListNode(5);
		ListNode b3  = new ListNode(6);
		b1.next = b2;
		b2.next = b3;
		
		a2.next = c1;		
		b3.next = c1;
				
		IntersectionOfLinked obje = new IntersectionOfLinked();
		ListNode rr = obje.getIntersectionNode(a1, b1);
		
		obje.print(rr);
		
	}
	
	private void print(ListNode head) {
		StringBuilder bl = new StringBuilder();
		ListNode it = head;
		while(it != null) {
			bl.append(it.val);
			it = it.next;
			if(it != null) {
				bl.append(", ");
			}
		}
		System.out.println("LinkedList [" + bl.toString() + "]");
	}
}
