package com.indeed.karat;

/*
only one pair + triples - 0 triple, 1 triple ..................... n triple

true
falsse
True
True
flase
True
flase
flase
True
flase
flase
flase
True
True
flase
flase
 */

import java.util.HashMap;
import java.util.Map;

public class Titles {
    public static void main(String[] argv) {
        String tiles1 = "11133555";  // true
        String tiles2 = "111333555"; // false - there is no pair
        String tiles3 = "00000111"; // true
        String tiles4 = "13233121"; // true
        String tiles5 = "11223344555"; // false - more than one pair
        String tiles6 = "99999999"; // true
        String tiles7 = "999999999"; // false
        String tiles8 = "9"; // false
        String tiles9 = "99"; // true
        String tiles10 = "000022"; // false
        String tiles11 = "888889"; // false
        String tiles12 = "889"; // false
        String tiles13 = "88888844"; // true
        String tiles14 = "77777777777777"; // true
        String tiles15 = "1111111"; // false
        String tiles16 = "1111122222"; // false

        if (complete(tiles1)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles2)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles3)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles4)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles5)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles6)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles7)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles8)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles9)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles10)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles11)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles12)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles13)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles14)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles15)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
        if (complete(tiles16)) {
            System.out.println("True");
        } else {
            System.out.println("false");
        }
    }
    public static boolean complete(String tiles) {
        // corner case:
        if(tiles.length() == 0) {
            return false;
        }

        // Step 1: Set up hashmap (Set up Data Structure for this problem)
        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<tiles.length(); i++) {
            char c = tiles.charAt(i);
            map.put(c, map.getOrDefault(c, 0)+1);
        }

        // Step 2: Doing the logic
        int numberOfPair = 0;
        for(Character key : map.keySet()) {
            int count = map.get(key);
            // Case: More than one pair
            if (count % 3 == 2) {
                numberOfPair++;
            }
            if(numberOfPair > 1) {
                return false;
            }

            // Case: remainder is 1
            if (count % 3 == 1) {
                return false;
            }
        }

        if(numberOfPair == 1) {
            return true;
        }
        return false;
    }
}
