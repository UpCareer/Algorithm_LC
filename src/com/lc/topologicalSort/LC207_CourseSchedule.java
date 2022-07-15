//package com.lc.topologicalSort;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//
///**
// * There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
// * You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that you must take course bi first
// * if you want to take course ai.
// *
// * For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
// * Return true if you can finish all courses. Otherwise, return false.
// *
// *
// *
// * Example 1:
// *
// * Input: numCourses = 2, prerequisites = [[1,0]]
// * Output: true
// * Explanation: There are a total of 2 courses to take.
// * To take course 1 you should have finished course 0. So it is possible.
// * Example 2:
// *
// * Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
// * Output: false
// * Explanation: There are a total of 2 courses to take.
// * To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1.
// * So it is impossible.
// */
//
//public class LC207_CourseSchedule {
//   /* public boolean canFinish(int numCourses, int[][] prerequisites) {
//        int[] res = new int[numCourses];
//        int[] indegree = new int[numCourses];
//
//        // get the indegree for each course
//        for(int[] pre : prerequisites) {
//            indegree[pre[0]]++;
//        }
//
//        // put courses with indegree == 0 to queue
//        Queue<Integer> queue = new LinkedList<Integer>();
//        for(int i = 0; i < numCourses; i++) {
//            if(indegree[i] == 0) {
//                queue.offer(i);
//            }
//        }
//
//        // execute the course
//        int i = 0;
//        while(!queue.isEmpty()) {
//            Integer cur = queue.poll();
//            res[i++] = cur;
//
//            // remove the pre = curr : update the indegree
//            for(int[] pre : prerequisites) {
//                if(pre[1] == cur) {
//                    indegree[pre[0]]--;
//                    if(indegree[pre[0]] == 0) {
//                        queue.offer(pre[0]);
//                    }
//                }
//            }
//        }
//        return i == numCourses ? true : false;
//    }*/
//
//    List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
//        List<Integer>[] graph = new LinkedList<>[numCourses];
//        for(int i = 0; i < numCourses; i++) {
//            graph[i] = new LinkedList<Integer>();
//        }
//        for(int[] edge : prerequisites) {
//            int from = edge[1];
//            int to = edge[0];
//            graph[from].add(to);
//        }
//        return graph;
//    }
//
//    // Set up Graph
//    public boolean canFinish(int numCourses, int[][] prerequisites) {
//        int[] res = new int[numCourses];
//        int[] indegree = new int[numCourses];
//
//        // get the indegree for each course
//        for(int[] pre : prerequisites) {
//            indegree[pre[0]]++;
//        }
//
//        // build the graph
//        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
//
//        // put courses with indegree == 0 to queue
//        Queue<Integer> queue = new LinkedList<Integer>();
//        for(int i = 0; i < numCourses; i++) {
//            if(indegree[i] == 0) {
//                queue.offer(i);
//            }
//        }
//
//        // execute the course
//        int i = 0;
//        while(!queue.isEmpty()) {
//            Integer cur = queue.poll();
//            res[i++] = cur;
//
//            // remove the pre = curr : update the indegree
//            for(Integer tmp : graph[cur]) {
//                indegree[tmp]--;
//                if(indegree[tmp] == 0) {
//                    queue.offer(tmp);
//                }
//            }
//        }
//        return i == numCourses ? true : false;
//    }
//
//
//}
