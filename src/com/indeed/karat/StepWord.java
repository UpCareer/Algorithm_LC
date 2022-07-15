package com.indeed.karat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class StepWord {

    public static void main(String[] args) {
        String word = "love";
        StringBuilder sb = new StringBuilder(word);
        int i=0;
        for(char c = 'a'; c <= 'z'; c++) {
            for (int index = 0; index <= word.length(); index++) {
                sb.insert(index, c);
                System.out.println(sb.toString());
                sb = new StringBuilder(word);
                i++;
            }
        }
        System.out.println(i);
    }

    public static List<List<String>> getStepWords(String[] words) {
        List<List<String>> result = new ArrayList<>();

        // Corner case
        if(words.length == 0) return result;

        // Step 1: set up Map, (word_length -> List of words)
        Map<Integer, List<String>> map = new HashMap<>();
        for(String word : words) {
            int len = word.length();
            List<String> wordList = map.getOrDefault(len, new ArrayList<>());
            wordList.add(word);
            map.put(len, wordList);
        }

        // Step 2: Loop each key and Set up a set which include all its possible step words
        //           and Set up a Map as key -> Stepwords Set
        Map<String, Set<String>> stepWordsSet = new HashMap<>();
        for(String word : words) {
            StringBuilder sb = new StringBuilder(word);
            for(char c = 'a'; c <= 'z'; c++) {
                for (int index = 0; index <= word.length(); index++) {
                    sb.insert(index, c);

                    sb = new StringBuilder(word);
                   // i++;
                }
            }

        }

        return null;

    }

}
