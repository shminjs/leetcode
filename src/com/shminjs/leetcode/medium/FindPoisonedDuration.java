package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/7/7.
 * 495
 * In LLP world, there is a hero called Teemo and his attacking can make his enemy Ashe be in poisoned condition. Now, given
 * the Teemo's attack ascending time series toward Ashe and the poisoning time duration per Teemo's attacking, you need to output
 * the total time that Ashe is in poisoned condition.
 * You may assume that Teemo attack at the very beginning of a specific time point, and makes Ashe be inpoisoned condition immediately.
 */
public class FindPoisonedDuration {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries.length == 0) return 0;
        if (timeSeries.length == 1) return duration;
        int result = 0;
        for (int i = 1; i < timeSeries.length; i++) {
            if (timeSeries[i] - timeSeries[i-1] < duration) {
                result += timeSeries[i] - timeSeries[i-1];
            } else {
                result += duration;
            }
        }
        result += duration;
        return result;
    }
}
