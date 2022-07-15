package com.lc.cyclicsort;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Give an unsorted array containing numbers and a number K, find the first K missing positive numbers in the array
 *
 * Example 1:
 * Input: [3,-1,4,5,5], k = 3
 * output: [1,2,6]
 * Explanation: The smallest missing positive numbers are 1, 2 and 6.
 *
 * Example 2:
 * Input: [2,3,4], k = 3
 * output: [1,5,6]
 * Explanation: The smallest missing positive numbers are 1, 5 and 6.
 *
 * Example 3:
 * Input: [-2,-3,4], k = 2
 * output: [1,2]
 * Explanation: The smallest missing positive numbers are 1 and 2.
 *
 * Solution: place the numbers on their correct indices and ignore all numbers that are out of the range of the array.
 * once we are done with the cyclic sort we will iterate through the array to find indices that do not have the correct
 * numbers.
 */

public class FindFIrstKMissingPositiveNumbers {
    public static List<Integer> findNumbers(int[] nums, int k) {
        int i = 0;
        while(i < nums.length) {
            if(nums[i] > 0 && nums[i] <= nums.length && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            } else {
                i++;
            }
        }

        List<Integer> missingNumbers = new ArrayList<>();
        Set<Integer> extraNumbers = new HashSet<>();
        for(i = 0; i < nums.length && missingNumbers.size() < k; i++) {
            if (nums[i] != i + 1) {
                missingNumbers.add(i+1);
                extraNumbers.add(nums[i]);
            }
        }

        // add the remaining missing numbers
        for(i = 1; missingNumbers.size() < k; i++) {
            int candidateNumber = i + nums.length;
            // ignore if the array contains the candidate number
            if(!extraNumbers.contains(candidateNumber)) {
                missingNumbers.add(candidateNumber);
            }
        }
        return missingNumbers;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        List<Integer> missingNumbers = FindFIrstKMissingPositiveNumbers.findNumbers(new int[] {3,-1,4,5,5}, 3);
        System.out.println("Missing numbers: " + missingNumbers);
        missingNumbers = FindFIrstKMissingPositiveNumbers.findNumbers(new int[] {2,3,4}, 3);
        System.out.println("Missing numbers: " + missingNumbers);
        missingNumbers = FindFIrstKMissingPositiveNumbers.findNumbers(new int[] {-2,-3,4}, 3);
        System.out.println("Missing numbers: " + missingNumbers);
    }
}
