package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/5.
 * 500
 * Given a List of words, return words that can be typed using letters of alphabet on only rows's of American keyboard like the image below.
 */
public class FindWords {
    public String[] findWords(String[] words) {
        int[] indexs = new int[words.length];
        int ind = 0;
        for (int i = 0; i < words.length; i++) {
            if (detect(words[i])) {
                indexs[ind] = i;
                ind++;
            }
        }
        String[] finds = new String[ind];
        for (int i = 0; i < ind; i++) {
            finds[i] = words[indexs[i]];
        }
        return finds;
    }

    private boolean detect(String word) {
        String alphabet = "QqWwEeRrTtYyUuIiOoPpAaSsDdFfGgHhJjKkLlZzXxCcVvBbNnMm";
        int pos = alphabet.indexOf(word.charAt(0));
        if (pos > 37) {
            for (int i = 1; i < word.length(); i++) {
                if (alphabet.indexOf(word.charAt(i)) <= 37) {
                    return false;
                }
            }
        } else if (pos > 19) {
            for (int i = 1; i < word.length(); i++) {
                if (alphabet.indexOf(word.charAt(i)) > 37 || alphabet.indexOf(word.charAt(i)) <= 19) {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < word.length(); i++) {
                if (alphabet.indexOf(word.charAt(i)) > 19) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String[] input = {"hello", "Alaska", "Dad", "Peace"};
    }
}
