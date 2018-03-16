package com.shminjs.leetcode.test;

import java.util.Scanner;

/**
 * Created by shimin on 2017/9/21.
 */
public class HelloPrime {
    public int[] genPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        for (int i = 2; i < n; i++) {
            isPrime[i] = true;
        }

        for (int i = 2; i * i < n; i++) {
            if (!isPrime[i]) continue;
            for (int j = i * i; j < n; j += i) {
                isPrime[j] = false;
            }
        }
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) count++;
        }
        int[] res = new int[count];
        int ind = 0;
        for (int i = 2; i < n; i++) {
            if(isPrime[i]) {
                res[ind++] = i;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        HelloPrime hp = new HelloPrime();
        int[] array = hp.genPrimes(N);
        int count = 0;
        int rep = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length; j++) {
                if (array[i] + array[j] == N) {
                    if (i == j) rep++;
                    count++;
                }
            }
        }
        System.out.println((count-rep) / 2 + rep);
    }
}
