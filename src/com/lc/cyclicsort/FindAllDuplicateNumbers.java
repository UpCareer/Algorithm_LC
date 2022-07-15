package com.lc.cyclicsort;

import java.util.ArrayList;
import java.util.List;

/**
 * Problem Statement:
 *
 * We are given an unsorted array containing n numbers taken from the range 1 to n. The array has some numbers appearing
 * twice, find all these duplicate numbers without using any extra space.
 *
 * Example 1:
 * Input: [3, 4, 4, 5, 5]
 * output: [4, 5]
 *
 * Example 2:
 * Input: [5, 4, 7, 2, 3, 5, 3]
 * output: [3 ,5]
 */

public class FindAllDuplicateNumbers {

    public static List<Integer> findNumbers(int[] nums) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] != nums[nums[i]-1]) {
                swap(nums, i, nums[i]-1);
            } else {
                i++;
            }
        }

        List<Integer> duplicateNumbers = new ArrayList<>();
        for(i = 0; i < nums.length; i++) {
            if(nums[i] != i+1) {
                duplicateNumbers.add(nums[i]);
            }
        }

        return duplicateNumbers;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        List<Integer> duplicates = FindAllMissingNumbers.findNumbers(new int[] {3, 4, 4, 5, 5});
        System.out.println("Duplicates are: " + duplicates);

        duplicates = FindAllMissingNumbers.findNumbers(new int[] {5, 4, 7, 2, 3, 5, 3});
        System.out.println("Duplicates are: " + duplicates);
    }

}
