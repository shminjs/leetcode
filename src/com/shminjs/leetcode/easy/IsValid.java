package com.shminjs.leetcode.easy;


import java.util.LinkedList;

/**
 * Created by shimin on 2017/6/15.
 * 20
 * Given a string containing just the characters '(',')','{','}','[',']', determine if the input string is valid.
 * The brackets must close in the correct order. "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
public class IsValid {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char tmp = s.charAt(i);
            if (tmp == '(' || tmp == '{' || tmp == '[') {
                stack.push(tmp);
            } else {
                if (stack.isEmpty()) return false;
                else {
                    char top = stack.pop();
                    if (!((top == '(' && tmp == ')') || (top == '{' && tmp == '}') || (top == '[' && tmp == ']')))
                        return false;
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(new IsValid().isValid("()"));
    }
}
