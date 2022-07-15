package com.amazon;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC127_WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        HashSet<String> set = new HashSet<>();
        for(String word : wordList) {
            set.add(word);
        }
        if(!set.contains(endWord)) {
            return 0;
        }

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int level = 1;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                // Get the word from queue - "hit"
                String currentWord = queue.poll();
                // 'h' 'i' 't'
                char[] currentWord_array = currentWord.toCharArray();
                for(int j = 0; j < currentWord_array.length; j++) {
                    // loop each character in the current word
                    // 'h' - replace current character with each Alphabet Letters
                    char original_char = currentWord_array[j];
                    for(char c = 'a'; c <= 'z'; c++) {
                        if(currentWord_array[j] == c) {
                            continue;
                        }
                        currentWord_array[j] = c;
                        // Get transformed word
                        String transformedWord = String.valueOf(currentWord_array);
                        // If transformedWord is endWord, then return level
                        if(transformedWord.equals(endWord)) {
                            return level+1;
                        }
                        // Find the valid string in the level
                        if(set.contains(transformedWord)) {
                            // put the transformedWord in the level path (put the transformedWord in the queue)
                            queue.offer(transformedWord);
                            set.remove(transformedWord);
                        }
                    }
                    currentWord_array[j] = original_char;
                }
            }
            level++;
        }
        return 0;
    }
}
