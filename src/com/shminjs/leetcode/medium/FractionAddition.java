package com.shminjs.leetcode.medium;

/**
 * Created by shimin on 2017/10/10.
 * 592
 */
public class FractionAddition {
    public String fractionAddition(String expression) {
        int resNumerator = 0, resDenominator = 1;
        int curNumerator = 0, curDenominator = 1;
        boolean isNumerator = true;
        int operator = 1;
        int ind = 0;
        while (ind < expression.length()) {
            char c = expression.charAt(ind);
            if (Character.isDigit(c)) {
                int num = 0;
                while (ind < expression.length() && Character.isDigit(expression.charAt(ind))) {
                    num = num * 10 + expression.charAt(ind) - '0';
                    ind++;
                }
                if (isNumerator) curNumerator = num;
                else curDenominator = num;
                isNumerator = !isNumerator;
                if (ind == expression.length()) {
                    // 立即执行
                    int gcdN = gcd(resDenominator, curDenominator);
                    int commonDe = resDenominator * curDenominator / gcdN;
                    int commonNu = resNumerator * (commonDe / resDenominator) + operator * (curNumerator * (commonDe / curDenominator));
                    int gcdR = gcd(Math.abs(commonNu), commonDe);
                    resNumerator = commonNu / gcdR;
                    resDenominator = commonDe / gcdR;
                }
            } else if (c == '/') {
                ind++;
            } else if (c == '+') {
                // 执行前面的任务，然后将+赋给operator
                int gcdN = gcd(resDenominator, curDenominator);
                int commonDe = resDenominator * curDenominator / gcdN;
                int commonNu = resNumerator * (commonDe / resDenominator) + operator * (curNumerator * (commonDe / curDenominator));
                int gcdR = gcd(Math.abs(commonNu), commonDe);
                resNumerator = commonNu / gcdR;
                resDenominator = commonDe / gcdR;
                operator = 1;
                ind++;
            } else if (c == '-') {
                // 执行前面的任务，然后将-号赋给operator.
                int gcdN = gcd(resDenominator, curDenominator);
                int commonDe = resDenominator * curDenominator / gcdN;
                int commonNu = resNumerator * (commonDe / resDenominator) + operator * (curNumerator * (commonDe / curDenominator));
                int gcdR = gcd(Math.abs(commonNu), commonDe);
                resNumerator = commonNu / gcdR;
                resDenominator = commonDe / gcdR;
                operator = -1;
                ind++;
            }
        }
        return Integer.toString(resNumerator) + "/" + Integer.toString(resDenominator);
    }

    private int gcd(int a, int b) {
        return a % b == 0 ? b : gcd(b, a % b);
    }

    public static void main(String[] args) {
        System.out.println(new FractionAddition().fractionAddition("-1/2+1/2"));
    }
}
