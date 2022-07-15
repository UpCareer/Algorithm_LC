package com.amazon.oa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 Amazon Prime Video is a subscription video-on-demand over-the-top streaming and rental service. The team at Prime Video
 is developing a method to divide movies into groups based on the number of awards they have won. A group can consist of
 any number of movies, but the difference in the number of awards won by any two movies in the group must not exceed k.
 The movies can be grouped together irrespective of their initial order.
 Determine the minimum number of groups that can be formed such that each movie is in exactly one group.
 Example The numbers of awards per movie are awards = [1, 5, 4, 6, 8, 9, 2]. and the maximum allowed difference is k = 3.
 One way to divide the movies into the minimum number of groups is:
 • The first group can contain (2. 1). The maximum difference between awards of any two movies is 1
 which does not exceed k
 • The second group can contain (5, 4, 6). The maximum difference between awards of any two movies is 2
 which does not exceed k
 • The third group can contain (8. 9). The maximum difference between awards of any two movies is 1
 which does not exceed k The movies can be divided into a minimum of 3 groups.
 Function Description Complete the function minimumGroups in the editor below.
 minimumGroups has the following parameters: int awards[n]: the number of awards each movie has earned
 int k: the maximum difference in awards counts
 Returns int: the minimum number of groups into which all the movies can be divided

 Intuition

 Sort the movies in ascending order
 Assume an imaginary bucket and initialize it with the smallest award (the smallest in the sorted list)
 Traverse through the award list and and and check the difference between the current award and the smallest element in the bucket.
 If the difference exceeds K, increment count (create another imaginary bucket because both awards can't stay in the same bucket because the difference exceeds K)
 Return count
 */

public class MoviesRatingsDecrease {

    public static int getMinGroups(Integer[] awards,int k) {
        // Sort the array
        List<Integer> awardsList =  new ArrayList<Integer>(Arrays.asList(awards));
        Collections.sort(awardsList);

        return 0;
    }
}
