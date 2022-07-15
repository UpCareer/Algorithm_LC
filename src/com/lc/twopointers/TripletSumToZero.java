package com.lc.twopointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Problem Statement
 *
 * Given an array of unsorted numbers, find all unique triplets in it that add up to zero.
 *
 * Example 1:
 * Input: [-3, 0, 1,2,-1,1,-2]
 * output: [-3,1,2], [-2,0,2],[-2,1,1],[-1,0,1]
 * Explanation: There are four unique triplets whose sum is equal to zero
 *
 * Time Complexity: Sorting O(NlogN). SearchPair() take O(N)
 * Total: O(NlogN + N*N) = O(N*N)
 */

public class TripletSumToZero {

    public static List<List<Integer>> searchTriplets(int[] arr) {
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();
        for(int i=0; i<arr.length; i++) {
            // locate the first unduplicated element
            if(i > 0 && arr[i] == arr[i-1]) {
                continue;
            }
            searchPair(arr, -arr[i], i+1, triplets);
        }
        return triplets;
    }

    private static void searchPair(int[] arr, int targetSum, int left, List<List<Integer>> triplets) {
        int right = arr.length - 1;
        while(left < right) {
            int currentSum = arr[left] + arr[right];
            if(currentSum == targetSum) {
                // found the triplet
                triplets.add(Arrays.asList(-targetSum, arr[left], arr[right]));
                left++;
                right--;
                while(left < right && arr[left] == arr[left-1]) left++;
                while(left < right && arr[right] == arr[right + 1]) right--;
            } else if (targetSum > currentSum) {
                left++;
            } else {
                right--;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(TripletSumToZero.searchTriplets(new int[] {-3, 0, 1, 2,-1, 1, -2}));
        System.out.println(TripletSumToZero.searchTriplets(new int[] {-5, 2, -1, -2, 3}));
    }
 }
