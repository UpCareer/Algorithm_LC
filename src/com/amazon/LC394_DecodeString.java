package com.amazon;

import java.util.Stack;

public class LC394_DecodeString {
    public String decodeString(String s) {
        Stack<Integer> counts = new Stack();
        Stack<String> result = new Stack();
        String res = "";
        int index = 0;
        // "3[a2[c]]"
        while(index < s.length()) {
            if(Character.isDigit(s.charAt(index))) {
                int count = 0;
                while(Character.isDigit(s.charAt(index))) {
                    count = 10 * count + (s.charAt(index) - '0');
                    index += 1;
                }
                counts.push(count);
            } else if (s.charAt(index) == '[') {
                result.push(res);
                res = "";
                index += 1;
            } else if (s.charAt(index) == ']') {
                StringBuilder temp = new StringBuilder(result.pop());
                int count = counts.pop();
                for(int i=0; i<count; i++) {
                    temp.append(res);
                }
                res = temp.toString();
            } else {
                res += s.charAt(index);
                index += 1;
            }
        }
        return res;
    }
}