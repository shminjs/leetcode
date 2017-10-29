package com.shminjs.leetcode.medium;

import java.util.Stack;

/**
 * Created by shimin on 2017/9/26.
 * 682
 */
public class CalPoints {
    public int calPoints(String[] ops) {
        int sum = 0;
        Stack<Integer> stack = new Stack<>();
        for (String op : ops) {
            if (op.equals("+")) {
                int oprand1 = stack.pop();
                int oprand2 = stack.pop();
                int res = oprand1 + oprand2;
                sum += res;
                stack.push(oprand1);
                stack.push(oprand2);
                stack.push(res);
            } else if (op.equals("C")) {
                int oprand1 = stack.pop();
                sum -= oprand1;
            } else if (op.equals("D")) {
                int oprand1 = stack.peek();
                int res = oprand1 * 2;
                sum += res;
                stack.push(res);
            } else {
                int res = Integer.parseInt(op);
                sum += res;
                stack.push(res);
            }
        }
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new CalPoints().calPoints(new String[]{"5","-2","4","C","D","9","+","+"}));
    }
}
