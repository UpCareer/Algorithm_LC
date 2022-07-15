package com.lc.cyclicsort;

/**
 * 循环排序(Cyclic Sort)专题:
 *  Cyclic Sort pattern which is very useful to solve the problems involving arrays containing numbers in a given range,
 *  finding the missing or duplicate numbers.
 *
 *  什么时候使用该模板:
 *
 * 这些问题一般设计到排序好的数组，而且数值一般满足于一定的区间
 * 如果问题让你需要在排好序/翻转过的数组中，寻找丢失的/重复的/最小的元素
 *
 * How do we define an element as sorted ?
 * An element which is at a position equal to the number of elements in the data-set that it is greater than or equal to
 *
 * Cycle sort: An unstable, in-place, comparison-based sorting algorithm which sorts the list using a set of cycles
 */

/**
 * Grokking the Coding Interview: Patterns for Coding Questions
 * Introduction (cyclic sort):
 *
 * This pattern describes an interesting approach to deal with problems involving arrays containing numbers in a give
 * range. For example, take following problem:
 * "You are given an unsorted array containing numbers from the range 1 to n. The array can have duplicates, which means
 * that some numbers will be missing. Find all the missing numbers"
 *
 * To efficiently solve this problem, we can use the fact that input array contains numbers in the range of 1 to n.
 * For example, to efficiently sort the array, we can try placing each number in its correct place, i.e., placing '1'
 * at index '0', placing 2 at index 1, and so on. Once we are done with the sorting, we can iterate the array to find
 * all indices that are missing the correct numbers. These will be our required numbers.
 */

/**
 * Problem Statement: We are given an array containing n objects. Each object, when created, was assigned a unique number
 * from 1 to n based on their creation sequence. This means that the object with sequence number 3 was created just before
 * the object with sequence number 4.
 *
 * Write a function to sort the objects in-place on their creation sequence number in O(n) and without any extra sapce.
 * For simplicity, let's assume we are passed an integer array contianing only the sequence numbers, though each number
 * is actually an object
 *
 * Example 1:
 * Input: [3,1,5,4,2]
 * output: [1,2,3,4,5]
 *
 * Example 2:
 * Input: [2,6,4,3,1,5]
 * output: [1,2,3,4,5, 6]
 *
 * Solution: Instead, what if we iterate the array one number at a time, and if the current number we are iterating is not
 * at the correct index, we swap it with the number at its correct index. This way we will go through all numbers and
 * place them in their correct indices, hence, sorting the whole array.
 * */


public class CyclicSort {

    public static void sort(int[] nums) {
        int i=0;
        while(i < nums.length) {
            int j = nums[i]-1;
            if(nums[i] != nums[j]) {
                swap(nums, i, j);
            } else {
                i++;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {3,1,5,4,2};
        CyclicSort.sort(arr);
        for(int num : arr) {
            System.out.println(num + " ");
        }
        System.out.println();
    }

}
