package com.wayfair;

/*Update:
        Leetcode has taken down the original coding questions posts that I had added. :(
        Adding the questions here in the comments.

        Find Contiguous URL History:
        Write a function that takes two users' browsing histories as input and
        returns the longest contiguous sequence of URLs that appears in both.
        Sample input:
        user0 = ["/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"]
        user1 = ["/start", "/pink", "/register", "/orange", "/red", "a"]
        user2 = ["a", "/one", "/two"]
        user3 = ["/pink", "/orange", "/yellow", "/plum", "/blue", "/tan", "/red", "/amber",
        "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow", "/BritishRacingGreen"]
        user4 = ["/pink", "/orange", "/amber", "/BritishRacingGreen", "/plum", "/blue", "/tan", "/red",
        "/lavender", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow"]
        user5 = ["a"]
        user6 = ["/pink","/orange","/six","/plum","/seven","/tan","/red", "/amber"]

        Sample output:
        findContiguousHistory(user0, user1) => ["/pink", "/register", "/orange"]
        findContiguousHistory(user0, user2) => [] (empty)
        findContiguousHistory(user0, user0) => ["/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two"]
        findContiguousHistory(user2, user1) => ["a"]
        findContiguousHistory(user5, user2) => ["a"]
        findContiguousHistory(user3, user4) => ["/plum", "/blue", "/tan", "/red"]
        findContiguousHistory(user4, user3) => ["/plum", "/blue", "/tan", "/red"]
        findContiguousHistory(user3, user6) => ["/tan", "/red", "/amber"]

        n: length of the first user's browsing history m: length of the second user's browsing history
        */

import java.util.ArrayList;
import java.util.List;

public class FindContiguousURLHistory {
    public static List findContiguousHistory(String user1[], String user2[]) {
        List result = new ArrayList<>();
        if (user1.length == 0 || user2.length == 0) {
            return result;
        }
        int dp[][] = new int[user1.length + 1][user2.length + 1];
        int max = Integer.MIN_VALUE;
        int endIndex = -1;
        // Solution 1:
//        for (int i = user1.length - 1; i >= 0; i--) {
//            for (int j = user2.length - 1; j >= 0; j--) {
//                if (user1[i].equals(user2[j])) {
//                    dp[i][j] = dp[i + 1][j + 1] + 1;
//                    if (max < dp[i][j]) {
//                        max = dp[i][j];
//                        endIndex = j;
//                    }
//                    break;
//                }
//            }
//        }
        // Solution 2:
        // dp[i][j] longest contiguous URL history in user1[0....i-1] user2[0....j-1]
        for(int i=1; i<=user1.length; i++) {
            for(int j=1; j<=user2.length; j++) {
                if(user1[i-1].equals(user2[j-1])) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                    if (max < dp[i][j]) {
                        max = dp[i][j];
                        endIndex = j;
                    }
                    break;
                }
            }
        }
        if (max == Integer.MIN_VALUE) {
            return result;
        }
        for (int i = endIndex-max; i < endIndex; i++) {
            result.add(user2[i]);
        }

        return result;
    }

    public static void main(String[] args) {
        String[] user0 = { "/start", "/green", "/blue", "/pink", "/register", "/orange", "/one/two" };
        String[] user1 = { "/start", "/pink", "/register", "/orange", "/red", "a" };
        String[] user2 = { "a", "/one", "/two" };
        String[] user3 = { "/pink", "/orange", "/yellow", "/plum", "/blue", "/tan", "/red", "/amber", "/HotRodPink",
                "/CornflowerBlue", "/LightGoldenRodYellow", "/BritishRacingGreen" };
        String[] user4 = { "/pink", "/orange", "/amber", "/BritishRacingGreen", "/plum", "/blue", "/tan", "/red",
                "/lavender", "/HotRodPink", "/CornflowerBlue", "/LightGoldenRodYellow" };
        String[] user5 = { "a" };
        String[] user6 = { "/pink", "/orange", "/six", "/plum", "/seven", "/tan", "/red", "/amber" };

        System.out.println(findContiguousHistory(user0, user1));
        System.out.println(findContiguousHistory(user0, user2));
        System.out.println(findContiguousHistory(user2, user1));
        System.out.println(findContiguousHistory(user5, user2));
        System.out.println(findContiguousHistory(user3, user4));
        System.out.println(findContiguousHistory(user4, user3));
        System.out.println(findContiguousHistory(user3, user6));

    }

}
