package com.indeed.karat;

/*
    Example 2:
    Input:
            ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
    Output:
            ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC811_subdomains {

    public static void main(String[] args) {
        String[] input = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        List<String> output = subdomainVisits(input);
        System.out.println(output);
    }

    public static List<String> subdomainVisits(String[] cpdomains) {
        List<String> result = new ArrayList<>();
        if(cpdomains.length == 0) {
            return result;
        }

        // Step 1: Set up the hashMap
        Map<String, Integer> map = new HashMap<>();
        for(String cpdomain : cpdomains) {
            // "900 google.mail.com"
            String[] temp = cpdomain.split("\\s+");
            int value = Integer.valueOf(temp[0]);
            String[] domainArray = temp[1].split("\\.");
            String key = "";
            for (int i = domainArray.length - 1; i >= 0; i--) {
                key = domainArray[i] + (i != domainArray.length - 1 ? "." : "") + key;
                map.put(key, map.getOrDefault(key, 0) + value);
            }
        }

        // Step 2: loop the map and give the result
        for(String key : map.keySet()) {
            String temp = map.get(key) + " " + key;
            result.add(temp);
        }

        return result;
    }
}
