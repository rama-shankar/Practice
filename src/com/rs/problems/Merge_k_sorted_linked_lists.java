package com.rs.problems;

import com.rs.help.ListNode;
import com.rs.help.U;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Merge_k_sorted_linked_lists {
    /**
     * Merges k sorted linked lists into a globally sorted
     * linked list.
     *
     * @param lists List of linked list ListNodes.
     * @return Head ListNode of the sorted output linked list.
     */
    public ListNode mergeLists(List<ListNode> lists) {
        if(lists == null && lists.size() == 0 || lists.size() == 1) lists.get(0);

        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.data));

        ListNode result = new ListNode(-1);
        ListNode cur = result;

        for(ListNode listNode : lists){
            if(listNode != null) pq.add(listNode);
        }

        while (!pq.isEmpty()){
            ListNode n = pq.poll();
            if(n !=  null){
                cur.next = n;
                cur = cur.next;
                if(n.next != null) pq.add(n.next);
            }

        }
        return result.next;
    }

    public static void main(String[] args) {
        Merge_k_sorted_linked_lists sol = new Merge_k_sorted_linked_lists();

        //1,4;2,5,6,7,9;0,3,8,10
        ListNode a1 = U.toListNode("1,4");
        ListNode a2 = U.toListNode("5,6,7,9");
        ListNode a3 = U.toListNode("0,3,8,10");
        System.out.println(sol.mergeLists(Arrays.asList(a1, a2, a3)));
    }
}
