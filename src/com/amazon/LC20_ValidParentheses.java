package com.amazon;

import java.util.Stack;

public class LC20_ValidParentheses {
    public boolean isValid(String s) {
        // Stack Data Structure
        Stack<Character> left = new Stack<>();
        for(char c: s.toCharArray()) {
            if(c == '{' || c == '(' || c == '[') {
                left.push(c);
            } else {
                if (!left.isEmpty() && leftOf(c) == left.peek()) {
                    left.pop();
                } else {
                    return false;
                }
            }
        }
        return left.isEmpty();
    }

    char leftOf(char c) {
        if (c == '}') return '{';
        if (c == ')') return '(';
        return '[';
    }
}
