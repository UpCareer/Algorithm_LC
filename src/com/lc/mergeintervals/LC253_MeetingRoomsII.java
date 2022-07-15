package com.lc.mergeintervals;

/*
Given an array of meeting time intervals consisting of start and end times [[s1, e1], [s2, e2], ...] (si < ei), find the
minimum number of conference rooms required

Example 1:
Input: [[0,30],[5,10],[15,20]]
Output: 2
 */

import java.util.Arrays;
import java.util.PriorityQueue;

public class LC253_MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals == null || intervals.length == 0) {
            return 0;
        }

        // Sort the intervals by starting times
        Arrays.sort(intervals, (a,b)->(a[0]-b[0]));

        // Set up Min heap data structure to store the intervals by ending time, Min heap size is trace the min
        // conference rooms
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // initialize the min heap
        pq.add(intervals[0][1]);
        for(int i=1; i<intervals.length-1; i++) {
            int[] curInterval = intervals[i];
            if(pq.peek() < curInterval[0]) {
                pq.poll();
            }
            pq.add(curInterval[1]);
        }
        return pq.size();
    }
}
