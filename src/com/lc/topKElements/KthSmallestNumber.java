package com.lc.topKElements;

import java.util.PriorityQueue;

/**
 * Kth Smallest Number
 * Problem Statement:
 * Given an unsorted array of numbers, find Kth smallest number in it.
 * Please note that it is the Kth Smallest number in the sorted order, not the Kth distinct element.
 *
 * Example 1:
 * Input: [1,5,12,2,11,5], K=3
 * output: 5
 * Explannation: The 3rd smallest number is 5, as the first two smaller numbers are [1,2]
 *
 * Solution: (Data Structure - Max Heap)
 * This problem follows the Top K numbers pattern but has two differences:
 *  1. Here we need to find the Kth Smallest number, whereas in Top K numbers we were dealing with K largest numbers.
 *  2. In this problem, we need to find only one number (Kth smallest) compared to finding all K largest numbers.
 *
 *  Time Complexity:
 *  O(NlogK)
 *  Space complexity:
 *  O(K)
 */

public class KthSmallestNumber {
    public static int findKthSmallestNumber(int[] nums, int k) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((n1, n2) -> n2 - n1);
        // put first K numbers in the max heap
        for(int i=0; i<k; i++)
            maxHeap.add(nums[i]);

        // go through the remaining numbers of the array, if the number from the array is smaller than the top(biggest)
        // number of the heap, remove the top number from heap and add the number from array
        for(int i=k; i < nums.length; i++) {
            if(nums[i] < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(nums[i]);
            }
        }
        // the root of the heap has the Kth smallest number
        return maxHeap.peek();
    }

    public static void main(String[] args) {
        int result  = KthSmallestNumber.findKthSmallestNumber(new int[] {1,5,12,2,11,5}, 3);
        System.out.println("Kth smallest number is: " + result);
        result  = KthSmallestNumber.findKthSmallestNumber(new int[] {1,5,12,2,11,5}, 4);
        System.out.println("Kth smallest number is: " + result);
        result  = KthSmallestNumber.findKthSmallestNumber(new int[] {5,12,11,-1,12}, 3);
        System.out.println("Kth smallest number is: " + result);
    }
}
