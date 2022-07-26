package com.lc.KwayMerge;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given M sorted arrays, find the Kth smallest number among all the arrays
 * Example 1:
 * Input: L1 = [2,6,8]; L2 = [3,6,7]; L3 = [1,3,4] k=5
 * output: 4
 * Explanation: The 5th smallest number among all the arrays is 4, this can be verified from the merged list of all the
 * arrays: [1 2 3 3 4 6 6 7 8]
 *
 * Input: L1 = [5,8,9]; L2 = [1,7]; k=3
 * output: 7
 * Explanation: The 5th smallest number among all the arrays is 7.
 *
 * Solution: This problem follows the K-way merge pattern and we can follow a similar approach as discussed in Merge K
 * Sorted Lists.
 *
 * We can start merging all the arrays, but instead of inserting numbers into a merged list, we will keep count to see
 * how many elements have been inserted in the merged list. Once that count is equal to K, we have found our required
 * number.
 *
 * A big difference from Merge K sorted lists is that in this problem, the input is a list of arrays compared to LinkedLists.
 * This means that when we want to push the next number in the heap we need to know what the index of the current number in
 * the current array was. To handle this, we will need to keep track of the array and the element indices
 *
 * Time complexity:
 * Since we will be going through at most K elements among all the arrays and we will remove/add one element in the heap
 * in each step, the time complexity of the above algorithm will be O(KlogM) where M is the total number of input arrays
 *
 * Space complexity:
 * The space complexity will be O(M) because, at any time, our min-heap will be storing one number from all the M input arrays
 *
 * Similar Problems
 * Problem 1: Given M sorted arrays, find the median number among all arrays
 * Solution: This problem is similar to our parent problem with K=Median. So if there are N total numbers in all the
 * arrays we need to find the Kth minimum number where K = N/2
 *
 * Problem 2: Given a list of K sorted arrays, merge them into one sorted list.
 * Solution: This problem is similar to Merge K Sorted Lists except that the input is a list of arrays compared to
 * LinkedLists. To handle this, we can use a similar approach as discussed in our parent problem by keeping a track of
 * the array and the element indices.
 */

// Node DataStructure to trace next insert node; this Data Structure is very important to solve this problem
// and Node is reused
class Node {
    int elementIndex;
    int arrayIndex;

    Node(int elementIndex, int arrayIndex) {
        this.elementIndex = elementIndex;
        this.arrayIndex = arrayIndex;
    }
}

public class KthSmallestNumberInMSortedLists {
    public static int findKthSmallest(List<Integer[]> lists, int k) {
        PriorityQueue<Node> minHeap = new PriorityQueue<Node>(
                (n1,n2) -> lists.get(n1.arrayIndex)[n1.elementIndex] - lists.get(n2.arrayIndex)[n2.elementIndex]
        );

        // put the 1st element of each array in the min heap
        for(int i=0; i<lists.size(); i++) {
            if(lists.get(i) != null)
                minHeap.add(new Node(0,i));
        }

        // take the smallest (top) element from the min heap, if the running count is equal to K return the number
        // if the array of the top element has more elements, add the next element to the heap
        int numberCount = 0;
        int result = 0;
        while(!minHeap.isEmpty()) {
            Node node = minHeap.poll();
            result  = lists.get(node.arrayIndex)[node.elementIndex];
            if(++numberCount == k)
                break;
            node.elementIndex++;
            if(lists.get(node.arrayIndex).length > node.elementIndex)
                minHeap.add(node);
        }
        return result;
    }

    public static void main(String[] args) {
        Integer[] l1 = new Integer[]{2,6,8};
        Integer[] l2 = new Integer[]{3,6,7};
        Integer[] l3 = new Integer[]{1,3,4};
        List<Integer[]> lists = new ArrayList<Integer[]>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);
        int result = KthSmallestNumberInMSortedLists.findKthSmallest(lists, 5);
        System.out.print("Kth smallest number is: " + result);
    }
}
