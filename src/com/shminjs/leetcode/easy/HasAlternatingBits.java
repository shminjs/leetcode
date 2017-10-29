package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/10/8.
 * 693
 */
public class HasAlternatingBits {
    public boolean hasAlternatingBits(int n) {
        boolean flag = true;
        boolean begin = false;
        int pre = -1;
        for (int i = 31; i >= 0; i--) {
            int j = (n >> i) & 0x1;
            if (!begin && j == 1) {
                begin = true;
                pre = 1;
                continue;
            }
            if (begin) {
                if (pre != -1) {
                    if (pre == j) {
                        flag = false;
                        break;
                    } else {
                        pre = j;
                    }
                }
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        System.out.println(new HasAlternatingBits().hasAlternatingBits(1));
    }
}
