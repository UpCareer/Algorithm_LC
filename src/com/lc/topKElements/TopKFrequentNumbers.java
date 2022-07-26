package com.lc.topKElements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Problem Statement
 * Given an unsorted array of numbers, find the top K frequently occurring numbers in it.
 *
 * Example 1:
 * Input: [1,3,5,12,11,12,11], k=2
 * output: [12,11]
 * Explanation: Both 11 and 12 appeared twice
 *
 * Example 2:
 * Input: [5,12,11,3,11], k=2
 * output: [11,5] or [11,12] or [11,3]
 * Explanation: only 11 appeared twice, all other numbers appeared once.
 *
 * Solution:
 * This problem follows Top K Numbers.
 * We first need to know the frequency of each number, for which we can use a HashMap. Once we have the frequency map,
 * we can use a Min Heap to find the K most frequently occurring number. In the Min Heap, instead of comparing numbers
 * we will compare their frequencies in order to get frequently occurring numbers.
 *
 * Time complexity: O(N + N * logK)
 * Space complexity: O(N)
 */

// Map.Entry is very important data structure.
public class TopKFrequentNumbers {
    public static List<Integer> findTopKFrequentNumbers(int[] nums, int k) {
        // find the frequency of each number
        Map<Integer, Integer> numFrequencyMap = new HashMap<>();
        for(int n : nums)
            numFrequencyMap.put(n, numFrequencyMap.getOrDefault(n,0)+1);

        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<Map.Entry<Integer, Integer>>(
                (e1, e2) -> (e1.getValue() - e2.getValue())
        );

        // go through all numbers of the numFrequencyMap and push them in the minHeap, which will have
        // top k frequent numbers. If the heap size is more than K, we remove the smallest (top) number
        for(Map.Entry<Integer, Integer> entry : numFrequencyMap.entrySet()) {
            minHeap.add(entry);
            if(minHeap.size() > k) {
                minHeap.poll();
            }
        }

        // create a list of top k numbers
        List<Integer> topNumbers = new ArrayList<>();
        while(!minHeap.isEmpty()) {
            topNumbers.add(minHeap.poll().getKey());
        }
        return topNumbers;
    }

    public static void main(String[] args) {
        List<Integer> result = TopKFrequentNumbers.findTopKFrequentNumbers(new int[] {1,3,5,12,11,12,11}, 2);
        System.out.println("Here are the K frequent numbers: " + result);
        result = TopKFrequentNumbers.findTopKFrequentNumbers(new int[] {5,12,11,3,11}, 2);
        System.out.println("Here are the K frequent numbers: " + result);
    }
}
