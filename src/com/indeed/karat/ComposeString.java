package com.indeed.karat;

/*
第一道coding：给一个字典String[] words = {"cat", "baby", "dog", "bird", "car", "ax"},
 另一个String s = "tcabnihjs", 问第二个String中的字符能否构成第一个字典中的单词，注意每个字符只能使用一次

 第一题是给出一个string数组，一个string，返回string中字母可以构成的数组中的string，
 比如[ "cat", "baby", "dog", "bird", "car", "ax" ]，"tcabnihjs" 将返回"cat"。
 */

import java.util.ArrayList;
import java.util.List;

public class ComposeString {

    public static void main(String[] args) {
        String[] words = { "cat", "baby", "dog", "bird", "car", "ax"};
        String sequence = "tcabnihjs";
        List<String> result = compose(words, sequence);
        System.out.println(result);
    }

    public static List<String> compose(String[] words, String sequence) {
        List<String> result = new ArrayList<>();

        // Corner case:
        if(words.length == 0 || sequence.length() == 0) {
            return result;
        }

        // Step 1: Set up array to record the sequence count
        int[] count = new int[256];
        for(int i=0; i<sequence.length(); i++) {
            count[sequence.charAt(i)]++;
        }

        // Step 2: Loop through the String array
        for(String word : words) {
            int[] tempCount = count;
            boolean flag = true;
            for(int i=0; i<word.length(); i++) {
                if(tempCount[word.charAt(i)] == 0) {
                    flag = false;
                    break;
                }
                tempCount[word.charAt(i)]--;
            }
            if(flag) {
                result.add(word);
            }
        }
        return result;
    }
}
