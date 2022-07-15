package com.indeed.vo.whiteboard;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Coding 2: Moving window average，这个 window 是个时间段，有一个 recordValue API 要实现，一个 getAverage API
 * 要实现过去 10 分钟内recorded value 的平均数. Follow up 是 moving window median.
 * 注意这个 follow up 是计算落在 window 时间段 内的所有值的，我觉得是 leetcode 480 的变种.
 * 面经里都说 follow-up 都在讲 quick sort O(n) 实现 median，我一开始说可以用 sort (quick sort)来实现，
 * 提了一句两个堆估计也可以. 面试官让我解释两个堆的方法，好像有点期待我写出来
 * (这跟面经不一样！被问到的时候有点懵，还剩 15 分钟我觉得写不出来 bug free 的代码，所以只是讲了用两个堆的思路，写了 pseudo code).
 * 我真是自己给自己挖坑... 回来 LC 一搜，发现我那个解法太麻烦了...
 *
 * 第一轮白板是做moving average， 求最后5分钟的平均值，地里有的题， follow up是问中位数怎么求，就是findKth largest value，
 * 用quick select就行。地里有个link是关于indeed onsite的面经题，翻一下就能找到
 *
 * 在Google搜Moving Average From Data Stream，有个expiration window，超时的就不用算了。
 * 我就用了deque(as queue), dict, running_sum(int), running_cnt(int)。分析了复杂度什么的，面试官也没说不对。
 *
 * 第三轮：遇到了一个笑里藏刀的面试官带了一个印度shadow，开始聊简历和写code的时候他一直面带微笑以为是礼貌+觉得暂时都ok。题目是moving average，
 * 每个数据record后第五分钟就过期，写record和求没过期data的average两个function。写完这段code的时候我的心情还是很平静的，
 * 而且是最后一轮白板了也比较轻松。。。直到这位interviewer开始follow up。。。先是每个function的time complexity
 * 然后best case/worst case/average time。。简直lz的噩梦。。lz有一处helper func里面的时间复杂度说的不太对，
 * 他就开始给我引导，然后开始扣每一行代码的time complexity！每一行！他一直试图想引导和帮助我得到正确的结果。。
 * 这里lz感觉很凉了。。虽然他还是一直面带微笑。。。但这时候的笑已经让我心里发慌了。。后面还有点时间，问了getmedian的follow up，
 * lz给了quick select的想法，问时间，答O(n) on average，他说这个慢你能不能do better，
 * 我说那就用min heap + max heap维持前一半和后一半，这样O(logn)， 但建立慢不过只用建一次之后插入logn比较快适合多次操作，
 * 又问了删除怎么办，答每个删除logn，但最差情况可能删除的多时间很大，又问average情况，我说logn，时间不够没让写这里的代码。Q&A，三轮结束。
 *
 * 3. Moving Average
 * 感觉就是这一轮跪的，被阿三黑了......一开始很简单秒了，然后要求getMedian()，提出可以quickSelect或者两个heap，他想要两个heap的解法，
 * 问了怎么实现，没有写代码因为时间不够，最后问到了heap的细节，删除是如何实现的，因为需要删掉expired event，时间复杂度是多少。
 * 还要问amortized time complexity。这个阿三真的是问了一堆time complexity。
 *
 * 印度哥 + 国人小哥shadow (听口音感觉美本)
 * 考面经moving average
 * follow up : moving median
 * get median的方法 我第一个方法说用一个heap去存items, 并且分析这个做法的複杂度(insert , remove等等的...)
 * 面试官好像很困惑如何从heap裡面Remove特定的item, 一直问这个问题, 反而shadow后来提醒他 heap是提供这样的方法的.
 * 然后又问还有没有其他方法, 就说了quick select了并分析了用这个的複杂度.
 *
 * 1.moving average 有follow up问如何很快的给出median，可以用linkedlist或者2 heaps维护，
 * 这样加入的时候会复杂很多但是维护和输出median很方便
 *
 * 白板第一题时moving avg，刚上来是说简历，我那时还很有精力热情的跟面试官讨论了我的一个ml proj，因为他是做ml这方面的，
 * 表现得很有兴趣。followup是moving median，我第一次followup口头说的是quickselect得到中间数，面试官考了一些其相关的内容和复杂度，
 * 然后面试官说要是getmedian被叫很多次怎么办，我就说用俩个queue来储存records，虽说这样一来record（）这一步会慢到O（logn）。。
 * 期间我们还讨论到了double啊啥的边边角角的问题，感觉这一关面得最好，我对这题最熟悉，最后面试官告别前说I see you enjoyed problem solving.
 * That's good.
 *
 * moving average
 * 一直输入integer，每个interger有一个timestamp，然后移除超过10000毫秒的interger，实现record方法和get averge方法
 * followup：get medium（解：quick select）--followup：如果getmedium用的很频繁怎么办（解：输入进queue的时候就sort）
 *
 * 第一轮： moving avg，就是一个stream输入，给一个int getNow()API获取当前timestamp，完成两个函数void record(int value)
 * 和double getAvg()，有输入时会自动call record，然后call getAvg()时返回5分钟内的平均值。用一个queue来存数据，一个sum存当前和，
 * 每次record和getAvg时pop掉过期的数据就好了。follow up： 如果还要getMedium呢？我说用two heap，他说太慢了因为record要o(logN)，
 * 说这个getMedium call得很少，可以直接在当前的数据结构上implement，于是其实就是求unsorted list的medium，
 * 用quick select能O(n)时间得到，面试官表示很满意。
 */

class StreamEvent {
    int val;
    int time;

    public StreamEvent(int val, int time) {
        this.val = val;
        this.time = time;
    }
}

public class Moving_Average_III {
    // Data Structure - queue
    Queue<StreamEvent> queue = new LinkedList<>();

    private int sum = 0;

    public int getNow() {
        return 0;
    }

    public void record(int val) {
        StreamEvent newEvent = new StreamEvent(val, getNow());
        queue.offer(newEvent);
        sum = sum + val;
        removeExpiredEvents();
    }

    private void removeExpiredEvents() {
        while(!queue.isEmpty() && isEventExpired(queue.peek())) {
            StreamEvent event = queue.poll();
            sum = sum - event.val;
        }

    }

    private boolean isEventExpired(StreamEvent event) {
        return getNow() - event.time > 300;
    }

    public double getAvg() {
        removeExpiredEvents();
        if(!queue.isEmpty()) {
            return (double) sum/queue.size();
        }
        return 0.0;
    }
}
