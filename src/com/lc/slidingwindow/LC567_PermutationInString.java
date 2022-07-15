package com.lc.slidingwindow;
/*
Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
In other words, return true if one of s1's permutations is the substring of s2.

Example 1:

Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains one permutation of s1 ("ba").
Example 2:

Input: s1 = "ab", s2 = "eidboaoo"
Output: false
 */

public class LC567_PermutationInString {
    public boolean checkInclusion(String s1, String s2) {
        char[] pattern = s1.toCharArray();
        char[] text = s2.toCharArray();

        int pLen = s1.length();
        int tLen = s2.length();

        int[] pFreq = new int[26];
        int[] winFreq = new int[26];

        for(int i=0; i<pLen; i++) {
            pFreq[pattern[i]-'a']++;
        }

        // count how many different characters in pattern
        int pCount = 0;
        for(int i=0; i<26; i++) {
            if(pFreq[i] > 0) {
                pCount++;
            }
        }

        int start_index = 0;
        int end_index = 0;
        int winCount = 0; // match pCount
        for(end_index = 0; end_index < tLen; end_index++) {
            if(pFreq[text[end_index] - 'a'] > 0) {
                winFreq[text[end_index] - 'a']++;
                if(winFreq[text[end_index] - 'a'] == pFreq[text[end_index] - 'a']) {
                    winCount++;
                }
            }
            while(pCount == winCount) {
                if(end_index - start_index + 1 == pLen) {
                    return true;
                }
                if(pFreq[text[start_index] - 'a'] > 0) {
                    winFreq[text[start_index] - 'a']--;
                    if(winFreq[text[start_index] - 'a'] < pFreq[text[start_index] - 'a']) {
                        winCount--;
                    }
                }
                start_index++;
            }
        }
        return false;
    }
}
