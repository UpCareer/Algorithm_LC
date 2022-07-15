package com.amazon;

import java.util.PriorityQueue;

public class MedianFinder {
    private PriorityQueue<Integer> large;
    private PriorityQueue<Integer> small;
     public MedianFinder() {
         // Min Heap
         large = new PriorityQueue<>();
         // Max Heap
         small = new PriorityQueue<>((a,b)-> {return b-a;});
     }

    public void addNum(int num) {
         if (small.size() >= large.size()) {
             small.offer(num);
             large.offer(small.poll());
         } else {
             large.offer(num);
             small.offer(large.poll());
         }

    }

    public double findMedian() {
         if (large.size() < small.size()) {
             return small.peek();
         } else if (large.size() > small.size()) {
             return large.peek();
         }
         return (large.peek()+small.peek()) / 2.0;
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */
