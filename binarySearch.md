# Binary Search

## 367 Valid Perfect Square

```java
class Solution {
    public boolean isPerfectSquare(int num) {
        long l = 1, h = (long )num;
        while (l <= h) {
            long m = (l + h) / 2;
            long cmp = m * m;
            if (cmp == num) {
                return true;
            } else if (cmp < num) {
                l = m + 1;
            } else {
                h = m - 1;
            }
        }
        return false;
    }
}
```

## 374 Guess Number Higher or Lower

```java
/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */

public class Solution extends GuessGame {
    public int guessNumber(int n) {
        int l = 1, h = n;
        while (l <= h) {
            int m = l + (h - l) / 2;
            int cmp = guess(m);
            if (cmp == 0) {
                return m;
            } else if (cmp < 0) {
                h = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
}
```

## 441 Arranging Coins

```java
class Solution {
    public int arrangeCoins(int n) {
        long l = 0L, h = (long )n;
        while (l <= h) {
            long m = l + (h - l) / 2;
            long cmp = m * (m + 1) / 2;
            if (cmp == n) {
                l = m;
                break;
            } else if (cmp > n) {
                h = m - 1;
            } else if (cmp + m + 1 > n) {
                l = m;
                break;
            } else {
                l = m + 1;
            }
        }
        return (int )l;
    }
}
```

## 475 Heaters

```java
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int ans = 0;
        for (int i = 0; i < houses.length; i++) {
            int index = Arrays.binarySearch(heaters, houses[i]);
            if (index < 0) {
                index = -(index + 1);
                if (index == 0) {
                    ans = Math.max(ans, Math.abs(heaters[0] - houses[i]));
                } else if (index == heaters.length) {
                    ans = Math.max(ans, Math.abs(heaters[heaters.length-1] - houses[i]));
                } else {
                    ans = Math.max(ans, Math.min(Math.abs(heaters[index-1] - houses[i]), Math.abs(heaters[index] - houses[i])));
                }
            }
        }
        return ans;
    }
}
```

## 69 Sqrt(x)

```java
class Solution {
    public int mySqrt(int x) {
        if (x == 0) {
            return 0;
        }
        int l = 1, h = Integer.MAX_VALUE;
        int ans = 0;
        while (l <= h) {
            int m = l + (h - l) / 2;
            if (m > x / m) {
                h = m - 1;
            } else {
                ans = m;
                l = m + 1;
            }
        }
        return ans;
    }
}
```

## First Bad Version

```java
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1, h = n;
        int ans = 0;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (isBadVersion(m)) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        return l;
    }
}
```

## 378 Kth Smallest Element in a Sorted Matrix

```java
class Solution {
    public int kthSmallest(int[][] matrix, int k) {
        int low = matrix[0][0], hig = matrix[matrix.length-1][matrix[0].length-1] + 1;
        while (low < hig) {
            int mid = low + (hig - low) / 2;
            int count = 0, j = matrix[0].length - 1;
            for (int i = 0; i < matrix.length; i++) {
                while (j >= 0 && matrix[i][j] > mid) j--;
                count += (j + 1);
            }
            if (count < k) {
                low = mid + 1;
            } else {
                hig = mid;
            }
        }
        return low;
    }
}
// 1 2
// 1 3
```

## 436 Find Right Interval

```java
/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        int[][] aux = new int[intervals.length][2];
        for (int i = 0; i < intervals.length; i++) {
            aux[i][0] = intervals[i].start;
            aux[i][1] = i;
        }
        Arrays.sort(aux, (a, b) -> a[0] - b[0]);
        int[] ans = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            ans[i] = binarySearch(aux, intervals[i].end);
        }
        return ans;
    }
    
    private int binarySearch(int[][] aux, int key) {
        // TODO: 找到第一个大于key的数字位置
        int l = 0, h = aux.length;
        while (l < h) {
            int m = l + (h - l) / 2;
            if (aux[m][0] >= key) {
                h = m;
            } else {
                l = m + 1;
            }
        }
        if (l == aux.length) return -1;
        else return aux[l][1];
    }
}

// Another solution

class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++) {
            map.put(intervals[i].start, i);
        }
        int[] ans = new int[intervals.length];
        for (int i = 0; i < ans.length; i++) {
            Integer k = map.ceilingKey(intervals[i].end);
            ans[i] = k == null ? -1 : map.get(k);
        }
        return ans;
    }
}
```

## 240 Searc a 2D Matrix II

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) return false;
        int r = 0, c = matrix[0].length - 1;
        while (r < matrix.length && c >= 0) {
            if (matrix[r][c] == target) {
                return true;
            } else if (matrix[r][c] > target) {
                c--;
            } else {
                r++;
            }
        }
        return false;
    }
}
```

## 658 Find K Closet Elements

```java
class Solution {
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new LinkedList<>();
        // 添加第一个数字
        int index = Arrays.binarySearch(arr, x);
        int l = 0, r = 0;
        if (index >= 0) {
            res.add(arr[index]);
            l = index - 1;
            r = index + 1;
        } else {
            index = -(index + 1);
            l = index - 1;
            r = index;
        }
        while ((l >= 0 || r < arr.length) && res.size() < k) {
            if (l < 0) {
                res.add(arr[r++]);
            } else if (r >= arr.length) {
                res.add(0, arr[l--]);
            } else {
                if (Math.abs(arr[l]-x) <= Math.abs(arr[r]-x)) {
                    res.add(0, arr[l--]);
                } else {
                    res.add(arr[r++]);
                }
            }
        }
        return res;
    }
}
```

## 50 Pow(x, n)

```java
class Solution {
    public double myPow(double x, int n) {
        long N = (long )n;
        if (N < 0) {
            x = 1 / x;
            N = -N;
        }
        return fastPow(x, N);
    }
    
    private double fastPow(double x, long n) {
        if (n == 0) {
            return 1.0;
        }
        
        double half = fastPow(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        } else {
            return half * half * x;
        }
    }
}
```

## 29 Divide Two Integers

```java
class Solution {
    public int divide(int dividend, int divisor) {
        int sign = 1;
        if ((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            sign = -1;
        }
        
        long ldividend = Math.abs((long )dividend);
        long ldivisor = Math.abs((long )divisor);
        
        if (ldivisor == 0) return Integer.MAX_VALUE;
        if (ldividend == 0 || (ldividend < ldivisor)) return 0;
        
        long ans = ldivide(ldividend, ldivisor);
        if (ans > Integer.MAX_VALUE) {
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        } else {
            return (int )(sign * ans);
        }
    }
    
    private long ldivide(long ldividend, long ldivisor) {
        if (ldividend < ldivisor) {
            return 0;
        }
        
        long sum = ldivisor, count = 1L;
        while ((sum + sum) < ldividend) {
            sum += sum;
            count += count;
        }
        return count + ldivide(ldividend - sum, ldivisor);
    }
}
```