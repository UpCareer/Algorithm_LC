package com.amazon;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

public class LC02_AddTwoNumbers {
        public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
            ListNode dummy = new ListNode();
            ListNode p = dummy;
            ListNode p1 = l1;
            ListNode p2 = l2;
            int carry = 0;
            while(p1 != null || p2 != null || carry > 0) {
                int val = carry;
                if(p1 != null) {
                    val = val + carry;
                    p1 = p1.next;
                }
                if(p2 != null) {
                    val = val + carry;
                    p2 = p2.next;
                }
                carry = val / 10;
                val = val % 10;
                p.next = new ListNode(val);
                p = p.next;
            }
            return dummy.next;
        }
}
