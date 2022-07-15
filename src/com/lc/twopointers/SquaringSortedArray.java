package com.lc.twopointers;

/**
 * Problem Statement:
 *
 * Given a sorted array, create a new array containing squares of all the numbers of the input array in the sorted order
 *
 * Example 1:
 * Input: [-2, -1, 0, 2, 3]
 * output: [0, 1, 4, 4, 9]
 *
 */

public class SquaringSortedArray {
    public static int[] makeSquares(int[] arr) {
        int n = arr.length;
        int[] squares = new int[n];
        int highestSquareIndex = n-1;
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            int leftSquare = arr[left] * arr[left];
            int rightSquare = arr[right] * arr[right];
            if(leftSquare > rightSquare) {
                squares[highestSquareIndex--] = leftSquare;
                left++;
            } else {
                squares[highestSquareIndex--] = rightSquare;
                right--;
            }
        }
        return squares;
    }

    public static void main(String[] args) {
        int[] result = SquaringSortedArray.makeSquares(new int[] {-2, -1, 0, 2, 3});
        for(int num : result) {
            System.out.println(num + " ");
        }
        System.out.println();
    }
}
