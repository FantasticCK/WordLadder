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

class Solution2 {
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

class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        if (!dict.contains(endWord)) return 0;

        Set<String> beginSet = new HashSet<>();
        beginSet.add(beginWord);
        dict.remove(beginWord);

        Set<String> endSet = new HashSet<>();
        endSet.add(endWord);
        dict.remove(endWord);

        int level = 1;
        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            Set<String> currentSet = (beginSet.size() < endSet.size()) ? beginSet : endSet;
            Set<String> oppositeSet = (beginSet.size() >= endSet.size()) ? beginSet : endSet;
            Set<String> temp = new HashSet<>();

            for (String s : currentSet) {
                for (int i = 0; i < s.length(); i++) {
                    for (int j = 0; j < 26; j++) {
                        char c = (char) (j + 'a');
                        if (s.charAt(i) == c)
                            continue;
                        String next = s.substring(0, i) + c + s.substring(i + 1);
                        if (oppositeSet.contains(next))
                            return level + 1;

                        if (dict.contains(next)) {
                            temp.add(next);
                            dict.remove(next);
                        }
                    }
                }
            }
            currentSet.clear();
            currentSet.addAll(temp);

            level++;
        }
        return 0;
    }
}

