package com.wayfair;

/*
Sample Input:

        student_course_pairs_1 = [
        ["58", "Software Design"],
        ["58", "Linear Algebra"],
        ["94", "Art History"],
        ["94", "Operating Systems"],
        ["17", "Software Design"],
        ["58", "Mechanics"],
        ["58", "Economics"],
        ["17", "Linear Algebra"],
        ["17", "Political Science"],
        ["94", "Economics"],
        ["25", "Economics"],
        ]

        Sample Output (pseudocode, in any order):

        find_pairs(student_course_pairs_1) =>
        {
        [58, 17]: ["Software Design", "Linear Algebra"]
        [58, 94]: ["Economics"]
        [58, 25]: ["Economics"]
        [94, 25]: ["Economics"]
        [17, 94]: []
        [17, 25]: []
        }

        Additional test cases:

        Sample Input:

        student_course_pairs_2 = [
        ["42", "Software Design"],
        ["0", "Advanced Mechanics"],
        ["9", "Art History"],
        ]

        Sample output:

        find_pairs(student_course_pairs_2) =>
        {
        [0, 42]: []
        [0, 9]: []
        [9, 42]: []
        }
*/

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OverlapCourses {

    public static List<String> find_overlap_course_pairs(String[][] student_course_pairs) {
        if(student_course_pairs.length == 0 || student_course_pairs == null) {
            return null;
        }

        // Set up Map (studentID, courses_list)
        Map<String, List<String>> student_courses_map = new HashMap<String, List<String>>();
        for(String[] student_course_pair : student_course_pairs) {
            //  ["58", "Software Design"],
            List<String> list = student_courses_map.get(student_course_pair[0]);
            if (list == null) {
                list = new ArrayList<>();
                student_courses_map.put(student_course_pair[0], list);
            }
            list.add(student_course_pair[1]);
        }

        // Student_course_map is like (58, "Software Design"->"Linear Algebra"->"Mechanics"->"Economics")
        for(String studentID_1 : student_courses_map.keySet()) {
        }


        return null;
    }
}
