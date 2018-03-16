# Dynamic Programing

## Overlaping Subproblems

Example: Fibonacci Numbers

### Origin code
```java
int fib(int n) {
  if (n <= 1) {
    return n;
  }
  return fib(n-1) + fib(n-2);
}
```

**NOTE**: 重复计算的问题

### Memoization (Top Down)

```java
/* Java program for Memoized version */
public class Fibonacci {
  final int MAX = 100;
  final int NIL = -1;

  int lookup[] = new int[MAX];

  /* Function to initailize NIL value in lookup table */
  void _initialize() {
    for (int i = 0; i < MAX; i++) {
      lookup[i] = NIL;
    }
  }

  /* Function for nth Fibonacci number */
  int fib(int n) {
    if (lookup[n] == NIL) {
      if (n <= 1) {
        lookup[n] = n;
      } else {
        lookup[n] = fib(n-1) + fib(n-2);
      }
    }
    return lookup[n];
  }

  public static void main(String[] args) {
    Fibonacci f = new Fibonacci();
    int n = 40;
    f._initialize();
    System.out.println("Fibonacci number is" + " " + f.fib(n));
  }
}
```

### Tabulation (Bottom Up)

```java
public class Fibonacci {
  int fib(int n) {
    int f[] = new int[n+1];
    f[0] = 0;
    f[1] = 1;
    for (int i = 2; i <= n; i++) {
      f[i] = f[i-1] + f[i-2];
    }
    return f[n];
  }

  public static void main(String[] args) {
    Fibonacci f = new Fibonacci();
    int n = 9;
    System.out.println("Fibonacci number is " + f.fib(n));
  }
}
```

## How to solve a Dynamic Programming Problem?

> Steps to solve a DP  
> 1. Identify if it is a DP problem
> 2. Decide a state expression with least parameters
> 3. Formulate state relationship
> 4. Do tbulation (or add memoization)

## Tabulation vs Memoizatation

bottom up vs top down

## Basic Problems

### Fibonacci numbers

```java
// methond 1 (Use recursion)

// Fibonacci Series using Recursion
class fibonacci {
  static int fib(int n) {
    if (n <= 1) {
      return n;
    }
    return fib(n-1) + fib(n-2);
  }

  public static void main(String[] args) {
    int n = 9;
    System.out.println(fib(n));
  }
}

// method 2 (Use Dynamic Programming)

// Fibonacci Series using Dynamic Programming
class Fibonacci {
  static int fib(int n) {
    /* Declare an array to store Fibonacci numbers. */
    int[] f = new int[n+1];
    int i;

    /* 0th and 1st number of the series are 0 and 1 */
    f[0] = 0;
    f[1] = 1;

    for (int i = 2; i <= n; i++) {
      /* Add the previous 2 number in the series and store it */
      f[i] = f[i] + f[i-2];
    }

    return f[n];
  }

  public static void main(String[] args) {
    int n = 9;
    System.out.println(fib(n));
  }
}

// method 3 (Space Optimized method 2)

// Java program for Fibonacci Series using Space Optimized Method
class Fibonacci {
  static int fib(int n) {
    int a = 0, b = 1, c;
    if (n == 0) {
      return a;
    }
    for (int i = 2; i <= n; i++) {
      c = a + b;
      a = b;
      b = c;
    }
    return b;
  }
  public static void main(String[] args) {
    int n = 9;
    System.out.println(fib(n));
  }
}
```

### Dynamic Programming (Binomial Coefficient)

```java
// A Dynamic Programming based solution that uses table C[][] to 
// calculate the Binomial Coefficient
class BinomialCoefficient {
  // Returns value of Binomial Coefficient C(n, k)
  static int binomialCoeff(int n, int k) {
    int[][] C = new int[n+1][k+1];
    int i, j;
    for (i = 0; i <= n; i++) {
      for (j = 0; j <= min(i, k); j++) {
        if (j == 0 || j == i) {
          C[i][j] == 1;
        } else {
          // Calculate value using previosly stored values
          C[i][j] = C[i-1][j-1] + C[i-1][j];
        }
      }
    }
    return C[n][k];
  }
}
```

```c++
// C++ program for space optimized Dynamic Programming
// Solution of Binomial Coefficient
#include<bits/stdc++.h>
using namespace std;

int binomialCoeff(int n, int k) {
  int C[k+1];
  memset(C, 0, sizeof(C));

  C[0] = 1;
  for (int i = 1; i <= n; i++) {
    // Compute next row of pascal triangle using
    // the previous row
    for (int j = min(i, j); j > 0; j--) {
      C[j] = C[j] + C[j-1];
    }
  }
  return C[k];
}
```

### Longest Common Subsequence

```java
// A Native recursive implementation of LCS problem in java 

public class LongestCommonSubsequnence {
  /* Return length of LCS for X[0..m-1], Y[0..n-1] */
  int lcs(char[] X, char[] Y, int m, int n) {
    if (m == 0 || n == 0)
      return 0;
    if (X[m-1] == Y[n-1])
      return 1 + lcs(X, Y, m - 1, n - 1);
    else
      return max(lcs(X, Y, m, n-1), lcs(X, Y, m-1, n));
  } 

  int max(int a, int b) {
    return a > b ? a : b;
  }

  public static void main(String[] args) {
    LongestCommonSubsequence lcs = new LongestCommonSubsequence();
    String s1 = "AGGTAB";
    String s2 = "GXTXAYB";

    char X = s1.toCharArray();
    char Y = s2.toCharArray();

    int m = X.length;
    int n = Y.length;

    System.out.println("Length of LCS is " +lcs.lcs(X, Y, m, n));
  }
}

/* Dynamic Program Java implementation of LCS problem */
public class LongestCommonSubsequence {
  int lcs(char[] X, char[] Y, int m, int n) {
    int[][] L = new int[m+1][n+1];

    for (int i = 0; i <= m; i++) {
      for (int j = 0; j <= n; j++) {
        if (i == 0 || j == 0) {
          L[i][j] == 0;
        } else if (X[i-1] == Y[j-1]) {
          L[i][j] = 1 + L[i-1][j-1];
        } else {
          L[i][j] = max(L[i-1][j], L[i][j-1]);
        }
      }
    }
    return L[m][n];
  }
}
```

### Largest Sum Contiguous Subarray

```java
class Kadane {
  static int maxSubArraySum(int[] a) {
    int size = a.length;
    int max_so_far = Integer.MIN_VALUE, max_ending_here = 0;
    for (int i = 0; i < size; i++) {
      max_ending_here = max_ending_here + a[i];
      if (max_so_far < max_ending_here>) {
        max_so_far = max_ending_here;
      }
      if (max_ending_here < 0) {
        max_einding_here = 0;
      }
    }
    return max_so_far;
  }
}
```

### Ugly Number

```java
class UglyNumber {
  /* This function divides a by greatest divisible
  power of b */
  int maxDivide(int a, int b) {
    while (a % b == 0)
      a = a / b;
    return a;
  }

  /* Function to check if a number is ugly or not */
  int isUgly(int no) {
    no = maxDivide(no, 2);
    no = maxDivide(no, 3);
    no = maxDivide(no, 5);

    return no == 1 ? 1 : 0;
  }

  /* Function to get the nth ugly number */
  int getNthUglyNo(int n) {
    int i = 1;
    int count = 1;

    // check for all integer until count becomes n 
    while (n > count) {
      i++;
      if (isUgly(i) == 1) {
        count++;
      }
    }
    return i;
  }
}

// Java program to find nth ugly number
class UglyNumber {
  /* Function to get the nth ugly number */
  int getNthUglyNo(int n) {
    int[] ugly = new int[n];
    int i2 = 0, i3 = 0, i5 = 0;
    int next_multiple_of_2 = 2;
    int next_multiple_of_3 = 3;
    int next_multiple_of_5 = 5;
    int next_ugly_no = 1;

    ugly[0] = 1;
    for (int i = 0; i < n; i++) {
      next_ugly_no = Math.min(next_multiple_of_2, Math.min(next_multiple_of_3, next_multiple_of_5));

      ugly[i] = next_ugly_no;

      if (next_ugly_no == next_multiple_of_2) {
        i2 = i2 + 1;
        next_multiple_of_2 = ugly[i2] * 2;
      }
      if (next_ugly_no == next_multiple_of_3) {
        i3 = i3 + 1;
        next_multiple_of_3 = ugly[i3] * 3;
      }
      if (next_ugly_no = next_multiple_of_5) {
        i5 = i5 + 1;
        next_multiple_of_5 = ugly[i5] * 5;
      }
    }
    return next_ugly_no;
  }
}
```

### Maximum size square sub-matrix with all 1s

```c
#include<stdio.h>
#define bool int
#define R 6
#define C 5
void printMaxSubSquare(bool M[R][C]) {
  int i, j;
  int S[R][C];
  int max_of_s, max_i, max_j;

  /* Set first column of S[][] */
  for (i = 0; i < R; i++) {
    S[i][0] = M[i][0];
  }
  for (j = 0; j < C; j++) {
    S[0][j] = M[0][j];
  }

  /* Construct other entries of S[][] */
  for (i = 1; i < R; i++) {
    for (j = 1; j < C; j++) {
      if (M[i][j] == 1) {
        S[i][j] = min(S[i][j-1], S[i-1][j], S[i-1][j-1]) + 1;
      } else {
        S[i][j] = 0;
      }
    }
  }
}
```

### Longest Increasing Subsequence

```c
#include<stdio.h>
#include<stdlib.h>

int lst(int arr[], int n) {
  int *list, i, j, max = 0;
  list = (int *)malloc(sizeof(int) * n);

  /* Initialize LIS values for all indexes */
  for (i = 0; i < n; i++)
    list[i] = 1;
  
  for (i = 1; i < n; i++){
    for (j = 0; j < i; j++) {
      if (arr[i] > arr[j] && list[i] < list[j] + 1) {
        list[i] = list[j] + 1;
      }
    }
  }
  for (i = 0; i < n; i++) {
    if (max < list[i]) {
      max = list[i];
    }
  }
  free(list);
  return max;
}
```

### Min Cost Path

```c
/* A native recursive implementation of MCP problem */
#include<stdio.h>
#include<limits.h>
#define R 3
#define C 3

int main(int x, int y, int z);

/* REturns costs of minmum cost path from (0, 0) to (m, n) int mat[R][C] */
int minCost(int cost[R][C], int m, int n) {
  if (n < 0 || m < 0)
    return INT_MAX;
  else if (m == 0 && n == 0) {
    return cost[m][n];
  } else {
    return cost[m][n] + min(minCost(cost, m-1, n-1),
                            minCost(cost, m-1, n),
                            minCost(cost, m, n-1))
  }
}
```

### 0-1 Knapsack Problem

```java
class Knapsack {
  static int knapSack(int W, int wt[], int val[], int n) {
    // Base Case
    if (n == 0 || W == 0) {
      return 0;
    }

    // If weight of the nth iem is more than Knapsack capacity W, then
    // this item cannot be included in the optimal solution
    if (wt[n-1] > W) {
      return knapSack(W, wt, val, n-1);
    }

    else return Math.max(val[n-1] + knapSack(W-wt[n-1], wt, val, n-1), knapSack(W, wt, val, n-1));
  }
}


// DP Solution
class Knapsack {
  static int knapSack(int W, int[] wt, int[] val, int n) {
    int i, w;
    int[][] K = new int[n+1][W+1];

    // Build table K[][] in bottom up manner
    for (int i = 0; i <= n; i++) {
      for (int w = 0; w <= W; w++) {
        if (i == 0 || w == 0) {
          K[i][w] = 0;
        } 
        else if (wt[i-1] <= w) {
          K[i][w] = Math.max(val[i-1] + K[i-1][w-wt[i-1]], K[i-1][w]);
        }
        else {
          K[i][w] = K[i-1][w];
        }
      }
    }
  }
}
```