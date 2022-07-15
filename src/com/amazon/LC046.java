package com.amazon;

import java.util.LinkedList;
import java.util.List;

public class LC046 {

    List<List<Integer>> res = new LinkedList<>();

    // Main Function
    public List<List<Integer>> permute(int[] nums) {
        // Record the Path - permutation
        LinkedList<Integer> track = new LinkedList<>();
        backtrack(nums, track);
        return res;
    }

    void backtrack(int[] nums, LinkedList<Integer> track) {
        // End Condition
        if (track.size() == nums.length) {
            res.add(new LinkedList<Integer>(track));
            return;
        }

        for (int i=0; i < nums.length; i++) {
            // Rule out invalid Choose
            if (track.contains(nums[i])) {
                continue;
            }
            // Make Choice
            track.add(nums[i]);
            // Enter next level decision tree
            backtrack(nums, track);
            // Remove Choice
            track.removeLast();
        }
    }
}
