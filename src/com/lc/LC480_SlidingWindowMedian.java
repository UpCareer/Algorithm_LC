package com.lc;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class LC480_SlidingWindowMedian {

    // Data Structure
    Queue<Integer> minHeap;
    Queue<Integer> maxHeap;

    public LC480_SlidingWindowMedian() {
        minHeap = new PriorityQueue<Integer>();
        maxHeap = new PriorityQueue<Integer>(Collections.reverseOrder());
    }

    public double[] medianSlidingWindow(int[] nums, int k) {
        int start = 0;
        double[] result = new double[nums.length - k + 1];
        for (int end = 0; end < nums.length; end++) {
            add(nums[end]);
            int size = end - start + 1;
            if (size == k) {
                result[start] = findMedian();
                remove(nums[start]);
                start++;
            }
        }
        return result;
    }

    // Assume: MaxHeap.size() >= MinHeap.size
    // Divide numbers in the range into two parts: smaller part in maxHeap and larger part in minHeap
    public void balance() {
        if(maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        } else if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public void add(int num) {
        if(maxHeap.isEmpty() || maxHeap.peek() > num) {
            maxHeap.add(num);
        } else {
            minHeap.add(num);
        }
        balance();
    }

    public void remove(int num) {
        // remove num from correct heap
        if(num <= maxHeap.peek()) {
            maxHeap.remove(num);
        } else {
            minHeap.remove(num);
        }
        balance();
    }
    private double findMedian() {
        if(maxHeap.size() > minHeap.size()) return maxHeap.peek();
        if(minHeap.size() > maxHeap.size()) return minHeap.peek();
        return maxHeap.peek()/2.0 + minHeap.peek() /2.0;
    }

}
