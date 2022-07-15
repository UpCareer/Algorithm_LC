package com.indeed.vo.whiteboard;

/** =============================================================================
Follow Up

 1.memory不够大怎么办（数据点非常密集，5分钟就把内存爆了）

 2.getMedium方法实现

 需要注意的是follow up都是在原有的代码基础上做改进。

 对于1的方法，数据点密集的话，选择10秒的时间段，合并数据，得到一个10秒的和和数据数量，那么queue
 size就被一个int变量替换掉，这样丢掉过期数据的时候要更新sum和数据总和。这样会造成一定的偏差，
 但是没办法，条件不好内存不够就忍忍吧。

 对于2，就是quick select的find kth in an array的方法。复杂度是O(n).
 =============================================================================== **/

import java.util.Deque;
import java.util.LinkedList;

/** =============================================================================
Follow Up code
============================================================================= **/

public class Moving_Average_II {
    //queue的容量被限制
    private Deque<NewEvent> queue = new LinkedList<>(); //改成deque的话，可以从后面查
    private long sum = 0; //改用long显得严谨点儿
    int dataNum = 0;  // record how many events in the queue

    //这个是每次记录读进来的时间的,这个不用自己写,就是直接返回当前系统时间
    //假设它返回的是秒
    private int getNow(){
        return 0;
    }

    private boolean isExpired(int curTime, int preTime){
        return curTime - preTime > 300;
    }

    private void removeExpireEvent(){
        while (!queue.isEmpty() && isExpired(getNow(), queue.peekFirst().time)){
            NewEvent curE = queue.poll();
            sum -= curE.val;
            dataNum -= curE.size;
        }
    }

    public void record(int val){ //其实就是record这里有了两种办法，一种是建个新的，另一种就是合起来
        NewEvent last = queue.peekLast();

        // Case 1: Aggregate instead creating a new Event
        if (getNow() - last.time < 10){
            last.size += 1;
            last.val += val;
        } else { // Case 2:
            NewEvent event = new NewEvent(getNow(), val);
            queue.addLast(event);
        }
        dataNum += 1;
        sum += val;
        removeExpireEvent();
    }

    public double getAvg(){
        removeExpireEvent();
        if (!queue.isEmpty()){
            return (double) sum/dataNum;
        }
        return 0.0;
    }

    //实现find Median，其实O(1)操作的话，要始终维护两个heap，这样塞进去会很慢
    //原有基础上实现的话，那就直接quick select的办法了。
    //复杂度是On，因为每次average case是去掉一半，就是O(n)+O(n/2)+O(n/4)+... 最后出来是O(2n)
    //那这个需要把整个queue给倒出来再塞回去。
    public double getMedian(){
        removeExpireEvent();
        int[] temp = new int[queue.size()];
        for (int i = 0; i<queue.size(); i++){
            temp[i] = queue.poll().val;
        }
        //这里还得把queue还原回去,先不写了。
        int len = temp.length;
        if (len % 2 == 0){
            return 0.5*(findKth(temp, len/2, 0, len-1) + findKth(temp, len/2-1, 0, len-1));
        }
        return (double)findKth(temp, len/2, 0, len-1);
    }
    public int findKth(int[] temp, int k, int start, int end){
        int pivot = temp[start];
        int left = start, right = end;
        while (left < right){
            while (temp[right] > pivot && left < right){
                right--;
            }
            while (temp[left] <= pivot && left < right){
                left++;
            }
            swap(temp, left, right);
        }
        swap(temp, start, right);
        if (k == right){
            return pivot;
        }
        else if (k < right){
            return findKth(temp, k, start, right-1);
        }

        return findKth(temp, k, right+1, end);
    }
    public void swap(int[] temp, int left, int right){
        int i = temp[left];
        temp[left] = temp[right];
        temp[right] = i;
    }
}
class NewEvent{
    int val;
    int time;
    int size;
    public NewEvent(int val, int time){
        this.val = val;
        this.time = time;
        this.size = 1;
    }
}
