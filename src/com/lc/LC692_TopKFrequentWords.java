package com.lc;

/*
Input: words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
Output: ["the","is","sunny","day"]
Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
with the number of occurrence being 4, 3, 2 and 1 respectively.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class LC692_TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {

        List<String> result = new ArrayList<>();

        // corner case:
        if(words.length == 0) return result;

        // Step 1: Set up Hashmap
        Map<String, Integer> map = new HashMap<>();
        for(String word : words) {
            map.put(word, map.getOrDefault(word, 0)+1);
        }

        // Step 2: Set up min Heap (PriorityQueue in Java)
        PriorityQueue<String> pq = new PriorityQueue<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                int frequency1 = map.get(o1);
                int frequency2 = map.get(o2);
                if(frequency1 == frequency2) return o2.compareTo(o1);
                return frequency1 - frequency2;
            }
        });

        // Step 3: loop each string and put into heap
        for(String key : map.keySet()) {
            pq.add(key);
            if(pq.size() > k) pq.poll();
        }

        // Step 4: take a result from heap and reverse the list
        while(!pq.isEmpty()) {
            result.add(pq.poll());
        }

        Collections.reverse(result);

        return result;
    }
}
