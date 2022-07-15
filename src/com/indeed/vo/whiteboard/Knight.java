package com.indeed.vo.whiteboard;

/*
https://leetcode.com/problems/minimum-knight-moves/

Given an infinite chessboard, find minimum no. of steps for a knight to reach from the origin to (x, y).

Accepted solution: Bidirectional BFS.
There's also constant time solution.

Follow-up:
A list of forbidden coordinates are introduced where knight can’t reach. Handle this in your code. Make sure the infinite loop is handled since the board is infinite.

Related problems:

https://leetcode.com/problems/escape-a-large-maze

Suppose if branching factor of tree is b and distance of goal vertex from source is d, then the normal BFS/DFS searching complexity would be O(bexp(d)).
 On the other hand, if we execute two search operation then the complexity would be O(bd/2) for each search and total complexity would be O(b exp(d/2 )+ b exp(d/2))
  which is far less than O(bd).
 */

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Knight {

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public boolean isInside(int x, int y, int size) {
        if(x >= 1 && x <= size && y >= 1 && y <= size) {
            return true;
        }
        return false;
    }

    // BFS: shortest path in unweighted graph
    public int getShortestPath(int[]source, int[] target, int[][] blocked, int boardSize) {
        // Set up a Set data structure to keep blocked points
        int[][] dirs = {{-2, -1}, {-2, 1},{2, 1},{2, -1},{-1, 2},{-1, -2},{1, 2},{1, -2}};
        Set<String> block = new HashSet<>();
        for(int[] p : blocked) {
            block.add(p[0] + "," + p[1]);
        }

        // Do BFS from source until find the target
        Queue<Point> q = new LinkedList<>();
        Point start = new Point(source[0], source[1]);
        q.offer(start);
        // Set up a Set data structure to keep visited point
        Set<String> visited = new HashSet<>();
        visited.add(source[0] + "," + source[1]);

        int move = 0;

        while(!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Point cur = q.poll();
                // reach target
                if (cur.x == target[0] && cur.y == target[1]) {
                    return move;
                }

                // loop for all reachable points
                for (int[] dir : dirs) {
                    int next_x = cur.x + dir[0];
                    int next_y = cur.y + dir[1];

                    // check whether next point is able to push to the queue
                    if (!block.contains(next_x + "," + next_y) && !visited.contains(next_x + "," + next_y) &&
                            isInside(next_x, next_y, boardSize)) {
                        Point next = new Point(next_x, next_y);
                        q.offer(next);
                        visited.add(next_x + "," + next_y);
                    }
                }
            }
            move++;
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
