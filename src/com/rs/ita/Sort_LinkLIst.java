package com.rs.ita;

import com.rs.help.ListNode;
import com.rs.help.U;

public class Sort_LinkLIst {
    ListNode sort(ListNode node) {
        if(node.next ==  null) return  node;
        ListNode mid = mid(node);
        ListNode nextMid = mid.next;
        mid.next = null;
        ListNode left = sort(node);
        ListNode right = sort(nextMid);
        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right)
    {
        ListNode dummy = new ListNode(-1);
        ListNode cdummy = dummy;
        while (left != null && right != null){
            if(left.data == right.data){
                dummy.next = left;
                left = left.next;
                dummy.next.next = right;
                right = right.next;
                dummy = dummy.next.next;

            }else if(left.data < right.data){
                dummy.next = left;
                left = left.next;
                dummy = dummy.next;
            }else{
                dummy.next = right;
                right = right.next;
                dummy = dummy.next;
            }
        }

        while (left != null){
            dummy.next = left;
            left = left.next;
            dummy = dummy.next;
        }

        while (right != null){
            dummy.next = right;
            right = right.next;
            dummy = dummy.next;
        }
        return cdummy.next;
    }


    ListNode mid(ListNode node){
        ListNode p2x = node;
        ListNode nodePre = node;
        while (p2x != null && p2x.next != null){
            nodePre = node;
            node = node.next;
            p2x = p2x.next.next;
        }
        return  nodePre;
    }

    public static void main(String[] args) {
        Sort_LinkLIst o = new Sort_LinkLIst();
        System.out.println(o.sort(U.toListNode("6,5,4,3,2,1")));
    }
}
