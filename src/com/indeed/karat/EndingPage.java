package com.indeed.karat;

/**

 You are reading a Build Your Own Story book.
 It is like a normal book except that choices on some pages affect the story,
 sending you to one of two options for your next page.

 These choices are really stressing you out, so you decide that you'll either always pick the first option,
 or always pick the second option.

 You start reading at page 1 and read forward one page at a time unless you reach a choice or an ending.
 The choices are provided in a list, sorted by the page containing the choice,
 and each choice has two options of pages to go to next. In this example, you are on page 3,
 where there is a choice. Option 1 goes to page 14 and Option 2 goes to page 2.

 choices1 = [[3, 14, 2]] => [current_page, option_1, option_2]
 The ending pages are also given in a sorted list:
 endings = [6, 15, 21, 30]

 Given a list of endings, a list of choices with their options,
 and the choice you will always take (Option 1 or Option 2),
 return the ending you will reach. If you get stuck in a loop, return -1.

 Example:
 find_ending(endings, choices1, 1) (always Option 1)

 Start: 1 -> 2 -> 3(choice) -> 14 -> 15(end) => Return 15

 find_ending(endings, choices1, 2) (always Option 2)
 Start: 1 -> 2 -> 3(choice) -> 2 -> 3(choice) -> 2... => Return -1

 Additional inputs:
 choices2 = [[5, 11, 28], [9, 19, 29], [14, 16, 20], [18, 7, 22], [25, 6, 30]]
 choices3 = [][br]
 choices4 = [[2, 10, 15], [3, 4, 10], [4, 3, 15], [10, 3, 15]]

 Complexity Variable:
 n = number of pages
 (endings and choices are bound by the number of pages)
 All Test Cases - camelCase:
 findEnding(endings, choices1, 1) => 15
 findEnding(endings, choices1, 2) => -1
 findEnding(endings, choices2, 1) => 21
 findEnding(endings, choices2, 2) => 30
 findEnding(endings, choices3, 1) => 6
 findEnding(endings, choices3, 2) => 6
 findEnding(endings, choices4, 1) => -1
 findEnding(endings, choices4, 2) => 15

 //[4, 2]
 1->2->3->4

 endings = [6, 15, 21, 30]
 choices2 = [[5, 11, 28], [9, 19, 29], [14, 16, 20], [18, 7, 22], [25, 6, 30]]
 find_ending(endings, choices1, 1) (always Option 1)

 Start: 1 -> 2 -> 3(choice) -> 14 -> 15(end) => Return 15
 **/

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
Algorithm:
克拉的面试时间非常紧张，一旦有bug卡住容易慌，平时要训练自己快速debug，知道哪里容易出错就好。
感觉这题就是Set记录visited，endings，map记录《choice，optionX》，用一个while loop一步一步search，每次检查是否end或者visited
 **/

public class EndingPage {

    public static void main(String[] args) {
        int[] endings = {6, 15, 21, 30};
        int[][] choices1 = {{3, 14, 2}};
        int[][] choices2 = {{5, 11, 28}, {9, 19, 29}, {14, 16, 20}, {18, 7, 22}, {25, 6, 30}};
        int[][] choices3 = {};
        int[][] choices4 = {{2, 10, 15}, {3, 4, 10}, {4, 3, 15}, {10, 3, 15}};

        System.out.println(findEnding(endings, choices1, 1)); // => 15
        System.out.println(findEnding(endings, choices1, 2)); // => -1
        System.out.println(findEnding(endings, choices2, 1)); // => 21
        System.out.println(findEnding(endings, choices2, 2)); // => 30
        System.out.println(findEnding(endings, choices3, 1)); // => 6
        System.out.println(findEnding(endings, choices3, 2)); // => 6
        System.out.println(findEnding(endings, choices4, 1)); // => -1
        System.out.println(findEnding(endings, choices4, 2)); // => 15



    }
    public static int findEnding(int[] endings, int[][] choices, int option) {
        // Corner case:
        if(endings.length == 0) {
            return -1;
        }

        if(choices.length == 0) {
            return endings[0];
        }

        //Step 1: Set up Data structure - ending Set and visited Set
        Set<Integer> endingSet = new HashSet<>();
        Set<Integer> visitedSet = new HashSet<>();
        for(int i = 0; i < endings.length; i++) {
            endingSet.add(endings[i]);
        }

        // Step 2: Set up choice mapping, page : choices
        Map<Integer, int[]> map = new HashMap<>();
        for(int[] choice : choices) {
            map.put(choice[0], choice);
        }

        // Step 3: Start to looking for the ending page
        int currentPage = 0;
        while(!endingSet.contains(currentPage) && !visitedSet.contains(currentPage)) {
            visitedSet.add(currentPage);
            int nextPage;
            // check if currentPage has choice
            if(map.containsKey(currentPage)) {
                int[] choice = map.get(currentPage);
                nextPage = choice[option];
            } else {
                nextPage = currentPage + 1;
            }
            currentPage = nextPage;
        }

        if(endingSet.contains(currentPage)) {
            return currentPage;
        }
        return -1;
    }
}
