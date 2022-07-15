package com.indeed.vo.whiteboard;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Validate_Python_Indentation_II {
    public static boolean valid_python_indentation(List<String> inputs){
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<inputs.size();i++){
            String str =  inputs.get(i);
            String abbr = getAbbr(str);
            int level = str.length()-abbr.length();

            if(i!=0 && inputs.get(i-1).charAt(inputs.get(i-1).length()-1)==':'){
                if(level<=stack.peek()) return false;
            }else{
                while(!stack.isEmpty() && level<stack.peek()) stack.pop();
                if(!stack.isEmpty() && level!=stack.peek()) return false;

            }
            stack.push(level);
            System.out.println(level);
        }

        return true;

    }

    private static String getAbbr(String str) {
        String result = str.trim();
        return result;
    }

    public static void main(String[] args){
        List<String> inputs = new ArrayList<>();
        inputs.add("def");
        inputs.add("abc:");
        inputs.add("  bcc");
        inputs.add("  abc:");
        inputs.add("    def");
        inputs.add("    def");
        inputs.add("  bcc");

        System.out.println(valid_python_indentation(inputs));
    }
}
