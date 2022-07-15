package com.indeed.vo.whiteboard;

/**
 * 一个是unroll linked list follow up不是delete了，变成问如果一直在同一个地方insert会怎么样，
 * 会变得越来越像linked list很多只有一个元素的node然后可能浪费空间，问怎么办，那就是判断下一个node是不是有空间然后推过去。
 * 比如那个unroll list 如果插入的index比size大，或者要查询的index小于0或者比size大，不要返回空，扔一个outofbound异常
 *
 * 两轮code都是地里原题。第一轮是unrolled linked list。面试官不怎么在意code具体实现，开始写之前就说了corner case比较多，
 * 不期望我能全部写完，但是需要我先把整体方法告诉他。他主要关注的点怎么插入新元素可以更节省空间。
 *
 * 一轮白板，unrolled linked list，小哥讲解很细，问了很多分析structure的问题，
 * 比如insert几种方式，你想选哪种。等写代码就很晚了，好像follow up都没怎么写。
 *
 *  题目是unrolled linked list，他还问我有没有听过这个data structure，本着面经说的装没见过的原则，
 *  lz拿出了从小披床单演小龙女修炼出来的演技苦恼地说了没有。要写的就是get + insert两个function。follow up：
 *  让我在每种可能的情况（比如不同的插入的位置）都跑一个case，并且问了时间复杂度，问了这种数据结构与linked list和array相比的优缺点，
 *  lz只准备了优点（两者优点combination：既能占据比较少的空间，又能比较快的查找插入和删除），说到缺点有点懵，
 *  面试官给了提示说比array空间多+比ll插入查找快。。。这不是废话吗。。。我。。。之后就是常规Q&A，一轮结束，面试官一个白人小哥，很nice
 *
 * 2. Unrolled Linked List
 * 先讨论各种corner case，讨论好了之后才让写代码。先写get()，被指出了一个小bug，curr = curr.next应该在update size之后。
 * 然后写insert()。整体很顺利。
 *
 * unrolled Linked List，一个美国大哥面的，带了一个shadow。大哥还说没听过这资料结构很正常，他也是到这边才知道有这资料结构。先写了get的
 * function，然后在follow up了add和delete。最后follow up代码没写完，但面试官问了我思路我也答上了，并分析了两种不同做法的优缺点，
 * 然后表示满意并认同我的分析。
 *
 * 3.unrolled linked list 有follow up问如何更快，没答出来，说是用rope可以，不是很了解
 *
 * 第二轮：Unrolled Linked List，有以下数据结构：
 * class Node {
 *         char[] chars = new char[5]; //固定大小5
 *         int len;
 * }
 * class LinkedList {
 *         Node head;
 *         int totalLen;
 * }
 * 实现以下成员函数：char get(int index), void insert(char ch, int index)
 * get比较容易，就是从head traverse，定位第index个char；insert有点麻烦，有几种情况需要考虑。
 * 时间有点不太够，所以insert函数没完全实现 T.T
 */



class SingleNode {
    char[] chars = new char[5]; //固定大小5
    int len;
    SingleNode next;
    public SingleNode(){};
}

class UnrolledLinkedList {
    SingleNode head;
    int totalLen;
    public UnrolledLinkedList(SingleNode head, int total) {
        this.head = head;
        this.totalLen = total;
        int count = 0;
        SingleNode cur = head;
        while(cur != null) {
            count += cur.len;
            cur = cur.next;
        }
        this.totalLen = count;
    }

    public char get(int index) {
        // corner case:
        if(index < 0 || index >= this.totalLen || this.totalLen == 0) {
            throw new IndexOutOfBoundsException();
        }

        SingleNode cur = this.head;
        // cur != null && index >= 0 ------ 这个非常重要
        while(cur != null && index >= 0) {
            if(index >= cur.len) {
                index = index - cur.len;
            } else {
                return cur.chars[index];
            }
            // move cur
            cur = cur.next;
        }
        return ' ';
    }

    public void insert(char ch, int index) {
        // corner case:
        if(index < 0 || index > this.totalLen) {
            throw new IndexOutOfBoundsException();
        }


        SingleNode cur = this.head;
        // Search for the index Location ==> very important to remember
        while(cur != null && index >= 0) {
            // move to next node
            if(index >= cur.len) {
                index = index - cur.len;
                cur = cur.next;
            } else {
                // find the node, where the index should belong to
                if(cur.len == 5) {
                    // Need to create a new Node
                    SingleNode insertNode = new SingleNode();
                    insertNode.chars[0] = cur.chars[4];
                    insertNode.len = 1;
                    insertNode.next = cur.next;
                    cur.next = insertNode;
                    cur.len = cur.len -1;
                }
                cur.len = cur.len + 1;
                for(int i = cur.len-1; i > index; i--) {
                    cur.chars[i] = cur.chars[i-1];
                }
                cur.chars[index] = ch;
                break;
            }
        }
        if(cur == null) {
            SingleNode newNode = new SingleNode();
            newNode.chars[0] = ch;
            newNode.len = 1;
            SingleNode tail = new SingleNode();
            tail.next = head;
            while(tail.next != null){
                tail = tail.next;
            }
            tail.next = newNode;
        }
        totalLen += 1;
    }

    public void delete(int index) {
        // corner case
        if (index < 0 || index >= this.totalLen) {
            throw new IndexOutOfBoundsException();
        }

        // Search the index node
        SingleNode cur = head;
        SingleNode prev = new SingleNode();
        prev.next = head;
        while (cur != null && index >= 0) {
            if (index >= cur.len) {
                // move to next node
                index = index - cur.len;
                prev = prev.next;
                cur = cur.next;
            } else {
                // find the node where the index below to
                if (cur.len == 1) {
                    prev.next = cur.next;
                } else {
                    for (int i = index; i < cur.len - 1; i++) {
                        cur.chars[i] = cur.chars[i + 1];
                    }
                    cur.len = cur.len - 1;
                    break;
                }
            }
        }
        totalLen = totalLen - 1;
    }
}





