package com.amazon;

public class LC680_ValidPalindromeII {
    public boolean validPalindrome(String s) {
        // Two pointers solution
        int a_pointer = 0;
        int b_pointer = s.length() -1;
        while(a_pointer <= b_pointer) {
            if(s.charAt(a_pointer) != s.charAt(b_pointer)) {
                return helper(s, a_pointer+1, b_pointer) || helper(s, a_pointer, b_pointer-1);
             }
            a_pointer++;
            b_pointer--;
        }
        return true;
    }

    private boolean helper(String s, int begin, int end) {
        int a_pointer = begin;
        int b_pointer = end;
        while(a_pointer <= b_pointer) {
            if(s.charAt(a_pointer) != s.charAt(b_pointer)) {
                return false;
            }
            a_pointer++;
            b_pointer--;
        }
        return true;
    }
}
