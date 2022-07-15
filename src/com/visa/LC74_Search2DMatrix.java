package com.visa;

/**
 * Write an efficient algorithm that searches for a value target in an m x n integer matrix.
 * This matrix has the following properties:
 *      Integers in each row are sorted from left to right.
 *      The first integer of each row is greater than the last integer of the previous row.
 *
 * Solution: 只要知道二维数组的的行数 m 和列数 n，二维数组的坐标 (i, j) 可以映射成一维的 index = i * n + j；
 *           反过来也可以通过一维 index 反解出二维坐标 i = index / n, j = index % n。
 */

public class LC74_Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int left = 0;
        int right = m * n - 1;
        while(left <= right) {
            int mid = left + (right - left)/2;
            if(get(matrix, mid) == target) {
                return true;
            } else if (get(matrix, mid) < target) {
                left = mid + 1;
            } else if (get(matrix, mid) > target) {
                right = mid - 1;
            }
        }
        return false;
    }

    int get(int[][] matrix, int index) {
        int m = matrix.length;
        int n = matrix[0].length;
        int i = index/n;
        int j = index % n;
        return matrix[i][j];
    }
}
