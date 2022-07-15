package com.wayfair;

import java.util.HashMap;
import java.util.LinkedList;

public class FindHalfWayCourse {

     // Follow up: 有交叉课程

    /*  HashMap: order
            ["Algorithms""FoundationsofComputerScience"]
            ["DataStructures""Algorithms"]
            ["FoundationsofComputerScience""Logic"]
            ["Logic""Compilers"]
            ["Compilers""DistributedSystems"]
     */

      /*  HashMap: reverse order
            ["FoundationsofComputerScience" "Algorithms"]
            ["Algorithms" "DataStructures"]
            ["Logic" "FoundationsofComputerScience"]
            ["Compilers" "Logic"]
            ["DistributedSystems" "Compilers"]
     */

    /* "DataStructures" <- "Algorithms" <- "FoundationsofComputerScience"
    *  <- "Logic" <- "Compilers" <- "DistributedSystems"  */
    public static void main(String[] args) {
        String[][] courses = {{"Algorithms","FoundationsofComputerScience"},
                {"DataStructures","Algorithms"},
                {"FoundationsofComputerScience","Logic"},
                {"Logic","Compilers"},
                {"Compilers","DistributedSystems"}};

        String ret = findHalfway(courses);
        System.out.println("ret = " + ret);
        return;
    }

    public static String findHalfway(String[][] courses) {
        LinkedList<String> courseSequence = new LinkedList<>();

        int size = courses.length;

        // init two HashMap
        HashMap<String, String> ab = new HashMap<>();
        HashMap<String, String> ba = new HashMap<>();
        // Set up the hashMap
        for(int i=0; i<size; i++) {
            String front = courses[i][0];
            String end = courses[i][1];
            ab.put(front, end);
            ba.put(end, front);
        }

        // initialize the list
        courseSequence.add(courses[0][0]);
        courseSequence.add(courses[0][1]);

        for(int j=1; j<size; j++) {
            String currentHead = courseSequence.getFirst();
            String currentTail = courseSequence.getLast();
            if(ba.get(currentHead) != null) {
                courseSequence.addFirst(ba.get(currentHead));
            }
            if(ab.get(currentTail) != null) {
                courseSequence.addLast(ab.get(currentTail));
            }
        }
        return courseSequence.get(size/2);
    }




}
