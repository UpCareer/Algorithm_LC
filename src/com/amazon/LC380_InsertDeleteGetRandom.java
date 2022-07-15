package com.amazon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class LC380_InsertDeleteGetRandom {
    /** Initialize your data structure here. */
    List<Integer> list;
    HashMap<Integer, Integer> map;
    Random rand;
    public LC380_InsertDeleteGetRandom() {
        list = new ArrayList<>(); // Make sure Get O(1)
        map = new HashMap<>(); // use HashMap to map the value to an index of the string.
        rand = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if(map.containsKey(val)) {
            return false;
        }

        map.put(val, list.size());
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if(!map.containsKey(val)) return false;
        list.remove(map.get(val));
        map.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int n = rand.nextInt(list.size());
        return list.get(n);
    }
}
