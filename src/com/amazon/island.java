package com.amazon;

public class island {
    void traverse(TreeNode node) {
        traverse(node.left);
        traverse(node.right);
    }

    void dfs_1(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length;
        int n = grid[0].length;
        if(i<0 || j<0 || i>=m || j>=n) {
            // out of bound
            return;
        }
        if(visited[i][j]) {
            // visted (i,j)
            return;
        }
        // otherwise visit(i,j)
        visited[i][j] = true;
        dfs_1(grid, i-1, j, visited);
        dfs_1(grid, i+1, j, visited);
        dfs_1(grid, i, j-1, visited);
        dfs_1(grid, i, j+1, visited);
    }

    // 方向数组，分别代表上、下、左、右
    int[][] dirs = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};

    void dfs(int[][] grid, int i, int j, boolean[][] visited) {
        int m = grid.length, n = grid[0].length;
        if (i < 0 || j < 0 || i >= m || j >= n) {
            // 超出索引边界
            return;
        }
        if (visited[i][j]) {
            // 已遍历过 (i, j)
            return;
        }

        // 进入节点 (i, j)
        visited[i][j] = true;
        // 递归遍历上下左右的节点
        for (int[] d : dirs) {
            int next_i = i + d[0];
            int next_j = j + d[1];
            dfs(grid, next_i, next_j, visited);
        }
        // 离开节点 (i, j)
    }
}
