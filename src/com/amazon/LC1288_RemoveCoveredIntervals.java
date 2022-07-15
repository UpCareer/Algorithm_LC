package com.amazon;

import java.util.Arrays;

public class LC1288_RemoveCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        // Sorting (increase) based on starting point,
        // If starting point is same, Sorting (decrease) based on the ending point
        Arrays.sort(intervals, (a, b) -> {
            if(a[0] == b[0]) {
                return b[1] - a[1];
            }
            return a[0] - b[0];
        });

        // record the merge one (starting point and ending point)
        int left = intervals[0][0];
        int right = intervals[0][1];

        int res = 0;
        for(int i = 1; i < intervals.length; i++) {
            int[] interval = intervals[i];
            // Case 1: CoveredInterval
            if (left <= interval[0] && right >= interval[1]) {
                res++;
            }
            // Case 2: Intersect Interval, merge
            if (right >= interval[0] && right <= interval[1]) {
                right = interval[1];
            }
            // case 3: No overlap, update starting point and ending point
            if (right < interval[0]) {
                left = interval[0];
                right = interval[1];
            }
        }
        return intervals.length - res;
    }
}
