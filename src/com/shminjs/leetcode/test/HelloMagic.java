package com.shminjs.leetcode.test;

import java.util.Scanner;

/**
 * Created by shimin on 2017/9/17.
 */
public class HelloMagic {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        double Hi = sc.nextDouble();
        double[] w = new double[k];
        double sum = 0.0;
        for (int i = 0; i < k; i++) {
            sum += w[i];
        }
        double[] x = new double[k];
        double ave = Hi / sum;
        double power = 0.0;
        for (int i = 0; i < k; i++) {
            x[i] = ave * w[i];
            power += w[i] * Math.log(x[i]);
        }
        System.out.printf("%.5f\n", power);
        for (int i = 0; i < k; i++) {
            if (i != k-1) {
                System.out.printf("%.5f ", x[i]);
            } else {
                System.out.printf("%.5f", x[i]);
            }
        }
    }
}
