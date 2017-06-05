package com.shminjs.leetcode.easy;

import java.util.List;
import java.util.ArrayList;

/**
 * Created by shimin on 2017/6/5.
 * 412
 * Write a progrram that outputs the string representation of numbers from 1 to n.
 * But for multiples o three it should output "Fizz" instead of the number and for the
 * multiples of five output "Buzz". For number which are mutiples of both three and five output
 * "FizzBuzz".
 */
public class FizzBuzz {
    public List<String> fizzBuzz(int n) {
        ArrayList<String> al = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (i % 15 == 0) {
                al.add("FizzBuzz");
            } else if (i % 3 == 0) {
                al.add("Fizz");
            } else if (i % 5 == 0) {
                al.add("Buzz");
            } else {
                al.add(Integer.toString(i));
            }
        }
        return al;
    }
}
