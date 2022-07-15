package com.lc.twopointers;

import java.util.Arrays;

/**
 * Problem statement:
 * Given an array of unsorted numbers and a target number, find a triplet in the array whose sum is as close to the
 * target number as possible, return the sum of the triplet. If there are more than one such triplet, return the
 * sum of the triplet with the smallest sum
 *
 * Example 1:
 * Input:[-2, 0, 1, 2], target = 2
 * output: 1
 * Explanation: The triplet [-2,1,2] has the closest sum to the target
 *
 * Example 2:
 * Input:[-3, -1, 1, 2], target = 1
 * output: 0
 * Explanation: The triplet [-3,1,2] has the closest sum to the target
 *
 * Example 3:
 * Input:[1, 0, 1, 1], target = 100
 * output: 3
 * Explanation: The triplet [1,1,1] has the closest sum to the target
 */

public class TripleSumCloseToTarget {
    public static int searchTriplet(int[] arr, int targetSum) {
        if(arr == null || arr.length < 3) throw new IllegalArgumentException();
        Arrays.sort(arr);
        int smallestDifferent = Integer.MAX_VALUE;
        for(int i = 0; i < arr.length-2; i++) {
            int left = i+1, right = arr.length - 1;
            while(left < right) {
                // comparing the sum of three numbers to the targetSum can cause overflow
                // so, we will try to find a target difference
                int targetDiff = targetSum - arr[i] - arr[left] - arr[right];
                if (targetDiff == 0) // we have found a triplet with an exact sum
                    return targetSum - targetDiff; // return sum of all the numbers

                // the second part of the above if is to handle the smallest sum when we have more than one solution
                if (Math.abs(targetDiff) < Math.abs(smallestDifferent)
                        || (Math.abs(targetDiff) == Math.abs(smallestDifferent) && targetDiff > smallestDifferent)) {
                    smallestDifferent = targetDiff;// save the closest and the biggest difference
                }

                if (targetDiff > 0) left++;
                else right--;
            }
        }
        return targetSum - smallestDifferent;
    }

    public static void main(String[] args) {
        System.out.println(TripleSumCloseToTarget.searchTriplet(new int[] {-2, 0, 1, 2},2));
        System.out.println(TripleSumCloseToTarget.searchTriplet(new int[] {-3, -1, 1, 2},1));
        System.out.println(TripleSumCloseToTarget.searchTriplet(new int[] {1, 0, 1, 1},100));
    }
}
