package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/10/29.
 */
public class IsOneBitCharacter {
    public boolean isOneBitCharacter(int[] bits) {
        int pre = -1;
        for (int i = 0; i < bits.length; ) {
            if (pre < bits.length - 1) {
                pre = i;
            }
            if (bits[i] == 0) {
                i += 1;
            } else {
                i += 2;
            }
        }
        return pre == bits.length - 1;
    }
}
