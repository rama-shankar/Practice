package com.rs.help;// Some methods and signatures you'll find useful.
// Click 'Hide' to hide me.

public class ListNode {
    public int data;
    public ListNode next;
    public ListNode(int data) { this.data = data; }

    @Override
    public String toString() {
        return data + "->" + next ;
    }
}
