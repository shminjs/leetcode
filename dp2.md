# Dynamic Programming

## 70 Climbing Stairs

```java
class Solution {
    public int climbStairs(int n) {
        int p1 = 1, p2 = 0;
        int tmp = 0;
        for (int i = 1; i <= n; i++) {
            tmp = p1;
            p1 = p1 + p2;
            p2 = tmp;
        }
        return p1;
    }
}
```

## 198 House Robber

```java
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = Math.max((i-2>=0? dp[i-2]:0) + nums[i], i-1>=0? dp[i-1]:0);
        }
        return dp[nums.length-1];
    }
}
```

## 303 Range Sum Query - Immutable

```java
class NumArray {
    int[] preSum;

    public NumArray(int[] nums) {
        preSum = new int[nums.length+1];
        int pre = 0;
        for (int i = 1; i <= nums.length; i++) {
            pre += nums[i-1];
            preSum[i] = pre;
        }
    }
    
    public int sumRange(int i, int j) {
        return preSum[j+1] - preSum[i];
    }
}
```

## 338 Counting Bits

```java
class Solution {
    public int[] countBits(int num) {
        int[] res = new int[num+1];
        int offset = 1;
        for (int i = 1; i < num + 1; i++) {
            if (offset * 2 == i) {
                offset *= 2;
            }
            res[i] = res[i - offset] + 1;
        }
        return res;
    }
}
```

## 413 Arithmetic Slices

```java
class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        if (A.length == 0) return 0;
        int[] substraction = new int[A.length-1];
        for (int i = 1; i < A.length; i++) {
            substraction[i-1] = A[i] - A[i-1];
        }
        int res = 0;
        int count = 0;
        Integer pre = null;
        for (int i = 0; i < substraction.length; i++) {
            if (pre != null) {
                if (pre == substraction[i]) {
                    count++;
                    if (count >= 2) {
                      res += (count - 1);
                    }
                } else {
                    count = 1;
                }
                pre = substraction[i];
            } else {
                pre = substraction[i];
                count = 1;
            }
        }
        return res;
    }
}

// better solution
public class Solution {
    public int numberOfArithmeticSlices(int[] A) {
        int[] dp = new int[A.length];
        int sum = 0;
        for (int i = 2; i < dp.length; i++) {
            if (A[i] - A[i - 1] == A[i - 1] - A[i - 2]) {
                dp[i] = 1 + dp[i - 1];
                sum += dp[i];
            }
        }
        return sum;
    }
}
```

## 712 Minimum ASCII Delete Sum for Two Strings

```java
class Solution {
    public int minimumDeleteSum(String s1, String s2) {
        int[][] L = new int[s1.length()+1][s2.length()+1];
        
        for (int i = s1.length() - 1; i >= 0; i--) {
            L[i][s2.length()] = L[i+1][s2.length()] + s1.codePointAt(i);
        }
        
        for (int j = s2.length() - 1; j >= 0; j--) {
            L[s1.length()][j] = L[s1.length()][j+1] + s2.codePointAt(j);
        }
        
        for (int i = s1.length()-1; i >= 0; i--) {
            for (int j = s2.length()-1; j >= 0; j--) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    L[i][j] = L[i+1][j+1];
                } else {
                    L[i][j] = Math.min(L[i+1][j] + s1.codePointAt(i), L[i][j+1] + s2.codePointAt(j));
                }
            }
        }
        return L[0][0];
    }
}
```

## 646 Maximum Length of Pair Chain

```java
class Solution {
    public int findLongestChain(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> (a[0] - b[0]));
        int[] dp = new int[pairs.length];
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 0; i < pairs.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (pairs[i][0] > pairs[j][1] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```

## 343 Integer Break

```java
class Solution {
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], Math.max(j, dp[j]) * Math.max(i-j, dp[i-j]));
            }
        }
        return dp[n];
    }
}
```

## 486 Predict the Winner

```java
class Solution {
    public boolean PredictTheWinner(int[] nums) {
        int[][] dp = new int[nums.length+1][nums.length+1];
        int sum = 0;
        for (int i = 1; i <= nums.length; i++) {
            dp[i][i] = nums[i-1];
            sum += nums[i-1];
        }
        for (int len = 2; len <= nums.length; len++) {
            for (int i = 1; i <= nums.length - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Math.max(nums[i-1] - dp[i+1][j], nums[j-1] - dp[i][j-1]);
            }
        }
        return dp[1][nums.length] >= 0;
    }
}

// native solution
public class Solution {
    public boolean PredictTheWinner(int[] nums) {
        return winner(nums, 0, nums.length - 1, 1) >= 0;
    }
    public int winner(int[] nums, int s, int e, int turn) {
        if (s == e)
            return turn * nums[s];
        int a = turn * nums[s] + winner(nums, s + 1, e, -turn);
        int b = turn * nums[e] + winner(nums, s, e - 1, -turn);
        return turn * Math.max(turn * a, turn * b);
    }
}
```

## 650 2 Keys Keyboard

```java
class Solution {
    public int minSteps(int n) {
        if (n < 1) return 0;
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[1] = 0;
        for (int i = 2; i <= n; i++) {
            for (int j = i-1; j <= n; j += i-1) {
                dp[j] = Math.min(dp[j], j / (i-1) + dp[i-1]);
            }
        }
        return dp[n];
    }
}
```

## 309 Best Time to Buy and Sell Stock with Cooldown

```java
class Solution {
    public int maxProfit(int[] prices) {
        int[][] dp = new int[prices.length+1][2];
        dp[0][1] = Integer.MIN_VALUE;
        dp[0][0] = 0;
        for (int i = 0; i < prices.length; i++) {
            dp[i+1][0] = Math.max(dp[i][0], dp[i][1] + prices[i]);
            dp[i+1][1] = Math.max(dp[i][1], i - 1 >= 0 ? (dp[i-1][0] - prices[i]) : -prices[i]);
        }
        return dp[prices.length][0];
    }
}
```

## 392 Is Subsequence

```java
class Solution {
    public boolean isSubsequence(String s, String t) {
        int j = 0;
        for (int i = 0; i < t.length() && j < s.length(); i++) {
            if (s.charAt(j) == t.charAt(i)) j++;
        }
        return j == s.length();
    }
}

// Follow up 存储t的各字母出现位置，然后后面再直接根据s的字母位置来找，好处就是一次可以推进很多字符。而不是像现在这样一次只推进一个字符。
```

## 494 Target Sum

```java
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        int sum = 0;
        for (int val : nums) sum += val;
        // 如果S大于sum,或者sum + S是奇数，显然是不存在的，直接返回0
        if (S > sum || (sum + S) % 2 == 1) return 0;
        // 从nums数组里面找出数字和等于值S的
        // 这是一个已经解决的问题
        S = (sum + S) / 2;
        int[][] dp = new int[nums.length+1][S+1];
        for (int j = 1; j <= S; j++) dp[0][j] = 0;
        dp[0][0] = 1;
        for (int j = 0; j <= S; j++) {
            for (int i = 1; i <= nums.length; i++) {
                if (j >= nums[i-1]) {
                    dp[i][j] = dp[i-1][j-nums[i-1]] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[nums.length][S];
    }
}
// a1 + a2 + a3 - a4 - a5 - a6 = s
// 2 * (a1 + a2 + a3) = s + a4 + a5 + a6 + a1 + a2 + a3
// 2 * (a1 + a2 + a3) = s + sum
// a1 + a2 + a3 = (s + sum) / 2;
// coin change有什么区别吗？如何找出子问题？顺序是否相关，这里应该是顺序无关类？
// 顺序无关怎么做？怎么把二维dp变成一维dp。
// 首先考虑二维，需要优化的时候再转换一维
// 什么子问题
// lcs(nums, i, S) = lcs(nums, i - 1, s - nums[i]) + lcs(nums, i - 1, s)
```

## 638 Shopping Offers

```java
class Solution {
    public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        return shoppingOffers(price, special, special.size()-1, needs);
    }
    
    private int shoppingOffers(List<Integer> price, List<List<Integer>> special, int i, List<Integer> needs) {
        if (i < 0) {
            // 此时已经做完选择
            int ans = 0;
            for (int j = 0; j < needs.size(); j++) {
                ans += needs.get(j) * price.get(j);
            }
            return ans;
        } else {
            // 满足啥情况？不满足情况
            if (isTrue(needs, special.get(i))) {
                // 满足情况
                List<Integer> sub = new ArrayList<>(needs);
                for (int j = 0; j < sub.size(); j++) {
                    sub.set(j, sub.get(j) - special.get(i).get(j));
                }
                return Math.min(special.get(i).get(sub.size()) + shoppingOffers(price, special, i, sub), shoppingOffers(price, special, i - 1, needs));
            } else {
                // 不满足情况
                return shoppingOffers(price, special, i - 1, needs);
            }
        }
    }
    
    private boolean isTrue(List<Integer> needs, List<Integer> cmp) {
        for (int i = 0; i < needs.size(); i++) {
            if (needs.get(i) < cmp.get(i)) return false;
        }
        return true;
    }
}
// 思路整理：有点像sub set sum的一个问题，此时某个special是可以重复取的，但是取是有条件的
// 必须完全满足条件才能取，然后最后如果都不能满足，就单取。所以如果都是0，单取金额是0，否则相应乘法即可。
// 所以也类似于某种coin change，某些单位的货币数量不限，但是最后的结果正好化开且硬币个数最少？
// 子问题
// lcs(prices, special, i, needs) = Math.min(lcs(prices, special, i - 1, needs), lcs(prices, special, i, needs - special[i]))
// 公式是这个，bottom-up怎么记录状态
// up-bottom怎么记录状态同样是一个问题
// 根据这个问题，暂时肯定可以得到的是递推关系表示
// 先把递推表示出来
```

## 516 Longest Palindromic Subsequence 

```java
// TLE solution
class Solution {
    public int longestPalindromeSubseq(String s) {
        return longestPalindromeSubseq(s, 0, s.length() - 1);
    }
    
    private int longestPalindromeSubseq(String s, int p, int q) {
        if (p == q) {
            return 1;
        } else if (p > q) {
            return 0;
        } else {
            if (s.charAt(p) == s.charAt(q)) {
                return 2 + longestPalindromeSubseq(s, p + 1, q - 1);
            } else {
                return Math.max(longestPalindromeSubseq(s, p + 1, q), longestPalindromeSubseq(s, p, q - 1));
            }
        }
    }
}
// 子问题如何体现
// lcs(s, 0, n)
// if (s[0] == s[n])  { lcs(s, 0, n) = 2 + lcs(s, 1, n -1); }
// else { lcs(s, 0, n) = Math.max(lcs(s, 0, n-1), lcs(s, 1, n)); }

// memorization
class Solution {
    public int longestPalindromeSubseq(String s) {
        int[][] dp = new int[s.length()][s.length()];
        return longestPalindromeSubseq(s, 0, s.length() - 1, dp);
    }
    
    private int longestPalindromeSubseq(String s, int p, int q, int[][] dp) {
        if (p == q) {
            dp[p][q] = 1;
            return 1;
        } else if (p > q) {
            return 0;
        } else if (dp[p][q] != 0) {
            return dp[p][q];
        }else {
            if (s.charAt(p) == s.charAt(q)) {
                int ans = 2 + longestPalindromeSubseq(s, p + 1, q - 1, dp);
                dp[p][q] = ans;
                return dp[p][q];
            } else {
                int ans = Math.max(longestPalindromeSubseq(s, p + 1, q, dp), longestPalindromeSubseq(s, p, q - 1, dp));
                dp[p][q] = ans;
                return dp[p][q];
            }
        }
    }
}

// bottom-up solution
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = 1;
        for (int cl = 2; cl <= n; cl++) {
            for (int i = 0; i < n - cl + 1; i++) {
                int j = i + cl - 1;
                if (s.charAt(i) == s.charAt(j) && cl == 2) {
                    dp[i][j] = 2;
                } else if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[0][n-1];
    }
}
```

## 377 Combination Sum IV

```java
class Solution {
    public int combinationSum4(int[] nums, int target) {
        // 排序一下更好
        Arrays.sort(nums);
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] <= i) {
                    dp[i] += dp[i-nums[j]];
                }
            }
        }
        return dp[target];
    }
}
// 这一题很有意思，有的代码只需要简单改一改就是其他的情况了
// 刚看到了，和geekforgeek上的coin change题目很类似，coin change那一题是没有统计顺序的，这里是统计了顺序
// comb[target] = sum(comb[target-nums[i]]), 0<= i < nums.length, and target >= nums[i]
// 看到区别没有，与lcs(s, i, amount)这种的不同，其实是跟特定的i是没有关系的
```

## 322 Coin Change

```java
// 老老实实写递推公式
// f[x] = Math.min(f[x-2]+1, f[x-5]+1, f[x-7]+1);
class Solution {
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] f = new int[amount+1];
        int i = 0, j = 0;
        f[0] = 0;
        for (i = 1; i <= amount; i++) {
            f[i] = Integer.MAX_VALUE;
            for (j = 0; j < n; j++) {
                if (i >= coins[j] && f[i-coins[j]] != Integer.MAX_VALUE && f[i-coins[j]] + 1 < f[i]) {
                    f[i] = f[i-coins[j]] + 1;
                }
            }
        }
        return f[amount] == Integer.MAX_VALUE ? -1 : f[amount];
    }
}
```

## 688 Knight Probability in Chessboard

```java
class Solution {
    private static int[][] dirs = {{-2, -1}, {-1, -2}, {-2, 1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}};
    public double knightProbability(int N, int K, int r, int c) {
        double[][] dp1 = new double[N][N];
        double[][] dp2 = new double[N][N];
        dp1[r][c] = 1.0;
        for (int k = 1; k <= K; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    // 开始计算每一个点到周围八个点的概率
                    for (int[] dir : dirs) {
                        if (i + dir[0] < 0 || i + dir[0] >= N || j + dir[1] < 0 || j + dir[1] >= N) {
                            continue;
                        }
                        dp2[i+dir[0]][j+dir[1]] += dp1[i][j] * 0.125;
                    }
                    dp1[i][j] = 0.0;
                }
            }
            double[][] temp = dp2;
            dp2 = dp1;
            dp1 = temp;
        }
        double ans = 0.0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                ans += dp1[i][j];
            }
        }
        return ans;
    }
    
    private void printArray(double[][] nums) {
        System.out.println("++++++++++");
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length; j++) {
                System.out.print(nums[i][j] + "\t");
            }
            System.out.println();
        }
    }
}
```

## 474 Ones and Zeroes

```java
// TLE solution
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        Map<String, int[]> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            int count0 = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0') {
                    count0++;
                }
            }
            int count1 = strs[i].length() - count0;
            map.put(strs[i], new int[]{count0, count1});
        }
        int ans = findMaxForm(strs, map, strs.length-1, m, n);
        return ans == Integer.MIN_VALUE ? 0 : ans;
    }
    
    private int findMaxForm(String[] strs, Map<String, int[]> map, int p, int m, int n) {
        if (p < 0 || (m == 0 && n == 0)) {
            return 0;
        } else {
            int[] pp = map.get(strs[p]);
            if (pp[0] <= m && pp[1] <= n) {
                return Math.max(1 + findMaxForm(strs, map, p - 1, m - pp[0], n - pp[1]), findMaxForm(strs, map, p-1, m, n));
            } else {
                return findMaxForm(strs, map, p-1, m, n);
            }
        }
    }
}

// 这一题类似于背包，但是背包里任何一个元素是任意可取的
// lcs(strs, i, m, n) = Math.max(strs, i-1, m-strs[i], n-strs[i]), lcs(strs, i-1, m, n))
// 先写出递归形式

// DP solution
class Solution {
    public int findMaxForm(String[] strs, int m, int n) {
        Map<String, int[]> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            int count0 = 0;
            for (int j = 0; j < strs[i].length(); j++) {
                if (strs[i].charAt(j) == '0') {
                    count0++;
                }
            }
            int count1 = strs[i].length() - count0;
            map.put(strs[i], new int[]{count0, count1});
        }
        int[][] dp = new int[m+1][n+1];
        for (int k = 0; k < strs.length; k++) {
            int[] pp = map.get(strs[k]);
            for (int i = m; i >= 0; i--){
                for (int j = n; j >= 0; j--) {
                    if (pp[0] <= i && pp[1] <= j) {
                        dp[i][j] = Math.max(dp[i][j], 1 + dp[i-pp[0]][j-pp[1]]);
                    }
                }
            }
        }
        return dp[m][n];
    }
}
```

## 416 Partition Equal Subset Sum

```java
class Solution {
    public boolean canPartition(int[] nums) {
        int S = 0;
        for (int num :nums) S += num;
        if (S % 2 == 1) return false;
        S /= 2;
        boolean[][] dp = new boolean[nums.length+1][S+1];
        for (int j = 1; j <= S; j++) dp[0][j] = false;
        for (int i = 0; i <= nums.length; i++) dp[i][0] = true;
        for (int i = 1; i <= nums.length; i++) {
            for (int j = 1; j <= S; j++) {
                if (j >= nums[i-1]) {
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-nums[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[nums.length][S];
    }
}
// subset sum problem
// dp[i][s] = dp[i-1][s] || dp[i-1][s-nums[i]]
```

## 300 Longest Increasing Subsequence

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        Arrays.fill(dp, 1);
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[j] < nums[i] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```

## 279 Perfect Squares

```java
class Solution {
    public int numSquares(int n) {
        List<Integer> squares = new ArrayList<>();
        for (int i = 1; i * i <= n; i++) {
            squares.add(i * i);
        }
        int[] dp = new int[n+1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < squares.size(); j++) {
                if (i >= squares.get(j) && dp[i - squares.get(j)] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i], 1 + dp[i-squares.get(j)]);
                }
            }
        }
        return dp[n];
    }
}
```

## 750 Number of Corner Rectangles

```java
class Solution {
    public int countCornerRectangles(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length - 1; i++) {
            for (int j = i + 1; j < grid.length; j++) {
                int counter = 0;
                for (int k = 0; k < grid[0].length; k++) {
                    if (grid[i][k] == 1 && grid[j][k] == 1) counter++;
                }
                if (counter > 0) ans += counter * (counter - 1) / 2;
            }
        }
        return ans;
    }
}
```

## 746 Min Cost Climing Stairs

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int[] dp = new int[cost.length];
        for (int i = 0; i < cost.length; i++) {
            dp[i] = Math.min(i-2>=0 ? dp[i-2] : 0, i-1>=0 ? dp[i-1] : 0) + cost[i];
        }
        return Math.min(dp[cost.length-1], dp[cost.length-2]);
    }
}

// dp[i] = Math.min(dp[i-2], dp[i-1]) + nums[i];
```

## 764 Largest Plus Sign

```java
class Solution {
    public int orderOfLargestPlusSign(int N, int[][] mines) {
        int[][] origin = new int[N][N];
        int[][] left = new int[N][N];
        int[][] right = new int[N][N];
        int[][] up = new int[N][N];
        int[][] down = new int[N][N];
        for (int[] sub : origin) {
            Arrays.fill(sub, 1);
        }
        for (int i = 0; i < mines.length; i++) {
            origin[mines[i][0]][mines[i][1]] = 0;
        }
        // 可以在一个双层循环完成所有的事情
        int c1 = 0, c2 = 0, c3 = 0, c4 = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (origin[i][j] == 1) {
                    c1++;
                } else {
                    c1 = 0;
                }
                left[i][j] = c1;
                if (origin[i][N-j-1] == 1) {
                    c2++;
                } else {
                    c2 = 0;
                }
                right[i][N-j-1] = c2;
                if (origin[j][i] == 1) {
                    c3++;
                } else {
                    c3 = 0;
                }
                down[j][i] = c3;
                if (origin[N-j-1][i] == 1) {
                    c4++;
                } else {
                    c4 = 0;
                }
                up[N-j-1][i] = c4;
            }
            c1 = 0; c2 = 0; c3 = 0; c4 = 0;
        }
        int res = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (origin[i][j] == 1) {
                    int min = Math.min(Math.min(left[i][j], right[i][j]), Math.min(up[i][j], down[i][j]));
                    res = Math.max(min, res);
                }
            }
        }
        return res;
    }
}
// 当然也可以只有一个数组，也就是还可以更简单
```

## 376 Wiggle Subsequence

```java
class Solution {
    public int wiggleMaxLength(int[] nums) {
        if (nums.length == 0) return 0;
        int[][] dp = new int[nums.length][2];
        // 第一行先增加，后减少。第二行先减少，后增加。
        dp[0][0] = 1; dp[0][1] = 1;
        int res = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] < nums[j] && dp[j][1] + 1 > dp[i][0]) {
                    dp[i][0] = dp[j][1] + 1;
                }
                if (nums[i] > nums[j] && dp[j][0] + 1 > dp[i][1]) {
                    dp[i][1] = dp[j][0] + 1;
                }
            }
            res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
        }
        return res;
    }
}
```

## 375 Guess Number Higher or Lower II

```java
class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n][n];
        for (int[] sub : dp) {
            Arrays.fill(sub, -1);
        }
        depthSearch(dp, 0, n-1);
        return dp[0][n-1];
    }
    
    private int depthSearch(int[][] dp, int start, int end) {
        if (start == end) {
            dp[start][end] = 0;
        } else if (dp[start][end] == -1) {
            int min = Integer.MAX_VALUE;
            int left = 0, right = 0;
            for (int i = start; i <= end; i++) {
                left = i - 1 >= start ? depthSearch(dp, start, i - 1) : 0;
                right = i + 1 <= end ? depthSearch(dp, i + 1, end) : 0;
                min = Math.min(min, Math.max(left, right) + i);
            }
            dp[start][end] = 1 + min;
        }
        return dp[start][end];
    }
}


class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n][n];
        for (int len = 1; len < n; len++) {
            for (int i = 0; i < n - len; i++) {
                int j = i + len;
                int min = Integer.MAX_VALUE;
                for (int k = i; k <= j; k++) {
                    int left = k - 1 >= i ? dp[i][k-1] : 0;
                    int right = k + 1 <= j ? dp[k+1][j] : 0;
                    min = Math.min(min, Math.max(left, right) + k);
                }
                dp[i][j] = 1 + min;
            }
        }
        return dp[0][n-1];
    }
}
```

## 213 House Robber II

```java
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int d1 = nums[0], d2 = Integer.MIN_VALUE, di = 0;
        for (int i = 1; i < nums.length - 1; i++) {
            di = Math.max(d1, d2 + nums[i]);
            d2 = d1;
            d1 = di;
        }
        int c1 = 0, c2 = 0, ci = 0;
        for (int i = 1; i < nums.length; i++) {
            ci = Math.max(c1, c2 + nums[i]);
            c2 = c1;
            c1 = ci;
        }
        return Math.max(d1, c1);
    }
}
```

## 368 Largest Divisible Subset

```java
class Solution {
    public List<Integer> largestDivisibleSubset(int[] nums) {
        if (nums.length == 0) return new ArrayList<>();
        int[] back = new int[nums.length];
        Arrays.fill(back, -1);
        int[] dp = new int[nums.length];
        Arrays.sort(nums);
        int maxI = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 1; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if ((nums[i] % nums[j] == 0) && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    back[i] = j;
                }
                if (dp[i] > max) {
                    max = dp[i];
                    maxI = i;
                }
            }
        }
        List<Integer> res = new LinkedList<>();
        while (back[maxI] != -1) {
            res.add(0, nums[maxI]);
            maxI = back[maxI];
        }
        res.add(0, nums[maxI]);
        return res;
    }
}
```

## 264 Ugly Number II

```java
class Solution {
    public int nthUglyNumber(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n];
        dp[0] = 1;
        int two = 0;
        int three = 0;
        int five = 0;
        int ind = 1;
        while (ind < n) {
            int p = Math.min(dp[two] * 2, Math.min(dp[three] * 3, dp[five] * 5));
            dp[ind++] = p;
            if (p == dp[two] * 2) {
                two++;
            }
            if (p == dp[three] * 3) {
                three++;
            }
            if (p == dp[five] * 5) {
                five++;
            }
        }
        return dp[n-1];
    }
}
```

## 467 Unique Substring in Wrappound String

```java
class Solution {
    public int findSubstringInWraproundString(String p) {
        int[] count = new int[26];
        int max = 1;
        Character pre = null;
        for (int i = 0; i < p.length(); i++) {
            if (pre != null && isContinues(pre, p.charAt(i))) {
                max++;
            } else {
                max = 1;
            }
            pre = p.charAt(i);
            int index = p.charAt(i) - 'a';
            count[index] = Math.max(count[index], max);
        }
        int res = 0;
        for (int i = 0; i < 26; i++) res += count[i];
        return res;
    }
    
    private boolean isContinues(char p1, char p2) {
        int i1 = p1 - 'a';
        int i2 = p2 - 'a';
        if ((i1 + 1) % 26 == i2) return true;
        else return false;
    }
}
```

## 673 Number of Longest Increasing Subsequence

```java
class Solution {
    public int findNumberOfLIS(int[] nums) {
        int res = 0;
        int max = Integer.MIN_VALUE;
        int[] dp = new int[nums.length];
        int[] counts = new int[nums.length];
        Arrays.fill(counts, 1);
        Arrays.fill(dp, 1);
        for (int i = 0; i < nums.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        counts[i] = counts[j];
                    } else if (dp[i] == dp[j] + 1) {
                        // System.out.println(i + " | " + j);
                        counts[i] += counts[j];
                    }
                }
            }
            // System.out.println(Arrays.toString(counts));
            // System.out.println(Arrays.toString(dp));
            if (dp[i] > max) {
                max = dp[i];
                res = counts[i];
            } else if (dp[i] == max) {
                res += counts[i];
            }
        }
        return res;
    }
}
```

## 139 Word Break

```java
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i = 0; i < dp.length; i++) {
            if (dp[i]) {
                // dp[i] = true, 从i+1处计算
                for (String word : wordDict) {
                    if (isSame(s, i+1, word)) {
                        dp[i+word.length()] = true;
                    }
                }
            }
        }
        return dp[s.length()];
    }
    
    private boolean isSame(String s, int start, String word) {
        int i = start-1, j = 0;
        for (; i < s.length() && j < word.length(); i++, j++) {
            if (s.charAt(i) != word.charAt(j)) return false;
        }
        return j == word.length();
    }
}
// true false false false false false false false false
```

## 576 Out of Boundary Paths

```java
class Solution {
    static int[][] dirs = {{0, -1}, {-1, 0}, {0, 1}, {1, 0}};
    public int findPaths(int m, int n, int N, int i, int j) {
        int ans = 0;
        int[][] dp1 = new int[m][n];
        int[][] dp2 = new int[m][n];
        dp1[i][j] = 1;
        for (int c = 0; c < N; c++) {
            for (int k = 0; k < m; k++) {
                for (int l = 0; l < n;  l++) {
                    if (dp1[k][l] >= 1) {
                        for (int[] dir : dirs) {
                            int x = k + dir[0];
                            int y = l + dir[1];
                            if (x < 0 || y < 0 || x >= m || y >= n) {
                                ans = (ans + dp1[k][l]) % (1_000_000_000 + 7);
                            } else {
                                dp2[x][y] = (dp2[x][y] + dp1[k][l]) % (1_000_000_000 + 7);
                            }
                        }
                        dp1[k][l] = 0;
                    }
                }
            }
            int[][] temp = dp2;
            dp2 = dp1;
            dp1 = temp;
        }
        return ans;
    }
}
```

## 221 Maximal Square

```java
class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int[][] dp = new int[matrix.length+1][matrix[0].length+1];
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                if (matrix[i-1][j-1] == '1') {
                    dp[i][j] = 1 + Math.min(dp[i-1][j], Math.min(dp[i][j-1], dp[i-1][j-1]));
                    ans = Math.max(ans, dp[i][j]);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return ans * ans;
    }
}
```

## 304 Range Sum Query 2D-Immutable

```java
class NumMatrix {
    private int[][] dp;

    public NumMatrix(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return;
        dp = new int[matrix.length+1][matrix[0].length+1];
        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[0].length; j++) {
                dp[i][j] = dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + matrix[i-1][j-1];
            }
        }
    }
    
    public int sumRegion(int row1, int col1, int row2, int col2) {
        return dp[row2+1][col2+1] + dp[row1][col1] - dp[row1][col2+1] - dp[row2+1][col1];
    }
}
```

## 523 Continuous Subarray Sum

```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            preSum += nums[i];
            if (k != 0 ) preSum %= k;
            if (map.containsKey(preSum)) {
                if (i - map.get(preSum) > 1) return true;
            } else {
                map.put(preSum, i);
            }
        }
        return false;
    }
}
```

## 464 Can I Win

```java
class Solution {
    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal <= maxChoosableInteger) return true;
        int sum = (maxChoosableInteger + 1) * maxChoosableInteger / 2;
        if (desiredTotal > sum) return false;
        else if (desiredTotal == sum) return maxChoosableInteger % 2 == 1;
        else return canIWin(maxChoosableInteger, 0, desiredTotal, new Boolean[1 << maxChoosableInteger]);
    }
    
    private boolean canIWin(int n, int state, int remain, Boolean[] dp) {
        if (dp[state] != null) return dp[state];
        for (int i = n; i >= 1; i--) {
            int pick = 1 << (i - 1);
            if ((pick & state) != 0) continue;
            if (remain <= i || !canIWin(n, state | pick, remain - i, dp)) {
                dp[state] = true;
                return true;
            }
        }
        dp[state] = false;
        return false;
    }
}
```

## 312 Burst Ballons

```java
class Solution {
    public int maxCoins(int[] nums) {
        int[] inums = new int[nums.length+2];
        int n = 1;
        for (int x : nums) if (x > 0) inums[n++] = x;
        inums[0] = inums[n++] = 1;
        
        int[][] dp = new int[n][n];
        for (int k = 2; k < n; k++) {
            for (int left = 0; left < n - k; left++) {
                int right = left + k;
                for (int i = left + 1; i < right; i++) {
                    dp[left][right] = Math.max(dp[left][right], inums[left] * inums[i] * inums[right] + dp[left][i] + dp[i][right]);
                }
            }
        }
        return dp[0][n-1];
    }
}
```

## 514 Freedom Trail

```java
class Solution {
    public int maxCoins(int[] nums) {
        int[] inums = new int[nums.length+2];
        int n = 1;
        for (int x : nums) if (x > 0) inums[n++] = x;
        inums[0] = inums[n++] = 1;
        
        int[][] dp = new int[n][n];
        for (int k = 2; k < n; k++) {
            for (int left = 0; left < n - k; left++) {
                int right = left + k;
                for (int i = left + 1; i < right; i++) {
                    dp[left][right] = Math.max(dp[left][right], inums[left] * inums[i] * inums[right] + dp[left][i] + dp[i][right]);
                }
            }
        }
        return dp[0][n-1];
    }
}
```

## 517 Super Washing Machines

```java
class Solution {
    public int findMinMoves(int[] machines) {
        int sum = 0;
        for (int machine : machines) sum += machine;
        if (sum % machines.length != 0) return -1;
        int avg = sum / machines.length, cnt = 0, max = 0;
        for (int i = 0; i < machines.length; i++) {
            cnt += machines[i] - avg;
            max = Math.max(Math.max(max, Math.abs(cnt)), machines[i] - avg);
        }
        return max;
    }
}
```

## 546 Remove Boxes

```java
class Solution {
    public int removeBoxes(int[] boxes) {
        int n = boxes.length;
        int[][][] dp = new int[n][n][n];
        return removeBoxesSub(boxes, 0, n-1, 0, dp);
    }
    
    private int removeBoxesSub(int[] boxes, int i, int j, int k, int[][][] dp) {
        if (i > j) return 0;
        if (dp[i][j][k] != 0) return dp[i][j][k];
        
        for (; i + 1 <= j && boxes[i+1] == boxes[i]; i++, k++);
        int res = (k+1) * (k+1) + removeBoxesSub(boxes, i + 1, j, 0, dp);
        for (int m = i + 1; m <= j; m++) {
            if (boxes[m] == boxes[i]) {
                res = Math.max(res, removeBoxesSub(boxes, i + 1, m - 1, 0, dp) + removeBoxesSub(boxes, m, j, k + 1, dp));
            }
        }
        dp[i][j][k] = res;
        return res;
    }
}
```

## 691 Stickers to Spell Word

```java
class Solution {
    public int minStickers(String[] stickers, String target) {
        int N = target.length();
        int[] dp = new int[1 << N];
        for (int i = 1; i < 1 << N; i++) dp[i] = -1;
        
        for (int state = 0; state < 1 << N; state++) {
            if (dp[state] == -1) continue;
            for (String sticker : stickers) {
                int now = state;
                for (char letter : sticker.toCharArray()) {
                    for (int i = 0; i < N; i++) {
                        if (((now >> i) & 1) == 1) continue;
                        if (letter == target.charAt(i)) {
                            now |= 1 << i;
                            break;
                        }
                    }
                }
                if (dp[now] == -1 || dp[now] > dp[state] + 1) {
                    dp[now] = dp[state] + 1;
                }
            }
        }
        return dp[(1 << N) - 1];
    }
}
```

## 664 Strange Printer

```java
class Solution {
    public int strangePrinter(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        return strangePrinterSub(s, 0, n - 1, dp);
    }
    
    private int strangePrinterSub(String s, int i, int j, int [][] dp) {
        if (i > j) return 0;
        if (dp[i][j] != 0) return dp[i][j];
        
        for (; i + 1 <= j && s.charAt(i+1) == s.charAt(i); i++);
        int res = 1 + strangePrinterSub(s, i + 1, j, dp);
        for (int m = i + 1; m <= j; m++) {
            if (s.charAt(m) == s.charAt(i)) {
                res = Math.min(res, strangePrinterSub(s, i + 1, m - 1, dp) + strangePrinterSub(s, m, j, dp));
            }
        }
        dp[i][j] = res;
        return res;
    }
}
```

## 363 Max Sum of Rectangle No Larger Than K

```java
class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int cols = matrix[0].length;
        int rows = matrix.length;
        int ans = Integer.MIN_VALUE;
        for (int leftCol = 0; leftCol < cols; leftCol++) {
            int[] tmp = new int[rows];
            for (int rightCol = leftCol; rightCol < cols; rightCol++) {
                for (int i = 0; i < rows; i++) {
                    tmp[i] += matrix[i][rightCol];
                }
                // System.out.println(Arrays.toString(tmp));
                int cur = maxSumOneD(tmp, k);
                // System.out.println(cur);
                ans = Math.max(ans, cur);
            }
        }
        return ans;
    }
    
    private int maxSumOneD(int[] a, int k) {
        int res = Integer.MIN_VALUE;
        // Use treeset to help us find the rectangle with maxSUm <= k with O(logN) time
        TreeSet<Integer> set = new TreeSet<>();
        
        // add 0 to cover the single row case
        set.add(0);
        int currSum = 0;
        
        for (int i : a){
            currSum += i;
            // we use sum subtraction (currSum - sum) to get the subarray with sub <= k
            // therefore we need to look for the smallest sum >= currSum - k
            Integer num = set.ceiling(currSum - k);
            if (num != null) res = Math.max(res, currSum - num);
            set.add(currSum);
        }
        
        return res;
    }
}
// 这一题很有意思，子问题是求Maximum sum subarray having sum less than or equal to given sum
// geeksforgeeks上有类似 https://www.geeksforgeeks.org/maximum-sum-subarray-sum-less-equal-given-sum/
// 但是geeksforgeeks上的针对的是全positive情况
// 如果有nagitive就不适用了，所以改进型的方案就是这种
```

## 403 Frog Jump

```java
// 110ms
class Solution {
    public boolean canCross(int[] stones) {
        Map<Integer, Integer> map = new HashMap<>();
        Set<Integer>[] dp = (Set<Integer>[])new Set[stones.length];
        for (int i = 0; i < dp.length; i++) {
            map.put(stones[i], i);
            dp[i] = new HashSet<Integer>();
        }
        dp[0].add(0);
        for (int i = 0; i < stones.length; i++) {
            if (dp[i].size() != 0) {
                for (int step : dp[i]) {
                    add(stones, map, dp, i, step - 1);
                    add(stones, map, dp, i, step);
                    add(stones, map, dp, i, step + 1);
                }
            }
        }
        return dp[stones.length-1].size() != 0;
    }
    
    private void add(int[] stones, Map<Integer, Integer> map, Set[] dp, int start, int step) {
        if (step <= 0) {
            return;
        } else {
            if (map.containsKey(stones[start] + step)) {
                int index = map.get(stones[start] + step);
                dp[index].add(step);
            }
        }
    }
}
// 思路一开始是这样，但是为了提高效率，使用了Map保存位置信息，并且使用Set保存step信息，保证了Step不重复，进一步降低了复杂度
// 然而这并不是最合适的方法，相当于广度搜索了，此处感觉深度搜索更加合适，只要是有一个true就直接返回，更加合适。
// 36ms
class Solution {
    public boolean canCross(int[] stones) {
        if (stones.length < 2) return true;
        if (stones[1] != 1) return false;
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Boolean>[] dp =(Map<Integer, Boolean>[]) new Map[stones.length];
        for (int i = 0; i < stones.length; i++) {
            dp[i] = new HashMap<>();
            map.put(stones[i], i);
        }
        return canCrossDfs(stones, map, dp, 1, 1);
    }
    
    private boolean canCrossDfs(int[] stones, Map<Integer, Integer> map, Map<Integer, Boolean>[] dp, int start, int k) {
        if (start >= stones.length || k <= 0 || start == -1) return false;
        else if (start == stones.length - 1) return true;
        else if (dp[start].containsKey(k)) {
            return dp[start].get(k);
        } else {
            int f1 = map.getOrDefault(stones[start] + k + 1, -1);
            int f2 = map.getOrDefault(stones[start] + k, -1);
            int f3 = map.getOrDefault(stones[start] + k - 1, -1);
            if (f1 + f2 + f3 == -1) {
                dp[start].put(k, false);
                return false;
            }
            boolean res = canCrossDfs(stones, map, dp, f1, k + 1) || canCrossDfs(stones, map, dp, f2, k) || canCrossDfs(stones, map, dp, f3, k - 1);
            dp[start].put(k, res);
            return res;
        }
    }
}
```

## 354 Russian Doll Envelopes

```java
// 354ms
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
            public int compare(int[] a1, int[] a2) {
                if (a1[0] == a2[0]) {
                    return a1[1] - a2[1];
                } else {
                    return a1[0] - a2[0];
                }
            }
        });
        int[] dp = new int[envelopes.length];
        int max = 0;
        for (int i = 1; i < envelopes.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max + 1;
    }
}
// 可以把复杂度从n^2降低为nlong(n)
public class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }
}
```

## 552 Student Attendance Record II

```java
// 545ms
class Solution {
    static int MOD = 1_000_000_000 + 7;
    public int checkRecord(int n) {
        int[][][] dp = new int[n+1][2][3];
        dp[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            // A L P
            dp[i][0][0] = ((dp[i-1][0][1] % MOD + dp[i-1][0][2] % MOD) % MOD + dp[i-1][0][0] % MOD) % MOD;
            dp[i][1][0] = (((((dp[i-1][0][0] % MOD + dp[i-1][0][1] % MOD) % MOD + dp[i-1][0][2] % MOD) % MOD + dp[i-1][1][0] % MOD) % MOD + dp[i-1][1][1] % MOD) % MOD + dp[i-1][1][2] % MOD) % MOD; 
            dp[i][0][1] = dp[i-1][0][0] % MOD;
            dp[i][0][2] = dp[i-1][0][1] % MOD;
            dp[i][1][1] = dp[i-1][1][0] % MOD;
            dp[i][1][2] = dp[i-1][1][1] % MOD;
        }
        int ans = (((((dp[n][0][0] % MOD + dp[n][1][0] % MOD) % MOD + dp[n][0][1] % MOD) % MOD + dp[n][0][2] % MOD) % MOD + dp[n][1][1] % MOD) % MOD + dp[n][1][2] % MOD) % MOD;
        return ans;
    }
}
```

## 466 Count The Repetitions

```java
class Solution {
    public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
            char[] s1c = s1.toCharArray();
            char[] s2c = s2.toCharArray();
            int m = s1c.length, n = s2c.length;
            int[] repeatCount = new int[n+1];
            int[] nextIndex = new int[n+1];
            int j = 0, cnt = 0;
            for (int k = 1; k <= n1; k++) {
                for (int i = 0; i < m; i++) {
                    if (s1c[i] == s2c[j]) {
                        j++;
                        if (j == n) {
                            cnt++;
                            j = 0;
                        }
                    }
                }
                repeatCount[k] = cnt;  // record the k-th repeatCount and nextIndex
                nextIndex[k] = j;
                for (int start = 0; start < k; start++) {
                    // see if you have met this nextIndex before
                    // if found, you can calculate the 3 parts
                    if (nextIndex[start] == j) {  
                        // prefixCount is the start-th repeatCount
                        int prefixCount = repeatCount[start];

                        // (repeatCount[k] - repeatCount[start]) is the repeatCount of one occurrance of the pattern
                        // There are (n1 - start) / (k - start) occurrances of the pattern
                        // So (repeatCount[k] - repeatCount[start]) * ((n1 - start) / (k - start))
                        // is the repeatCount of the repetitive part
                        int patternCount = (repeatCount[k] - repeatCount[start]) * ((n1 - start) / (k - start));

                        // The suffix contains the incomplete repetitive remnant (if any)
                        // Its length is (n1 - start) % (k - start)
                        // So the suffix repeatCount should be 
                        // repeatCount[start + (n1 - start) % (k - start)] - repeatCount[start]
                        int suffixCount = repeatCount[start + (n1-start)%(k-start)] - repeatCount[start];
                        return (prefixCount+patternCount+suffixCount) / n2;
                    }
                }
            }

            // when apply n1 times s1 can make a s2  (有疑问的这里)
            return repeatCount[n1] / n2;
        }
}
```

## 321 Create Maximum Number

```java
class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = nums1.length;
        int n = nums2.length;
        int[] ans = new int[k];
        for (int i = Math.max(0, k - n); i <= m && i <= k; i++) {
            int[] tmp = merge(maxNumber(nums1, i), maxNumber(nums2, k - i), k);
            if (greater(tmp, 0, ans, 0)) ans = tmp;
        }
        return ans;
    }
    
    private int[] merge(int[] nums1, int[] nums2, int k) {
        int[] ans = new int[k];
        int index = 0;
        for (int i = 0, j = 0; index < k; ) {
            ans[index++] = greater(nums1, i, nums2, j) ? nums1[i++] : nums2[j++];
        }
        return ans;
    }
    
    private boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;j++;
        }
        return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
    }
    
    private int[] maxNumber(int[] nums, int k) {
        if (k > nums.length) return null;
        int n = nums.length;
        int[] ans = new int[k];
        for (int i = 0, j = 0; i < nums.length; i++) {
            while (j + n - i > k && j > 0 && ans[j-1] < nums[i]) j--;
            if (j < k) ans[j++] = nums[i];
        }
        return ans;
    }
}
```

## 132 Palindrome Partitioning II

```java
// TLE
class Solution {
    public int minCut(String s) {
        int[][] dp = new int[s.length()][s.length()];
        for (int[] sub : dp) {
            Arrays.fill(sub, Integer.MAX_VALUE);
        }
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = 0;
        }
        for (int len = 1; len < s.length(); len++) {
            for (int i = 0; i + len < s.length(); i++) {
                int j = i + len;
                if (isPalindrome(s.substring(i, j+1))) {
                    dp[i][j] = 0;
                } else {
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + 1);
                    }
                }
            }
        }
        // for (int[] sub : dp) {
        //     System.out.println(Arrays.toString(sub));
        // }
        return dp[0][s.length()-1];
    }
    
    private boolean isPalindrome(String s) {
        int l = 0, h = s.length() - 1;
        while (l <= h) {
            if (s.charAt(l++) != s.charAt(h--)) return false;
        }
        return true;
    }
}
// dp[i][j] = Math.min(dp[i][p] + dp[p+1][j] + 1, dp[i][j]), 当然还要判断dp[i][j]是否是回文字符串
// 区间形式，从len = 1 逐渐变大，最后返回dp[0][s.length()-1]
// 正常的递推式应该是这样。

// AC solution
class Solution {
    public int minCut(String s) {
        int n = s.length();
        int[] dp = new int[n+1];
        for (int i = 0; i < dp.length; i++) dp[i] = i-1;
        for (int i = 0; i < s.length(); i++) {
            for (int j = 0; i - j >= 0 && i + j < n && s.charAt(i-j) == s.charAt(i+j); j++) {
                dp[i+j+1] = Math.min(dp[i+j+1], 1 + dp[i-j]);
            }
            
            for (int j = 1; i - j + 1 >= 0 && i + j < n && s.charAt(i-j+1) == s.charAt(i+j); j++) {
                dp[i+j+1] = Math.min(dp[i+j+1], 1 + dp[i-j+1]);
            }
        }
        return dp[s.length()];
    }
}
```

## 639 Decode Ways II

```java
class Solution {
    int M = 1000000007;
    public int numDecodings(String s) {
        if (s.length() == 0) return 0;
        long[] dp = new long[s.length()+1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '*' ? 9 : (s.charAt(0) == '0' ? 0 : 1);
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                dp[i+1] = (9 * dp[i]) % M;
                if (s.charAt(i-1) == '1') {
                    dp[i+1] = (dp[i+1] + 9 * dp[i-1]) % M;
                } else if (s.charAt(i-1) == '2') {
                    dp[i+1] = (dp[i+1] + 6 * dp[i-1]) % M;
                } else if (s.charAt(i-1) == '*') {
                    dp[i+1] = (dp[i+1] + 15 * dp[i-1]) % M;
                }
            } else {
                dp[i+1] = s.charAt(i) != '0' ? dp[i] : 0;
                if (s.charAt(i-1) == '1') {
                    dp[i+1] = (dp[i+1] + dp[i-1]) % M;
                } else if (s.charAt(i-1) == '2' && s.charAt(i) <= '6') {
                    dp[i+1] = (dp[i+1] + dp[i-1]) % M;
                } else if (s.charAt(i-1) == '*') {
                    dp[i+1] = (dp[i+1] + (s.charAt(i) <= '6' ? 2 : 1) * dp[i-1]) % M;
                }
            }
        }
        return (int)dp[s.length()];
    }
}
```

## 140 Word Break II

```java
class Solution {
    public List<String> wordBreak(String s, List<String> wordDict) {
        return dfs(s, wordDict, new HashMap<>());
    }
    
    private List<String> dfs(String s, List<String> wordDict, Map<String, List<String>> map) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        
        List<String> res = new ArrayList<>();
        if (s.isEmpty()) {
            res.add("");
            return res;
        }
        for (String str : wordDict) {
            if (s.startsWith(str)) {
                List<String> subList = dfs(s.substring(str.length()), wordDict, map);
                for (String word : subList) {
                    res.add(str + (word.isEmpty() ? "" : " ") + word);
                }
            }
        }
        map.put(s, res);
        return res;
    }
}
```

## 174 Dungeon Game

```java
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int M = dungeon.length;
        int N = dungeon[0].length;
        int[][] dp = new int[M+1][N+1];
        for (int[] sub : dp) Arrays.fill(sub, Integer.MAX_VALUE);
        dp[M-1][N] = 1; dp[M][N-1] = 1;
        for (int i = M - 1; i >= 0; i--) {
            for (int j = N - 1; j >= 0; j--) {
                dp[i][j] = Math.min(aux(dungeon[i][j], dp[i+1][j]), aux(dungeon[i][j], dp[i][j+1]));
            }
        }
        return dp[0][0];
    }
    
    private int aux(int value, int preResult) {
        if (preResult == Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        } else if (value == 0) {
            return preResult;
        } else if (value > 0 && value >= preResult) {
            return 1;
        } else {
            return preResult - value; 
        }
    }
}
```

## 787 Cheapst Flights Within K Stops

```java
class Solution {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        int[][] dp = new int[n][K+1];
        for (int[] sub : dp) Arrays.fill(sub, Integer.MAX_VALUE);
        List[] map = (List<Integer>[] )new List[n];
        // Arrays.fill(map, new ArrayList<>());
        for (int i = 0; i < n; i++) {
            map[i] = new ArrayList<>();
        }
        for (int i = 0; i < flights.length; i++) {
            map[flights[i][0]].add(i);
        }
        Queue<Integer> q = new LinkedList<>();
        q.offer(src);
        dp[src][0] = 0;
        int k = 0;
        while (!q.isEmpty() && k <= K) {
            int N = q.size();
            for (int i = 0; i < N; i++) {
                List<Integer> dsts = map[q.poll()];
                for (int index : dsts) {
                    int[] flight = flights[index];
                    int prev = dp[flight[1]][k];
                    dp[flight[1]][k] = Math.min((k > 0 ? dp[flight[0]][k-1] : 0) + flight[2], dp[flight[1]][k]);
                    if (prev != dp[flight[1]][k]) q.offer(flight[1]);
                }
            }
            k++;
        }
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= K; i++) ans = Math.min(ans, dp[dst][i]);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
```