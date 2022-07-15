package com.lc;

import java.util.Random;
/*
 // Main driver method
    public static void main(String[] args)
    {
        // Creating an empty ArrayList of string type
        ArrayList<String> mylist = new ArrayList<String>();

        // Adding custom input elements to list object
        mylist.add("code");
        mylist.add("quiz");
        mylist.add("geeksforgeeks");
        mylist.add("quiz");
        mylist.add("practice");
        mylist.add("qa");

        // Printing list before shuffling
        System.out.println("Original List : \n" + mylist);

        // Shuffling the list
        Collections.shuffle(mylist);

        // Printing list after shuffling
        System.out.println("\nShuffled List : \n" + mylist);
    }
 */

// Knuth shuffle

public class LC384_Shuffle_Array {
    private int[] nums;
    private Random rnd;

    public LC384_Shuffle_Array(int[] nums) {
        this.nums = nums;
        this.rnd = new Random();
    }

    public int[] reset() {
        return nums;
    }

    public int[] shuffle() {
        int[] ret = new int[nums.length];
        for(int i=0; i<nums.length; i++) {
            ret[i] = nums[i];
        }

        for(int j=0; j<ret.length-1; j++) {
            int index = j + rnd.nextInt(ret.length-j);
            swap(ret, j, index);
        }

        return ret;
    }

    private void swap(int[] ret, int i, int j) {
        int temp = ret[i];
        ret[i] = ret[j];
        ret[j] = temp;
    }
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */

