package com.wayfair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LongestCommonContinuousSubarray {

    public static void main(String[] args) {
        String[] sArray1 = {"3234.html", "xys.html", "7hsaa.html"};
        String[] sArray2 = {"3234.html", "sdhsfjdsh.html", "xys.html", "7hsaa.html"};
        String[] ret = lCCSubarray(sArray1,sArray2);
        for(String str : ret) {
            System.out.println(str);
        }
    }

    public static String[] lCCSubarray(String[] sArray1, String[] sArray2) {
        if (sArray1 == null || sArray2 == null || sArray1.length == 0 || sArray2.length == 0) {
            return new String[0];
        }

        int m = sArray1.length;
        int n = sArray2.length;

        List<String> list1 = Arrays.asList(sArray1);
        List<String> list2 = Arrays.asList(sArray2);
        List<String> result = new ArrayList<String>();

        int[][] memo = new int[m+1][n+1];
        for(int[] row : memo) {
            Arrays.fill(row, 0);
        }

        int max = 0;
        for(int i=1; i<=m; i++) {
            for(int j=1; j<=n; j++) {
                if(sArray1[i-1] == sArray2[j-1]) {
                    memo[i][j] = 1 + memo[i-1][j-1];
                    if(memo[i][j] > max) {
                        max = memo[i][j];
                        result = list1.subList(i-max, i);
                    }
                }
            }
        }

        String[] ret = new String[result.size()];
        ret = result.toArray(ret);
        return ret;
    }
}
