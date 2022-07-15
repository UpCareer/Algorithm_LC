package com.indeed.karat;
/*
You decide to create a substitution cipher.
The cipher alphabet is based on a key shared amongst those of your friends who don't mind spoilers.
Suppose the key is:
"The quick onyx goblin, Grabbing his sword ==}-------- jumps over the 1st lazy dwarf!".
We use only the unique letters in this key to set the order of the characters in the substitution table.
T H E Q U I C K O N Y X G B L R A S W D J M P V Z F
(spaces added for readability)
We then align it with the regular alphabet:
A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
T H E Q U I C K O N Y X G B L R A S W D J M P V Z F
Which gives us the substitution mapping: A becomes T, B becomes H, C becomes E, etc.
Write a function that takes a key and a string and encrypts the string with the key.
Example:
key = "The quick onyx goblin, Grabbing his sword ==}-------- jumps over the 1st lazy dwarf!"
encrypt("It was all a dream.", key) -> "Od ptw txx t qsutg."
encrypt("Would you kindly?", key) -> "Pljxq zlj yobqxz?"
Complexity analysis:
m: The length of the message
k: The length of the key
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Cipher {

    public static void main(String[] args) {
        String key = "The quick onyx goblin, Grabbing his sword ==}-------- jumps over the 1st lazy dwarf!";
        String input1 = "It was all a dream.";
        String input2 = "Would you kindly?";

        String result1 = encrypt(input1, key);
        String result2 = encrypt(input2, key);

        /*
            encrypt("It was all a dream.", key) -> "Od ptw txx t qsutg."
            encrypt("Would you kindly?", key) -> "Pljxq zlj yobqxz?"
         */
        System.out.println(result1);
        System.out.println(result2);
    }

    public static String encrypt(String input, String key) {

        String result = "";
        // Corner case:
        if(input.length() == 0 || key.length() == 0) {
            return result;
        }

        // Step 1: Set up LinkedHashSet to keep the unique letters in this key and keep the order
        // We use only the unique letters in this key to set the order of the characters in the substitution table.

        //keypoint: LinkedHashSet vs TreeSet vs HashSet
        //          Character usage
        Set<Character> keySet = new LinkedHashSet<>();
        for(int i=0; i<key.length(); i++) {
            if (Character.isLetter(key.charAt(i))) {
                keySet.add(Character.toUpperCase(key.charAt(i)));
            }
        }

        // Step 2: Set up Map to map encryption mapping
        Map<Character, Character> map = new HashMap<>();
        // How to get the element in Set one by one, use List or Array
        List<Character> list = new ArrayList<>(keySet);
        int index = 0;
        for(char c='a'; c<='z'; c++) {
            map.put(Character.toUpperCase(c),list.get(index++));
        }

        // Step 3: Encrypt the input string
        // StringBuilder usuage
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if(Character.isLetter(c) && Character.isUpperCase(c)) {
                sb.append(map.get(c));
            } else if (Character.isLetter(c) && Character.isLowerCase(c)) {
                sb.append(Character.toLowerCase(map.get(Character.toUpperCase(c))));
            } else {
                sb.append(c);
            }
        }

        return sb.toString();
    }
}
