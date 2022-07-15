package com.lc.mergeintervals;

/**
 * Given an array of jobs with different time requirements, where each job consists of start time, end time and CPU load.
 * The task is to find the maximum CPU load at any time if all jobs are running on the same machine.
 *
 * Examples:
 *
 * Input: jobs[] = {{1, 4, 3}, {2, 5, 4}, {7, 9, 6}}
 * Output: 7
 * Explanation:
 * In the above-given jobs, there are two jobs which overlaps.
 * That is, Job [1, 4, 3] and [2, 5, 4] overlaps for the time period in [2, 4]
 * Hence, the maximum CPU Load at this instant will be maximum (3 + 4 = 7).
 *
 * Input: jobs[] = {{6, 7, 10}, {2, 4, 11}, {8, 12, 15}}
 * Output: 15
 * Explanation:
 * Since, There are no jobs that overlaps.
 * Maximum CPU Load will be – max(10, 11, 15) = 15
 *
 * This problem is generally the application of the Merge Intervals.
 * Approach: The idea is to maintain min-heap for the jobs on the basis of their end times.
 * Then, for each instance find the jobs which are complete and remove them from the Min-heap.
 * That is, Check that the end-time of the jobs in the min-heap had ended before the start time of the current job.
 * Also at each instance, find the maximum CPU Load on the machine by taking the sum of all the jobs that are present
 * in the min-heap.
 *
 * For Example:
 *
 * Given Jobs be {{1, 4, 3}, {2, 5, 4}, {7, 9, 6}}
 * Min-Heap - {}
 *
 * Instance 1:
 * The job {1, 4, 3} is inserted into the min-heap
 * Min-Heap - {{1, 4, 3}},
 * Total CPU Load  = 3
 *
 * Instance 2:
 * The job {2, 5, 4} is inserted into the min-heap.
 * While the job {1, 4, 3} is still in the CPU,
 * because end-time of Job 1 is greater than
 * the start time of the new job {2, 5, 4}.
 * Min-Heap - {{1, 4, 3}, {2, 5, 4}}
 * Total CPU Load = 4 + 3 = 7
 *
 * Instance 3:
 * The job {7, 9, 6} is inserted into the min-heap.
 * After popping up all the other jobs because their
 * end time is less than the start time of the new job
 * Min Heap - {7, 9, 6}
 * Total CPU Load =  6
 *
 * Maximum CPU Load = max(3, 7, 6) = 7
 */

public class MaximumCPULoad {
}
