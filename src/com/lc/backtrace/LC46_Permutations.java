/*
package com.lc.backtrace;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC46_Permutations {

    List<List<String>> result = new LinkedList<>();

    public List<List<Integer>> permute(int[] nums) {

        LinkedList<Integer> track = new LinkedList<>();

        boolean[] used = new boolean[nums.length];

        backtrack(nums, track, used);
    }

    void backtrack(int[] nums, LinkedList<Integer> track, boolean[] used) {
        if(track.size() == nums.length) {
            result.add(new LinkedList(track));
            return;
        }

        for(int i=0; i<nums.length; i++) {
            if(used[i]) {
                continue;
            }
            track.add(nums[i]);
            used[i] = true;
            backtrack(nums, track, used);
            track.remove(nums[i]);
            used[i] = false;
        }
    }
}
*/
