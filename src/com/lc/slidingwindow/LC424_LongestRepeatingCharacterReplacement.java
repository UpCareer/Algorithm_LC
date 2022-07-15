package com.lc.slidingwindow;

/*
You are given a string s and an integer k. You can choose any character of the string and change it to any other
uppercase English character. You can perform this operation at most k times.

Return the length of the longest substring containing the same letter you can get after performing the above operations.

Example 1:

Input: s = "ABAB", k = 2
Output: 4
Explanation: Replace the two 'A's with two 'B's or vice versa.
Example 2:

Input: s = "AABABBA", k = 1
Output: 4
Explanation: Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeat
 */

public class LC424_LongestRepeatingCharacterReplacement {

    public int characterReplacement(String s, int k) {
        if (s == null && s.length() == 0) {
            return 0;
        }

        int start_index = 0;
        int end_index = 0;
        int[] char_counts = new int[26];
        int max_length = 0;
        int max_count = 0;

        for (end_index = 0; end_index < s.length(); end_index++) {
            char_counts[s.charAt(end_index) - 'A']++;
            int current_char_count = char_counts[s.charAt(end_index) - 'A'];
            max_count = Math.max(max_count, current_char_count);

            while (end_index - start_index - max_count + 1 > k) {
                char_counts[s.charAt(start_index) - 'A']--;
                start_index++;
            }

            max_length = Math.max(max_length, end_index - start_index + 1);
        }
        return max_length;
    }
}
