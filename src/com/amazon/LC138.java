package com.amazon;

import java.util.HashMap;
import java.util.Map;

public class LC138 {
    public Node copyRandomList(Node head) {
        if (head==null) {
            return null;
        }
        // sentinel node to help construct the deep copy
        Node dummy = new Node(0);
        Node cur = dummy;
        // Maintains the mapping between the node in the original list and
        // the corresponding node in the new list
        Map<Node, Node> map = new HashMap<>();
        while(head != null) {
            // deep copy the current node if necessary
            if (!map.containsKey(head)) {
                map.put(head, new Node(head.val));
            }
            // connect the copied node to the deep copy list
            cur.next = map.get(head);
            // copy the random node if necessary
            if (head.random != null) {
                if (!map.containsKey(head.random)) {
                    map.put(head.random, new Node(head.random.val));
                }
                // connect the copied node to the random pointer
                cur.next.random = map.get(head.random);
            }
            head = head.next;
            cur = cur.next;
        }
        return dummy.next;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    class LinkedList {
        Node head;

        public LinkedList(Node head) {
            this.head = head;
        }

    }

}
