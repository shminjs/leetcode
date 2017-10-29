package com.shminjs.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Created by shimin on 2017/9/10.
 * 636
 */
public class ExclusiveTime {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] res = new int[n];
        Stack<Element> stack = new Stack<>();
        for (String log : logs) {
            if (log.contains("start")) {
                int id = Integer.parseInt(log.substring(0, log.indexOf(':')));
                int start = Integer.parseInt(log.substring(log.lastIndexOf(':')+1));
                stack.push(new Element(id, 0, start));
            } else {
                Element elem = stack.pop();
                int end = Integer.parseInt(log.substring(log.lastIndexOf(':')+1));
                int time = end - elem.start + 1 - elem.minus;
                res[elem.id] = res[elem.id] + time;
                if (!stack.isEmpty()) {
                    Element e = stack.peek();
                    e.minus = e.minus + time + elem.minus;
                }
            }
        }
        return res;
    }

    class Element {
        int id;
        int minus;
        int start;
        public Element(int id, int minus, int start) {
            this.id = id;
            this.minus = minus;
            this.start = start;
        }
    }

    public static void main(String[] args) {
        String[] sts = new String[]{"0:start:0","0:start:1","0:start:2","0:end:3","0:end:4","0:end:5"};
        List<String> ag = new ArrayList<>();
        for (String str : sts) {
            ag.add(str);
        }
        System.out.println(Arrays.toString(new ExclusiveTime().exclusiveTime(1, ag)));
    }
}
