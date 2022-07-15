package com.indeed.vo.whiteboard;

/** =============================================================================
Question Description
Say I'm typing on a phone. Given a prefix String,and a dictionary.
Find all auto-complete word for the given prefix string
=============================================================================**/

import java.util.ArrayList;
import java.util.List;

/** =============================================================================
code
=============================================================================**/

class TrieNode{
    boolean hasWord;
    TrieNode[] children;
    //constructor忘了写括号了
    public TrieNode(){
        this.hasWord = false;
        this.children = new TrieNode[26];
    }
    public void insert(String word, int index){
        if (index == word.length()){
            hasWord = true;
            return;
        }
        //这里居然忘了写 -'a' 太不应该了
        int pos = word.charAt(index)-'a';
        if (children[pos] == null){
            children[pos] = new TrieNode();
        }
        children[pos].insert(word, index+1);
    }
    public TrieNode find(String prefix, int index){
        if (index == prefix.length()){
            return this;
        }
        int pos = prefix.charAt(index)-'a';
        if (children[pos] == null){
            return null;
        }
        return children[pos].find(prefix, index+1);
    }
}

//就是trie和DFS的结合，感觉写的很好。
public class Auto_Complete {
    TrieNode root;
    public Auto_Complete(List<String> words){
        this.root = new TrieNode();
        for (String word: words) {
            root.insert(word, 0);
        }
    }
    public List<String> find(String prefix){
        List<String> res = new ArrayList<>();
        TrieNode cur = root;
        TrieNode pRoot = cur.find(prefix, 0);
        helper(res, pRoot, prefix);
        return res;
    }
    public void helper(List<String> res, TrieNode pRoot, String curS){
        if (pRoot == null){
            return;
        }
        if (pRoot.hasWord){
            res.add(curS);
        }

        String tempS = curS;
        for (int i = 0; i < 26; i++){
            char c = (char)('a'+i);
            helper(res, pRoot.children[i], tempS + c);
        }
    }
    public static void main(String[] args) {

        List<String> words = new ArrayList<>();
        words.add("ab");
        words.add("a");
        words.add("de");
        words.add("abde");

        Auto_Complete test = new Auto_Complete(words);
        String prefix = "ab";
        List<String> res = test.find(prefix);
        System.out.println(res);
    }
}
