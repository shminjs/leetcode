package com.shminjs.leetcode.easy;

/**
 * Created by shimin on 2017/6/24.
 * 38
 * The count-and-say sequence is the sequence of integers with the first five terms as following.
 *
 * 这一题实在是没理解意思，二刷的时候重新做一下。
 */
public class CountAndSay {
    public String countAndSay(int n) {
        StringBuilder curr = new StringBuilder("1");
        StringBuilder prev = null;
        int count;
        char say;
        for (int i = 1; i < n; i++) {
            prev = curr;
            curr = new StringBuilder();
            count = 1;
            say = prev.charAt(0);

            for (int j = 1, len = prev.length(); j < len; j++) {
                if (prev.charAt(j) != say) {
                    curr.append(count).append(say);
                    count = 1;
                    say = prev.charAt(j);
                } else {
                    count++;
                }
            }
            curr.append(count).append(say);
        }
        return curr.toString();
    }
}
