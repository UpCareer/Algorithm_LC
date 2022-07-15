package com.lc.topologicalSort;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
 * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first
 * if you want to take course ai.
 *
 * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
 * Return the ordering of courses you should take to finish all courses. If there are many valid answers,
 * return any of them. If it is impossible to finish all courses, return an empty array.
 *
 * Example 1:
 *
 * Input: numCourses = 2, prerequisites = [[1,0]]
 * Output: [0,1]
 * Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0.
 * So the correct course order is [0,1].
 *
 * Example 2:
 *
 * Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
 * Output: [0,2,1,3]
 * Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
 * Both courses 1 and 2 should be taken after you finished course 0.
 * So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
 *
 * Example 3:
 *
 * Input: numCourses = 1, prerequisites = []
 * Output: [0]
 */


public class LC210_CourseScheduleII {

    public List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        LinkedList[] graph = new LinkedList[numCourses];
        for(int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for(int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            graph[from].add(to);
        }

        return graph;
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        int[] indegree = new int[numCourses];

        // initialize the indegree array
        for(int[] pre : prerequisites) {
            indegree[pre[0]]++;
        }

        List<Integer>[] graph = buildGraph(numCourses, prerequisites);

        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 0; i < numCourses; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }
        int index = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();
            res[index++] = cur;
            for(Integer tmp : graph[cur]) {
                indegree[tmp]--;
                if(indegree[tmp] == 0) {
                    queue.offer(tmp);
                }
            }
        }
        return index == numCourses ? res : new int[numCourses];
    }
}
