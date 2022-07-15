package com.wayfair;

/*
We are working on a security system for a badged-access room in our company's building.

We want to find employees who badged into our secured room unusually often. We have an unordered list of names and entry times over a single day. Access times are given as numbers up to four digits in length using 24-hour time, such as "800" or "2250".

Write a function that finds anyone who badged into the room three or more times in a one-hour period. Your function should return each of the employees who fit that criteria, plus the times that they badged in during the one-hour period. If there are multiple one-hour periods where this was true for an employee, just return the earliest one for that employee.

badge_times = [
  ["Paul",      "1355"], ["Jennifer",  "1910"], ["Jose",    "835"],
  ["Jose",       "830"], ["Paul",      "1315"], ["Chloe",     "0"],
  ["Chloe",     "1910"], ["Jose",      "1615"], ["Jose",   "1640"],
  ["Paul",      "1405"], ["Jose",       "855"], ["Jose",    "930"],
  ["Jose",       "915"], ["Jose",       "730"], ["Jose",    "940"],
  ["Jennifer",  "1335"], ["Jennifer",   "730"], ["Jose",   "1630"],
  ["Jennifer",     "5"], ["Chloe",     "1909"], ["Zhang",     "1"],
  ["Zhang",       "10"], ["Zhang",      "109"], ["Zhang",   "110"],
  ["Amos",         "1"], ["Amos",         "2"], ["Amos",    "400"],
  ["Amos",       "500"], ["Amos",       "503"], ["Amos",    "504"],
  ["Amos",       "601"], ["Amos",       "602"], ["Paul",   "1416"],
];

Expected output (in any order)
   Paul: 1315 1355 1405
   Jose: 830 835 855 915 930
   Zhang: 10 109 110
   Amos: 500 503 504

n: length of the badge records array
*/



import java.util.*;

public class EntriesAccess {
    public static void main(String[] argv) {
        String[][] badgeTimes = new String[][] {
                {"Paul", "1355"},
                {"Jennifer", "1910"},
                {"Jose", "835"},
                {"Jose", "830"},
                {"Paul", "1315"},
                {"Chloe", "0"},
                {"Chloe", "1910"},
                {"Jose", "1615"},
                {"Jose", "1640"},
                {"Paul", "1405"},
                {"Jose", "855"},
                {"Jose", "930"},
                {"Jose", "915"},
                {"Jose", "730"},
                {"Jose", "940"},
                {"Jennifer", "1335"},
                {"Jennifer", "730"},
                {"Jose", "1630"},
                {"Jennifer", "5"},
                {"Chloe", "1909"},
                {"Zhang", "1"},
                {"Zhang", "10"},
                {"Zhang", "109"},
                {"Zhang", "110"},
                {"Amos", "1"},
                {"Amos", "2"},
                {"Amos", "400"},
                {"Amos", "500"},
                {"Amos", "503"},
                {"Amos", "504"},
                {"Amos", "601"},
                {"Amos", "602"},
                {"Paul", "1416"},
        };

        List<String> result = oneHourAccessList(badgeTimes);
        System.out.println(result);
    }


    public static List<String> oneHourAccessList(String[][] badges_times) {
        List<String> result = new ArrayList<>();
        // Corner case:
        if(badges_times.length == 0) {
            return result;
        }

        // Step 1: Set up the hashmap
        Map<String, List<Integer>> name_entry_map = new HashMap<>();
        for(String[] badge_time : badges_times) {
            String user = badge_time[0];
            int entry = Integer.valueOf(badge_time[1]);
            List<Integer> entries = name_entry_map.getOrDefault(user, new ArrayList<Integer>());
            entries.add(entry);
            name_entry_map.put(user, entries);
        }

        // Step 2: loop each key and sort its value list
        for(String key : name_entry_map.keySet()) {
            List<Integer> entries = name_entry_map.get(key);
            Collections.sort(entries);
        }

        // Step 3: loop each key and applied two pointer strategies to each list and find the correct answer
        for(String key: name_entry_map.keySet()) {
            List<Integer> entries = name_entry_map.get(key);
            int start = 0;
            int end = 0;
            // 1315 1355 1405
            while(end < entries.size()) {
                if (entries.get(end) - entries.get(start) <= 100) {
                    end++;
                    if(end == entries.size() && end - start >= 3 ) {
                        List<Integer> userEntry = entries.subList(start, end);
                        String temp = key + userEntry.toString();
                        result.add(temp);
                    }
                } else {
                    if(end - start >= 3 ) {
                        List<Integer> userEntry = entries.subList(start,end);
                        String temp = key + userEntry.toString();
                        result.add(temp);
                        break;
                    }
                    start++;
                }
            }
        }
        return result;
    }
}

