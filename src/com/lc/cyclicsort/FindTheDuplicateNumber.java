package com.lc.cyclicsort;

/**
 * Problem Statement:
 * We are given an unsorted array containing n+1 numbers taken from the range 1 to n. The array has only one duplicate
 * but it can be represented multiple times. Find that duplicate number without using any extra space. You are, however,
 * allowed to modify the input array.
 *
 * Example 1:
 * Input: [1, 4, 4, 3, 2]
 * output 4
 *
 * Example 2:
 * Input: [2, 1, 3, 3, 5, 4]
 * output 3
 *
 * Solution: This problem follows the Cyclic sort pattern and shares similarities with Find the Missing Number. Following
 * a similar approach, we will try to place each number on its correct index. Since there is only one duplicate, if while
 * swapping the number with its index both the numbers being swapped are same, we have found our duplicate !
 */

public class FindTheDuplicateNumber {
    public static int findNumber(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] != i+1) {
                if(nums[i] != nums[nums[i] - 1]) {
                    swap(nums, i, nums[i]-1);
                } else {
                    return nums[i];
                }
            } else {
                i++;
            }
        }
        return -1;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        System.out.println(FindTheDuplicateNumber.findNumber(new int[] {1, 4, 4, 3, 2}));
        System.out.println(FindTheDuplicateNumber.findNumber(new int[] {2, 1, 3, 3, 5, 4}));
        System.out.println(FindTheDuplicateNumber.findNumber(new int[] {2, 4, 1, 4, 4}));
    }
}
