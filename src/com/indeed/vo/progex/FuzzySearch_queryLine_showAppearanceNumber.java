package com.indeed.vo.progex;

/*
tokenID  token DocFrequency InvertedIndex(DocID; termFrequency; <POS>)
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
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/** =============================================================================
题目内容：

给出若干行的文字，再给query，输出所在的行数。行数要排序。先根据出现的频率排序，如果频率一样，
        就按照行数大小来排序。
        举例：
        a b     //1
        b a a   //2
        a b b   //3
        a       //4
        query(a),输出就是（2，1，3,4），
        query(b)输出(3,1,2)
        query(a & b) 输出(2,3,1)
        query(a | b) 输出(2,3,1,4)
        因为query(a&b)(a|b)的频率计算都是单独算a和b出现次数然后求和的。
        只不过或的时候更宽容一点儿吧，有一个就行，与的时候要两个都在。

        据说上机题只需要用到hashmap。
        然后计算频率的时候看清楚一些就行吧。
        还有上机题需要scanner么？

        暂时没什么想法，除了暴力做之外，可以把每个词的query单独拎出来，然后遇到a&b的时候就比较好做了。
        上机只有一轮，90分钟，看命了。

 =============================================================================**/
public class FuzzySearch_queryLine_showAppearanceNumber {

    private HashMap<String, LinkedHashMap<Integer, Integer>> indexMap;

    public FuzzySearch_queryLine_showAppearanceNumber() {
        indexMap = new HashMap<String, LinkedHashMap<Integer, Integer>>();
    }

    public void updateIndexMap(int line, String[] input) {
        // Corner Case
        if(input == null || input.length == 0) {
            return;
        }
        for(String str : input) {
            LinkedHashMap<Integer, Integer> countByLineMap = indexMap.getOrDefault(str, new LinkedHashMap<Integer, Integer>());
            countByLineMap.put(line, countByLineMap.getOrDefault(line, 0) + 1);
            indexMap.put(str, countByLineMap);
        }
    }

    public List<Integer> query(String str) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        if(!indexMap.containsKey(str)) {
            return result;
        }

        LinkedHashMap<Integer, Integer> ret = sort(indexMap.get(str));

        result.addAll(ret.keySet());
        return result;
    }

    public List<Integer> andQuery(String query1, String query2) {
        ArrayList<Integer> result = new ArrayList<Integer>();
        LinkedHashMap<Integer, Integer> query1Hm = indexMap.get(query1);
        LinkedHashMap<Integer, Integer> query2Hm = indexMap.get(query2);
        HashSet<Integer> set1 = new HashSet<>(query1Hm.keySet());
        HashSet<Integer> set2 = new HashSet<>(query2Hm.keySet());

        // Intersection
        set1.retainAll(set2);
        HashMap<Integer, Integer> andMap = new HashMap<>();
        for(Integer i : set1) {
            if(query1Hm.containsKey(i) && query2Hm.containsKey(i)) {
                int sum = query1Hm.get(i) + query2Hm.get(i);
                andMap.put(i,sum);
            }
        }
        LinkedHashMap<Integer, Integer> ret = sort(andMap);
        result.addAll(ret.keySet());
        return result;
    }

    public List<Integer> orQuery(String query1, String query2) {
        ArrayList<Integer> result = new ArrayList<>();
        LinkedHashMap<Integer, Integer> query1Hm = indexMap.get(query1);
        LinkedHashMap<Integer, Integer> query2Hm = indexMap.get(query2);
        HashSet<Integer> set1 = new HashSet<>(query1Hm.keySet());
        HashSet<Integer> set2 = new HashSet<>(query2Hm.keySet());

        // union
        set1.addAll(set2);
        HashMap<Integer, Integer> orMap = new HashMap<>();
        for(Integer i : set1) {
            int sum = 0;
            if(query1Hm.containsKey(i)) {
                sum = sum + query1Hm.get(i);
            }

            if (query2Hm.containsKey(i)) {
                sum = sum + query2Hm.get(i);
            }
            orMap.put(i,sum);
        }
        LinkedHashMap<Integer, Integer> ret = sort(orMap);
        result.addAll(ret.keySet());
        return result;
    }

    public List<Integer> topKQuery(String query, int k) {
        List<Integer> res = new ArrayList<>();
        LinkedHashMap<Integer, Integer> topKHm = indexMap.get(query);
        PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Integer> >() {
            @Override
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2)
            {
                if(o1.getValue() == (o2.getValue())) {
                    return (o2.getKey()).compareTo(o1.getKey());
                }
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        for(Map.Entry<Integer, Integer> entry: topKHm.entrySet()) {
            pq.add(entry);
            if(pq.size() > k) pq.poll();
        }

        while(!pq.isEmpty()) {
            Map.Entry<Integer, Integer> entry = pq.poll();
            res.add(Integer.valueOf(entry.getKey()));
        }

        Collections.reverse(res);
        return res;
    }

    private LinkedHashMap<Integer, Integer> sort(HashMap<Integer, Integer> hm) {
        // Create a list from elements of HashMap
        List<Map.Entry<Integer, Integer> > list =
                new LinkedList<Map.Entry<Integer, Integer> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Integer, Integer> >() {
            public int compare(Map.Entry<Integer, Integer> o1,
                               Map.Entry<Integer, Integer> o2)
            {
                if(o1.getValue() == (o2.getValue())) {
                    return (o1.getKey()).compareTo(o2.getKey());
                }
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        // put data from sorted list to hashmap
        LinkedHashMap<Integer, Integer> temp = new LinkedHashMap<Integer, Integer>();
        for (Map.Entry<Integer, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
     }

    public static void main(String[] args) {
        FuzzySearch_queryLine_showAppearanceNumber fs = new FuzzySearch_queryLine_showAppearanceNumber();
        String[] words = null;
        try {
            File file=new File("src/com/indeed/vo/progex/FuzzySearch_queryLine_showAppearanceNumber");
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String s=null;
            int line = 1;
            while((s=reader.readLine())!=null){
                words=s.split("\\W+");
                fs.updateIndexMap(line, words);
                line++;
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(fs.query("a"));
        System.out.println(fs.query("b"));
        System.out.println(fs.andQuery("a", "b"));
        System.out.println(fs.orQuery("a", "b"));
        for(int k=1; k<=4; k++) {
            System.out.println(fs.topKQuery("a", k));
        }
        for(int k=1; k<=3; k++) {
            System.out.println(fs.topKQuery("b", k));
        }
    }
}
