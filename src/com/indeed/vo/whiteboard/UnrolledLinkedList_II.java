package com.indeed.vo.whiteboard;

/** =============================================================================
Question

Given a LinkedList, every node contains an array. Every element of the array is char
        implement two functions
        1. get(int index) find the char at the index
        2. insert(char ch, int index) insert the char to the index
============================================================================= **/
/** =============================================================================
code思路
============================================================================= **/

class Node {
    char[] cArray = new char[5]; //定长5,反正总要有定长
    int len; // actual number of chars in the cArray
    Node next;
    public Node() {};
}

public class UnrolledLinkedList_II {
    Node head;
    int totalLen; //这个totalLen代表所有char的个数
    public UnrolledLinkedList_II(Node head, int total) {
        this.head = head;
        this.totalLen = total;
        // 可能totalLen是不给的，要遍历一遍去求
        int count = 0;
        Node cur = head;
        while(cur != null) {
            count = count + cur.len;
            cur = cur.next;
        }
        this.totalLen = count;
    }

    public char get(int index) {
        // corner case:
        if(index < 0 || index >= this.totalLen || totalLen == 0) {
            return ' ';
        }

        Node cur = head;
        while(cur != null && index >=0 ) {
            if(index >= cur.len) {
                index = index - cur.len;
            } else {
                return cur.cArray[index];
            }
            cur = cur.next;
        }
        return ' ';
    }

    // insert需要考虑
    // 1.普通插进去。
    // 2.插入的node满了，要在后面加个node。
    // 3.插入的node是空的，那就要在尾巴上加个新node。
    // 还需要考虑每个node的len，以及totalLen的长度变化。
    public void insert(char ch, int index){
        // corn case:
        if(index > this.totalLen) {
            return;
        }

        Node cur = head;
        while(cur != null && index >=0) {
            if(index >= cur.len) {
                index = index - cur.len;
            } else {
                // 插入的node满了，要在后面加个node
                if(cur.len == 5) {
                    Node newNode = new Node();
                    newNode.cArray[0] = cur.cArray[cur.len-1];
                    newNode.len++;
                    newNode.next = cur.next;
                    cur.next = newNode;
                    cur.len--;
                }
                cur.len++;
                for(int i=cur.len-1; i>index; i--) {
                    cur.cArray[i] = cur.cArray[i-1];
                }
                cur.cArray[index] = ch;
                break;
            }
            cur = cur.next;
        }
        if(cur == null) {
            Node newNode = new Node();
            newNode.cArray[0] = ch;
            newNode.len = 1;
            Node tail = new Node();
            tail.next = head;
            while(tail.next != null) {
                tail = tail.next;
            }
            tail.next = newNode;
        }
        totalLen = totalLen + 1;
    }

    /**=============================================================================
        Follow Up:
            删除一个数怎么处理，需要注意的地方也就是如果node空了就删掉吧。
            那就需要记录前一个node了，这样比较好删掉当前node。
     **/

    /** =============================================================================
        Follow Up code

        //类似insert，先考虑清楚delete的情况
        //1.普通的去掉一个node里面的点。
        //2.去掉node之后，这个点空了，那就把点删掉。
        //也要考虑每个node里面长度的变化。
     **/

    public void delete(int index) {
        // Corner Case
        if (index < 0 || index >= this.totalLen) {
            return;
        }

        Node prev = new Node();
        prev.next = head;
        Node cur = head;
        while (cur != null && index >= 0) {
            if (index >= cur.len) {
                index = index - cur.len;
            } else {
                if (cur.len == 1) {
                    prev.next = cur.next;
                } else {
                    for (int i = index; i < cur.len - 1; i++) {
                        cur.cArray[i] = cur.cArray[i + 1];
                    }
                    cur.len = cur.len - 1;
                }
            }
            prev = prev.next;
            cur = cur.next;
        }
        this.totalLen = this.totalLen - 1;
        return;
    }

    public static void main(String[] args) {
        Node n1 = new Node(); //a b
        Node n2 = new Node(); //b
        Node n3 = new Node(); //a b c d e

        n1.cArray[0] = 'a';
        n1.cArray[1] = 'b';
        n2.cArray[0] = 'b';
        n3.cArray[0] = 'a';
        n3.cArray[1] = 'b';
        n3.cArray[2] = 'c';
        n3.cArray[3] = 'd';
        n3.cArray[4] = 'e';

        n1.next = n2;
        n2.next = n3;
        n1.len = 2;
        n2.len = 1;
        n3.len = 5;
    }
}




