# Array

## 561 Array Partition I

```java
class Solution {
    public int arrayPairSum(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = 0; i < nums.length; i += 2) {
            res += nums[i];
        }
        return res;
    }
}
```

## 566 Reshape the Matrix

```java
class Solution {
    public int[][] matrixReshape(int[][] nums, int r, int c) {
        int m = nums.length, n = nums[0].length;
        if (m * n != r * c) return nums;
        int[][] res = new int[r][c];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int index = i * n + j;
                res[index/c][index%c] = nums[i][j];
            }
        }
        return res;
    }
}
```

## 485 Max Consecutive Ones

```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int res = 0;
        int counter = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                res = Math.max(res, counter);
                counter = 0;
            } else {
                counter++;
            }
        }
        return Math.max(res, counter);
    }
}

// Another solution, interesting
class Solution {
  public int findMaxConsecutiveOnes(int[] nums) {
    int result = 0;
    int count = 0;
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == 1) {
        count++;
        res = Math.max(res, count);
      } else {
        count = 0;
      }
    }
    return res;
  }
}
```

## Max Area of Island

```java
class Solution {
    private final static int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    private int count = 0;
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j]) {
                    count = 0;
                    dfs(grid, i, j, visited);
                    res = Math.max(res, count);
                }
            }
        }
        return res;
    }
    
    private void dfs(int[][] grid, int x, int y, boolean[][] visited) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || visited[x][y] || grid[x][y] == 0) return;
        visited[x][y] = true;
        count++;
        for (int[] dir : dirs) {
            dfs(grid, x + dir[0], y + dir[1], visited);
        }
    }
}

// Another solution
// 可以用值把dfs的结果接着，然后返回回来。
class Solution {
    private final static int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
    private int count = 0;
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (!visited[i][j]) {
                    res = Math.max(res, dfs(grid, i, j, visited, 0));
                }
            }
        }
        return res;
    }
    
    private int dfs(int[][] grid, int x, int y, boolean[][] visited, int area) {
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || visited[x][y] || grid[x][y] == 0) return area;
        visited[x][y] = true;
        area++;
        area = dfs(grid, x + dirs[0][0], y + dirs[0][1], visited, area);
        area = dfs(grid, x + dirs[1][0], y + dirs[1][1], visited, area);
        area = dfs(grid, x + dirs[2][0], y + dirs[2][1], visited, area);
        area = dfs(grid, x + dirs[3][0], y + dirs[3][1], visited, area);
        return area;
    }
}
```

## 448 Find All Numbers Disappeared in an Array

```java
class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            nums[index] = -Math.abs(nums[index]);
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) res.add(i+1);
        }
        return res;
    }
}
```

## 283 Move Zeros

```java
class Solution {
    public void moveZeroes(int[] nums) {
        int ind = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[ind++] = nums[i];
            }
        }
        for (int i = ind; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}
```

## 697 Degree of an Array

```java
// 太暴力，看看有没有其它解决办法。
// 有意思，一直没尝试过Map<Integer, Integer[]>
// 或者还可以Map<Integer, int[]>呢
// 所以一个直接的优化就是：
// Map<Integer, int[]> int[] {nums, start, end}
class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        Map<Integer, Integer> start = new HashMap<>();
        Map<Integer, Integer> end = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (!start.containsKey(nums[i])) start.put(nums[i], i);
            end.put(nums[i], i);
        }
        int count = 0;
        int res = nums.length;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > count) {
                count = entry.getValue();
                res = end.get(entry.getKey()) - start.get(entry.getKey()) + 1;
            } else if (entry.getValue() == count) {
                res = Math.min(res, end.get(entry.getKey()) - start.get(entry.getKey()) + 1);
            }
        }
        return res;
    }
}
```

## 122 Best Time to Buy and Sell Stock II

```
class Solution {
    public int maxProfit(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] - prices[i-1] > 0) {
                res += prices[i] - prices[i-1];
            }
        }
        return res;
    }
}
```

## 167 Two Sum II - Input array is sorted

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int start = 0, end = numbers.length - 1;
        while (start < end) {
            int cmp = numbers[start] + numbers[end];
            if (cmp > target) {
                end--;
            } else if (cmp < target) {
                start++;
            } else {
                break;
            }
        }
        return new int[]{start + 1, end + 1};
    }
}
```

## 169 Majority Element

```java
class Solution {
    public int majorityElement(int[] nums) {
        int cmp = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == cmp) {
                count++;
            } else if (count == 0) {
                cmp = nums[i];
                count = 1;
            } else {
                count--;
            }
        }
        return cmp;
    }
}
```

## 217 Contains Duplicate

```java
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) return true;
        }
        return false;
    }
}

// A faster solution
class Solution {
    public boolean containsDuplicate(int[] nums) {
        int len = nums.length;
        if(len < 2){
            return false;
        }
        int max = nums[0];
        int min = nums[len - 1];
        for(int v : nums){
            if(v > max)
                max = v;
            else if(v < min) //else 小优化
                min = v;
        }
        boolean[] hash = new boolean[max - min + 1];
        for(int v : nums){
            if(hash[v - min]){
                return true;
            }
            else{
                hash[v - min] = true;
            }
        }
        return false;
    }
}
```

## 661 Image Smoother

```java
class Solution {
    public int[][] imageSmoother(int[][] M) {
        int[][] res = new int[M.length][M[0].length];
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[0].length; j++) {
                int sum = 0;
                int count = 0;
                for (int k = Math.max(0, i - 1); k < Math.min(i + 2, M.length); k++) {
                    for (int l = Math.max(0, j - 1); l < Math.min(j + 2, M[0].length); l++) {
                        sum += M[k][l];
                        count += 1;
                    }
                }
                res[i][j] = sum / count;
            }
        }
        return res;
    }
}
```

## 628 Maximum Product of Three Numbers

```java
class Solution {
    public int maximumProduct(int[] nums) {
        Integer p1 = null, p2 = null, p3 = null;
        Integer n1 = null, n2 = null;
        for (int i = 0; i < nums.length; i++) {
            if (p1 == null || nums[i] > p1) {
                p3 = p2;
                p2 = p1;
                p1 = nums[i];
            } else if (p2 == null || nums[i] > p2) {
                p3 = p2;
                p2 = nums[i];
            } else if (p3 == null || nums[i] > p3) {
                p3 = nums[i];
            }
            if (nums[i] < 0) {
                if (n1 == null || nums[i] < n1) {
                    n2 = n1;
                    n1 = nums[i];
                } else if (n2 == null || nums[i] < n2) {
                    n2 = nums[i];
                }
            }
        }
        if (n1 != null && n2 != null) {
            return Math.max(n1 * n2 * p1, p1 * p2 * p3);
        } else {
            return p1 * p2 * p3;
        }
    }
}
```

## 268 Missing Number

```java
class Solution {
    public int missingNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res ^ i ^ nums[i];
        }
        return res ^ nums.length;
    }
}
```

## 674 Longest Continuous Increasing Subsequence

```java
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums.length == 0) return 0;
        int res = 1;
        int count = 1;
        int pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > pre) count++;
            else count = 1;
            res = Math.max(res, count);
            pre = nums[i];
        }
        return res;
    }
}
```

## Best Time to Buy and Sell Stock

```java
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        int profit = 0;
        int minSofar = prices[0];
        for (int i = 1; i < prices.length; i++) {
            minSofar = Math.min(minSofar, prices[i]);
            if (prices[i] > minSofar) {
                profit = Math.max(profit, prices[i] - minSofar);
            }
        }
        return profit;
    }
}
```

## Search Insert Position

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return lo;
    }
}
```

## 53 Maximum Subarray

```java
class Solution {
    public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        int maxendinghere = nums[0];
        int maxsofar = nums[0];
        for (int i = 1; i < nums.length; i++) {
            maxendinghere = Math.max(maxendinghere + nums[i], nums[i]);
            maxsofar = Math.max(maxsofar, maxendinghere);
        }
        return maxsofar;
    }
}
```

## 27 Remove Element

```java
class Solution {
    public int removeElement(int[] nums, int val) {
        int ind = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[ind++] = nums[i];
            }
        }
        return ind;
    }
}
```

## 118 Pascal's Triangle

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        if (numRows < 1) return res;
        for (int i = 1; i <= numRows; i++) {
            if (i == 1) {
                List<Integer> sub = new ArrayList<>();
                sub.add(1);
                res.add(sub);
            } else {
                List<Integer> sub = new ArrayList<>(i);
                sub.add(1);
                for (int j = 1; j < i-1; j++) {
                    List<Integer> last = res.get(res.size()-1);
                    sub.add(last.get(j) + last.get(j-1));
                }
                sub.add(1);
                res.add(sub);
            }
        }
        return res;
    }
}
```

## 66 Plus One

```java
class Solution {
    public int[] plusOne(int[] digits) {
        boolean flag = false;
        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 9) {
                flag = true;
                break;
            }
        }
        int[] res = null;
        if (flag) {
            res = new int[digits.length];
            int ind = digits.length - 1;
            int acc = 1;
            while (ind >= 0) {
                if (acc == 0) {
                    res[ind] = digits[ind];
                } else {
                    res[ind] = (digits[ind] + acc) % 10;
                    acc = (digits[ind] + acc) / 10;
                }
                ind--;
            }
            return res;
        } else {
            res = new int[digits.length + 1];
            res[0] = 1;
            return res;
        }
    }
}
```

## 643 Maximum Average Subarray I

```java
class Solution {
    public double findMaxAverage(int[] nums, int k) {
        double sum = 0.0f;
        for (int i = 0; i < k; i++) {
            sum += nums[i];
        }
        double res = sum;
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i-k] + nums[i];
            res = Math.max(res, sum);
        }
        return res / k;
    }
}
```

## 119 Pascal's Triangle II

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        int[] sub = new int[rowIndex + 1];
        for (int i = 0; i <= rowIndex; i++) {
            if (i == 0) {
                sub[0] = 1;
            } else {
                for (int j = i-1; j >= 1; j--) {
                    sub[j] = sub[j-1] + sub[j];
                }
                sub[i] = 1;
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < sub.length; i++) {
            res.add(sub[i]);
        }
        return res;
    }
}
```

## 1 Two Sum

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int p1 = -1, p2 = -1;
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                p1 = map.get(target - nums[i]);
                p2 = i;
                break;
            }
            map.put(nums[i], i);
        }
        return new int[]{p1, p2};
    }
}
```

## Remove Duplicates from Sorted Array

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 1) return nums.length;
        int ind = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) nums[ind++] = nums[i];
        }
        return ind;
    }
}
```

## 219 Contains Duplicate II

```java
class Solution {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                if (i - map.get(nums[i]) <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
}
```

## Merge Sorted Array

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int ind = m + n - 1;
        while (m > 0 || n > 0) {
            if (m <= 0) {
                nums1[ind--] = nums2[--n];
            } else if (n <= 0) {
                break;
            } else {
                if (nums1[m-1] < nums2[n-1]) {
                    nums1[ind--] = nums2[--n];
                } else {
                    nums1[ind--] = nums1[--m];
                }
            }
        }
    }
}

// another simple solution

class Solution {
    public void merge(int[] A, int m, int[] B, int n) {
        int i = m-1, j = n-1, k = m + n - 1;
        while (i > -1 && j > -1) A[k--] = (A[i] > B[j]) ? A[i--] : B[j--];
        while (j > -1) A[k--] = B[j--];
    }
}
```

## Can Place Flowers

```java
class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length;) {
            if (flowerbed[i] == 0) {
                if ((i == 0 || flowerbed[i-1] == 0) && (i == flowerbed.length-1 || flowerbed[i+1] == 0)) {
                    n--;
                    i += 2;
                    continue;
                }
            }
            i += 1;
        }
        return n <= 0;
    }
}
```

## 581 Shortest Unsorted Contious Subarray

```java
class Solution {
    public int findUnsortedSubarray(int[] nums) {
        int min = Integer.MAX_VALUE;
        int start = -1, end = -2;
        for (int i = nums.length - 1; i >= 0; i--) {
            min = Math.min(min, nums[i]);
            if (nums[i] > min) {
                start = i;
            }
        }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            if (nums[i] < max) {
                end = i;
            }
        }
        return end - start + 1;
    }
}
```

## 532 K-diff Pairs in an Array

```java
class Solution {
    public int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        int res = 0;
        int p1 = 0, p2 = 1;
        while (p2 < nums.length) {
            if (nums[p2] - nums[p1] < k) {
                p2++;
            } else if (nums[p2] - nums[p1] == k) {
                res++;
                // handle p1, p2
                p1++;p2++;
                while (p1 < nums.length && nums[p1] == nums[p1-1]) {
                    p1++;
                }
                if (p2 <= p1) p2 = p1 + 1;
            } else {
                if (p1 + 1 < p2) p1++;
                else p2++;
            }
        }
        return res;
    }
}
```

## 414 Third Maximum Number

```java
class Solution {
    public int thirdMax(int[] nums) {
        Integer p1 = null, p2 = null, p3 = null;
        for (int i = 0; i < nums.length; i++) {
            if (p1 != null && nums[i] == p1) continue;
            if (p2 != null && nums[i] == p2) continue;
            if (p3 != null && nums[i] == p3) continue;
            if (p1 == null || nums[i] > p1) {
                p3 = p2;
                p2 = p1;
                p1 = nums[i];
            } else if (p2 == null || nums[i] > p2) {
                p3 = p2;
                p2 = nums[i];
            } else if (p3 == null || nums[i] > p3) {
                p3 = nums[i];
            }
        }
        if (p3 != null) return p3;
        else return p1;
    }
}
```

## Rotate Array

```java
class Solution {
    public void rotate(int[] nums, int k) {
        int p = k % nums.length;
        reverse(nums, 0, p);
        reverse(nums, p+1, nums.length-1);
        reverse(nums, 0, nums.length-1);
    }
    private void reverse(int[] nums, int start, int end) {
        while (start <= end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;end--;
        }
    }
}
```

## 189 Rotate Array

```java
class Solution {
    public void rotate(int[] nums, int k) {
        int p = k % nums.length;
        if (p == 0) return;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, p - 1);
        reverse(nums, p, nums.length-1);
    } 
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start] = nums[end];
            nums[end] = tmp;
            start++;end--;
        }
    }
}
```

## 442 Find All Duplicates in an Array

```java
class Solution {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            int ind = Math.abs(nums[i]) - 1;
            if (nums[ind] < 0) {
                res.add(Math.abs(nums[i]));
            }
            nums[ind] = -nums[ind];
        }
        return res;
    }
}
```

## 495 Teemo Attacking

```java
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries.length == 0) return 0;
        int pre = timeSeries[0];
        int res = 0;
        for (int i = 0; i < timeSeries.length; i++) {
            res += Math.min(timeSeries[i]-pre, duration);
            pre = timeSeries[i];
        }
        res += duration;
        return res;
    }
}
```

## 667 Beautiful Arrangement II

```java
class Solution {
    public int[] constructArray(int n, int k) {
        int[] res = new int[n];
        for (int i = 0, l = 1, r = n; l <= r; i++) {
            res[i] = k > 1 ? (k-- % 2 != 0 ? l++ : r--) : l++;
        }
        return res;
    }
}
```

## 238 Product of Array Except Self

```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            res[i] = i == nums.length - 1 ? 1 : res[i+1] * nums[i+1];
        }
        int pred = 1;
        for (int i = 0; i < res.length; i++) {
            pred = i == 0 ? 1 : pred * nums[i-1];
            res[i] = pred * res[i];
        }
        return res;
    }
}
```

## 216 Combination Sum III

```java
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(res, new ArrayList<>(), k, n, 1);
        return res;
    } 
    private void backtrack(List<List<Integer>> res, List<Integer> sub, int k, int n, int start) {
        if (sub.size() == k) {
            if (n == 0) {
                res.add(new ArrayList<>(sub));
            }
            return;
        }
        for (int i = start; i < 10; i++) {
            sub.add(i);
            backtrack(res, sub, k, n - i, i + 1);
            sub.remove(sub.size()-1);
        }
    }
}
```

## 560 Subarray Sum Equals K

```java
class Solution {
    public int subarraySum(int[] nums, int k) {
        int pre = 0;
        int count = 0;
        Map<Integer, Integer> preSum = new HashMap<>();
        preSum.put(0, 1);
        for (int i = 0; i < nums.length; i++) {
            pre += nums[i];
            if (preSum.containsKey(pre - k)) {
                count += preSum.get(pre - k);
            }
            preSum.put(pre, preSum.getOrDefault(pre, 0)+1);
        }
        return count;
    }
}
```

## 152 Maximum Product Subarray

```java
class Solution {
    public int maxProduct(int[] nums) {
        int max = nums[0];
        int maxP = 1;
        int maxN = 1;
        boolean flag = true;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                maxN = 1;
                maxP = 1;
                max = Math.max(0, max);
                flag = true;
                continue;
            } else {
                maxP *= nums[i];
                maxN *= nums[i];
                if (nums[i] < 0 && flag) {
                    maxN = 1;
                    flag = false;
                    max = Math.max(max, maxP);
                } else {
                    max = Math.max(max, Math.max(maxP, maxN));
                }
            }
        }
        return max;
    }
}
```

## 287 Find the Duplicate Number

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        int slow = n;
        int fast = n;
        do {
            slow = nums[slow-1];
            fast = nums[nums[fast-1]-1];
        } while (slow != fast);
        slow = n;
        while (slow != fast) {
            slow = nums[slow-1];
            fast = nums[fast-1];
        }
        return slow;
    }
}
```

## 621 Task Scheduler

```java
class Solution {
    public int leastInterval(char[] tasks, int n) {
        int[] letters = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            letters[tasks[i]-'A']++;
        }
        Arrays.sort(letters);
        int leftSlot = (letters[25] - 1) * n;
        for (int i = 24; i >= 0; i--) {
            if (letters[i] != 0) {
                leftSlot -= Math.min(letters[25]-1, letters[i]);
            }
        }
        return leftSlot < 0 ? tasks.length : leftSlot + tasks.length;
    }
}
```

## 78 Subsets

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }
    private void backtrack(int[] nums, int start, List<Integer> sub, List<List<Integer>> res) {
        res.add(new ArrayList<>(sub));
        for (int i = start; i < nums.length; i++) {
            sub.add(nums[i]);
            backtrack(nums, i+1, sub, res);
            sub.remove(sub.size()-1);
        }
    }
}
```

## 611 Valid Triangle Number

```java
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int count = 0;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                count += upper_bound(nums, j + 1, nums[i] + nums[j] - 1) - (j + 1);
            }
        }
        return count;
    }
    private int upper_bound(int[] nums, int start, int key) {
        int first = start, len = nums.length - start;
        int half, middle;

        while (len > 0) {
            half = len >> 1;
            middle = first + half;
            if (nums[middle] > key) {
                len = half;
            } else {
                first = middle + 1;
                len = len - half - 1;
            }
        }
        return first;
    }
}

// better solution
// 怎么说呢？我的上面解法是定住两个较小点，更优的解法应该是定住最大点就行了。
// 这样子效率更优。
class Solution {
    public int triangleNumber(int[] nums) {
        Arrays.sort(nums);
        int res = 0;
        for (int i = nums.length - 1; i >= 2; i--) {
            int l = 0, r = i - 1;
            while (l < r) {
                if (nums[l] + nums[r] > nums[i]) {
                    res += r - l;
                    r--;
                } else {
                    l++;
                }
            }
        }
        return res;
    }
}
```

## 62 Unique Paths

```java
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 0; j < n; j++) dp[0][j] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j-1] + dp[i-1][j];
            }
        }
        return dp[m-1][n-1];
    }
}
```

## 153 Find Minimum in Rotated Sorted Array

```java
class Solution {
    public int findMin(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        int min = Integer.MAX_VALUE;
        while (lo <= hi) {
            if (nums[lo] <= nums[hi]) {
                min = Math.min(min, nums[lo]);
                break;
            }
            int mid = lo + (hi - lo) / 2;
            if (nums[lo] <= nums[mid]) {
                min = Math.min(nums[lo], min);
                lo = mid + 1;
            } else if (nums[mid] <= nums[hi]) {
                min = Math.min(nums[mid], min);
                hi = mid - 1;
            }
        }
        return min;
    }
}
```

## 15 3Sum

```java
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length <= 2) return res;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            if (i < nums.length - 1 && nums[i] == nums[i+1]) continue;
            int l = 0, r = i - 1;
            int target = -nums[i];
            while (l < r) {
                if (nums[l] + nums[r] == target) {
                    res.add(Arrays.asList(new Integer[]{nums[l], nums[r], nums[i]}));
                    l++; r--;
                    while (l < r && nums[l] == nums[l-1]) l++;
                    while (r > l && nums[r] == nums[r+1]) r--;
                } else if (nums[l] + nums[r] < target) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
}
```

## 16 3Sum Cloest

```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int min = Integer.MAX_VALUE;
        int res = Integer.MIN_VALUE;
        Arrays.sort(nums);
        for (int i = nums.length - 1; i >= 2; i--) {
            if (i < nums.length - 1 && nums[i] == nums[i+1]) continue;
            int l = 0, r = i - 1;
            int key = target - nums[i];
            while (l < r) {
                if (Math.abs(nums[l] + nums[r] + nums[i] - target) < min) {
                    min = Math.abs(nums[l] + nums[r] + nums[i] - target);
                    res = nums[i] + nums[l] + nums[r];
                }
                if (nums[l] + nums[r] == key) {
                    return target;
                } else if (nums[l] + nums[r] < key) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return res;
    }
}
```

## 4Sum

```java
class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length <= 3) return res;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i > 0 && nums[i] == nums[i-1]) continue;
            for (int j = i+1; j < nums.length - 2; j++) {
                if (j > i+1 && nums[j] == nums[j-1]) continue;
                int l = j + 1, r = nums.length - 1;
                int key = target - (nums[i] + nums[j]);
                while (l < r) {
                    if (nums[l] + nums[r] == key) {
                        res.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        l++;r--;
                        while (l < r && nums[l] == nums[l-1]) l++;
                        while (l < r && nums[r] == nums[r+1]) r--;
                    } else if (nums[l] + nums[r] < key) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }
        return res;
    }
}
```

## 59 Spiral Matrix II

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int x = 0, y = 0;
        int dx = 0, dy = 1;
        int ind = 1;
        while (ind <= n * n) {
            res[x][y] = ind++;
            if (x + dx == n || y + dy == n || y + dy == -1 || res[x+dx][y+dy] != 0) {
                int swap = dx;
                dx = dy;
                dy = -swap;
            }
            x += dx;
            y += dy;
        }
        return res;
    }
}
```

## 39 Combination Sum

```java
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>(), res);
        return res;
    }
    private void backtrack(int[] candidates, int target, int start, List<Integer> sub, List<List<Integer>> res) {
        if (target < 0) return;
        else if (target == 0) {
            res.add(new ArrayList<>(sub));
            return;
        } else {
            for (int i = start; i < candidates.length; i++) {
                sub.add(candidates[i]);
                backtrack(candidates, target - candidates[i], i, sub, res);
                sub.remove(sub.size()-1);
            }
        }
    }
}
```

## 48 Rotate Image

```java
class Solution {
    public void rotate(int[][] matrix) {
        int N = matrix.length;
        for (int level = 0; level < N / 2; level++) {
            for (int i = level; i < N - level - 1; i++) {
                int swap = matrix[level][i];
                matrix[level][i] = matrix[N-i-1][level];
                matrix[N-i-1][level] = matrix[N-level-1][N-i-1];
                matrix[N-level-1][N-i-1] = matrix[i][N-level-1];
                matrix[i][N-level-1] = swap;
            }
        }
    }
}
```

## 380 Insert Delete GetRandom O(1)

```java
class RandomizedSet {
    Map<Integer, Integer> map = null;
    List<Integer> list = null;
    Random random = new Random();

    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        list.add(val);
        map.put(val, list.size()-1);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val);
        if (index < list.size() - 1) {
            list.set(index, list.get(list.size()-1));
            list.set(list.size()-1, val);
            map.put(list.get(index), index);
        }
        list.remove(list.size()-1);
        map.remove(val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int rind = random.nextInt(list.size());
        return list.get(rind);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
```

## 64 Minimum Path Sum

```java
class Solution {
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                int up = i > 0 ? dp[i-1][j] : Integer.MAX_VALUE;
                int left = j > 0 ? dp[i][j-1] : Integer.MAX_VALUE;
                if (up == Integer.MAX_VALUE && left == Integer.MAX_VALUE) {
                    dp[i][j] = grid[i][j];
                } else {
                    dp[i][j] = Math.min(up, left) + grid[i][j];
                }
            }
        }
        return dp[grid.length-1][grid[0].length-1];
    }
}
```

## 75 Sort Colors

```java
class Solution {
    public void sortColors(int[] nums) {
        int zero = 0, one = 0, two = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) zero++;
            else if (nums[i] == 1) one++;
            else if (nums[i] == 2) two++;
        }
        for (int i = 0; i < zero; i++) {
            nums[i] = 0;
        }
        for (int i = zero; i < zero + one; i++) {
            nums[i] = 1;
        }
        for (int i = zero + one; i < zero + one + two; i++) {
            nums[i] = 2;
        }
    }
}

// another solution
class Solution {
    public void sortColors(int[] nums) {
        int begin = 0, last = nums.length - 1;
        int ind = 0;
        while (ind <= last) {
            if (nums[ind] == 0) swap(nums, ind++, begin++);
            else if (nums[ind] == 2) swap(nums, ind, last--);
            else ind++;
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

## 670 Maximum Swap

```java
class Solution {
    public int maximumSwap(int num) {
        char[] nums = Integer.toString(num).toCharArray();
        for (int i = 0; i < nums.length - 1; i++) {
            int ind = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] >= nums[ind] && nums[j] != nums[i]) {
                    ind = j;
                }
            }
            if (ind != i) {
                swap(nums, i, ind);
                break;
            }
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res * 10 + nums[i] - '0';
        }
        return res;
    }
    
    private void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}

// 对于这一题来说，由于n的大小很小，所以不用担心两层循环
// 因此启发就是很多问题可以先尝试出暴力的方法也未尝不可，
// 当然这一题还有O(n)的解法，这里写一下。
class Solution {
    public int maximumSwap(int num) {
        char[] nums = Integer.toString(num).toCharArray();
        int[] dp = new int[nums.length];
        int cur = nums.length - 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] > nums[cur]) {
                cur = i;
            }
            dp[i] = cur;
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[dp[i]] != nums[i]) {
                swap(nums, i, dp[i]);
                break;
            }
        }
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res = res * 10 + nums[i] - '0';
        }
        return res;
    }
    
    private void swap(char[] nums, int i, int j) {
        char temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
```

## 162 Find Peak Element

```java
class Solution {
    public int findPeakElement(int[] nums) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int right = mid + 1 < nums.length ? nums[mid+1] : Integer.MIN_VALUE;
            int left = mid - 1 > -1 ? nums[mid-1] : Integer.MIN_VALUE;
            if (nums[mid] >= left && nums[mid] >= right) {
                return mid;
            } else if (left > nums[mid]) {
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }
        return -1;
    }
}
```

## 209 Minimum Size Subarray Sum

```java
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        if (nums.length == 0) return 0;
        int p1 = 0, p2 = 1;
        int sum = nums[0];
        int count = Integer.MAX_VALUE;
        if (sum >= s) count = p2 - p1;
        while (true) {
            while (p2 < nums.length && sum < s) {
                sum += nums[p2++];
            }
            while (sum >= s) {
                count = Math.min(count, p2 - p1);
                sum -= nums[p1++];
            }
            if (p2 == nums.length) break;
        }
        return count == Integer.MAX_VALUE ? 0 : count;
    }
}

// more beautiful solution
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int ans = Integer.MAX_VALUE;
        int left = 0, sum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while (sum >= s) {
                ans = Math.min(ans, i+1 - left);
                sum -= nums[left++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}

// a nlog(n) solution, just for fun
// c++ solution
int minSubArrayLen(int s, vector<int>& nums) {
    int n = nums.size();
    if (n == 0) return 0;
    int ans = INT_MAX;
    vector<int> sums(n+1, 0);
    for (int i = 1; i <= n; i++) {
        sums[i] = sums[i-1] + nums[i-1];
    }
    for (int i = 1; i <= n; i++) {
        int to_find = s + sums[i-1];
        auto bound = lower_bound(sums.begin(), sums.end(), to_find);
        if (bound != sums.end()) {
            ans = min(ans, static_cast<int>(bound - (sums.begin() + i - 1)));
        }
    }
    return ans == INT_MAX ? 0 : ans;
}
```

## 289 Game of Life

```java
class Solution {
    public void gameOfLife(int[][] board) {
        // 0x01 means first time it is alive, and second time it is die
        // 0x00 means first time it is die, and second time it is die
        // 0x10 means first time it is die, and seconde time it is alive
        // 0x11 means first time it is alive, and second time it is alive
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                int alive = 0;
                for (int m = Math.max(0, i - 1); m < Math.min(board.length, i + 2); m++) {
                    for (int n = Math.max(0, j - 1); n < Math.min(board[0].length, j + 2); n++) {
                        if ((board[m][n] & 0x01)== 1) {
                            alive++;
                        }
                    }
                }
                if (board[i][j] == 1) {
                    if (alive == 3 || alive == 4) {
                        board[i][j] += 2;
                    }
                } else {
                    if (alive == 3) {
                        board[i][j] += 2;
                    }
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if ((board[i][j] & 0x2) == 2) {
                    board[i][j] = 1;
                } else {
                    board[i][j] = 0;
                }
            }
        }
    }
}
```

## 90 Subsets II

```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), res);
        return res;
    }
    
    private void backtrack(int[] nums, int start, List<Integer> sub, List<List<Integer>> res) {
        res.add(new ArrayList<>(sub));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i-1]) continue;
            sub.add(nums[i]);
            backtrack(nums, i + 1, sub, res);
            sub.remove(sub.size() - 1);
        }
    }
}
```

## 11 Container With Most Water

```java
class Solution {
    public int maxArea(int[] height) {
        int res = 0;
        int p1 = 0, p2 = height.length - 1;
        while (p1 < p2) {
            int temp = (p2 - p1) * Math.min(height[p1], height[p2]);
            res = Math.max(res, temp);
            if (height[p1] < height[p2]) {
                p1++;
            } else if (height[p1] > height[p2]) {
                p2--;
            } else {
                p1++;p2--;
            }
        }
        return res;
    }
}
```

## 80 Remove Duplicates from Sorted Array II

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;
        int ind = 1;
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i-1]) {
                count++;
                if (count <= 2) {
                    nums[ind++] = nums[i];
                }
            } else {
                count = 1;
                nums[ind++] = nums[i];
            }
        }
        return ind;
    }
}

// A better solution
class Solution {
    public int removeDuplicates(int[] nums) {
        int i = 0;
        for (int n : nums) {
            if (i < 2 || n > nums[i - 2]) {
                nums[i++] = n;
            }
        }
        return i;
    }
}
```

## 73 Set Matirx Zeros

```java
// 注意j是从1开始的
class Solution {
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int col0 = 1;
        for (int i = 0; i < m; i++) {
            if (matrix[i][0] == 0) col0 = 0;
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = matrix[0][j] = 0;
                }
            }
        }

        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 1; j--) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] = 0;
                }
            }
            if (col0 == 0) matrix[i][0] = 0;
        }
    }
}
```

## 713 Subarray Product Less Than K

```java
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int prod = 1, ans = 0, left = 0;
        for (int i = 0; i < nums.length; i++) {
            prod *= nums[i];
            while (prod >= k) prod /= nums[left++];
            ans += i - left + 1;
        }
        return ans;
    }
}
```

## 74 Search a 2D Matrix

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int n = matrix[0].length;
        int lo = 0, hi = matrix.length * matrix[0].length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (matrix[mid/n][mid%n] == target) {
                return true;
            } else if (matrix[mid/n][mid%n] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return false;
    }
}
```

## 40 Combination Sum II

```java
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }
    
    private void backtrack(int[] candidates, int start, int target, List<Integer> sub, List<List<Integer>> res) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            res.add(new ArrayList<>(sub));
        } else {
            for (int i = start; i < candidates.length; i++) {
                if (i > start && candidates[i] == candidates[i-1]) continue;
                sub.add(candidates[i]);
                backtrack(candidates, i+1, target - candidates[i], sub, res);
                sub.remove(sub.size()-1);
            }
        }
    }
}
```

## 120 Triangle

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int[] dp = new int[triangle.size()+1];
        dp[0] = Integer.MAX_VALUE;
        for (int i = 0; i < triangle.size(); i++) {
            List<Integer> sub = triangle.get(i);
            for (int j = sub.size(); j >= 1;j--) {
                int up = j == sub.size() ? Integer.MAX_VALUE : dp[j];
                int left = dp[j-1];
                if (up == Integer.MAX_VALUE && left == Integer.MAX_VALUE) {
                    dp[j] = sub.get(j-1);
                } else {
                    dp[j] = Math.min(left, up) + sub.get(j-1);
                }
            }
        }
        int res = dp[0];
        for (int i = 0; i < dp.length; i++) {
            res = Math.min(res, dp[i]);
        }
        return res;
    }
}
```

## 63 Unique Path II

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int[][] dp = new int[obstacleGrid.length+1][obstacleGrid[0].length+1];
        for (int i = 1; i <= obstacleGrid.length; i++) {
            for (int j = 1; j <= obstacleGrid[0].length; j++) {
                if (obstacleGrid[i-1][j-1] != 1) {
                    dp[1][1] = 1;
                    int left = i > 1 ? (obstacleGrid[i-2][j-1] == 1 ? 0 : dp[i-1][j]) : 0;
                    int up =  j > 1 ? (obstacleGrid[i-1][j-2] == 1 ? 0 : dp[i][j-1]) : 0;
                    dp[i][j] += left + up;
                }
            }
        }
        return dp[obstacleGrid.length][obstacleGrid[0].length];
    }
}
```

## 34 Search for a range

```java
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int p1 = -1, p2 = -1;
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                p1 = mid;
                p2 = mid;
                hi = mid - 1;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        lo = 0; hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                p2 = mid;
                lo = mid + 1;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        if (p1 != -1) {
            return new int[]{p1, p2};
        } else {
            return new int[]{-1, -1};
        }
    }
}
```

## 56 Merge Intervals

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
    public List<Interval> merge(List<Interval> intervals) {
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        int p1 = -1, p2 = -1;
        List<Interval> res = new LinkedList<>();
        if (intervals.size() == 0) return res;
        for (Interval item : intervals) {
            if (p1 == -1) {
                p1 = item.start;
                p2 = item.end;
            } else {
                if (p2 >= item.start) {
                    p2 = Math.max(p2, item.end);
                } else {
                    res.add(new Interval(p1, p2));
                    p1 = item.start;
                    p2 = item.end;
                }
            }
        }
        res.add(new Interval(p1, p2));
        return res;
    }
}
```

## 228 Summary Ranges

```java
class Solution {
    public List<String> summaryRanges(int[] nums) {
        int p1 = -1, p2 = -2;
        List<String> res = new ArrayList<>();
        if (nums.length == 0) return res;
        for (int i = 0; i < nums.length; i++) {
            if (p1 != -1) {
                if (nums[i] == p2) {
                    p2++;
                } else {
                    String ss = "" + p1;
                    if (p2 != p1 + 1) {
                        ss = ss + "->" + (p2-1);
                    }
                    res.add(ss);
                    p1 = nums[i];
                    p2 = p1 + 1;
                }
            } else {
                p1 = nums[i];
                p2 = nums[i] + 1;
            }
        }
        String ss = "" + p1;
        if (p2 != p1 + 1) ss = ss + "->" + (p2-1);
        res.add(ss);
        return res;
    }
}
```

## 55 Jump Game

```java
// more concise
class Solution {
    public boolean canJump(int[] nums) {
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i <= max) {
                max = Math.max(max, i + nums[i]);
            }
        }
        return max >= nums.length-1;
    }
}
```

## 79 Word Search

```java
class Solution {
    private static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (search(board, i, j, visited, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean search(char[][] board, int x, int y, boolean[][] visited, String word, int index) {
        if (index == word.length()) return true;
        if (x < 0 || y < 0 || x == board.length || y == board[0].length || visited[x][y]) {
            return false;
        }
        if (board[x][y] != word.charAt(index)) return false;
        visited[x][y] = true;
        boolean flag = false;
        for (int i = 0; i <dirs.length; i++) {
            flag |= search(board, x + dirs[i][0], y + dirs[i][1], visited, word, index + 1);
            if (flag) break;
        }
        visited[x][y] = false;
        return flag;
    }
}

// Another solution
class Solution {
        private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        boolean flag = false;
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (checkWord(board, i, j, word, 0)) {
                        flag = true;
                        break;
                    }
                }
            }
            if (flag) break;
        }
        return flag;
    }

    private boolean checkWord(char[][] board, int i, int j, String word, int ind) {
        if (!visited[i][j] && ind < word.length() && board[i][j] == word.charAt(ind)) {
            if (ind + 1 == word.length()) return true;
            visited[i][j] = true;
            boolean result = (i-1>=0 && checkWord(board, i-1, j, word, ind+1)) || (j-1>=0 && checkWord(board, i, j-1, word, ind+1)) || (i+1 < board.length && checkWord(board, i+1, j, word, ind+1)) || (j+1 < board[0].length && checkWord(board, i, j+1, word, ind+1));
            if (!result) visited[i][j] = false;
            return result;
        } else {
            return false;
        }
    }
}
```

## 31 Next Permutation

```java
class Solution {
    public void nextPermutation(int[] nums) {
        int p = nums.length - 1;
        int ind = nums.length - 2;
        while (ind >= 0) {
            if (nums[ind] < nums[ind+1]) {
                for (int i = nums.length - 1; i > ind; i--) {
                    if (nums[i] > nums[ind]) {
                        p = i;
                        break;
                    }
                }
                swap(nums, p, ind);
                break;
            }
            ind--;
        }
        reverse(nums, ind + 1, nums.length - 1);
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
}
```

# 33 Search in Rotated Sorted Array

```java
class Solution {
    public int search(int[] nums, int target) {
        int p = -1;
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            if (nums[lo] <= nums[hi]) {
                p = Math.max(p, binarysearch(nums, lo, hi, target));
                break;
            }
            
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == nums[hi]) {
                p = mid;
                break;
            } else if (nums[mid] < nums[hi]){
                p = Math.max(p, binarysearch(nums, mid, hi, target));
                hi = mid - 1;
            } else {
                p = Math.max(p, binarysearch(nums, lo, mid, target));
                lo = mid + 1;
            }
        }
        return p;
    }
    
    private int binarysearch(int[] nums, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}

// Another solution
// 一个很好的思路是找到最小值，前面的题目已经解决了这个问题。
// 找到了最小值，那么从某种程度上这个数组就是有序的，因此可以
// 尝试使用二分搜索解决问题。
```

## 81 Search in Rotated Array II

```java
class Solution {
    public boolean search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }
    
    private boolean search(int[] nums, int lo, int hi, int target) {
        if (lo <= hi) {
            if (nums[lo] < nums[hi]) {
                return binarysearch(nums, lo, hi, target) != -1;
            }
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return true;
            } else if (nums[mid] < nums[hi]) {
                if (binarysearch(nums, mid, hi, target) != -1) return true;
                return search(nums, lo, mid - 1, target);
            } else if (nums[mid] > nums[lo]) {
                if (binarysearch(nums, lo, mid, target) != -1) return true;
                return search(nums, mid + 1, hi, target);
            } else {
                return search(nums, lo, mid -1, target) || search(nums, mid + 1, hi, target);
            }
        } else {
            return false;
        }
    }
    
    private int binarysearch(int[] nums, int lo, int hi, int target) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}

// another solution
class Solution {
    public boolean search(int[] nums, int target) {
        if (nums.length == 0) return false;
        int lo = 0, hi = nums.length - 1;
        int mid = 0; // Initialize
        
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] == target) return true;
            if (nums[mid] > nums[hi]) {
                if (nums[mid] > target && nums[lo] <= target) hi = mid;
                else lo = mid + 1;
            } else if (nums[mid] < nums[hi]) {
                if (nums[mid] < target && nums[hi] >= target) lo = mid + 1;
                else hi = mid;
            } else {
                hi--;
            }
        }
        return nums[lo] == target;
    }
}
```

## 54 Spiral Matrix

```java
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0 || matrix[0].length == 0) return res;
        int m = matrix.length, n = matrix[0].length;
        boolean[][] visited = new boolean[m][n];
        int i = 0, j = 0;
        int di = 0, dj = 1;
        int ind = 0;
        while (ind < m * n) {
            res.add(matrix[i][j]);
            visited[i][j] = true;
            if (j + dj == n || i + di == m || j + dj == -1 || visited[i+di][j+dj]) {
                int swap = di;
                di = dj;
                dj = -swap;
            }
            i += di;
            j += dj;
            ind++;
        }
        return res;
    }
}
```

## 229 Majority Element II

```java
class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> res = new ArrayList<>();
        if (nums.length == 0) return res;
        int c1 = 0, c2 = 0, n1 = nums[0], n2 = nums[0];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == n1) {
                c1++;
            } else if(nums[i] == n2) {
                c2++;
            } else if (c1 == 0) {
                n1 = nums[i];
                c1 = 1;
            } else if (c2 == 0) {
                n2 = nums[i];
                c2 = 1;
            } else {
                c1--;
                c2--;
            }
        }
        c1 = 0; c2 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == n1) {
                c1++;
            } else if (nums[i] == n2) {
                c2++;
            }
        }
        if (c1 > nums.length / 3) res.add(n1);
        if (c2 > nums.length / 3) res.add(n2);
        return res;
    }
}
```

## 714 Best Time to Buy and Sell Stock with Transaction Fee

```java
class Solution {
    public int maxProfit(int[] prices, int fee) {
        int s0 = 0, s1 = Integer.MIN_VALUE;
        for (int p : prices) {
            int temp = s0;
            s0 = Math.max(s0, s1 + p);
            s1 = Math.max(s1, temp - p - fee);
        }
        return s0;
    }
}
```

## 689 Maximum Sum of 3 Non-Overlapping Subarrays

```java
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int[] sums = new int[nums.length - k + 1];
        int sum = 0;
        for (int i = 0;i < k; i++) {
            sum += nums[i];
        }
        sums[0] = sum;
        for (int i = k; i < nums.length; i++) {
            sum = sum - nums[i-k] + nums[i];
            sums[i-k+1] = sum;
        }
        int[][] dp = new int[sums.length][4];
        for (int i = 0; i < sums.length; i++) {
            dp[i][1] = Integer.MIN_VALUE;
            dp[i][2] = Integer.MIN_VALUE;
            dp[i][3] = Integer.MIN_VALUE;
        }
        for (int i = 0; i < sums.length; i++) {
            dp[i][1] = Math.max(sums[i], i > 0 ? dp[i-1][1] : Integer.MIN_VALUE);
            dp[i][2] = Math.max(i>=k ? dp[i-k][1] + sums[i] : Integer.MIN_VALUE, i > 0 ? dp[i-1][2] : Integer.MIN_VALUE);
            dp[i][3] = Math.max(i>=k ? dp[i-k][2] + sums[i] : Integer.MIN_VALUE, i > 0 ? dp[i-1][3] : Integer.MIN_VALUE);
        }
        int[] res = new int[3];
        int index = 2;
        int first = -1;
        for (int i = dp.length - 1; i >= 0; i--) {
            if (index == 2) {
                // 判断第三个数
                if (dp[i][3] == dp[i-k][2] + sums[i]) {
                    res[index] = i;
                    index--;
                    i = i - k + 1;
                }
            } else if (index == 1) {
                if (dp[i][2] == dp[i-k][1] + sums[i]) {
                    res[index] = i;
                    first = dp[i-k][1];
                    index--;
                    i = i - k + 1;
                }
            } else if (index == 0) {
                if (dp[i][1] == first) {
                    res[index] = i;
                }
            }
        }
        return res;
    }
}

// better solution
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length, maxsum = 0;
        int[] sums = new int[n+1], postLeft = new int[n], postRight = new int[n], ans = new int[3];
        for (int i = 0; i < n; i++) {
            sums[i+1] = sums[i] + nums[i];
        }
        for (int i = k, tot = sums[k] - sums[0]; i < n; i++) {
            if (sums[i+1] -sums[i+1-k] > tot) {
                postLeft[i] = i + 1 - k;
                tot = sums[i+1] - sums[i+1-k];
            } else {
                postLeft[i] = postLeft[i-1];
            }
        }
        postRight[n-k] = n-k;
        for (int i = n - k - 1, tot = sums[n] - sums[n-k]; i >= 0; i--) {
            if (sums[i+k] - sums[i] >= tot) {
                postRight[i] = i;
                tot = sums[i+k] - sums[i];
            } else {
                postRight[i] = postRight[i+1];
            }
        }
        // test all possible middle interval
        for (int i = k; i <= n - 2 * k; i++) {
            int l = postLeft[i-1], r = postRight[i+k];
            int tot = (sums[i+k] - sums[i]) + (sums[l+k] - sums[l]) + (sums[r+k] - sums[r]);
            if (tot > maxsum) {
                maxsum = tot;
                ans[0] = l; ans[1] = i; ans[2] = r;
            }
        }
        return ans;
    }
}
```

## 154 Find Minimum in Rotated Sorted Array II

```java
class Solution {
    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length-1);
    }
    
    private int findMin(int[] nums, int lo, int hi) {
        if (lo > hi || hi < lo) {
            return Integer.MAX_VALUE;
        } else if (lo == hi) {
            return nums[lo];
        } else {
            int min = Integer.MAX_VALUE;
            int mid = lo + (hi - lo) / 2;
            min = Math.min(min, nums[mid]);
            if (nums[mid] < nums[hi]) {
                return Math.min(min, findMin(nums, lo, mid-1));
            } else if (nums[lo] < nums[mid]) {
                min = Math.min(min, nums[lo]);
                return Math.min(min, findMin(nums, mid + 1, hi));
            } else {
                return Math.min(min, Math.min(findMin(nums, lo, mid-1), findMin(nums, mid + 1, hi)));
            }
        }
    }
}

// another solution
class Solution {
    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int mid = -1;
        while (lo < hi) {
            mid = lo + (hi - lo) / 2;
            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
            } else if (nums[mid] < nums[hi]) {
                hi = mid;
            } else {
                hi--;
            }
        }
        return nums[lo];
    }
}
```

## 128 Longest Consecutive Sequence

```java
class Solution {
    public int longestConsecutive(int[] nums) {
        // 第一个Integer表示某一个数，第二个Integer表示连续的长度，注意只有头和尾部。
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if (!map.containsKey(nums[i])) {
                // 不包含才有意义
                if (map.containsKey(nums[i]-1) && map.containsKey(nums[i]+1)) {
                    // 放在中间
                    int left = map.get(nums[i]-1);
                    int right = map.get(nums[i]+1);
                    map.put(nums[i]-left, right + left + 1);
                    map.put(nums[i]+right, right + left + 1);
                    map.put(nums[i], 1);
                    max = Math.max(right + left + 1, max);
                } else if (map.containsKey(nums[i]-1)) {
                    // 放在右边
                    int left = map.get(nums[i]-1);
                    map.put(nums[i], left + 1);
                    map.put(nums[i]-left, left + 1);
                    max = Math.max(left + 1, max);
                } else if (map.containsKey(nums[i]+1)) {
                    // 放在左边
                    int right = map.get(nums[i]+1);
                    map.put(nums[i], right + 1);
                    map.put(nums[i] + right, right + 1);
                    max = Math.max(max, right + 1);
                } else {
                    // 只有自己
                    map.put(nums[i], 1);
                    max = Math.max(max, 1);
                }
            }
        }
        return max;
    }
}

// 代码可以更精简。
```

## 45 Jump Game II

```java
class Solution {
    public int jump(int[] nums) {
        if (nums.length < 2) return 0;
        int begin = 1, end = Math.min(nums[0] + 0, nums.length-1);
        int ans = 1;
        while (end != nums.length - 1) {
            int max = Integer.MIN_VALUE;
            for (int i = begin; i <= end; i++) {
                max = Math.max(max, Math.min(nums[i] + i, nums.length-1));
            }
            begin = end + 1;
            end = max;
            ans++;
        }
        return ans;
    }
}

// A better solution
class Solution {
    public int jump(int[] nums) {
        int sc = 0;
        int e = 0;
        int max = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            max = Math.max(max, i + nums[i]);
            if (i == e) {
                sc++;
                e = max;
            }
        }
        return sc;
    }
}
```

## 57 Insert Interval

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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        // 二话不说，先排个序把，直接混了
        intervals.add(newInterval);
        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval v1, Interval v2) {
                return v1.start - v2.start;
            }
        });
        int start = -1, end = -1;
        List<Interval> res = new ArrayList<>();
        for (Interval item : intervals) {
            if (start == -1) {
                start = item.start;
                end = item.end;
            } else {
                if (end < item.start) {
                    res.add(new Interval(start, end));
                    start = item.start;
                    end = item.end;
                } else {
                    end = Math.max(end, item.end);
                }
            }
        }
        res.add(new Interval(start, end));
        return res;
    }
}

// another solution
class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> result = new ArrayList<>();
        int i = 0;
        while (i < intervals.size() && intervals.get(i).end < newInterval.start) {
            result.add(intervals.get(i++));
        }
        while (i < intervals.size() && intervals.get(i).start <= newInterval.end) {
            newInterval = new Interval(Math.min(newInterval.start, intervals.get(i).start), Math.max(newInterval.end, intervals.get(i).end));
            i++;
        }
        result.add(newInterval);
        while (i < intervals.size()) result.add(intervals.get(i++));
        return result;
    }
}
```

## Largest Rectangle in Histogram

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        // Create an empty stack. The stack holds indexes of hist[] array
        // The bars bars stored in stack are always in increasing order of
        // their heights
        Stack<Integer> s = new Stack<>();
        
        int max_area = 0;   // Initialize max area
        int tp = -1;        // To store top of stack
        int area_with_top = 0;  // To store with top bar as the smallest bar
        
        // Run through all bars of given histogram
        int i = 0;
        while (i < n) {
            // If this bar is higher than the bar on top stack, push it to stack
            if (s.isEmpty() || heights[s.peek()] <= heights[i]) {
                s.push(i++);
            } else {
                // If this bar is lower than the bar on top stack, then calculate area of
                // rectangle with stack top as the smallest (or minimum height) bar.
                // 'i' is 'right index' for the top and element before top in stack is 'left index'
                tp = s.pop(); // pop and store
                
                // calculate the are with hist[tp] stack as smallest bar
                area_with_top = heights[tp] * (s.isEmpty() ? i : i - s.peek() - 1);
                
                // Update max area, if need
                if (max_area < area_with_top) {
                    max_area = area_with_top;
                }
            }
        }
        
        // Now pop the remaining bars from stack and calculate areaa with every
        // poped bar as the smallest bar
        while (!s.isEmpty()) {
            tp = s.peek();
            s.pop();
            area_with_top = heights[tp] * (s.isEmpty() ? i : i - s.peek() - 1);
            if (max_area < area_with_top) {
                max_area = area_with_top;
            }
        }
        return max_area;
    }
}
```

## 85 Maximal Rectangle

```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int res = 0;
        int[] sub = new int[matrix[0].length];
        for (int j = 0; j < matrix[0].length; j++) {
            sub[j] = matrix[0][j] - '0';
        }
        res = Math.max(res, largestRectangleArea(sub));
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '0') {
                    sub[j] = 0;
                } else {
                    sub[j] += 1;
                }
            }
            res = Math.max(res, largestRectangleArea(sub));
        }
        return res;
    }
    
    private int largestRectangleArea(int[] hist) {
        int max_area = 0;
        int area_with_top = 0;
        int top = -1; // 存储peek值
        
        Stack<Integer> s = new Stack<>();
        int i = 0;
        while (i < hist.length) {
            if (s.isEmpty() || hist[s.peek()] <= hist[i]) {
                s.push(i++);
            } else {
                top = s.pop();
                
                area_with_top = hist[top] * (s.isEmpty() ? i : i - s.peek() - 1);
                max_area = Math.max(max_area, area_with_top);
            }
        }
        
        while (!s.isEmpty()) {
            top = s.pop();
            
            area_with_top = hist[top] * (s.isEmpty() ? i : i - s.peek() - 1);
            max_area = Math.max(max_area, area_with_top);
        }
        return max_area;
    }
}

// Another solution, I think is better.
class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0) return 0;
        int res = 0;
        int[] left = new int[matrix[0].length];
        int[] right = new int[matrix[0].length];
        int[] height = new int[matrix[0].length];
        Arrays.fill(left, 0);
        Arrays.fill(right, matrix[0].length);
        Arrays.fill(height, 0);
        for (int i = 0; i < matrix.length; i++) {
            int cur_left = 0, cur_right = matrix[0].length;
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') height[j]++;
                else height[j] = 0;
            }
            
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') left[j] = Math.max(left[j], cur_left);
                else { left[j] = 0; cur_left = j + 1; }
            }
            
            for (int j = matrix[0].length - 1; j >= 0; j--) {
                if (matrix[i][j] == '1') right[j] = Math.min(right[j], cur_right);
                else { right[j] = matrix[0].length; cur_right = j; }
            }
            
            for (int j = 0; j < matrix[0].length; j++) {
                res = Math.max(res, (right[j] - left[j]) * height[j]);
            }
        }
        return res;
    }
}
```

## 715 Range Module

```java
class RangeModule {
    TreeSet<Interval> ranges;

    public RangeModule() {
        ranges = new TreeSet<>();
    }
    
    public void addRange(int left, int right) {
        Iterator<Interval> iter = ranges.tailSet(new Interval(0, left-1)).iterator();
        while (iter.hasNext()) {
            Interval item = iter.next();
            if (right < item.left) break;
            left = Math.min(left, item.left);
            right = Math.max(right, item.right);
            iter.remove();
        }
        ranges.add(new Interval(left, right));
    }
    
    public boolean queryRange(int left, int right) {
        Interval res = ranges.higher(new Interval(0, left));
        return res != null && res.left <= left && res.right >= right;
    }
    
    public void removeRange(int left, int right) {
        Iterator<Interval> iter = ranges.tailSet(new Interval(0, left)).iterator();
        List<Interval> todo = new ArrayList<>();
        while (iter.hasNext()) {
            Interval item = iter.next();
            if (right < item.left) break;
            if (item.left < left) todo.add(new Interval(item.left, left));
            if (right < item.right) todo.add(new Interval(right, item.right));
            iter.remove();
        }
        for (Interval item : todo) ranges.add(item);
    }
}

class Interval implements Comparable<Interval> {
    int left;
    int right; 
    public Interval(int left, int right) {
        this.left = left;
        this.right = right;
    }
    
    public int compareTo(Interval that) {
        if (this.right == that.right) return this.left - that.left;
        return this.right - that.right;
    }
}

/**
 * Your RangeModule object will be instantiated and called as such:
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */
```

## 381 Insert Delete GetRandom O(1) - Duplicates allowed

```java
class RandomizedCollection {
    List<Integer> nums;
    Map<String, Integer> locs;
    List<String> reverse;
    Map<Integer, Integer> dups;
    Random random = new Random();

    /** Initialize your data structure here. */
    public RandomizedCollection() {
        nums = new ArrayList<>();
        locs = new HashMap<>();
        reverse = new ArrayList<>();
        dups = new HashMap<>();
    }
    
    /** Inserts a value to the collection. Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean flag = true;
        if (dups.getOrDefault(val, 0) > 0) flag = false;
        nums.add(val);
        dups.put(val, dups.getOrDefault(val, 0) + 1);
        locs.put(val + "-" + dups.get(val), nums.size()-1);
        reverse.add(val + "-" + dups.get(val));
        return flag;
    }
    
    /** Removes a value from the collection. Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        int len = dups.getOrDefault(val, 0);
        if (len == 0) return false;
        int index = locs.get(val + "-" + len);
        
        if (len == 1) dups.remove(val);
        else dups.put(val, len - 1);
        if (index < nums.size() - 1) {
            // 不是最后一个
            int val0 = nums.get(nums.size()-1);
            nums.set(index, val0);
            reverse.set(index, reverse.get(reverse.size()-1));
            locs.put(reverse.get(reverse.size()-1), index);
        }
        nums.remove(nums.size()-1);
        reverse.remove(reverse.size()-1);
        locs.remove(val + "-" + len);
        return true;
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return nums.get(random.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
```

## 42 Trapping Rain Water

```java
class Solution {
    public int trap(int[] height) {
        int ans = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int max_left = 0, max_right = 0;
            for (int j = i; j >= 0; j--) {
                max_left = Math.max(max_left, height[j]);
            }
            for (int j = i; j < height.length; j++) {
                max_right = Math.max(max_right, height[j]);
            }
            ans += Math.min(max_left, max_right) - height[i];
        }
        return ans;
    }
}

// an improved solution
class Solution {
    public int trap(int[] height) {
        if (height.length < 2) return 0;
        int ans = 0;
        int[] max_rights = new int[height.length];
        int max_right = 0;
        for (int i = height.length - 1; i >= 0; i--) {
            max_right = Math.max(max_right, height[i]);
            max_rights[i] = max_right;
        }
        int max_left = height[0];
        for (int i = 1; i < height.length - 1; i++) {
            max_left = Math.max(max_left, height[i]);
            ans += Math.min(max_left, max_rights[i]) - height[i];
        }
        return ans;
    }
}

// a stack solution
class Solution {
    public int trap(int[] height) {
        int ans = 0, current = 0;
        Stack<Integer> s = new Stack<>();
        while (current < height.length) {
            while (!s.isEmpty() && height[current] > height[s.peek()]) {
                int top = s.pop();
                if (s.isEmpty()) break;
                int boundary = Math.min(height[current], height[s.peek()]) - height[top];
                ans += boundary * (current - s.peek() - 1);
            }
            s.push(current++);
        }
        return ans;
    }
}
```

## 41 First Missing Positive

```java
class Solution {
    public int firstMissingPositive(int[] nums) {
        TreeSet<Integer> set = new TreeSet<>();
        for (int i = 0; i <= nums.length; i++) {
            set.add(i+1);
        }
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(nums[i])) {
                set.remove(nums[i]);
            }
        }
        return set.first();
    }
}

// another solution
class Solution {
    public int firstMissingPositive(int[] nums) {
        int i = 0;
        for (; i < nums.length; ) {
            if (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i]-1] != nums[i]) {
                swap(nums, i, nums[i]-1);
            } else {
                i++;
            }
        }
        i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] != i+1) return i+1;
        }
        return nums.length + 1;
    }
    
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
```

## Median of Two Sorted Arrays

```java
class Solution {
    public double findMedianSortedArrays(int[] A, int[] B) {
        int m = A.length;
        int n = B.length;
        if (m > n) { // to ensure m<=n
            int[] temp = A; A = B; B = temp;
            int tmp = m; m = n; n = tmp;
        }
        int iMin = 0, iMax = m, halfLen = (m + n + 1) / 2;
        while (iMin <= iMax) {
            int i = (iMin + iMax) / 2;
            int j = halfLen - i;
            if (i < iMax && B[j-1] > A[i]){
                iMin = iMin + 1; // i is too small
            }
            else if (i > iMin && A[i-1] > B[j]) {
                iMax = iMax - 1; // i is too big
            }
            else { // i is perfect
                int maxLeft = 0;
                if (i == 0) { maxLeft = B[j-1]; }
                else if (j == 0) { maxLeft = A[i-1]; }
                else { maxLeft = Math.max(A[i-1], B[j-1]); }
                if ( (m + n) % 2 == 1 ) { return maxLeft; }

                int minRight = 0;
                if (i == m) { minRight = B[j]; }
                else if (j == n) { minRight = A[i]; }
                else { minRight = Math.min(B[j], A[i]); }

                return (maxLeft + minRight) / 2.0;
            }
        }
        return 0.0;
    }
}
```

## 126 Word Ladder II

```java
class Solution {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> nodeNeighbors = new HashMap<>();
        Map<String, Integer> distance = new HashMap<>();
        
        dict.add(beginWord);
        bfs(beginWord, endWord, dict, nodeNeighbors, distance);
        dfs(beginWord, endWord, dict, nodeNeighbors, distance, new ArrayList<>(), res);
        return res;
    }
    
    // BFS: Trace every node's distance from the start node (level by level)
    private void bfs(String start, String end, Set<String> dict, Map<String, List<String>> nodeNeighbors, Map<String, Integer> distance) {
        for (String str : dict) {
            nodeNeighbors.put(str, new ArrayList<>());
        }
        
        Queue<String> queue = new LinkedList<>();
        queue.offer(start);
        distance.put(start, 0);
        
        while (!queue.isEmpty()) {
            int count = queue.size();
            boolean foundEnd = false;
            for (int i = 0; i < count; i++) {
                String cur = queue.poll();
                int curDistance = distance.get(cur);
                
                List<String> neighbors = getNeighbors(cur, dict);
                
                for (String neighbor : neighbors) {
                    nodeNeighbors.get(cur).add(neighbor);
                    if (!distance.containsKey(neighbor)) {
                        distance.put(neighbor, curDistance + 1);
                        if (end.equals(neighbor)) {
                            foundEnd = true;
                        } else {
                            queue.offer(neighbor);
                        }
                    }
                }
                if (foundEnd) {
                    break;
                }
            }
        }
    }
    
    // Find all next level nodes.
    private List<String> getNeighbors(String node, Set<String> dict) {
        List<String> res = new ArrayList<>();
        char[] chs = node.toCharArray();
        
        for (char ch = 'a'; ch <= 'z'; ch++) {
            for (int i = 0; i < chs.length; i++) {
                if (chs[i] == ch) continue;
                char old_ch = chs[i];
                chs[i] = ch;
                if (dict.contains(String.valueOf(chs))) {
                    res.add(String.valueOf(chs));
                }
                chs[i] = old_ch;
            }
        }
        return res;
    }
    
    // DFS: output all paths with the shorest distance
    private void dfs(String cur, String end, Set<String> dict, Map<String, List<String>> nodeNeighbors, Map<String, Integer> distance, List<String> solution, List<List<String>> res) {
        solution.add(cur);
        if (end.equals(cur)) {
            res.add(new ArrayList<>(solution));
        } else {
            for (String next : nodeNeighbors.get(cur)) {
                if (distance.get(next) == distance.get(cur) + 1) {
                    dfs(next, end, dict, nodeNeighbors, distance, solution, res);
                }
            }
        }
        solution.remove(solution.size() - 1);
    }
}
```

## 795 Number of Subarrays with Bounded Maximum

```java
class Solution {
    public int numSubarrayBoundedMax(int[] A, int L, int R) {
        return count(A, R) - count(A, L-1);
    }
    
    private int count(int[] A, int bound) {
        int ans = 0;
        int count = 0;
        for (int a : A) {
            if (a <= bound) count++;
            else count = 0;
            ans += count;
        }
        return ans;
    }
}
```