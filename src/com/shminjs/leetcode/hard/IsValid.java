package com.shminjs.leetcode.hard;

import java.util.Stack;

/**
 * Created by shimin on 2017/11/24.
 */
public class IsValid {
    public boolean isValid(String code) {
        if (code.length() == 0) return false;
        Stack<String> stack = new Stack<>();
        if (code.charAt(0) != '<') return false;
        boolean isCDATA = false;
        int ind = 0;
        while (ind < code.length()) {
            if (code.charAt(ind) == '<') {
                // 需要查看是否满足某种情况，是否是start tag, closed tag或者<![CDATA[content]]>
                if (ind + 1 < code.length() && code.charAt(ind+1) == '/') {
                    // closed tag
                    ind += 2;
                    int begin = ind;
                    while (ind < code.length() && code.charAt(ind) != '>') {
                        ind++;
                    }
                    String CTAG = code.substring(begin, ind);
                    ind++;
                    if (CTAG.length() == 0 || CTAG.length() > 9 || !isUpper(CTAG) || stack.isEmpty() || !stack.peek().equals(CTAG)) return false;
                    stack.pop();
                } else if (ind + 9 < code.length() && code.substring(ind, ind+9).equals("<![CDATA[")) {
                    // cdata-content
                    ind = ind + 9;
                    boolean closed = false;
                    while (ind + 3 < code.length()) {
                        if (code.substring(ind, ind+3).equals("]]>")) {
                            closed = true;
                            break;
                        }
                        ind++;
                    }
                    if (!closed) {
                        return false;
                    }
                    ind = ind + 3;
                } else {
                    // maybe start tag
                    ind++;
                    int begin = ind;
                    while (ind < code.length() && code.charAt(ind) != '>') {
                        ind++;
                    }
                    String TAG = code.substring(begin, ind);
                    ind++;
                    if (TAG.length() == 0 || TAG.length() > 9 || !isUpper(TAG)) return false;
                    stack.push(TAG);
                }
            } else {
                if (ind == code.length() - 1) {
                    return false;
                }
                ind++;
            }
        }
        return stack.isEmpty();
    }

    private boolean isUpper(String tag) {
        if (tag.equals("")) return false;
        for (int i = 0; i < tag.length(); i++) {
            if (!Character.isUpperCase(tag.charAt(i))) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(new IsValid().isValid("<DIV>This is the first line <![CDATA[<div>]]></DIV>"));
    }
}
