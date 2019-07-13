package com.CK;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<String> wordList = new ArrayList<String>(Arrays.asList("hot", "dot", "dog", "lot", "log", "cog"));
        String beginWord = "hit";
        String endWord = "cog";

        Solution solution = new Solution();
        System.out.println(solution.ladderLength(beginWord, endWord, wordList));

    }
}

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord))
            return 0;
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int res = 0;
        while (!q.isEmpty()) {
            for (int k = q.size(); k > 0; k--) {
                String word = q.poll();
                if (word.equals(endWord)) {
                    return res + 1;
                }
                for (int i = 0; i < word.length(); i++) {
                    char[] newWord = word.toCharArray();
                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        newWord[i] = ch;
                        String s = new String(newWord);
                        if (wordSet.contains(s) && !s.equals(word)) {
                            q.offer(s);
                            wordSet.remove(s);
                        }
                    }
                }
            }
            ++res;
        }
        return 0;
    }
}

