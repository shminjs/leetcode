# Divide and Conquer

## 241 Different Ways to Add Parentheses

```java
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        if (isNumber(input)) {
            res.add(Integer.valueOf(input));
            return res;
        }
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            if (ch == '*' || ch == '-' || ch == '+') {
                String linput = input.substring(0, i);
                String rinput = input.substring(i+1);
                List<Integer> left = diffWaysToCompute(linput);
                List<Integer> right = diffWaysToCompute(rinput);
                for (Integer l : left) {
                    for (Integer r : right) {
                        switch (ch) {
                            case '+':
                                res.add(l + r);
                                break;
                            case '-':
                                res.add(l - r);
                                break;
                            case '*':
                                res.add(l * r);
                                break;
                        }
                    }
                }
            }
        }
        return res;
    }
    
    private boolean isNumber(String input) {
        for (int i = 0; i < input.length(); i++) {
            if (!Character.isDigit(input.charAt(i))) return false;
        }
        return true;
    }
}
```

## 215 Kth Largest Element in an Array

```java
class Solution {
    public int findKthLargest(int[] nums, int k) {
        int lo = 0, hi = nums.length - 1;
        while (lo < hi) {
            int i = partition(nums, lo, hi);
            if (i + 1 == k) return nums[i];
            else if (i + 1 < k) lo = i + 1;
            else hi = i - 1;
        }
        return nums[lo];
    }
    
    
    private int partition(int[] nums, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = nums[lo];
        while (true) {
            // find item on lo to swap
            while (nums[++i] > v) {
                if (i == hi) break;
            }
            
            // find item on hi to swap
            while (v > nums[--j]) {
                if (j == lo) break;
            }
            
            if (i >= j) break;
            
            exch(nums, i, j);
        }
        
        // put partitioning item v at a[j]
        exch(nums, lo, j);
        
        // now, a[lo .. j-1] <= a[j] <= a[j+1 .. hi]
        return j;
    }
    
    private void exch(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
```

## Count of Smaller Number After Self

```java
class Solution {
    public List<Integer> countSmaller(int[] nums) {
        Integer[] res = new Integer[nums.length];
        TreeNode root = null;
        for (int i = nums.length - 1; i >= 0; i--) {
            root = insert(root, nums[i], res, i, 0);
        }
        return Arrays.asList(res);
    }
    
    private TreeNode insert(TreeNode root, int val, Integer[] res, int index, int preSum) {
        if (root == null) {
            res[index] = preSum;
            root = new TreeNode(val);
            return root;
        } else if (val >= root.val) {
            // 插入右边
            root.right = insert(root.right, val, res, index, preSum + root.count + (val > root.val ? 1 : 0));
        } else {
            // 插入左边
            root.count++;
            root.left = insert(root.left, val, res, index, preSum);
        }
        return root;
    }
}

class TreeNode {
    int val;
    int count;
    TreeNode left, right;
    public TreeNode(int val) {
        this.val = val;
    }
}
```

## 327 Count of Range Sum

```java
class Solution {
    public int countRangeSum(int[] nums, int lower, int upper) {
        long[] preSum = new long[nums.length+1];
        for (int i = 1; i <= nums.length; i++) {
            preSum[i] = preSum[i-1] + (long )nums[i-1];
        }
        // System.out.println(Arrays.toString(preSum));
        long[] aux = new long[preSum.length];
        int res = mergeSort(preSum, aux, 0, preSum.length, lower, upper);
        // System.out.println(Arrays.toString(preSum));
        return res;
    }
    
    private int mergeSort(long[] preSum, long[] aux, int lo, int hi, int lower, int upper) {
        if (lo >= hi - 1) return 0;
        int count = 0;
        int mid = lo + (hi - lo) / 2;
        int left = mergeSort(preSum, aux, lo, mid, lower, upper);
        int right = mergeSort(preSum, aux, mid, hi, lower, upper);
        System.arraycopy(preSum, lo, aux, lo, hi-lo);
        // int i = lo, j = mid;
        // while (i < mid || j < hi) {
        //     if (j == hi || (i < mid && aux[i] < aux[j])) {
        //         preSum[i+j-mid] = aux[i];
        //         if (j != hi && aux[i] + lower <= aux[j] && aux[j] >= aux[i] + upper) count += j - i;
        //         i++;
        //     } else {
        //         if (i != mid && aux[i] + lower <= aux[j] && aux[j] >= aux[i] + upper) count += j - i;
        //         preSum[i+j-mid] = aux[j];
        //         j++;
        //     }
        // }
        int j = mid, k = mid, m = mid;
        for (int i = lo, r = lo; i < mid; i++, r++) {
            while (k < hi && aux[i] + lower > aux[k]) k++;
            while (j < hi && aux[i] + upper >= aux[j]) j++;
            while (m < hi && aux[i] >= aux[m]) preSum[r++] = aux[m++];
            preSum[r] = aux[i];
            count += j - k;
        }
        return count + left + right;
    }
}
```

## 227 Basic Calculator II

```java
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        int ind = 0;
        char operator = '+';
        while (ind < s.length()) {
            if (Character.isDigit(s.charAt(ind))) {
                int num = 0;
                while (ind < s.length() && Character.isDigit(s.charAt(ind))) {
                    num = num * 10 + s.charAt(ind) - '0';
                    ind++;
                }
                int p1 = 0;
                switch (operator) {
                    case '/':
                        p1 = stack.pop();
                        stack.push(p1 / num);
                        break;
                    case '*':
                        p1 = stack.pop();
                        stack.push(p1 * num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    default:
                        stack.push(num);
                }
            } else if (s.charAt(ind) == '-') {
                // 减号怎么处理
                operator = '-';
                ind++;
            } else if (s.charAt(ind) == '*') {
                operator = '*';
                ind++;
            } else if (s.charAt(ind) == '/') {
                operator = '/';
                ind++;
            } else if (s.charAt(ind) == '+') {
                operator = '+';
                ind++;
            } else {
                ind++;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.peek();
            stack.pop();
        }
        return res;
    }
}
```

## 493 Reverse Pairs

```java
// TLE solution
class Solution {
    public int reversePairs(int[] nums) {
        int[] aux = new int[nums.length];
        return mergeSort(nums, aux, 0, nums.length);
    }
    
    private int mergeSort(int[] nums, int[] aux, int lo, int hi) {
        if (lo >= hi - 1) return 0;
        int mid = lo + (hi - lo) / 2;
        int count = 0;
        int left = mergeSort(nums, aux, lo, mid);
        int right = mergeSort(nums, aux, mid, hi);
        System.arraycopy(nums, lo, aux, lo, hi - lo);
        for (int i = lo, j = mid, r = lo; i < mid; i++, r++) {
            // we can do better here
            int c = hi - 1;
            while (c >= mid && 2L * aux[c] >= (long )aux[i]) c--;
            while (j < hi && aux[j] <= aux[i]) nums[r++] = aux[j++];
            nums[r] = aux[i];
            count += c - mid + 1;
        }
        // System.out.println(Arrays.toString(nums));
        return count + left + right;
    }
}

// AC solution
class Solution {
    public int reversePairs(int[] nums) {
        int[] aux = new int[nums.length];
        return mergeSort(nums, aux, 0, nums.length);
    }
    
    private int mergeSort(int[] nums, int[] aux, int lo, int hi) {
        if (lo >= hi - 1) return 0;
        int mid = lo + (hi - lo) / 2;
        int count = 0;
        int left = mergeSort(nums, aux, lo, mid);
        int right = mergeSort(nums, aux, mid, hi);
        System.arraycopy(nums, lo, aux, lo, hi - lo);
        for (int i = lo, j = mid, r = lo; i < mid; i++, r++) {
            // int c = hi - 1;
            // while (c >= mid && 2L * aux[c] >= (long )aux[i]) c--;
            count += upper_bound(aux, mid, hi, (long )aux[i], 2L) - mid;
            while (j < hi && aux[j] <= aux[i]) nums[r++] = aux[j++];
            nums[r] = aux[i];
        }
        // System.out.println(Arrays.toString(nums));
        return count + left + right;
    }
    
    private int upper_bound(int[] aux, int lo, int hi, long key, long times) {
        int first = lo, len = hi - lo;
        while (len > 0) {
            int half = len >> 1;
            int mid = first + half;
            if (times * aux[mid] >= key) {
                len = half;
            } else {
                first = mid + 1;
                len = len - half - 1;
            }
        }
        return first;
    }
}
```

## 218 The Skyline Problem

```java
class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        List<int[]> res = new ArrayList<>();
        List<int[]> heights = new ArrayList<>();
        for (int[] b : buildings) {
            heights.add(new int[]{b[0], -b[2]});
            heights.add(new int[]{b[1], b[2]});
        }
        Collections.sort(heights, (a, b) -> (a[0] == b[0]) ? a[1] - b[1] : a[0] - b[0]);
        // String up = "", down = "";
        // for (int i = 0; i < heights.size(); i++) {
        //     up += heights.get(i)[0] + "\t";
        //     down += heights.get(i)[1] + "\t";
        // }
        // System.out.println(up + "\n" + down);
        TreeMap<Integer, Integer> map = new TreeMap<>(Collections.reverseOrder());
        map.put(0, 1);
        int prev = 0;
        for (int[] h : heights) {
            if (h[1] < 0) {
                // TODO: left
                Integer v = map.get(-h[1]);
                int c = v == null ? 1 : v + 1;
                map.put(-h[1], c);
            } else {
                // TODO: right
                Integer v = map.get(h[1]);
                if (v != null && v == 1) {
                    map.remove(h[1]);
                } else {
                    map.put(h[1], v-1);
                }
            }
            int curv = map.firstKey();
            if (curv != prev) {
                res.add(new int[]{h[0], curv});
                prev = curv;
            }
        }
        return res;
    }
}
// (2, -10), (9, 10), (3, -15), (7, 15), (5, -12), (12, -12), (15, -10), (20, 10)
// sort之后怎么分
// (2, -10), (3, -15), (5, -12), (7, 15), (9, 10), (12, -12), (15, -10), (20, 10);
```