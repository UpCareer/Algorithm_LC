package com.indeed.karat;

/*
第一题： transponse a string using a matrix, input string, row number, column number 同时保证 row * col == string.length();
例子，input = "One does not simply walk into Mordor" 转换成, row 6, col 6
One do
es not
simpl
y walk
into
Mordor
然后从列读 输出string Oe y Mnss ioe iwnr nmatddoploootlk r
testing case 还有 "1.21 gigawatts!" row 3, col 5; row 5, col 3
其实题目应该很简单，但是实在是慌了，浪费了些时间，下面是当时写完的code
//Time O(N * N)
//Space O(N)

 */

public class TransposeString {

    public static void main(String[] args) {
        String input = "One does not simply walk into Mordor";
        String result = transponse(input, 6, 6);
        System.out.println(result);
    }

    public static String transponse(String input, int row, int col) {
        String result = "";
        if(input.length() == 0) {
            return result;
        }

        // Step 1: Set up Matrix with row * col to represent the String
        int index = 0;
        char[][] matrix = new char[row][col];
        for(int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                matrix[i][j] = input.charAt(index++);
            }
        }

        StringBuilder sb = new StringBuilder();

        // Step 2: transponse the maxtrix
        for(int i=0; i<col; i++) {
            for(int j=0; j<row; j++) {
                sb.append(matrix[j][i]);
            }
        }
        return sb.toString();
    }
}
