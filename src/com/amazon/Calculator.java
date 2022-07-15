package com.amazon;

import java.util.Stack;

public class Calculator {


    public static void main(String[] args) {
        String s = "1+2-4+5+6-8";
        int res = calculate(s);
        System.out.println(res);
    }

    //1. String to Integer
    private static int stringToInteger(String str) {
        int n=0;
        for(int i=0; i<str.length(); i++) {
            n = 10*n + (str.charAt(i)-'0');
        }
        return n;
    }

    //2. Process operator
    private static int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        char operator = '+';
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            // if character is digit, put to num
            if(Character.isDigit(c)) {
                num = 10*num + (c- '0');
            }
            // if character is not digit, then it is a operator sign
            // then push the num and operator to the stack
            // Two conditions to trigger the push stack
            if((!Character.isDigit(c) && c != ' ') || i == s.length()-1) {
                int pre;
                switch(operator) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        pre = stack.pop();
                        stack.push(pre*num);
                        break;
                    case '/':
                        pre = stack.pop();
                        stack.push(pre/num);
                        break;
                }
                // update current operator and set num to 0
                operator = c;
                num = 0;
            }
        }
        // add all the number in the stack
        int res = 0;
        while(!stack.isEmpty()) {
            res += stack.pop();
        }
        return res;
    }
}
