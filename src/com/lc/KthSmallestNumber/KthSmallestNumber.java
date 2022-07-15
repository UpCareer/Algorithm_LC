package com.lc.KthSmallestNumber;

/**
 * Problem statement
 * Given an unsorted array of numbers, find Kth smallest number in it.
 * Please note that it is the Kth smallest number in the sorted order, not the Kth distinct element
 *
 * Example 1:
 * Input: [1,5,12,2,11,5], K=3
 * output: 5
 * Explanation: The 3rd smallest number is 5, as the first two smaller numbers are [1,2].
 *
 * Example 2:
 * Input: [1,5,12,2,11,5], K=4
 * output: 5
 * Explanation: The 4th smallest number is 5, as the first two smaller numbers are [1,2,5].
 *
 * Example 3:
 * Input: [5,12,11,-1,12], K=3
 * output: 11
 * Explanation: The 3rd smallest number is 11, as the first two smaller numbers are [5, -1].
 *
 * Similar Problems are:
 * 1. Find the Kth largest number in an unsorted array.
 * 2. Find the median of an unsorted array.
 * 3. Find the K smallest or largest numbers in an unsorted array.
 *
 */

public class KthSmallestNumber {
    /**
     * 1. Brute-force: time complexity O(N*K) space complexity O(1)
     *
     * 2. Brute-force using Sorting:
     *    we can use an in-place sort like a HeapSort to sort the input array to et the Kth smallest number.
     *    Time Complexity: Sorting will take O(NlogN) and if we are not using an in-place sorting algorithm,
     *    We will need O(N) space
     *
     * 3. Using Max-Heap
     *    We can iterate the array and use a Max Heap to keep track of K smallest number. In the end, the root of the
     *    heap will have the Kth smallest number.
     *
     *    public static int findKthSmallestNumber(int[] nums, int k) {
     *        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>((n1,n2)-> n2-n1);
     *        // put first K numbers in the max heap
     *        for(int i=0; i<k; i++) maxHeap.add(nums[i]);
     *
     *        // go through the remaining numbers of the array, if the number from the array is smaller than the
     *        // top (biggest) numbers of the heap, remove the top number from heap and add the number from array
     *        for(int i=k; k< nums.length; i++) {
     *          if(nums[i] < maxHeap.peek()) {
     *              maxHeap.poll();
     *              maxHeap.add(nums[i]);
     *          }
     *        }
     *        // the root of heap has the kth smallest number
     *        return maxHeap.peek();
     *    }
     *
     *    Time complexity: O(N*logk); Space Complexity: O(K)
     *
     * 4. Using Min-Heap
     *    We can use a Min Heap to find the Kth smallest number. We can insert all the numbers in the min-heap (size is N)
     *    and then extract the top K numbers from the heap to find the Kth smallest number.
     *    Time Complexity: Building a heap with N elements will take O(N), extracting K number will take O(K*logN)
     *                     Overall, the time complexity of this algorithm will be O(N + K*logN) and the space complexity
     *                     will be O(N)
     *
     * 5. Using Partition Scheme of Quicksort
     *    Quicksort picks a number called pivot and partition the input array around it. After partitionning, all numbers
     *    smalller than the pivot are to the left of the pivot, and all numbers greater than or equal to the pivot are
     *    to the right of the pivot. This ensures that the pivot has reached its correct sorted position
     *    We can use this partitioning scheme to find the Kth smallest number. We will recursively partition the input
     *    array and if, after partitioning, our pivot is at the K-1 index we have found our required number, if not, we
     *    will choose one of following option:
     *    1. If pivot's position is larger than K-1, we will recursively partition the array on numbers lower than the pivot
     *    2. If pivot's position is smaller than K-1, we will recursively partition the array on numbers greater
     *       than the pivot
     *
     *    The above algorithm is known as QuickSelect and has a worst case time complexity of O(N*N). The best and average
     *    is O(N), which is better than the best and average case of QuickSort.
     *    The worst-case occurs when, at every step. The partition procedure splits the N-length array into arrays of size 1
     *    and N-1. This can only happen when the input array is sorted or it all of its elements are the same. This 'unlucky'
     *    selection of pivot elements requires O(N) recursive calls.
     *
     * 6. Using Randomized Partitioning Scheme of Quicksort
     *    The worst case for Quicksort occurs when the partition procedure splits the N-length array into arrays of size 1
     *    and N-1. To mitigate this, instead of always picking a fixed index for pivot (for example, in the above algorithm
     *    we always pick nums[high] as the pivot), we can randomly select an element as pivot. After randomly choosing the
     *    pivot element, we expect the split of the input array to be reasonably well balanced on average.
     *
     *    This algorithm has the same worst and average case time complexities as for the previous algorithm. But choosing
     *    the pivot randomly has the effect of rendering the worst-case very unlikely, particular for the large arrays.
     *    expected time complexity of the above algorithm will be O(N), but the absolute worst case is still O(N*N). This
     *    algorithm is a lot faster than the non-randomized version.
     *
     * 7. Using the Median of Medians
     *    We can use the Median of Medians algorithm to choose a good pivot for the partitioning algorithm of the quicksort.
     *    This algorithm finds an approximate median of an array in linear time O(N). When this approximate median is used
     *    as the pivot, the worst-case complexity of the partitioning procedure reduces to linear O(N), which is also the
     *    asymptotically optimal worst-case complexity of any sorting / select algorithm.
     *
     *    This is how the partitioning algorithm works:
     *    1. If we have 5 or less than 5 elements in the input array, we simply take its first element as the pivot. If not
     */

}
