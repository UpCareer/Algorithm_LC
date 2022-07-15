package com.lc.twopointers;

/**
 * Problem statement
 * Give an unsorted array of numbers and a target "key", remove all instances of key in-place and return new length of
 * array
 *
 * Example 1:
 * Input: [3,2,3,6,3,10,9,3] key = 3
 * output 4
 * Explanation: The first four elements after removing every key will be [2,6,10,9]
 */
public class RemoveDuplicatesByTargetKey {
    public static int remove(int[] arr, int key) {
        int nextElement = 0; // index of the next element which is not key
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] != key) {
                arr[nextElement] = arr[i];
                nextElement++;
            }
        }
        return nextElement;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {3,2,3,6,3,10,9,3};
        System.out.println(RemoveDuplicatesByTargetKey.remove(arr, 3));
    }
}
