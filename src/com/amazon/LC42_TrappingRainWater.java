package com.amazon;

public class LC42_TrappingRainWater {
    public int trap(int[] height) {

        /* Solution 1: brutal force
        int n = height.length;
        int res = 0;
        for(int i=1; i<n-1; i++) {
            int l_max = 0;
            int r_max = 0;
            // Find the highest bar in the right
            for (int j=i; j<n; j++) {
                r_max = Math.max(r_max, height[j]);
            }
            // Find the highest bar in the left
            for (int j=i; j>=0; j--) {
                l_max = Math.max(l_max, height[j]);
            }
            res += Math.min(l_max, r_max) - height[i];
        }
        return res;*/

     /*   Solution 2: Memo
        if (height.length == 0) {
            return 0;
        }
        int n = height.length;
        int res = 0;
        // 数组充当备忘录
        int[] l_max = new int[n];
        int[] r_max = new int[n];
        // 初始化 base case
        l_max[0] = height[0];
        r_max[n - 1] = height[n - 1];
        // 从左向右计算 l_max
        for (int i = 1; i < n; i++)
            l_max[i] = Math.max(height[i], l_max[i - 1]);
        // 从右向左计算 r_max
        for (int i = n - 2; i >= 0; i--)
            r_max[i] = Math.max(height[i], r_max[i + 1]);
        // 计算答案
        for (int i = 1; i < n - 1; i++)
            res += Math.min(l_max[i], r_max[i]) - height[i];
        return res;*/


        int left = 0, right = height.length - 1;
        int l_max = 0, r_max = 0;

        int res = 0;
        while (left < right) {
            l_max = Math.max(l_max, height[left]);
            r_max = Math.max(r_max, height[right]);

            // res += min(l_max, r_max) - height[i]
            if (l_max < r_max) {
                res += l_max - height[left];
                left++;
            } else {
                res += r_max - height[right];
                right--;
            }
        }
        return res;
    }
}
