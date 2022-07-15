package com.lc.cyclicsort;

/**
 * Problem Statement:
 * Given an unsorted array containing numbers, find the smallest missing positive number in it.
 *
 * Example 1:
 * Input: [-3,1,5,4,2]
 * output: 3
 * Explanation: The smallest missing positive number is '3'
 *
 * Example 2:
 * Input: [3,-2,0,1,2]
 * output: 4
 *
 * Solution: ignore all numbers that are out of the range of the array(i.e., all negative numbers and all numbers
 * greater than or equal to the length of the array)
 */
public class FindTheSmallestMissingPositiveNumber {
    public static int findNumber(int[] nums) {
        int i = 0;
        while( i < nums.length ) {
            if(nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }

        for(i = 0; i < nums.length; i++) {
            if(nums[i] != i + 1) return i+1;
        }

        return nums.length + 1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(FindTheSmallestMissingPositiveNumber.findNumber(new int[] {-3,1,5,4,2}));
        System.out.println(FindTheSmallestMissingPositiveNumber.findNumber(new int[] {3,-2,0,1,2}));
        System.out.println(FindTheSmallestMissingPositiveNumber.findNumber(new int[] {3,2,5,1}));
    }
}
