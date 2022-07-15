package com.lc.topologicalSort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 * You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules
 * of this new language. Derive the order of letters in this language.
 *
 * Example 1:
 * Input:
 * [
 *  "wrt",
 *  "wrf",
 *  "er",
 *  "ett",
 *  "rftt"
 * ]
 *
 * output: "wertf"
 */

public class LC269_AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, Set<Character>> graph = new HashMap<>();
        int[] inDegree = new int[26];
        buildGraph(graph, inDegree, words);
        return bfs(graph, inDegree);
    }

    private void buildGraph(Map<Character, Set<Character>> graph, int[] indegree, String[] words) {
        for(String s : words) {
            for(char c : s.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
            }
        }

        for(int i=1; i<words.length; i++) {
            String first = words[i-1];
            String second = words[i];
            int len = Math.min(first.length(), second.length());
            for(int j=0; j<len; j++) {
                char out = first.charAt(j);
                char in = second.charAt(j);
                // find first different character in the two strings
                if (out != in) {
                    if(!graph.get(out).contains(in)) {
                        graph.get(out).add(in);
                        indegree[in - 'a']++;
                    }
                    break;
                }

                // Thanks for @Jay He
                if (j + 1 == len && first.length() > second.length()) {
                    graph.clear();
                    return;
                }
            }

        }
    }

    private String bfs(Map<Character, Set<Character>> graph, int[] inDegree) {
        StringBuilder sb = new StringBuilder();
        Queue<Character> q = new LinkedList<>();
        for (char c : graph.keySet()) {
            if (inDegree[c - 'a'] == 0) {
                q.offer(c);
            }
        }

        while (!q.isEmpty()) {
            char out = q.poll();
            sb.append(out);
            for (char in : graph.get(out)) {
                inDegree[in - 'a']--;
                if (inDegree[in - 'a'] == 0) {
                    q.offer(in);
                }
            }
        }

        return sb.length() == graph.size() ? sb.toString() : "";
    }

}
