package com.rs.help;

/**
 * This class represents a node of a doubly linked list.
 * The `data` field holds the data value of this node,
 * and the `next` field holds a reference to the next node
 * in the linked list, which is `null` if this is the last
 * node in the list. Similarly, the `previous` field holds
 * a reference to the previous node.
 */
public class DoubleLinkListNode {
  int data;       // Data as an Integer
  DoubleLinkListNode next;  // Reference to the next node, null if this is the last node
  DoubleLinkListNode previous;  // Reference to the previous node, null if this is the first node

  /**
   * Constructor
   *
   * @param data     Integer data
   * @param previous ListNode referencing the previous node in the list, or null
   * @param next     ListNode referencing the next node in the list, or null
   */
  DoubleLinkListNode(int data, DoubleLinkListNode previous, DoubleLinkListNode next) {
    this.data = data;
    this.previous = previous;
    this.next = next;
  }

  /**
   * Constructor that sets the previous and next fields to null.
   *
   * @param data Integer data
   */
  DoubleLinkListNode(int data) {
    this(data, null, null);
  }
}