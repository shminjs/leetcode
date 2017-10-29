package com.shminjs.leetcode.medium;

import java.util.Stack;

/**
 * Created by shimin on 2017/8/25.
 * 591
 */
public class IsValid {
    public boolean isValid(String code) {
        Stack<String> stack = new Stack<>();
        int count = 0;
        int start = 0;
        int end = -1;
        boolean hasTAG = false;
        boolean isCDATA = false;
        for (int i = 0; i < code.length(); i++) {
            if (code.charAt(i) == '[') {
                if (code.substring(start).startsWith("<![CDATA[")) isCDATA = true;
            } else if (code.charAt(i) == '<') {
                // start_tag
                if (count == 0) {
                    start = i;
                    count++;
                } else {
                    // 不能简单返回一个false，需要判断是否存在CDATA
                    if (!isCDATA) return false;
                }
            } else if (code.charAt(i) == '>') {
                if (count == 1) {
                    // 计算tag，可能有start_tag，也可能有close_tag.
                    if (isCDATA) {
                        if (code.substring(start+1, i).endsWith("]]")) {
                            if (stack.isEmpty()) return false;
                            isCDATA = false;
                            count--;
                        }
                    } else {
                        String tag = code.substring(start+1, i);
                        if (!tag.contains("/")) {
                            if ((start != 0 && stack.isEmpty()) || tag.contains("!") || tag.contains("[") || tag.contains("]") || tag.contains(" ") || !tag.equals(tag.toUpperCase()) || tag.equals("") || tag.length() > 9) return false;
                            stack.push(tag);
                            hasTAG = true;
                        } else if (tag.startsWith("/")) {
                            if (stack.isEmpty()) {
                                return false;
                            } else {
                                String start_tag = stack.pop();
                                if (!start_tag.equals(tag.substring(1))) {
                                    return false;
                                }
                            }
                        }
                        count--;
                        end = i;
                    }
                    if (i != code.length() -1 && stack.isEmpty()) return false;
                }
            }
        }
        if (stack.isEmpty()) return true && hasTAG;
        else return false;
    }

    public static void main(String[] args) {
        System.out.println(new IsValid().isValid("<DIV>This is the first line <![CDATA[<div>]]></DIV>"));
    }
}
