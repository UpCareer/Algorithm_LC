package com.lc.twopointers;

import java.util.Arrays;

/**
 * Problem Statement:
 * Given an array arr of unsorted numbers and a target sum, count all triplets in it such that arr[i] + arr[j] + arr[k]
 * < target where i, j, and k are three different indices. Write a function to return the count of such triplets.
 *
 * Example 1:
 * Input: [-1, 0, 2, 3], target = 3
 * output: 2
 * Explanation: There are two triplets whose sum is less that the target: [-1, 0, 3], [-1, 0, 2]
 *
 * Example 1:
 * Input: [-1, 4, 2, 1, 3], target = 5
 * output: 4
 * Explanation: There are four triplets whose sum is less that the target: [-1, 1, 4], [-1, 1, 3],
 *
 * Time Complexity: O(N*logN + N*N)
 * Space Complexity: O(N)
 */

public class TripletsWithSmallerSum {
    public static int searchTriplets(int[] arr, int target) {
        Arrays.sort(arr);
        // List<List<Integer>> triplets = new ArrayList<>();
        int count = 0;
        for(int i=0; i < arr.length; i++) {
            count += searchPair(arr, target - arr[i], i);
        }
        return count;
    }

    private static int searchPair(int[] arr, int targetSum, int first) {
        int count = 0;
        int left = first + 1;
        int right = arr.length - 1;
        while(left < right) {
            if(arr[left] + arr[right] < targetSum) { // found the triplet
                 // since arr[right] >= arr[left], therefore, we can replace arr[right] by any number between
                // left and right to get a sum less that the target sum
                /**
                 * for(int i = right; i > left; i--)
                 *   triplets.add(Arrays.asList(arr[first], arr[left], arr[i]))
                 */
                count += right - left;
                left++;
            } else {
                right--;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(TripletsWithSmallerSum.searchTriplets(new int[] {-1, 0, 2, 3}, 3));
        System.out.println(TripletsWithSmallerSum.searchTriplets(new int[] {-1, 4, 2, 1, 3}, 5));
    }
}
