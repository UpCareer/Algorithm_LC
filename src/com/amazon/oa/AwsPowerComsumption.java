package com.amazon.oa;

import java.util.List;
import java.util.PriorityQueue;

/*
Question on AWS power consumption, given BootingPower[i], processingPower[i], powerMax.
        For maximum utilization, the data center wishes to group these processors into clusters. Clusters can only be formed of processors located adjacent to each other. For example, processors 2,3,4,5 can form a cluster, but 1,3,4 cannot.
        cluster of k processors defined as (i, i+1,...., i+k-1)

        Net power consumption = maximum booting power among the k processors + (sum of processing power of processors)*k.
        A cluster is said to be sustainable if it's net power consumption does not exceed a given threshold value powerMax.

        Example:
        bootingPower = [3,6,1,3,4]
        processingPower = [2,1,3,4,5]
        powerMax = 25

        If k = 2, any adjacent pair can be choosen. The highest usage is the pair [4,5] with net power consumption 4 + (4 + 5)2 = 22. Next, try k = 3. Group the first 3 processors together as:
        Here,
        Max booting power = max(3,6,1)
        Sum of processing power = 2 + 1+ 3 = 6
        Net power consumption = 6 + 63 = 24 <= powerMax

        Thus, k = 3 is a sustainable cluster.
        Example:
        bootingPower = [8,8,10,9,2]
        processingPower = [4,1,4,5,3]
        powerMax = 33

        If k = 2, consisting of first 2 processors.
        Net power consumption = max(8,8) + (4+1)*2 = 18 <= 33 (powerMax)

        Thus, k = 2 is a sustainable cluster.

        Example:
        bootingPower = [11,12,19]
        processingPower = [10,8,7]
        powerMax = 6

        k = 0, not possible to form any sustainable clusters.

        I tried to use a sliding window but only passed few cases. Can somehow solve this question.
 */
public class AwsPowerComsumption {

    public static int findMaximumSustainableClusterSize(List<Integer> processingPower,
                                                        List<Integer> bootingPower, long maxPower) {
        // Max Heap
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>((a, b) -> b - a);
        int maxLength = 0;
        int currentLength = 1;
        int startIdx = 0;
        int endIdx = 0;
        int currentSumProcessingPower = processingPower.get(0);
        // Priority queue is used to find the maximum booting power
        priorityQueue.add(bootingPower.get(0));
        // sliding window
        while (endIdx < processingPower.size()) {
            int currentBootingPower = priorityQueue.peek();
            long currentPower = currentBootingPower + ((long) currentSumProcessingPower) * currentLength;

            if (currentPower <= maxPower) {
                maxLength = currentLength;
                endIdx++;
                currentLength++;
            } else {
                currentSumProcessingPower -= processingPower.get(startIdx);
                priorityQueue.remove(bootingPower.get(startIdx));
                startIdx++;
                endIdx++;
            }

            if (endIdx < processingPower.size()) {
                priorityQueue.add(bootingPower.get(endIdx));
                currentSumProcessingPower += processingPower.get(endIdx);
            }
        }
        return maxLength;
    }



}
