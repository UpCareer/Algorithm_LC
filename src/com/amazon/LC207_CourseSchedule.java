package com.amazon;

import java.util.LinkedList;
import java.util.List;

public class LC207_CourseSchedule {

    // 记录一次 traverse 递归经过的节点
    boolean[] onPath;
    // 记录遍历过的节点，防止走回头路
    boolean[] visited;
    // 记录图中是否有环
    boolean hasCycle = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<Integer>[] graph = buildGraph(numCourses, prerequisites);
        visited = new boolean[numCourses];
        onPath = new boolean[numCourses];

        for(int i=0; i < numCourses; i++) {
            traverse(graph, i);
        }

        return !hasCycle;
    }

    private void traverse(List<Integer>[] graph, int start) {
        if(onPath[start]) {
            // there is a circle
            hasCycle = true;
        }

        if(visited[start] || hasCycle) {
            return;
        }

        visited[start] = true;
        onPath[start] = true;
        for(int t : graph[start]) {
            traverse(graph, t);
        }
        onPath[start] = false;
        return;
    }

    // Adjacency List vs Adjacency Matrix
    private List<Integer>[] buildGraph(int numCourses, int[][] prerequisites) {
        // There are numCourses vertex in the Graph
        List<Integer>[] graph = new LinkedList[numCourses];
        for(int i=0; i<numCourses; i++) {
            graph[i] = new LinkedList<>();
        }
        //  prerequisites = [[1,0],[0,1]]
        for(int[] edge : prerequisites) {
            int from = edge[1];
            int to = edge[0];
            graph[from].add(to);
        }
        return graph;
    }
}
