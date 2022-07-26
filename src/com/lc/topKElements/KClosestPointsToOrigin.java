package com.lc.topKElements;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Problem Statement:
 * Given an array of points in a 2D plane, find 'K' closest points to the origin
 *
 * Example 1:
 * Input: Points = [[1,2],[1,3]], k=1
 * output: [[1,2]]
 * Explanation: The Euclidean distance between (1,2) and the origin is sqrt(5)
 * The Euclidean distance between (1,3) and the origin is sqrt(10)
 * Since sqrt(5) < sqrt(10), therefore (1,2) is closer to the origin
 *
 * Example 2:
 * Input: Points = [[1,3],[3,4],[2,-1]], k=2
 * output: [[1,3], [2,-1]]
 *
 * Solution:
 * We can use a Max Heap to find K points closest to the origin
 *
 * Time Complexity: O(N * logK)
 * SPace Complexity: O(K)
 */

// Data structure is very important

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int distFromOrigin() {
        // ignoring sqrt
        return (x * x) + (y * y);
    }
}

public class KClosestPointsToOrigin {
    public static List<Point> findClosestPoints(Point[] points, int k) {
        PriorityQueue<Point> maxHeap = new PriorityQueue<>((p1, p2) -> p2.distFromOrigin() - p1.distFromOrigin());
        // put first K points in the max heap
        for(int i = 0; i < k; i++)
            maxHeap.add(points[i]);

        // go through the remaining points of the input array, if a point is closer to the origin than the top K elements
        // of the max-heap, remove the top point from heap and add the point from the input array
        for(int i=k; i<points.length; i++) {
            if(points[i].distFromOrigin() < maxHeap.peek().distFromOrigin()) {
                maxHeap.poll();
                maxHeap.add(points[i]);
            }
        }

        // the heap has K points closest to the origin, return them in a list
        return new ArrayList<>(maxHeap);
    }

    public static void main(String[] args) {
        Point[] points = new Point[] {new Point(1,3), new Point(3,4), new Point(2,-1)};
        List<Point> result = KClosestPointsToOrigin.findClosestPoints(points, 2);
        System.out.println("Here are the K points closest the origin: ");
        for(Point p : result)
            System.out.println("[" + p.x + "," + p.y + "]");
    }
}
