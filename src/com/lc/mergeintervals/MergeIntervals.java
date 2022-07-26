package com.lc.mergeintervals;

/*
Problem Statement:

Given a list of intervals, merge all the overlapping intervals to produce a list that has only mutually exclusive intervals.

Example 1:

Intervals: [[1,4], [2,5], [7,9]]
Output: [[1,5], [7,9]]
Explanation: Since the first two intervals [1,4] and [2,5] overlap, we merged them into
one [1,5].

Example 2:

Intervals: [[6,7], [2,4], [5,9]]
Output: [[2,4], [5,9]]
Explanation: Since the intervals [6,7] and [5,9] overlap, we merged them into one [5,9].

Example 3:

Intervals: [[1,4], [2,6], [3,5]]
Output: [[1,6]]
Explanation: Since all the given intervals overlap, we merged them into one.

Solution:
Let's take the example of two intervals ('a' and 'b') such that a.start <= b.start. There are four possible scenarios:
Our goal is to merge the intervals whenever they overlap. For the above - mentioned three overlapping scenarios, this
is how we will merge them:

The diagram above clearly shows a merging approach. Our algorithm will look like this:
1. Sort the intervals on the start time to ensure a.start <= b.start
2. If 'a' overlaps 'b' (i.e. b.start <= a.end), we need to merge them into a new interval 'C' such that:
         c.start = a.start
         c.end = max(a.end, b.end)
3. We will keep repeating the above two steps to merge 'c' with the next interval if it overlaps with 'c'

Time complexity: O(N * logN) where is N is the total number of intervals because sort the intervals take O(N*logN)
Space complexity: O(N) - as we need to return a list containing all the merged intervals.
                  O(N) - need O(N) space for sorting.
For Java, depending on its version, Collections.sort() either uses Merge sort or Timsort, and both algorithms need
O(N) space. Overall, we need a space complexity of O(N)

Similar Problems:
Problem 1: Given a set of intervals, find out if any two intervals overlap.
Example:
Intervals: [[1,4],[2,5],[7,9]]
output: True
Explanation: Intervals [1,4] and [2,5] overlap
Solution: We can follow the same approach as discussed above to find if any two intervals overlap
 */

import java.util.*;

class Interval {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
};

class MergeIntervals {

    public static List<Interval> merge(List<Interval> intervals) {
        if (intervals.size() < 2)
            return intervals;

        // sort the intervals by start time
        Collections.sort(intervals, (a, b) -> Integer.compare(a.start, b.start));

        List<Interval> mergedIntervals = new LinkedList<>();
        Iterator<Interval> intervalItr = intervals.iterator();

        // Get the first interval
        Interval interval = intervalItr.next();
        // Use start and end to denote current Interval
        int start = interval.start;
        int end = interval.end;

        while (intervalItr.hasNext()) {
            interval = intervalItr.next();
            if (interval.start <= end) { // overlapping intervals, adjust the 'end'
                end = Math.max(interval.end, end);
            } else { // non-overlapping interval, add the previous interval and reset
                mergedIntervals.add(new Interval(start, end));
                start = interval.start;
                end = interval.end;
            }
        }
        // add the last interval
        mergedIntervals.add(new Interval(start, end));

        return mergedIntervals;
    }

    public static void main(String[] args) {
        List<Interval> input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 5));
        input.add(new Interval(7, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(6, 7));
        input.add(new Interval(2, 4));
        input.add(new Interval(5, 9));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();

        input = new ArrayList<Interval>();
        input.add(new Interval(1, 4));
        input.add(new Interval(2, 6));
        input.add(new Interval(3, 5));
        System.out.print("Merged intervals: ");
        for (Interval interval : MergeIntervals.merge(input))
            System.out.print("[" + interval.start + "," + interval.end + "] ");
        System.out.println();
    }
}
