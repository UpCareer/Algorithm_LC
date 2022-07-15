package com.indeed.vo.whiteboard;

/* =============================================================================
Question Description
=============================================================================*/
/*
    Given n sorted stream, and a constant number k. The stream type is like iterator
        and it has two functions, move() and getValue(), find a list of numbers that each
        of them appears at least k times in these streams. Duplicate numbers in a stream
        should be counted as once.

        Note: In the interview, we should use min heap method
 */
/* =============================================================================
code
=============================================================================*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

class Stream{
    Iterator<Integer> iterator;
    public Stream(List<Integer> list){
        this.iterator = list.iterator();
    }
    public boolean move(){
        return iterator.hasNext();
    }
    public int getValue(){
        return iterator.next();
    }
}

class Num{
    int val;
    Stream stream;
    public Num(Stream stream){
        this.stream = stream;
        this.val = stream.getValue();
    }
}
public class MergeKSortedStreams {

    public List<Integer> getNumberInAtLeastKStream(List<Stream> lists, int k){
        List<Integer> res = new ArrayList<>();
        if (lists == null || lists.size() == 0) return res;

        PriorityQueue<Num> minHeap = new PriorityQueue<>(new Comparator<Num>() {
            @Override
            public int compare(Num o1, Num o2) {
                return o1.val - o2.val;
            }
        });

        //先把所有的stream的第一个元素放进heap里面: 每个stream 第一个元素
        // The size of the heap is K
        for (Stream s: lists) {
            if (s.move()){ //这里先判断一下要不就崩了
                minHeap.offer(new Num(s));
            }
        }

        while (!minHeap.isEmpty()){
            // Pick up the first stream
            Num cur = minHeap.poll();
            int curValue = cur.val;
            int count = 1;
            while (cur.stream.move()){
                int nextVal = cur.stream.getValue();
                if (nextVal == curValue){
                    continue;
                }
                else {
                    cur.val = nextVal;
                    minHeap.offer(cur);
                    break;
                }
            }
            //更新其他stream的头部，就是把指针往后挪，相同的数字就计数了。
            while (!minHeap.isEmpty() && curValue == minHeap.peek().val){
                count++;
                Num num = minHeap.poll();
               //int numVal = num.val;

                while (num.stream.move()){
                    int nextVal = num.stream.getValue();
                    if (curValue == nextVal){
                        continue;
                    }
                    else {
                        num.val = nextVal;
                        minHeap.offer(num);
                        break;
                    }
                }
            }

            if (count >= k){
                res.add(curValue);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MergeKSortedStreams test = new MergeKSortedStreams();
        Integer[] arr1 = {1,2,3,4};
        Integer[] arr2 = {2,5,6};
        Integer[] arr3 = {2,5,7};

        List<Integer> l1 = Arrays.asList(arr1);
        List<Integer> l2 = Arrays.asList(arr2);
        List<Integer> l3 = Arrays.asList(arr3);

        List<Stream> lists = new ArrayList<>();
        lists.add(new Stream(l1));
        lists.add(new Stream(l2));
        lists.add(new Stream(l3));

        List<Integer> res = test.getNumberInAtLeastKStream(lists, 2);
        System.out.println(res);
    }



}
