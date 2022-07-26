package com.lc.KwayMerge;

import java.util.PriorityQueue;

/**
 * Problem Statement:
 * Given an array of K sorted LinkedLists, merge them into one sorted list
 * Example 1:
 * Input: L1=[2 6 8], L2=[3 6 7], L3=[1 3 4]
 * output: [1 2 3 3 4 6 6 7 8]
 *
 * Example 2:
 * Input: L1=[5 8 9], L2=[1 7]
 * output: [1 5 7 8 9]
 *
 * Solution:
 * A brute force solution could be to add all elements of the given K lists to one list and sort it. If there are a total
 * of N elements in all the input lists, then the brute force solution will have a time complexity of O(N*logN) as we will
 * need to sort the merged list. Can we do better than this ? How can we utilize the fact that the input lists are
 * individually sorted ?
 *
 * If we have to find the smallest element of all the input lists, we have to compare only the smallest (i.e. the first)
 * element of all the lists. Once we have the smallest element, we can put it in the merged list. Following a similar pattern
 * we can find the next smallest element of all the lists to add it to the merged list.
 *
 * The best data structure that comes to mind the smallest number among a set of K numbers is a Heap.
 * 1. We can insert the frist element of each array in a Min Heap
 * 2. After this, we can take out the smallest (top) element from the heap and add it to the merged list.
 * 3. After removing the smallest element from the heap, we can insert the next element of the same list into the heap
 * 4. We can repeat steps 2 and 3 to populate the merged list in sorted order.
 *
 * Time complexity: O(NlogK) where N is the total number of elements in all the K input arrays
 *
 * Space complexity: O(K)
 */

class ListNode {
    int value;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

public class MergeKSortedList {
    public static ListNode merge(ListNode[] lists) {
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((n1,n2)->n1.value-n2.value);

        // put the root of each list in the min Heap
        for(ListNode root : lists) {
            if(root != null)
                minHeap.add(root);
        }

        // take the smallest (top) element from the min-heap and add it to the result;
        // if the top element has a next element add it to the heap
        ListNode resultHead = null; // for keep result
        ListNode resultTail = null; // for keep result
        while(!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            if(resultHead == null) {
                resultHead = node;
                resultTail = node;
            } else {
                resultTail.next = node;
                resultTail = resultTail.next;
            }
            if(node.next != null) {
                minHeap.add(node.next);
            }
        }
        return resultHead;
    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(6);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(3);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(7);

        ListNode l3 = new ListNode(1);
        l3.next = new ListNode(3);
        l3.next.next = new ListNode(4);

        ListNode result = MergeKSortedList.merge(new ListNode[] {l1, l2, l3});
        System.out.println("Here are the elements form the merged list: ");
        while(result != null) {
            System.out.println(result.value + " " );
            result = result.next;
        }
    }
}
