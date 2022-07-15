package com.amazon;

import java.util.LinkedList;
import java.util.List;

public class LC017 {
    // The mapping between the number of letters on keypad of phone
    String[] mapping = new String[] {
            "", "", "abc", "def", "ghi", "jkl",
            "mno", "pqrs", "tuv", "wxyz"
    };

    List<String> res = new LinkedList<>();

    public List<String> letterCombinations(String digits) {
        // Speical Case
        if (digits.isEmpty()) {
            return res;
        }
        // Start backtrack from digits[0]
        backtrack(digits, 0, new StringBuilder());
        return res;
    }

    void backtrack(String digits, int start, StringBuilder sb) {
        // Ending Condition
        if (sb.length() == digits.length()) {
            // Arrive tree leaf
            res.add(sb.toString());
            return;
        }
        // backtrack recursive part
        for(int i= start; i < digits.length(); i++) {
            int digit = digits.charAt(i) - '0';
            for(char c: mapping[digit].toCharArray()) {
                // Make Choice
                sb.append(c);
                // Recursive next level tree
                backtrack(digits, i+1, sb);
                //  revoke the choice
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}
