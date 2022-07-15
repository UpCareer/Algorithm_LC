package com.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC811_SubdomainVisitCount {
    public List<String> subdomainVisits(String[] cpdomains) {
        //存储对应的字符串和出现的次数
        Map<String, Integer> counts = new HashMap<String, Integer>();

        // cpdomains = ["900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org"]
        for(String domain : cpdomains){
            //将cpdomains分成了次数和域名info[0]是次数，info[1]是域名
            // "900 google.mail.com"
            // info[0] = "900" and info[1] = "google.mail.com"
            String[] info = domain.split("\\s+");
            //将域名按照“.”分开
            // dom[0] = google dom[1] = mail dom[2] = com
            String[] dom = info[1].split("\\.");

            int times = Integer.valueOf(info[0]);
            String temp = "";
            for(int i = dom.length - 1; i >= 0; i--){
                temp = dom[i] + (i == dom.length - 1? "": ".") + temp;
                counts.put(temp, counts.getOrDefault(temp, 0) + times);
                /*Integer java.util.Map.getOrDefault(Object key, Integer defaultValue)
                Returns the value to which the specified key is mapped, or defaultValue if this map contains no mapping for the key.*/

            }
        }
        //存储结果
        List<String> result = new ArrayList<String>() ;
        for(String s : counts.keySet()){
            result.add("" + counts.get(s) + " " + s) ;
        }
        return result ;
    }
}
