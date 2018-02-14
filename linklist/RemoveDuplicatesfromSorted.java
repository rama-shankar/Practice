package com.ib.linklist;


///Given 1->1->2->3->3, return 1->2->3.
public class RemoveDuplicatesfromSorted {

	public ListNode deleteDuplicates(ListNode A) {
		ListNode cur = A;
		ListNode head = A;
		A = A.next;
		while(A != null) {
			if(cur.val  == A.val) {
				cur.next = A.next;
			}else {
				cur = cur.next;
			}
			
			A = A.next;
		}
		
		return head;
	}

	public static void main(String[] args) {
		ListNode a = new ListNode(1);
		ListNode b = new ListNode(1);
		ListNode c = new ListNode(2);
		ListNode d = new ListNode(3);
		ListNode e = new ListNode(3);
		a.next = b;
		b.next = c;
		c.next = d;
		d.next = e;
		RemoveDuplicatesfromSorted rr = new RemoveDuplicatesfromSorted();
		ListNode.print(rr.deleteDuplicates(a));
		
	}

}
