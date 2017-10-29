package com.shminjs.leetcode.medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shimin on 2017/9/17.
 * 388
 */
public class LengthOfLongestPath {
    public int lengthLongestPath(String input) {
        List<Element> stack = new ArrayList<>();
        int ind = 0;
        int ss = 0;
        int tcount = 0;
        int max = Integer.MIN_VALUE;
        while (ind < input.length()) {
            if (input.charAt(ind) == '\n' || ind == input.length()-1) {
                // 可以截断.
                if (ind == input.length() - 1) ind++;
                if (tcount +1 > stack.size()) {
                    // 需要append
                    if (stack.isEmpty()) {
                        // 初始为空
                        stack.add(new Element(ind-ss, ind-ss));
                    } else {
                        Element elem = stack.get(stack.size()-1);
                        stack.add(new Element(elem.sofar + ind - (ss + tcount), ind - (ss + tcount)));
                    }
                } else {
                    // 不需要append
                    Element elem = stack.get(tcount-1);
                    stack.add(new Element(elem.sofar + ind - (ss + tcount), ind - (ss + tcount)));
                }
                max = Math.max(max, stack.get(tcount).sofar + tcount);
                ss = ind + 1;
                tcount = 0;
            } else if (input.charAt(ind) == '\t') {
                // 此处用来判断在第几层
                tcount++;
            }
            ind++;
        }
        return max;
    }

    class Element {
        int sofar;
        int count;
        public Element(int sofar, int count) {
            this.sofar = sofar;
            this.count = count;
        }
    }

    public static void main(String[] args) {
        System.out.println(new LengthOfLongestPath().lengthLongestPath("dir\n\tfile.txt"));
    }
}
