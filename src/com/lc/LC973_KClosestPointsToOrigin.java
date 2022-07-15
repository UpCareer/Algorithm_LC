package com.lc;

import java.util.PriorityQueue;

public class LC973_KClosestPointsToOrigin {
    public int[][] kClosest(int[][] points, int k) {
        // Corner case:
        if (points == null) {
            return null;
        }

        // Define a max heap
        PriorityQueue<int[]> maxHeap = new PriorityQueue<int[]>((a,b)->(b[0]*b[0]+b[1]*b[1]-(a[0]*a[0]+a[1]*a[1])));
        for(int[] point : points) {
            maxHeap.add(point);
            if(maxHeap.size() > k) {
                maxHeap.remove();
            }
        }
        int[][] result = new int[k][2];
        while(k > 0) {
            k--;
            result[k] = maxHeap.remove();
        }
        return result;
    }
}
