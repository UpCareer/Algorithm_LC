package com.indeed.vo.whiteboard;

/** =============================================================================
Question：
 Now you have a dice, and throw it multiple times.
 Find the possibility the sum of points is a target number
=============================================================================*/

/** =============================================================================
code
============================================================================= **/
//简单DFS了一下,还是很容易实现的。
//下一步需要看DP和记忆化搜索怎么搞。

public class Dice_Sum {

    public double getPossibility(int dice, int target) {
        // corner case: dice * 1 <= target range <= dice * 6
        if(dice <=0 || target < dice || target > 6 * target) {
            return 0.0;
        }
        int total = (int) Math.pow(6, dice);
        // The reason to use array object, we want record to bring the result back
        int[] record = new int[1];
        dfs(dice, target, record);
        int count = record[0];
        System.out.println(count);
        System.out.println(total);
        return (double) count / total;
    }

    public void dfs(int dice, int target, int[] record) {
        if(dice == 0 && target == 0) {
            // find one possibility
            record[0]++;
            return;
        }
        if(dice <= 0 || target <= 0) {
            return;
        }

        for(int i=1; i<=6; i++) {
            dfs(dice-1, target-i, record);
        }
        return;
    }

    public static void main(String[] args) {
        Dice_Sum test = new Dice_Sum();
        int dice = 2;
        int target = 4;
        double res1 = test.getPossibility(dice, target);
        System.out.print(res1);
    }
}
