package com.lc.topKElements;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Problem Statement
 * Given an unsorted array of numbers, find the 'K' largest numbers in it.
 *
 * Example 1:
 * Input: [3,1,,5,12,2,11], K=3
 * Output: [5,12,11]
 *
 * Example 2:
 * Input:[5,12,11,-1,12], K=3
 * Output: [12,11,12]
 *
 * Solution:
 * A brute force solution could be to sort the array and return the largest K numbers. The time complexity of such an
 * algorithm will be O(N*logN) as we need to use a sorting algorithm like Timsort if we use Java's Collection.sort().
 *
 * The best data structure that comes to mind to keep track of Top K elements is Heap. Let's see if we can use a heap
 * to find a better algorithm.
 *
 * If we iterate through the array one element at a time and keep 'K' largest numbers in a heap such that each time we
 * find a larger number than the smallest number in the heap, we do two things
 * 1. Take out the smallest number from the heap, and
 * 2. Insert the larger number into the heap.
 *
 * This will ensure that we always have 'K' largest numbers in the heap. The most efficient way to repeatedly find the
 * smallest number among a set of numbers will be to use a min-heap. As we know, we can find the smallest number in a min-heap
 * in constant time O(1), since the smallest number is always at the root of the heap. Extracting the smallest number from
 * a min-heap will take O(logN) (if the heap has N elements) as the heap needs to readjust after the removal of an element.
 *
 * Time Complexity: O(N*logK)
 * Space Complexity: O(K)
 */

public class TopKNumbers {
    public static List<Integer> findKLargestNumbers(int[] nums, int k) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<Integer>((n1, n2) -> n1 - n2);
        // put first 'K' numbers in the min heap
        for(int i = 0; i < k; i++)
            minHeap.add(nums[i]);

        // go through the remaining numbers of the array, if the number from the array is bigger than the top(smallest)
        // number of the min-heap, remove the top number from heap and add the number from array.
        for(int i = k; i < nums.length; i++) {
            if(nums[i] > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(nums[i]);
            }
        }

        // the heap has the top 'K' numbers, return them in a list
        return new ArrayList<>(minHeap);
    }

    public static void main(String[] args) {
        List<Integer> result = TopKNumbers.findKLargestNumbers(new int[] {3,1,5,12,2,11}, 3);
        System.out.println("Here are the top K numbers: " + result);
        result = TopKNumbers.findKLargestNumbers(new int[] {5,12,11,-1,12}, 3);
        System.out.println("Here are the top K numbers: " + result);
    }
}
