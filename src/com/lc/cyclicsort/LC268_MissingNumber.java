package com.lc.cyclicsort;

/**
Given an array containing n distinct numbers taken from 0, 1, 2, …, n, find the one that is missing from the array.

        Example 1:

        Input: [3, 0, 1]
        Output: 2
        Example 2:

        Input: [9, 6, 4, 2, 3, 5, 7, 0, 1]
        Output: 8
        Note:

        Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space
        complexity?

 Problem Statement:
 We are given an array containing n distinct numbers taken from the range 0 to n. Since the array has only n numbers
 of the total n+1 numbers, find the missing number.

 Example 1:
 Input: [4, 0, 3,1]
 output: 2

 Example 2:
 Input: [8,3,5,2,4,6,0,1]
 output: 7
*/
public class LC268_MissingNumber {

    public static int findMissingNumber(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] < nums.length && nums[i] != nums[nums[i]]) {
                swap(nums, i, nums[i]);
            } else {
                i++;
            }
        }

        // find the first number missing from its index, that will be our required number
        for(i = 0; i < nums.length; i++) {
            if(nums[i] != i) return i;
        }
        return nums.length;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(LC268_MissingNumber.findMissingNumber(new int[] {4, 0, 3, 1}));
        System.out.println(LC268_MissingNumber.findMissingNumber(new int[] {8, 3, 5, 2, 4, 6, 0, 1}));
    }

}
