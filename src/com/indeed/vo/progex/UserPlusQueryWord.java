package com.indeed.vo.progex;

/**
 * 第三轮：hackerrank做题，这道题是以前题的变形，输入是一堆query，每个query包含了user + query word, 输出是每当进来一个query时，根据之前的query，要返回一个最相关的单词，这题能够保证同一个user，只会query某个单词一次。重要信心
 * 具体看例子：
 * Input:
 * 7
 * A python
 * B java
 * A java
 * B php
 * C python
 * C java
 * D java
 * Output:
 * 0
 * 0
 * 0
 * 0
 * 1 java(因为目前A: python java, B: java php, search过python的人中还search最多的是java，1次)
 * 1 python php(此时 A: python java, B: java php, C: python)
 * 2 python(此时A: python java, B: java php, C: python java)
 * 这道题我一开始一直在map如何设计上纠结着，naive的方法最后一直有三个case过不了，所以思考的过程是：
 * map1<String, Set<String>> // user : the words he searched before
 * 大概能过6个case，开始优化
 * 加了个map2<String, List<String>> // word : list of users who searched this word before
 * 这个的话大概又过了4个，还是有三个过不了，此时我心怀侥幸，一直在优化中间的过程，而不是在优化思想了，结果没成功。直到最后还有20分钟时，换掉了思路，
 * 用了一个这个map
 * map3<String, Map<String, Integer>> // word : related word and times
 * 这样的话，每次一个新词进来，直接就能找到相关的词，然后找到出现次数最多的就好。然后再利用map1中这个user之前query的结果，去update map3。
 * 这个思路没有debug完，思路讲给了面试官听，他肯定了这个想法。
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

/**
 * A python
 * B java
 * A java
 * B php
 * C python
 * C java
 * D java
 *
 * A -> python java
 * B -> java php
 * c -> python Java
 * D -> java
 *
 */
public class UserPlusQueryWord {

    // data structure 1: HashMap<query_word, HashMap<relatedWord, appearance_times>>
    HashMap<String, HashMap<String, Integer>> indexMap;

    // data structure 2: HashMap(user, HashSet<String>) --> user: new relationship words
    HashMap<String, HashSet<String>> userQuerySetMap;

    public UserPlusQueryWord() {
        indexMap = new HashMap<String, HashMap<String, Integer>>();
        userQuerySetMap = new HashMap<String, HashSet<String>>();
    }


    public String getQueryResult (String[] input) {
        String ret;
        // input[0] = user input[1] = skill
        if(indexMap.containsKey(input[1])) {
            HashMap<String, Integer> count = indexMap.get(input[1]);
            ret = findMostRelatedRecords(count);
        } else {
            ret = "0";
        }
        updateIndexMap(input);
        return ret;
    }
    private void updateIndexMap(String[] input) {
        // input[0] is user like A B C D
        HashSet<String> curSet = userQuerySetMap.getOrDefault(input[0], new HashSet<String>());
        if(curSet.size() != 0) {
            // update countMap
            for(String query : curSet) {
                updateIndexMap(query,input[1]);
                updateIndexMap(input[1],query);
            }
        }
        curSet.add(input[1]);
        userQuerySetMap.put(input[0],curSet);
    }

    private void updateIndexMap(String indexMapKey, String countMapKey) {
        HashMap<String, Integer> countMap = indexMap.getOrDefault(indexMapKey, new HashMap<String, Integer>());
        countMap.put(countMapKey, countMap.getOrDefault(countMapKey, 0) + 1);
        indexMap.put(indexMapKey, countMap);
    }

    private String findMostRelatedRecords(HashMap<String, Integer> hashMap) {
        List<Integer> count = new ArrayList<>(hashMap.values());
        Collections.sort(count,Collections.reverseOrder());
        int max = count.get(0);
        StringBuilder sb = new StringBuilder();
        sb.append(max).append(" ");
        for(String key : hashMap.keySet()) {
            if(hashMap.get(key) == max) {
                sb.append(key).append(" ");
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        UserPlusQueryWord query = new UserPlusQueryWord();
        String[] words = null;
        try {
            File file=new File("src/com/indeed/vo/progex/UserPlusQueryWord");
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String s=null;
            while((s=reader.readLine())!=null){
                words=s.split("\\W+");
                String ret = query.getQueryResult(words);
                System.out.println(ret);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
