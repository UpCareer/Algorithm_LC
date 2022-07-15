package com.lc.mergeintervals;

/*
Given an array of meeting time intervals where intervals[i] = [starti, endi], determine if a person could attend all
meetings.

Example 1:
Input: intervals = [[0,30],[5,10],[15,20]]
output: false
 */

import java.util.Arrays;

public class LC252_MeetingRooms {

    public boolean canAttendMeetings(int[][] intervals) {
        // base case
        int n = intervals.length;
        if(n == 0) return true;

        // Sort the intervals by start time
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        // return false if there is overlapping
        for(int i = 0; i < n-1; i++) {
            int[] meeting1 = intervals[i];
            int[] meeting2 = intervals[i+1];
            if(meeting1[1] > meeting2[0]) {
                return false;
            }
        }
        return true;
    }
}
