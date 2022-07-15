package com.amazon;

public class LC1254_NumberOfClosedIslands {
    public int closedIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for(int j=0; j<n; j++) {
            // Flood the island in the upper side
            dfs(grid,0, j);
            // Flood the island in the bottom side
            dfs(grid, m-1, j);
        }
        for(int i=0; i<m; i++) {
            // Flood the island in the left side
            dfs(grid, i, 0);
            // Flood the island in the right side
            dfs(grid, i, n-1);
        }
        int res = 0;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j] == 0) {
                    res++;
                    dfs(grid,i,j);
                }
            }
        }
        return res;
    }

    // start from (i,j), floodfill
    void dfs(int[][] grid, int i, int j) {
        int m=grid.length;
        int n=grid[0].length;
        if(i<0 || j<0 || i>=m || j>=n) {
            return;
        }
        if(grid[i][j] == 1) {
            // It is already water
            return;
        }
        // Make it water
        grid[i][j] = 1;
        dfs(grid, i+1, j);
        dfs(grid, i-1, j);
        dfs(grid, i, j+1);
        dfs(grid, i, j-1);
    }
}
