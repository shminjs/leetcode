package com.shminjs.leetcode.test;

import java.util.TreeSet;

/**
 * Created by shimin on 2017/11/19.
 */
public class MyCalendar {
    TreeSet<Interval> set = new TreeSet<>();

    public MyCalendar() {
    }

    public boolean book(int start, int end) {
        Interval item = set.higher(new Interval(0, start));
        if (item == null || item.end == start || end <= item.start) {
            // 不存在？
            set.add(new Interval(start, end));
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        System.out.println(myCalendar.book(37, 50));
        System.out.println(myCalendar.book(33, 50));
        System.out.println(myCalendar.book(4, 17));
        System.out.println(myCalendar.book(35, 48));
        System.out.println(myCalendar.book(8, 25));
//        System.out.println(myCalendar.book(10, 20));
//        System.out.println(myCalendar.book(15, 25));
//        System.out.println(myCalendar.book(20, 30));
    }
}

class Interval implements Comparable<Interval> {
    int start;
    int end;

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int compareTo(Interval that) {
        if (this.end == that.end) {
            return this.start - that.start;
        } else {
            return this.end - that.end;
        }
    }
}
