package com.lc.cyclicsort;

import static java.util.Collections.swap;

/**
 * Problem Statement:
 * Find the Corrupt Pair:
 * We are given an unsorted array containing n numbers taken from the range 1 to n. The array originally contained all
 * the numbers from 1 to n, but due to a data error, one of the numbers got duplicated which also resulted in one number
 * going missing. Find both these numbers.
 *
 * Example 1:
 * Input: [3,1,2,5,2]
 * output: [2, 4]
 * Explanation: '2' is duplicated and '4' is missing
 *
 * Example 2:
 * Input: [3,1,2,3,6,4]
 * output: [3, 5]
 * Explanation: '3' is duplicated and '5' is missing
 *
 */

public class FindTheCorruptPair {
    public static int[] findNumbers(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] != nums[nums[i]-1]) {
                swap(nums, i, nums[i]-1);
            } else {
                i++;
            }
        }

        for(i = 0; i < nums.length; i++) {
            if(nums[i] != i+1) {
                return new int[] {nums[i], i+1};
            }
        }
        return  new int[] {-1, -1};
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = FindTheCorruptPair.findNumbers(new int[] {3,1,2,5,2});
        System.out.println(nums[0] + "," + nums[1]);
        nums = FindTheCorruptPair.findNumbers(new int[] {3,1,2,3,6,4});
        System.out.println(nums[0] + "," + nums[1]);

    }
}
