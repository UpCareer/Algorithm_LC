package com.lc.KwayMerge;

import java.util.PriorityQueue;

/**
 * Problem Statement:
 * Given an N * N matrix where each row and column is sorted in ascending order, find the Kth smallest element in the
 * matrix.
 *
 * Example 1:
 * Input: Matrix = [
 *      [2,6,8],
 *      [3,7,10],
 *      [5,8,11]
 * ],
 * K = 5
 * Output: 7
 * Explanation: The 5th smallest number in the matrix is 7
 *
 * Solution: This problem follows the K-way merge pattern and can be easily converted to Kth Smallest Number in M
 * Sorted Lists. As each row (or column) of the given matrix can be seen as a sorted list, we essentially need to find
 * the Kth smallest number in N sorted lists
 *
 * Time Complexity:
 * First, we inserted at most K or one element from each of the N rows, which will take O(min(K,N)).
 * Then we went through at most K elements in the matrix and remove/add one element in the heap in each step.
 * As we cannot have more than N elements in the heap in any condition, therefore, the overall time complexity of the
 * above algorithm will be O(min(K,N) + K*logN)
 *
 * Space Complexity:
 * The space complexity will be O(N) because, in the worst case, our min-heap will be storing one number from each of
 * the N rows.
 *
 * An Alternate Solution:
 * Since each row and column of the matrix is sorted, it is possible to use Binary Search to find the Kth smallest number?
 * The biggest problem to use Binary Search, in this case, is that we do not have a straightforward sorted array, instead,
 * we have a matrix. As we remember, in Binary Search, we calculate the middle index of the search space (1 to N) and see
 * if our required number is pointed out by the middle index; if not we either search in the lower half or the upper half.
 * In a sorted matrix, we cannot really find a middle. Even if we do consider some index as middle, it is not straightforward
 * to find the search space containing numbers bigger or smaller than the number pointed out by the middle index
 *
 *
 */

// Node is Data Structure; very important for this problem
class MatrixNode {
    int row;
    int col;
    MatrixNode(int row, int col) {
        this.row = row;
        this.col = col;
    }
}
public class KthSmallestNumberInSortedMatrix {

    public static int findKthSmallest(int[][] matrix, int k) {
        PriorityQueue<MatrixNode> minHeap = new PriorityQueue<MatrixNode>(
                (n1, n2) -> matrix[n1.row][n1.col] - matrix[n2.row][n2.col]
        );

        // put the 1st element of each row in the min heap
        // we don't need to push more than K elements in the heap
        for(int i = 0; i < matrix.length; i++) {
            minHeap.add(new MatrixNode(i,0));
        }

        // take the smallest (top) element from the min heap, if the running count is equal to k return the number
        // if the row of the top element has more elements, add the next element to the heap
        int numberCount = 0;
        int result = 0;
        while(!minHeap.isEmpty()) {
            MatrixNode node = minHeap.poll();
            result = matrix[node.row][node.col];
            if(++numberCount == k)
                break;
            node.col++;
            if(matrix[0].length > node.col)
                minHeap.add(node);
        }
        return result;
    }

    public static void main(String[] args) {
        int matrix[][] = {{2,6,8},{3,7,10},{5,8,11}};
        int result = KthSmallestNumberInSortedMatrix.findKthSmallest(matrix, 5);
        System.out.println("Kth smallest number is: " + result);
    }
}
