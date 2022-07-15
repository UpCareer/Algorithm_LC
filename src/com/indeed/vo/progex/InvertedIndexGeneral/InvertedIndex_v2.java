package com.indeed.vo.progex.InvertedIndexGeneral;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class InvertedIndex_v2 {

    private Map<String, ArrayList<String>> map=new HashMap<>();
    private ArrayList<String> list;
    private Map<String, Integer> nums=new HashMap<>();

    public void CreateIndex(String filepath){

        String[] words = null;
        try {

            File file=new File(filepath);
            BufferedReader reader=new BufferedReader(new FileReader(file));
            String s=null;
            while((s=reader.readLine())!=null){
                //Get words
                words=s.split("\\W+");

            }

            for (String string: words) {

                if (!map.containsKey(string)) {
                    list=new ArrayList<String>();
                    list.add(filepath);
                    map.put(string, list);
                    nums.put(string, 1);
                }else {
                    list=map.get(string);
                    //If this file name has not been included, put the file name in
                    if (!list.contains(filepath)) {
                        list.add(filepath);
                    }
                    //Total word frequency of the file
                    int count=nums.get(string)+1;
                    nums.put(string, count);
                }
            }
            reader.close();

        } catch (IOException e) {

            e.printStackTrace();
        }


    }

    public static void main(String[] args) {
        InvertedIndex_v2 index=new InvertedIndex_v2();

        for(int i=1;i<=2;i++){
            String path="src\\com\\indeed\\vo\\progex\\testfile"+i+".txt";
            index.CreateIndex(path);
        }
        for (Map.Entry<String, ArrayList<String>> map: index.map.entrySet()) {
            System.out.println(map.getKey()+":"+map.getValue());
        }

        for (Map.Entry<String, Integer> num: index.nums.entrySet()) {
            System.out.println(num.getKey()+":"+num.getValue());
        }
    }
}
