/*
package com.lc.az;

public class LC79_WordSearch {

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        int visited[][] = new int[row][col];
        for(int i=0; i<row; i++) {
            for(int j=0; j<col; j++) {
                // Entry Point
                if(board[i][j] == word.charAt(0)) {
                    return dfs();
                }
            }
        }
        return false;
    }

}
*/
