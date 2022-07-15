package com.amazon;

import java.util.HashMap;
import java.util.HashSet;

public class LC819 {
    public String mostCommonWord(String paragraph, String[] banned) {
        HashSet<String> bannedWords = new HashSet<>();
        HashMap<String, Integer> nonBannedWordsMap = new HashMap<>();

        // Set up HashSet from String[] banned
        for(String word: banned) {
            bannedWords.add(word);
        }

        // Pre-process String paragraph to String[]
        String[] words = paragraph.toLowerCase().split("\\W+");

        // Set up HashMap
        for(String word: words) {
            if (!bannedWords.contains(word)) {
                nonBannedWordsMap.put(word, nonBannedWordsMap.getOrDefault(word,0)+1);
            }
        }

        int max=0;
        String mostCommonWord = "";
        // loop the HashMap and get the mostCommonWord
        for(String word : nonBannedWordsMap.keySet()) {
            if (nonBannedWordsMap.get(word) > max) {
                max = nonBannedWordsMap.get(word);
                mostCommonWord = word;
            }
        }
        return mostCommonWord;
    }
}
