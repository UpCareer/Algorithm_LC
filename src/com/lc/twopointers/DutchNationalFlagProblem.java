package com.lc.twopointers;

/**
 * Problem Statement:
 * Given an array containing 0s, 1s and 2s, sort the array in-place. You should treat numbers of the array as objects,
 * hence, we cannot count 0s, 1s, and 2s to recreate the array
 * The flag of the Netherlands consists of three colors: red, white and blue; and since our input array also consists of
 * three different numbers that is why it is called Dutch National Flag problem.
 *
 * Example 1:
 * Input: [1, 0, 2, 1, 0]
 * output: [0, 0, 1, 1, 2]
 *
 * Example 1:
 * Input: [2, 2, 0, 1, 2, 0]
 * output: [0, 0, 1, 2, 2, 2]
 */

public class DutchNationalFlagProblem {
    public static void sort(int[] arr) {
        // all elements < low are 0 and all elements > high are 2
        // all elements from >= low < i are 1
        int low = 0;
        int high = arr.length - 1;
        for(int i=0; i<=high; ) {
            if(arr[i] == 0) {
                swap(arr, i, low);
                // increment i and low;
                i++;
                low++;
            } else if (arr[i] == 1) {
                i++;
            } else {
                // the case for arr[i] == 2
                swap(arr, i, high);
                // decrement high only, after the swap the nubmer at index i could be 0, 1 or 2
                high--;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,0,2,1,0};
        DutchNationalFlagProblem.sort(arr);
        for(int num: arr) {
            System.out.println(num + " ");
        }
        System.out.println();

        arr = new int[] {2, 2, 0, 1, 2, 0};
        DutchNationalFlagProblem.sort(arr);
        for(int num: arr) {
            System.out.println(num + " ");
        }
        System.out.println();
    }
}
