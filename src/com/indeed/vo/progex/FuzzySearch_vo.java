package com.indeed.vo.progex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class FuzzySearch_vo {

    static class Job {
        int id;
        Set<String> contentSet;
        int score;

        public Job(int id, String content) {
            this.id = id;
            this.contentSet = new HashSet<>(Arrays.asList(content.split("\\s+")));
        }
    }

    static int bestCount = 10;
    static List<Job> jobList = new ArrayList<>();

    public static void storeDocument(final String document, final int documentNumber) {
        // TODO implement
        jobList.add(new Job(documentNumber, document));
    }

    private static int getMatchScore(Set<String> contentSet, Set<String> keySet) {
        int score = 0;
        for (String key : keySet) {
            if (contentSet.contains(key)) {
                score++;
            }
        }
        return score;
    }

    public static String performSearch(final String search) {
        // TODO implement
        Set<String> searchKey = new HashSet<>(Arrays.asList(search.split("\\s+")));
        PriorityQueue<Job> heap = new PriorityQueue<Job>(bestCount, (job1, job2) ->
        {
            if (job1.score == job2.score) {
                return job1.id - job2.id;
            } else {
                return job2.score - job1.score;
            }
        });
        for (Job job : jobList) {
            job.score = getMatchScore(job.contentSet, searchKey);
            ;
            if (job.score != 0) {
                heap.add(job);
            }
        }
        List<Job> res = new ArrayList<>();
        int i = 0;
        while (!heap.isEmpty() && i < bestCount) {
            res.add(heap.poll());
            i++;
        }
        if (res.size() == 0) {
            return "-1";
        }
        StringBuilder sb = new StringBuilder();
        for (Job job : res) {
            sb.append(job.id + " ");
        }
        return sb.toString();

    }

}
