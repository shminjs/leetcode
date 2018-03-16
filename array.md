# Leetcode Array专题

## Easy

### 561 Array Partition I

[1, 4, 3, 2] 4 = min(1, 2) + min(3, 4)

```java
public int arrayPairSum(int[] nums) {
  Arrays.sort(nums);
  int sum = 0;
  for (int i = 0; i < nums.length; i += 2)
    sum += nums[i];
  return sum;
}
```

### 566 Reshape the Matrix

```java
public int[][] matrixReshape(int[][] nums, int r, int c) {
  int n = nums.length, m = nums[0].length;
  if (r * c != n * m) return nums;
  int[][] res = new int[r][c];
  for (int i = 0; i < r * c; i++) {
    res[i/c][i%c] = nums[i/m][i%m];
  }
  return res;
}
```

### 485 Max Consecutive Ones

```java
public int findMaxConsecutiveOnes(int[] nums) {
  int max = 0;
  int count = 0;
  for (int i = 0; i < nums.length; i++) {
    if (nums[i] == 1) {
      count++;
    } else {
      if (count > max) max = count;
      count = 0;
    }
  }
  if (count > max) max = count;
  return max;
}
```

### Find All Numbers Disppeared in an Array

```java
public List<Integer> findDisppearedNumbers(int[] nums) {
  ArrayList<Integer> al = new ArrayList<>();
  int ind;
  for (int i = 0; i < nums.length; i++) {
    ind = Math.abs(nums[i]) - 1;
    nums[ind] = -Math.abs(nums[ind]);
  }
  for (int i = 0; i < nums.length; i++) {
    if (nums[i] > 0) {
      al.add(i+1);
    }
  }
  return al;
}
```

### 283 Move Zeroes

```java
// my own solution
public void moveZeroes(int[] nums) {
  int zero = nums.length;
  for (int i = 0; i < nums.length; i++) {
    if (zeor == nums.length && nums[i] = 0) zeor = i;
    if (zero != nums.length && nums[i] != 0) {
      nums[zero] = nums[i];
      zero++;
    }
  }
  for (int i = zero; i < nums.length; i++) {
    nums[i] = 0;
  }
}

// another solution
public void moveZeroes(int[] nums) {
  if (nums == null || nums.length == 0) return;

  int insertPos = 0;
  for (int num : nums) {
    if (num != 0) nums[insertPos] = num;
  }

  while (insertPos < nums.length) {
    nums[insertPost++] = 0;
  }
}
```

### 167 Two Sum II - Input array is sorted

```java
public int[] twoSum(int[] num, int target) {
  int[] indice = new int[2];
  if (num == null || num.length < 2) return indice;
  int left = 0, right = num.length - 1;
  while (left < right) {
    int v = num[left] + num[right];
    if (v == target) {
      indice[0] = left + 1;
      indice[1] = right + 1;
      break;
    } else if (v > target) {
      right --;
    } else {
      left++;
    }
  }
  return indice;
}
```

### 122 Best time to Buy and Sell Stock II

```java
public int maxProfit(int[] prices) {
  int profit = 0;
  for (int i = 1; i < prices.length; i++) {
    int tmp = prices[i] - prices[i-1];
    if (tmp > 0)
      profit += temp;
  }
  return profit;
}
```

### 169 Majority Element

```java
public int majorityElement(int[] nums) {
  int major = nums[0], count = 1;
  for (int i = 1; i < num.length; i++) {
    if (count == 0) {
      count++;
      major = nums[i];
    } else if (major == nums[i]) {
      count++;
    } else {
      count--;
    }
  }
  return major;
}
```

### 217 Contains Duplicate

```java
public boolean containsDuplicate(int[] nums) {
  Arrays.sort(nums);
  for (int i = 1; i < nums.length; i++) {
    if (nums[i] == nums[i-1]) return true;
  }
  return false;
}
```

### 268 Missing Number

```java
public int missingNumber(int[] nums) {
  int xor = 0, i = 0;
  for (i = 0; i < nums.length; i++) {
    xor = xor ^ i ^ nums[i];
  }
  return xor ^ i;
}
```

### Best Time to Buy and Sell Stock

```java
public int maxProfit(int[] prices) {
  int maxCur = 0, maxSoFar = 0;
  for (int i = 1; i < prices.length; i++) {
    maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);
    maxSoFar = Math.max(maxCur, maxSoFar);
  }
  return maxSoFar;
}

// another solution
public int maxProfit(int[] prices) {
  int minPrice = Integer.MAX_VALUE;
  int minPro = 0;
  for (int i = 0; i < prices.length; i++) {
    minPrice = Math.min(minPrice, prices[i]);
    minPro = Math.max(minPro, prices[i] - minPrice);
  }
  return minPro;
}
```

### 35 Search Insert Position

```java
public int searchInsert(int[] nums, int target) {
  int ind = 0;
  for (int i = 0; i < nums.length; i++) {
    if (target > nums[i]) ind++;
  }
  return ind;
}
```

### 53 Maximum Subarray

```java
public int maxSubArray(int[] nums) {
  int maxsofar = nums[0];
  int maxendinghere = mums[0];
  for (int i = 1; i < nums.length; i++) {
    maxendinghere = Math.max(maxendinghere + nums[i], nums[i]);
    maxsofar = Math.max(maxsofar, maxendinghere);
  }
  return maxsofar;
}
```

### 27 Remove Element

```java
public int removeElement(int[] nums, int val) {
  int begin = 0;
  for (int i = 0; i < n; i++) {
    if (nums[i] != val) nums[begin++] = nums[i];
  }
  return begin;
}

// my own solution
public int removeElement(int[] nums, int val) {
  int p1 = -1;
  int i = 0;
  for (; i < nums.length; i++) {
    if (nums[i] == val) {
      if (p1 == -1) p1 = i;
    } else {
      if (p1 != -1) {
        nums[p1] = nums[i];
        p1++;
      }
    }
  }
  if (p1 == -1) return nums.length;
  else return p1;
}
```

### 66 Plus One

```java
public int[] plusOne(int[] digits) {
  int n = digits.length;
  for (int i = n - 1; i >= 0; i--) {
    if (digits[i] < 9) {
      digit[i]++;
      return digits;
    }
    digits[i] = 0;
  }

  int[] newNumbers = new int[n+1];
  newNumbers[0] = 1;
  return newNumers;
}
```

### 118 Pascal's Triangle

```java
public List<List<Integer>> generate(int numRows) {
  Lilst<List<Integer>> allows =  new ArrayList<>();
  ArrayList<Integer> row = new ArrayList<Integer>();
  for (int i = 0; i < numRows; i++) {
    row.add(0, 1);
    for (int j = 1; j < row.size()-1; i++) {
      row.set(j, row.get(j) + row.get(j+1));
    }
    allrows.add(new ArrayList<Integer>(row));
  }
}
```

### 119 Pacal's Triangle II

```java
public List<Integer> getRow(int rowIndex) {
  List<Integer> list = new ArrayList<Integer>();
  if (rowIndex < 0) return list;

  for (int i = 0; i < rowIndex; i++) {
    list.add(0, 1);
    for (int j = 1; j < list.size() - 1; j++) {
      list.set(j, list.get(j) + list.get(j + 1));
    }
  }
  return list;
}

// my own solution
public List<Integer> getRow(int rowIndex) {
  List<Integer> result = new ArrayList<>();
  int nCk = 1;
  for (int k = 0; k <= rowindex; i++) {
    result.add(nCk);
    nCk = (int)(1.0 * nCk * (rowIndex - k) / (k + 1));
  }
  return result;
}
```

### Remove Duplicates from Sorted Array

```java
public int removeDuplicates(int[] nums) {
  if (nums.length < 2) return nums.length;
  int id = 1;
  for (int i = 1; i < nums.length; i++) {
    if (nums[i] != nums[i-1]) nums[id++] = nums[i];
  }
  return id;
}
```

### Two Sum

```java
public int[] twoSum(int[] nums, int target) {
  int[] result = new int[2];
  Map<Integer, Integer> map = new HashMap<>();
  for (int i = 0; i < nums.length; i++) {
    if (map.containsKey(target - nums[i])) {
      result[1] = i + 1;
      result[0] = map.get(target - nums[i]);
      return result;
    }
    map.put(nums[i], i + 1);
  }
  return result;
}
```

### 219 Contains Duplicate II

```java
// my own solution
public boolean containsNearbyDuplicates(int[] nums, int k) {
  Map<Integer, Integer> map = new HashMap<>();
  for (int i = 0; i < nums.length; i++) {
    if (map.containsKey(nums[i])) {
      int ind = map.get(nums[i]);
      if (i - ind <= k) {
        return true;
      }
    }
    map.put(nums[i], i);
  }
  return false;
}
```

### 88 Merge Sorted Array

从尾部合并，good

```java
public void merge(int[] A, int m, int[] B, int n) {
  int i = m - 1, j = n - 1, k = m + n - 1;
  while (i > -1 && j > -1) A[k--] = (A[i] > B[j]) ? A[i--] : B[j--];
  while (j > -1) A[k--] = B[j--];
}
```

### 605 Can Place Flowers

```java
public boolean canPlaceFlowers(int[] flowerbed, int n) {
  int count = 0;
  for (int i = 0; i < flowerbed.length && count < n; i++) {
    if (flowerbed[i] == 0) {
      // get next and prev flower bed slot values. If i lies at the end the next and the prev are considered as 0.
      int next = (i == flowerbed.length - 1) ? 0 : flowerbed[i + 1];
      int prev = (i == 0) ? 0 : flowerbed[i-1];
      if (next == 0 && prev == 0) {
        flowerbed[i] = 1;
        count++;
      }
    }
  }
  return count == n;
}
```

### 581 Shortest Unsorted Continuous Subarray

```java
public int findUnsortedSubarray(int[] A) {
  int n = A.length, beg = -1, end = -2, min = A[n - 1], max = A[0];
  for (int i = 1; i < n; i++) {
    max = Math.max(max, A[i]);
    min = Math.min(min, A[n-1-i]);
    if (A[i] < max) end = i;
    if (A[n-1-i] > min) beg = n - 1 - i;
  }
  return end - beg + 1;
}
```

### 532 K-diff Pairs in an Array

```java
public int findPairs(int[] nums, int k) {
  if (nums == null || nums.length == 0 || k < 0) return 0;

  Map<Integer, Integer> map = new HashMap<>();
  int count = 0;
  for (int i : nums) {
    map.put(i, map.getOrDefault(i, 0) + 1);
  }

  for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
    if (k == 0) {
      // count how many elements in the array that appears more than twice
      if (entry.getValue() >= 2) {
        count++;
      }
    } else {
      if (map.containsKey(entry.getKey() + k)) {
        count++;
      }
    }
  }
  return count;
}
```

### 414 Third Maximum Number

```java
public int thirdMax(int[] nums) {
  Integer max1 = null;
  Integer max2 = null
  Integer max3 = null;
  for (Integer n : nums) {
    if (n.equals(max1) || n.equals(max2) || n.equals(max3)) continue;
    if (max1 == null || n > max1) {
      max3 = max2;
      max2 = max1;
      max1 = n;
    } else if (max2 == null || n > max2) {
      max3 = max2;
      max2 = n;
    } else if (max3 == null || n > max3) {
      max3 = n;
    }
  }
  return max3 == null ? max1 : max3;
}
```

### 189 Rotate Array

```java
public void rotate(int[] nums, int k) {
  k %= nums.length;
  reverse(nums, 0, nums.length - 1);
  reverse(nums, 0, k - 1);
  reverse(nums, k, nums.length - 1);
}

public void reverse(int[] nums, int start, int end) {
  whle (start < end) {
    int tmp = nums[start];
    nums[start] = nums[end];
    nums[end] = tmp;
    start++;
    end--;
  }
}
```

### 442 Find All Duplicate in an Array

```java
public List<Integer> findDublicates(int[] nums) {
  List<Integer> res = new ArrayList<>();
  for (int i = 0; i < nums.length; i++) {
    int index = Math.abs(nums[i]) - 1;
    if (nums[index] < 0) {
      res.add(Math.abs(index + 1));
    }
    nums[index] = -nums[index];
  }
}
```

### 495 Teemo Attacking

```java
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
```

### 565 Array Nesting

```java
// Using visited array
public int arrayNesting(int[] nums) {
  boolean[] visited = new boolean[nums.length];
  int res = 0;
  for (int i = 0; i < nums.length; i++) {
    if (!visited[i]) {
      int start = nums[i], count = 0;
      do {
        start = nums[start];
        count++;
        visited[start] = true;
      } while (start != nums[i]);
      res = Math.max(res, count);
    }
  }
  return res;
}

// Without using extra space
public int arrayNesting(int[] nums) {
  int res = 0;
  for (int i = 0; i < nums.length; i++) {
    if (nums[i] != Integer.MAX_VALUE) {
      int start = nums[i], count = 0;
      while (nums[start] != Integer.MAX_VALUE) {
        int temp = start;
        start = nums[start];
        count++;
        nums[temp] = Integer.MAX_VALUE;
      }
      res = Math.max(res, count);
    }
  }
  return res;
}
```

### 238 Product of Array Execept Self

```java
public int[] produceExceptSelf(int[] nums) {
  int n = nums.length;
  int[] res = new int[n];
  res[0] = 1;
  for (int i = 1; i < n; i++) {
    res[i] = res[i-1] * nums[i-1];
  }
  int right = 1;
  for (int i = n - 1; i >=0; i--) {
    res[i] *= right;
    right *= nums[i];
  }
}
```

## Combination Sum III

```java
public List<List<Integer>> combinationSum3(int k, int n) {
  List<List<Integer>> ans = new ArrayList();
  combination(ans, new ArrayList<Integer>(), k, 1, n);
  return ans;
}

private void combination(List<List<Integer>> ans, List<Integer> comb, int k, int start, int n) {
  if (comb.size() == k && n == 0) {
    List<Integer> li = new ArrayList<Integer>(comb);
    ans.add(li);
    return;
  }
  for (int i = start; i <= 9; i++) {
    comb.add(i);
    combination(ans, comb, k, i+1, n - i);
    comb.remove(comb.size() - 1);
  }
}

// my own solution
private static int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9}

public List<List<Integer>> combinationSum3(int k, int n) {
  List<List<Integer>> res = new LinkedList<>();
  int[] x = new int[nums.length];
  Arrays.fill(x, -1);
  int s = 0;
  while (s >= 0) {
    while (x[s] < 1) {
      x[s] = x[s] + 1;
      int count_re = count(x);
      int sum_re = sum(x);
      if (count_re == k && sum_re == n) {
        // 得到一个解，放入res中
        res.add(generate(x));
        break;
      }
      if (s < x.length - 1 && count_re < k && sum_re < n) {
        s = s + 1;
      }
    }
    x[s] = -1;
    s = s - 1;
  }
  return res;
}

private List<Integer> generate(int[] x) {
  List<Integer> res = new LinkedList<>();
  for (int i = 0; i < x.length; i++) {
    if (x[i] == 1) res.add(nums[i]);
  }
  return res;
}

private int sum(int[] x) {
  int result = 0;
  for (int i = 0; i < x.length; i++) {
    if (x[i] > 0) result += nums[i];
  }
  return result;
}

private int count(int[] x) {
  int sum = 0;
  for (int i = 0; i < x.length;i++) {
    if (x[i] > 0) sum += 1;
  }
  return sum;
}
```

### 287 Find the Duplicate Number

```java
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
```

### 621 Task Scheduler

```java
// Using Sorting

public class Solution {
  public int leastInterval(char[] tasks, int n) {
    int[] map = new int[26];
    for (char c : tasks) {
      map[c - 'A']++;
    }
    Arrays.sort(map);
    int time = 0;
    while (map[25] > 0) {
      int i = 0;
      while (map[25] > 0) {
        if (map[i] == 0) break;
        if (i < 26 && map[25 - i] > 0) map[25 - i]--;
        time++;
        i++;
      }
      Arrays.sort(map);
    }
    return time;
  }
}

// Using Priority-Queue 
public int leastInterval(char[] tasks, int n) {
  int[] map = new int[26];
  for (char c : tasks) map[c - 'A']++;
  PriorityQueue<Integer> queue = new PriorityQueue<>(26, Collections.reverseOrder());
  for (int f : map) {
    if (f > 0) queue.add(f);
  }
  int time = 0;
  while (!queue.isEmpty()) {
    int i = 0;
    List<Integer> temp = new ArrayList<>();
    while (i <= n) {
      if (!queue.isEmpty()) {
        if (queue.peek() > 1) {
          temp.add(queue.poll() - 1);
        } else {
          queu.poll();
        }
      }
      time++;
      if (queue.isEmpty() && temp.size() == 0) break;
      i++;
    }
    for (int l : temp) queue.add(l);
  }
  return time;
}

// another solution
public int leastInterval(char[] tasks, int n) {
  int[] map = new int[26];
  for (char c : tasks) map[c - 'A']++;
  Arrays.sort(map);
  int max_val = map[25] - 1, idle_slots = max_val * n;
  for (int i = 24; i >= 0 && map[i] > 0; i--) {
    idle_slots -= Math.min(map[i], max_val);
  }
  return idle_slots > 0 ? idle_slots + tasks.length : task.length;
}
```

### 62 Unique Paths

```java
public int uniquePaths(int m, int n) {
  int[][] dp = new int[m][n];
  dp[0][0] = 1;
  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n;j++) {
      int left = j - 1 > -1 ? dp[i-1][j-1] : 0;
      int right = i - 1 > -1 ? dp[i-1][j] : 0;
      dp[i][j] += left + up;
    }
  }
  return dp[m-1][n-1];
}
```

### 611 Valid Triangle Number

```java
public int triangleNumber(int[] A) {
  Arrays.sort(A);
  int count = 0, n = A.length;
  for (int i = n - 1; i >= 2; i--) {
    int l = 0, r = i-1;
    while (l < r) {
      if (A[l] + A[r] > A[i]) {
        count += r - l;
        r--；
      } else l++;
    }
  }
  return count;
}
```

### 78 Subsets

```java
public List<List<Integer>> subsets(int[] nums) {
  List<List<Integer>> list = new ArrayList<>();
  Arrays.sort(nums);
  backtrack(list, new ArrayList<>(), nums, 0);
  return list;
}

private void backtracking(List<List<Integer>> list, List<Integer> templist, int[] nums, int start) {
  list.add(new ArrayList<>(templist));
  for (int i = start; i < nums.length; i++) {
    tmeplist.add(nums[i]);
    backtrack(list, templist, nums, i+1);
    templist.remove(templist.size()-1);
  }
}
```

### 560 Subaray Sum Equals K

```java
public int subarraySum(int[] nums, int k) {
  int count = 0;
  int[] sums = new int[nums.length];
  Map<Integer, Integer> map = new HashMap<Integer, Integer>();
  for (int i = 0; i < nums.length; i++) {
    sums[i] = (i - 1 > 0 ? sums[i-1] : 0) + nums[i];
  }
  map.put(0, 1);
  for (int i = 0; i < sums.length; i++) {
    if (map.containsKey(sums[i] - k)) {
      count += map.get(sums[i] - k);
    }
    map.put(sum[i], map.getOrDefault(sums[i], 0) + 1);
  }
  return count;
}
```

### 153 Find Minimum in Rotated Sorted Array

```java
public int findMin(int[] nums) {
  int start = 0, end = nums.length - 1;

  while (start < end) {
    if (nums[start] < nums[end]) {
      return nums[start];
    }

    int mid = (start + end) / 2;
    if (nums[mid] >= nums[start]) {
      start = mid + 1;
    } else {
      end = mid;
    }
  }
  return nums[start];
}
```

### 35 Search Insert Position

```java
public int searchInsert(int[] A, int target) {
  int low = 0, high = A.length - 1;
  while (low <= high) {
    int mid = (low + high) / 2;
    if (A[mid] == target) return mid;
    else if (A[mid] > target) high = mid - 1;
    else low = mid + 1;
  }
  return low;
}
```

### 59 Spiral Matrix II

```java
public int[][] generateMatrix(int n) {
  int[][] spiral = new int[n][n];
  if (n <= 0) return spiral;
  int i = 0, j = 0;
  int ind = 1;
  int di = 0, dj = 1;
  while (ind <= n * n) {
    spiral[i][j] = ind++;
    if (i + dj == n || j + dj == n || j + dj == -1 || spiral[i+di][j+dj] != 0) {
      int tmp = di;
      di = dj;
      dj = -tmp;
    }
    i += di;
    j += dj;
  }
  return spiral;
}
```

### 380 Insert Delete GetRandom O(1)

```java
public class RandomizedSet {
  ArrayList<Integer> nums;
  HashMap<Integer, Integer> locs;
  java.util.Random rand = new java.util.Random();

  public RandomizedSet() {
    nums = new ArrayList<>();
    locs = new HashMap<>();
  }

  public boolean insert(int val) {
    boolean contain = locs.containsKey(val);
    if (contain) return false;
    locs.put(val, nums.size());
    nums.add(val);
    return true;
  }

  public boolean remove(int val) {
    boolean contain = locs.containsKey(val);
    if (!contain) return false;
    int loc = locs.get(val);
    if (loc < nums.size() - 1) {
      int lastone = nums.get(nums.size - 1);
      nums.set(loc, lastone);
      locs.put(lastone, loc);
    }
    locs.remove(val);
    nums.remove(nums.size() - 1);
    return true;
  }
}
```

### 39 Combination Sum

```java
public List<List<Integer>> combinationSum(int[] nums, int target) {
  List<List<Intger>> list = new ArrayList<>();
  Arrays.sort(nums);
  backtrack(list, new ArrayList<>(), nums, target, 0);
  retur list;
}

private void backtrack(List<List<Integer>> list, List<Integer> templist, int[] nums, int remain, int start) {
  if (remain < 0) return;
  else if (remain == 0) list.add(new ArrayList<>(templist));
  else {
    for (int i = start; i < nums.length; i++) {
      templist.add(nums[i]);
      backtrack(list, templist, nums, remain - nums[i], i);// we can reuse i
      temp.remove(templist.size()-1);
    }
  }
} 
```

### 48 Rotate Image

```java
public void rotate(int[][] matrix) {
  int N = matrix.length;
  for (int layer = 0; layer < N / 2; layer++) {
    int first = layer;
    int last = N - 1 - layer;
    for (int i = first; i < last; i++) {
      int offset = i - first;
      int top = matrix[first][i];
      matrix[first][i] = matrix[last-offset][first];
      matrix[last-offset][first] = matrix[last][last-offset];
      matrix[last][last-offset] = matrix[i][last];
      matrix[i][last] = top;
    }
  }
}
```

### 64 Minimum Path sum

```java
public int minPathSum(int[][] grid) {
  int m = grid.length;
  int n = grid.length;
  int[][] dp = new int[m+1][n+1];
  for (int i = 0; i <= m; i++) d[i][0] = Integer.MAX_VALUE;
  for (int j = 0; j <= n; j++) d[0][j] = Integer.MAX_VALUE;
  for (int i = 1; i <= m; i++) {
    for (int j = 1; j <= n; j++) {
      if (dp[i][j-1] != Integer.MAX_VALUE || dp[i-1][j] != Integer.MAX_VALUE) {
        dp[i][j] = Math.min(dp[i][j-1], dp[i-1][j]);
      } else {
        dp[i][j] = grid[i][j];
      }
    }
  }
  return dp[m][n];
}
```

### 75 Sort Colors

```java
public void sortColors(int[] nums) {
  int N = nums.length;
  int begin = 0, last = N - 1;
  int ind = 0;
  while (ind <= last) {
    if (nusm[ind] == 0) swap(nums, ind++, begin++);
    else if (numsj[ind] == 2) swap(nums, ind, last--);
    else ind++;
  }
}

private void swap(int[] nums, int i, int j) {
  int tmp = nums[j];
  nums[j] = nums[i];
  nums[i] = nums[j];
}
```

### 162 Find Peak Element

```java
public int findPeakElement(int[] nums) {
  int low = 0;
  int high = nums.length - 1;
  int mid = 0;
  while (low < high) {
    mid = low + (high - low) / 2;

    if (arr[mid] > arr[mid + 1]) {
      high = mid;
    } else {
      low = mid + 1;
    }
  }
  return low;
}
```

### 289 Game of Life

```java
public void gameOfLife(int[][] board) {
  if (board == null || board.length == 0) return;
  int m = board.length, n = board[0].length;

  for (int i = 0; i < n; i++) {
    for (int j = 0; j < n; j++) {
      int lives = liveNeighbors(board, m, n, i, j);

      // In the begining, every 2nd bit is 0
      // So we only need to care about when will the 2nd bit become 1.
      if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
        board[i][j] = 3;
      }
      if (board[i][j] == 0 && lives == 3) {
        board[i][j] = 2;
      }
    }
  }

  for (int i = 0; i < m; i++) {
    for (int j = 0; j < n; j++) {
      board[i][j] >>= 1;
    }
  }
}

private int liveNeighbors(int[][] board, int m, int n, int i, int j) {
  int lives = 0;
  for (int x = Math.max(i - 1, 0); x <= Math.min(i + 1, m - 1); x++) {
    for (int y = Math.max(j - 1, 0); y <= Math.min(j + 1, n -1); y++) {
      lives += board[x][y] & 1;
    }
  }
  lives -= board[i][j] & 1;
  return lives;
}
```

### 11 Container with the most water

```java
public int maxArea(int[] weight) {
  int start = 0;
  int end = height.length - 1;
  int ans = 0;
  while (start < end) {
    int v = (end - start) * Math.min(height[start], height[end]);
    if (v > ans) ans = v;
    if (height[start] < height[end]) {
      start++;
    } else {
      end--;
    }
  }
  return ans;
}
```

### 90 Subset II

```java
public List<List<Integer>> subsetWithDup(int[] nums) {
  List<List<Integer>> list = new ArrayLlist<>();
  Arrays.sort(nums);
  backtrack(list, new ArrayList<>(), nums, 0);
  return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> tmeplist, int[] nums, int start) {
  list.add(new ArrayList<>(templist));
  for (int i = start; i < nums.length; i++) {
    if (i > start && nums[i] == nums[i-1]) continue;
    tmeplist.add(nums[i]);
    backtrack(list, templist, nums, i+1);
    templist.remove(templist.size()-1);
  }
}
```

### 80 Remove Duplicates from sorted Array II

```java
public int removeDuplicates(int[] nums) {
  if (nums.length < 2) return nums.length;
  int id = 0;
  int count = 0;
  Integer pre = null;
  for (int i = 0; i < nums.length; i++) {
    if (pre != null) {
      if (pre == nums[i]) count++;
      else count = 1;
    } else {
      count = 1;
    }
    pre = nums[i];
    if (count <= 2) nums[id++] = nums[i];
  }
  return id;
}
```

### 73 Set Matrix Zeros

```java
public void setZeros(int[][] matrix) {
  int m = matrix.length;
  int n = matrix[0].length;

  int col0 = 1;
  for (int i = 0; i < m; i++) {
    if (matrix[i][0]== 0) col0 = 1;
    for (int j = 0; j < n; j++) {
      if (matrix[i][j] == 0) {
        matrix[i][0] =  = matrix[0][j] = 0;
      }
    }
  }

  for (int i = m - 1; i >= 0; i--) {
    for (int j = n - 1; j >= 0; j--) {
      if (matrix[i][0] == 0 || matrix[0][j] == 0) {
        matrix[i][j] = 0;
      }
    }
    if (col0 == 0) matrix[i][0] = 0;
  }
}
```

### 74 Search a 2D matrix

```java
public boolean searchMatrix(int[][] matrix, int target) {
  int m = matrix.length;
  int n = matrix[0].length;

  int l = 0, r = m * m - 1;
  while (l != r) {
    int mid = (l + r - 1) >> 1;
    if (matrix[mid / m][matrix % m] < target) l = mid + 1;
    else r = mid;
  }

  return matrix[r / m][r % m] == target;
}
```

### 40 Combination Sum II

```java
public List<List<Integer>> combinationSum2(int[] nums, int target) {
  List<List<Integer>> list = new ArrayList<>();
  Arrays.sort(nums);
  backtrack(list, new ArrayList<>(), nums, target, 0);
  return list;
}

private void backtrack(List<List<Integer>> list, List<Integer> templist, int[] nums, int remain, int start) {
  if (remain < 0) {
    return;
  } else if (remain == 0) list.add(new ArrayList<>(tmeplist));
  else {
    for (int i = start; i < nums.length; i++) {
      if (i > start && nums[i] = nums[i-1]) continue;
      templist.add(nums[i]);
      backtrack(list, templist, nums, remain - nums[i], i+1);
      templist.remove(templist.size()-1);
    }
  }
}
```

### 120 Triangle

```java
public int minimumTotal(List<List<Integer>> triangle) {
  int[] dp = new int[triangle+1];
  dp[0] = Integer.MAX_VALUE;
  int min = Integer.MAX_VALUE;
  for (int i = 0; i < triangle.size(); i++) {
    int n = triangle.get(i).size();
    for(int j = n; j >= 1; j--) {
      if (j != 1 && j == n) {
        dp[j] = dp[j-1] + triangle.get(i).get(j-1);
      } else [
        dp[j] = Math.min(dp[j], dp[j-1]) + triangle.get(i).get(j-1);
      ]
    }
  }
  for (int i = 0; i <= riangle.size(); i++){
    min = Math.min(min, dp[i]);
  }
  return min;
}
```

### 81 Search in Rotated Array II

```java
public boolean search(int[] nums, int target) {
  int start = 0, end = nums.length - 1, mid = -1;
  while (start <= end) {
    mid = (start + end) / 2;
    if (nums[mid] == target) {
      return true;
    }

    // If we know for sure right side is sorted or left side is unsorted
    if (nums[mid] < nums[end] || nums[mid] < nums[start]) {
      if (target > nums[mid] && target <= nums[end]) {
        start = mid + 1;
      } else {
        end = mid - 1;
      }
      // If we know for sure left side is sorted or right is sorted.
    } else if (nums[mid] > nums[left] || nums[mid] > nums[end]) {
      if (target < nums[mid] && target >= nums[start]) {
        end = mid - 1；
      } else {
        start = mid + 1;
      }
    } else {
      end--;
    }
  }
  return false;
}
```

int sum = 0;
for (Map<Integer, Integer> entry : map.higherEntry(key)) {
  sum += entry.getValue();
}
sum就是