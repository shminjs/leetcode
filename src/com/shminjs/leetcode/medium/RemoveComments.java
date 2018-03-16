package com.shminjs.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by shimin on 2017/11/5.
 */
public class RemoveComments {
    public List<String> removeComments(String[] source) {
        List<String> res = new ArrayList<>();
        int c1 = 0;
        boolean isEnd = false;
        for (String word : source) {
            int ind = 0;
            String ss = "";
            boolean isTwo = false;
            isEnd = c1 == 1;
            while (ind < word.length()) {
                if (word.charAt(ind) == '/') {
                    // 这里需要判断/*或者//
                    if (ind + 1 < word.length()) {
                        // 判断
                        ind++;
                        if (word.charAt(ind) == '/') {
                            // 是//
                            isTwo = true;
                        } else if (word.charAt(ind) == '*') {
                            // 是/*
                            if (c1 == 0) {
                                // 仅有一次
                                c1 = 1;
                                isEnd = false;
                            } else {
                                // don't count
                            }
                        } else {
                            // 什么也不是
                            if (!isTwo && c1 == 0) {
                                ss += "/";
                                ss += word.charAt(ind);
                            }
                        }
                    } else {
                        if (!isTwo && c1 == 0) ss += "/";
                    }
                } else if (word.charAt(ind) == '*') {
                    // 判断是否是*/。
                    if (ind + 1 < word.length()) {
                        ind++;
                        if (word.charAt(ind) == '/') {
                            if (c1 == 1) {
                                c1 = 0;
                                isEnd = true;
                            } else {
                                if (!isTwo) {
                                    ss += "*/";
                                }
                            }
                        }
                    } else {
                        if (!isTwo && c1 == 0) {
                            ss += "*";
                        }
                    }
                } else {
                    // 普通单词？
                    if (!isTwo && c1 == 0) {
                        ss += word.charAt(ind);
                    }
                }
                ind++;
            }
            if (isEnd) {
                if (res.size() > 0) {
                    ss = res.remove(res.size()-1) + ss;
                }
                if (!ss.equals("")) {
                    res.add(ss);
                }
            } else {
                res.add(ss);
            }

        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new RemoveComments().removeComments(
                new String[]{"class test{", "public: ", "   int x = 1;", "   /*double y = 1;*/", "   char c;", "};"}
        ));
    }
}
