package com.wayfair;

/* You are analyzing data for Aquaintly, a hot new social network.
   One of the fun features of Aquaintly is that users can rate movies they have seen from 1 to 5.
   We want to use these ratings to make movie recommendations.

   Ratings will be provided in the following format:
   [Member Name, Movie Name, Rating]
        We consider two users to have similar taste in movies if they have both rated the same movie as 4 or 5.
        A movie should be recommended to a user if:
        - They haven't rated the movie
        - A user with similar taste has rated the movie as 4 or 5

       Example:
        ratings = [
        ["Alice", "Frozen", "5"],
        ["Bob", "Mad Max", "5"],
        ["Charlie", "Lost In Translation", "4"],
        ["Charlie", "Inception", "4"],
        ["Bob", "All About Eve", "3"],
        ["Bob", "Lost In Translation", "5"],
        ["Dennis", "All About Eve", "5"],
        ["Dennis", "Mad Max", "4"],
        ["Charlie", "Topsy-Turvy", "2"],
        ["Dennis", "Topsy-Turvy", "4"],
        ["Alice", "Lost In Translation", "1"]
        ]

        If we want to recommend a movie to Charlie, we would recommend "Mad Max" because:
        - Charlie has not rated "Mad Max"
        - Charlie and Bob have similar taste as they both rated "Lost in Translation" 4 or 5
        - Bob rated "Mad Max" a 5

        Write a function that takes the name of a user and a collection of ratings, and returns a collection of all movie recommendations that can be made for the given user.
        All test cases:
        recommendations("Charlie", ratings) => ["Mad Max"]
        recommendations("Bob", ratings) => ["Inception", "Topsy-Turvy"]
        recommendations("Dennis", ratings) => ["Lost In Translation"]
        recommendations("Alice", ratings) => []

        Complexity Variable:
        R = number of ratings
        M = number of movies
        U = number of users
        */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class movieRatings {

    public static void main(String[] args) {
        String[][] ratings = {{"Alice", "Frozen", "5"}, {"Bob", "Mad Max", "5"}, {"Charlie", "Lost In Translation", "4"},
                {"Charlie", "Inception", "4"}, {"Bob", "All About Eve", "3"}, {"Bob", "Lost In Translation", "5"},
                {"Dennis", "All About Eve", "5"}, {"Dennis", "Mad Max", "4"}, {"Charlie", "Topsy-Turvy", "2"},
                {"Dennis", "Topsy-Turvy", "4"}, {"Alice", "Lost In Translation", "1"}};

        String[] users = {"Charlie", "Bob", "Dennis", "Alice"};
        for(String user : users) {
            List<String> ret = recommendations(user, ratings);
            System.out.println(user + ": " + ret);
        }

    }

    public static List<String> recommendations(String recommend_user, String[][] ratings) {

        List<String> result = new ArrayList<>();
        // Corner case
        if(ratings == null || ratings.length == 0) {
            return result;
        }


        // Step 1: watchedMoviesSet and highRatingWatchedMovieSet
        Set<String> watchedMoviesSet = new HashSet<String>();
        Set<String> highRatingWatchedMovieSet = new HashSet<String>();
        for(String rating[] : ratings) {
           // ["Alice", "Lost In Translation", "1"]
            if(recommend_user.equals(rating[0])) {
                int ratingValue = Integer.valueOf(rating[2]);
                if(ratingValue > 3) {
                    highRatingWatchedMovieSet.add(rating[1]);
                }
                watchedMoviesSet.add(rating[1]);
            }
        }

        // Corner case:
        if(highRatingWatchedMovieSet.size() == 0) {
            return result;
        }

        // Step 2 : commonRatingsUserSet
        Set<String> commonRatingsUserSet = new HashSet<>();
        for(String rating[] : ratings) {
            int ratingValue = Integer.valueOf(rating[2]);
            if(!recommend_user.equals(rating[0]) && highRatingWatchedMovieSet.contains(rating[1])
                && ratingValue > 3) {
                commonRatingsUserSet.add(rating[0]);
            }
        }

        // Step 3: loop through to Get result
        for(String rating[] : ratings) {
            // ["Alice", "Lost In Translation", "1"]
            int ratingValue = Integer.valueOf(rating[2]);
            if(commonRatingsUserSet.contains(rating[0]) && ratingValue > 3
                && !watchedMoviesSet.contains(rating[1])) {
                result.add(rating[1]);
            }
        }

        return result;
    }
}
