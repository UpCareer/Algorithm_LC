package com.amazon;

public class LC1020_NumberofEnclaves {
    public int numEnclaves(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int res = 0;
        // FloodFill the island in the boundary
        for(int i=0; i<m; i++) {
            // FloodFill the island in the left side
            dfs(grid, i, 0);
            // FloodFill the island in the right side
            dfs(grid, i, n-1);
        }
        for(int j=0; j<n; j++) {
            // FloodFill the island in the upper side
            dfs(grid, 0, j);
            dfs(grid, m-1, j);
        }
        for(int i=0; i<m; i++) {
            for(int j=0; j<n; j++) {
                if(grid[i][j]==1) res++;
            }
        }
        return res;
    }

    void dfs(int[][] grid, int i, int j) {
        int m=grid.length;
        int n=grid[0].length;
        // Corner Case
        if(i<0 || j<0 || i>=m || j>=n) {
            return;
        }
        if(grid[i][j]==0) {
            return;
        }
        grid[i][j]=0;
        dfs(grid, i-1, j);
        dfs(grid, i+1, j);
        dfs(grid, i, j-1);
        dfs(grid, i, j+1);
    }


}
