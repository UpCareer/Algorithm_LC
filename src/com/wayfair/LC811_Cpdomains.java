package com.wayfair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC811_Cpdomains {
    /*
    Example 2:
    Input:
            ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
    Output:
            ["901 mail.com","50 yahoo.com","900 google.mail.com","5 wiki.org","5 org","1 intel.mail.com","951 com"]
    */
    public static void main(String[] args) {
        String[] input = {"900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"};
        List<String> output = subdomainVisits(input);
        System.out.println(output);
    }

    public static List<String> subdomainVisits(String[] cpdomains) {
        if(cpdomains ==null || cpdomains.length == 0) {
            return null;
        }

        HashMap<String, Integer> map = new HashMap<>();
        // Preprocess the String Array
        for(String domainCount : cpdomains) {
            // "900 google.mail.com"
            String[] domainCountArray = domainCount.split("\\s+");
            // 900
            int count = Integer.valueOf(domainCountArray[0]);
            // google.mail.com
            String domain = domainCountArray[1];
            String[] domainArray = domain.split("\\.");
            // google mail com
            String tempValue = "";
            for(int i=domainArray.length-1; i>=0; i--) {
                tempValue = domainArray[i] + ( i == domainArray.length - 1 ? "" : ".") + tempValue;
                map.put(tempValue, map.getOrDefault(tempValue, 0) + count);
            }
        }
        List result = new ArrayList<String>();
        for(String key : map.keySet()) {
            result.add("" + map.get(key) + " " + key) ;
        }
        return result;
    }
}
