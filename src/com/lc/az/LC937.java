package com.lc.az;

import java.util.PriorityQueue;
import java.util.Queue;

public class LC937 {
    public static int[][] kClosest(int[][] points, int k) {
        // define a max heap
        Queue<int[]> maxHeap = new PriorityQueue<int[]>((a, b)->dist(b)-dist(a));

        //insert each point into the max heap
        for(int[] point : points) {
            maxHeap.offer(point);
            if(maxHeap.size()>k) {
                maxHeap.poll();
            }
        }

        // Get each points from the max heap and insert to array
        int[][] res = new int[k][2];
        int i = 0;
        while(!maxHeap.isEmpty()) {
            res[i++] = maxHeap.poll();
        }
        return res;
    }

    private static int dist(int[] point) {
        int x = point[0];
        int y = point[1];
        return x*x + y*y;
    }

    public static void main(String[] args) {
        int[][] points = {{3,3},{5,-1},{-2,4}};
        int[][] res = kClosest(points, 2);
        for (int[] point : res) {
            System.out.println("{" + point[0] + "," + point[1] + "}");
        }
    }
}


