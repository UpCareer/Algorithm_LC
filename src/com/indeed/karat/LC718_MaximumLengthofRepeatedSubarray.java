package com.indeed.karat;

/*
Input: nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
Output: 3
Explanation: The repeated subarray with maximum length is [3,2,1].
 */

public class LC718_MaximumLengthofRepeatedSubarray {

    public int findLength(int[] nums1, int[] nums2) {
        int max = 0;
        int m = nums1.length;
        int n = nums2.length;

        if(m == 0 || n == 0) {
            return max;
        }

        // dp[i][j] is the max length of Repeated Subarray nums1[0 ... i-1] and nums2[0 ... j-1]
        int dp[][] = new int[m+1][n+1];
        for(int i=0; i<=m; i++) {
            for(int j=0; j<=n; j++) {
                if(nums1[i] == nums2[j]) {

                }
            }
        }


        return max;





    }

}
