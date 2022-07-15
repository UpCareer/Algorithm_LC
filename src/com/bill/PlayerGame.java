package com.bill;

/**
 A video game developer is developing a game in which the character makes their way through several segments of a level.
 In each segment, if the character collects a coin the player scores 1 point.
 However, if the segment does not have a coin 1 point is deducted from the total score.
 Player 1 always begins the level and, at some point, the game play is turned over to Player 2 to complete the level.
 Player1's goal is to achieve heigher score than Player 2 once the level is completed.
 Given the segment status(whether they contain the coin or not),
 find the minimum number of segments Player1 should play so that, in the end Player1's score is greater than Player2.

 Example: [1,1,0,1]
 Player 1 has the follwoing option:

 Play 0 segment- Player1's score 0 and Player2's score ->3-1=2(3 points for segments with coin and -1 for no coin segment)
 Play 1 segment- Player1's score 1 and Player2's score -> 2-1=1
 Play 2 segment- Player1's score 2 and Player2's score -> 1-1=0
 So the answer is 2.

 What can be optimized solution
 */

public class PlayerGame {

    public static int getMinSegments(int[] input) {
        // Corner case
        if(input == null || input.length == 0) {
            return -1;
        }

        // Transform the input to score
        int[] score = new int[input.length];
        for(int i=0; i<input.length; i++) {
            if(input[i] == 0) {
                score[i] = -1;
            } else {
                score[i] = 1;
            }
        }

        // loop through the input and get the sum
        int sum = 0;
        for(int i=0; i < score.length; i++) {
            sum = sum + score[i];
        }

        int curSum = 0;
        int index = 0;
        for(int i=0; i < score.length; i++) {
            if(curSum > sum) {
                index = i;
                break;
            }
            curSum = curSum + score[i];
            sum = sum - curSum;
        }
        return index;
    }

    public static void main(String[] args) {
        int input[] = {1, 1, 0, 1};
        int res = getMinSegments(input);
        System.out.println(res);
    }
}
