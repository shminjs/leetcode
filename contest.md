# Contest

## Contest 58

### 724 Find Pivot Index

```java
class Solution {
    public int pivotIndex(int[] nums) {
        if (nums.length == 0) return -1;
        int[] right = new int[nums.length];
        for (int i = nums.length - 1; i >= 0; i--) {
            if (i == nums.length - 1) {
                right[i] = 0;
            } else {
                right[i] = right[i + 1] + nums[i+1];
            }
        }
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (preSum == right[i]) {
                return i;
            }
            preSum += nums[i];
        }
        return -1;
    }
}
```

### 725 Split Linked List in Parts

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode[] splitListToParts(ListNode root, int k) {
        ListNode[] res = new ListNode[k];
        if (root == null) return res;
        int len = 0;
        ListNode p = root;
        while (p != null) {
            len++;
            p = p.next;
        }
        int per = (int) Math.ceil(len * 1.0 / k);
        int[] sizes = new int[k];
        for (int i = 0; i < k; i++) {
            if ((len - per) >= (per - 1) * (k - i - 1)) {
                sizes[i] = per;
                len = len - per;
            } else {
                sizes[i] = per - 1;
                len = len - (per - 1);
            }
        }
        res[0] = root;
        int ind = 1;
        int count = sizes[0];
        p = root;
        ListNode pre = null;
        while (p != null) {
            if (count == 0) {
                count = sizes[ind];
                pre.next = null;
                res[ind++] = p;
                continue;
            } else {
                count--;
            }
            pre = p;
            p = p.next;
        }
        return res;
    }
}
```

### 726 Number of Atoms

```java
class Solution {
    public String countOfAtoms(String formula) {
        TreeMap<String, Integer> map = new TreeMap<>();
        int times = 1;
        count(formula, 1, map);
        // 对map进行处理
        String res = "";
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            res += entry.getKey();
            if (entry.getValue() > 1) {
                res += entry.getValue();
            }
        }
        return res;
    }
    
    private void count(String formula, int times, TreeMap<String, Integer> map) {
        if (!formula.contains("(")) {
            countNative(formula, times, map);
        } else {
            // 找到真正的位置
            boolean flag = false;
            int p = 0;
            int count = 0;
            int start = 0;
            while (p < formula.length()) {
                if (formula.charAt(p) == '(') {
                    if (!flag && count == 0) {
                        countNative(formula.substring(start, p), times, map);
                        start = p + 1;
                    }
                    count++;
                    flag = true;
                    p++;
                } else if (formula.charAt(p) == ')') {
                    count--;
                    if (count == 0) {
                        flag = false;
                        // 可以计算了
                        String subformula = formula.substring(start, p);
                        int tcount = 0;
                        while (p + 1 < formula.length() && Character.isDigit(formula.charAt(p+1))) {
                            p++;
                            tcount = tcount * 10 + formula.charAt(p) - '0';
                        }
                        if (tcount == 0) tcount = 1;
                        count(subformula, times * tcount, map);
                        start = p + 1;
                    }
                    p++;
                } else {
                    p++;
                }
            }
            countNative(formula.substring(start, formula.length()), times, map);
        }
    }
    
    // 不包括任何括号
    private void countNative(String formula, int times, TreeMap<String, Integer> map) {
        if (formula.equals("")) return;
        else {
            int ind = 0;
            while (ind < formula.length()) {
                char ch = formula.charAt(ind);
                if (Character.isUpperCase(ch)) {
                    int start = ind;
                    while (ind + 1 < formula.length() && isLowerOrZero(formula.charAt(ind+1))) {
                        ind++;
                    }
                    String atom = formula.substring(start, ind + 1);
                    int count = 0;
                    while (ind + 1 < formula.length() && Character.isDigit(formula.charAt(ind+1))) {
                        ind++;
                        count = count * 10 + formula.charAt(ind) - '0';
                    }
                    if (count == 0) count = 1;
                    map.put(atom, map.getOrDefault(atom, 0) + count * times);
                    ind++;
                }
            }
        }
    }
    
    private boolean isLowerOrZero(char ch) {
        return ch == '0' || Character.isLowerCase(ch);
    }
}
```

### 727 Minimum Window Subsequence

```java
class Solution {
    public String minWindow(String S, String T) {
        int min = Integer.MAX_VALUE;
        String res = "";
        for (int i = 0; i < S.length(); i++) {
            if (S.charAt(i) == T.charAt(0)) {
                // 从这开始
                int index = 0;
                for (int j = i; j < S.length(); j++) {
                    if(S.charAt(j) == T.charAt(index)) {
                        index++;
                    }
                    if (index == T.length()) {
                        if (j + 1 - i < min) {
                            min = j + 1 - i;
                            res = S.substring(i, j+1);
                        }
                        break;
                    }
                }
            }
        }
        return res;
    }
}
```

## contest 60

### 733 Flood Fill

```java
class Solution {
    private static final int[][] dirs = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] visited = new boolean[image.length][image[0].length];
        floodFill(image, visited, sr, sc, newColor, image[sr][sc]);
        return image;
    }
    
    private void floodFill(int[][] image, boolean[][] visited, int sr, int sc, int newColor, int cmpColor) {
        if (sr < 0 || sr == image.length || sc < 0 || sc == image[0].length || visited[sr][sc] || image[sr][sc] != cmpColor) return;
        visited[sr][sc] = true;
        image[sr][sc] = newColor;
        for (int[] dir : dirs) {
            floodFill(image, visited, sr + dir[0], sc + dir[1], newColor, cmpColor);
        }
    }
}
```

### 734 Sentence Similiarity

```java
class Solution {
    public boolean areSentencesSimilar(String[] words1, String[] words2, String[][] pairs) {
        if (words1.length != words2.length) return false;
        Map<String, Set<String>> map = new HashMap<>();
        for (String[] pair : pairs) {
            if (!map.containsKey(pair[0])) {
                map.put(pair[0], new HashSet<>());
            }
            if (!map.containsKey(pair[1])) {
                map.put(pair[1], new HashSet<>());
            }
            map.get(pair[0]).add(pair[1]);
            map.get(pair[1]).add(pair[0]);
        }
        for (int i = 0; i < words1.length; i++) {
            if (!words1[i].equals(words2[i])) {
                if (map.get(words1[i]) == null || !map.get(words1[i]).contains(words2[i])) return false;
            }
        }
        return true;
    }
}
```

### 735 Asteroid Collison

```java
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> list = new LinkedList<>();
        for (int i = 0; i < asteroids.length; i++) {
            if (list.isEmpty()) {
                list.push(asteroids[i]);
            } else {
                while (!list.isEmpty()) {
                    int k = list.peek();
                    if ((k < 0 && asteroids[i] < 0) || (k > 0 && asteroids[i] > 0) || (k < 0 && asteroids[i] > 0)) {
                        list.push(asteroids[i]);
                        break;
                    } else {
                        if (Math.abs(k) > Math.abs(asteroids[i])) {
                            break;
                        } else if (Math.abs(k) == Math.abs(asteroids[i])) {
                            list.pop();
                            break;
                        } else {
                            list.pop();
                            if (list.isEmpty()) {
                                list.push(asteroids[i]);
                                break;
                            }
                        }
                    }
                }
            }
        }
        int[] res = new int[list.size()];
        int ix = list.size()-1;
        for (Integer item : list) {
            res[ix--] = item;
        }
        return res;
    }
}
```

### 736 Parse Lisp Expression

```java
class Solution {
    public int evaluate(String expression) {
        Map<String, Integer> map = new HashMap<>();
        return evaluate(expression, map);
    }
    
    private int evaluate(String expression, Map<String, Integer> map) {
        if (expression.startsWith("(let")) {
            // let语句
            Map<String, Integer> envir = new HashMap<>(map);
            return let(expression, envir);
        } else if (expression.startsWith("(add")) {
            // add语句
            Map<String, Integer> envir = new HashMap<>(map);
            return add(expression, envir);
        } else if (expression.startsWith("(mul")) {
            // mul语句
            Map<String, Integer> envir = new HashMap<>(map);
            return mul(expression, envir);
        } else if (map.containsKey(expression)) {
            return map.get(expression);
        } else {
            return Integer.parseInt(expression);
        }
    }
    
    private int let(String expression, Map<String, Integer> envir) {
        String v = "";
        int ind = "(let".length();  // 从第一个空格开始，后面需要根据情况分割v,e
        while (ind < expression.length()) {
            if (expression.charAt(ind) == ' ') {
                // 空格开始，求解该式
                ind++;
                int count = 0;
                int start = ind;
                while (ind < expression.length() - 1) {
                    // 保证能把括号囊进入(let a1 3 b2 (add a1 1) b2)
                    if (expression.charAt(ind) == '(') {
                        count++;
                    } else if (expression.charAt(ind) == ')') {
                        count--;
                        if (count == 0) {
                            // 此时应该肯定可以退出了
                            ind++;
                            break;
                        }
                    } else if (expression.charAt(ind) == ' ' && count == 0) {
                        break;
                    }
                    ind++;
                }
                String e = expression.substring(start, ind);
                if (v.equals("")) {
                    v = e;
                } else {
                    envir.put(v, evaluate(e, envir));
                    v = "";
                }
            } else {
                ind++;
            }
        }
        return evaluate(v, envir);
    }
    
    private int add(String expression, Map<String, Integer> envir) {
        // (add 1 2)
        String e1 = "", e2 = "";
        int ind = "(add".length();
        while (ind < expression.length()) {
            if (expression.charAt(ind) == ' ') {
                ind++;
                int count = 0;
                int start = ind;
                while (ind < expression.length() - 1) {
                    // 保证能把括号囊进入(let a1 3 b2 (add a1 1) b2)
                    if (expression.charAt(ind) == '(') {
                        count++;
                    } else if (expression.charAt(ind) == ')') {
                        count--;
                        if (count == 0) {
                            // 此时应该肯定可以退出了
                            ind++;
                            break;
                        }
                    } else if (expression.charAt(ind) == ' ' && count == 0) {
                        break;
                    }
                    ind++;
                }
                if (e1.equals("")) {
                    e1 = expression.substring(start, ind);
                } else {
                    e2 = expression.substring(start, ind);
                    break;
                }
            } else {
                ind++;
            }
        }
        return evaluate(e1, envir) + evaluate(e2, envir);
    }
    
    private int mul(String expression, Map<String, Integer> envir) {
        // (mul 1 2)
        String e1 = "", e2 = "";
        int ind = "(add".length();
        while (ind < expression.length()) {
            if (expression.charAt(ind) == ' ') {
                ind++;
                int count = 0;
                int start = ind;
                while (ind < expression.length() - 1) {
                    // 保证能把括号囊进入(let a1 3 b2 (add a1 1) b2)
                    if (expression.charAt(ind) == '(') {
                        count++;
                    } else if (expression.charAt(ind) == ')') {
                        count--;
                        if (count == 0) {
                            // 此时应该肯定可以退出了
                            ind++;
                            break;
                        }
                    } else if (expression.charAt(ind) == ' ' && count == 0) {
                        break;
                    }
                    ind++;
                }
                if (e1.equals("")) {
                    e1 = expression.substring(start, ind);
                } else {
                    e2 = expression.substring(start, ind);
                    break;
                }
            } else {
                ind++;
            }
        }
        return evaluate(e1, envir) * evaluate(e2, envir);
    }
}
```

## Contest 61

### 739 Daily Temperatures

```java
class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if (temperatures[j] > temperatures[i]) {
                    res[i] = j - i;
                    break;
                }
            }
        }
        return res;
    }
}
```

### 738 Monotone Increasing Digis

```java
class Solution {
    public int monotoneIncreasingDigits(int N) {
        char[] nums = Integer.toString(N).toCharArray();
        char pre = '0';
        int i = 0;
        for (; i < nums.length; i++) {
            if (nums[i] < pre) {
                break;
            }
            pre = nums[i];
        }
        if (i == nums.length) return N;
        i--;
        for (; i >= 0; ) {
            if (i-1 >= 0 && nums[i] == nums[i-1]) {
                i--;
            } else {
                break;
            }
        }
        int res = 0;
        for (int j = 0; j < i; j++) {
            res = res * 10 + nums[j] - '0';
        }
        res = res * 10 + nums[i] - '0' - 1;
        for (int j = i + 1; j < nums.length; j++) {
            res = res * 10 + 9;
        }
        return res;
    }
}
```

### 740 Delete and Earn

```java
class Solution {
    public int deleteAndEarn(int[] nums) {
        // 暴力回溯试试效果
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + num);
        }
        int[] aux = new int[10000];
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            aux[entry.getKey()-1] = entry.getValue();
        }
        int[] dp = new int[10000];
        for (int i = 0; i < aux.length; i++) {
            dp[i] = Math.max((i-2>=0? dp[i-2]:0) + aux[i], (i-1>=0? dp[i-1]:0));
        }
        return dp[10000-1];
    }
}
```

### 741 Cherry Pickup

```java
// no solution 暂时
```

## Contest 62

### 744 Find Smallest Letter Greater Than Target

```java
class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int l = 0, len = letters.length;
        while (len > 0) {
            int half = len >> 1;
            int m = l + half;
            if (letters[m] > target) {
                len = half;
            } else {
                l = m + 1;
                len = len - half - 1;
            }
        }
        if (l == letters.length) l = 0;
        return letters[l];
    }
}
// 这个公式经常用到，需要注意。与传统的相比，需要注意的是l不仅要加1，len也需要减半再减去1。对应于lo, hi，就是lo和hi同步变化了。
```

### 743 Network Delay Time

```java
// 这一题没做出来的原因很简单，就是times的含义没理解，是一个m * 3的二维数组，我一直以为是一个m * 2的数组，这样子就与(u, v, w)联系不起来
// 因此也就没做出来，其实这一题就是迪杰斯特拉算法。
class Solution {
    public int networkDelayTime(int[][] times, int N, int K) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            int u = times[i][0];
            if (!map.containsKey(u)) {
                map.put(u, new ArrayList<>());
            }
            map.get(u).add(i);
        }
        int[] visited = new int[N+1];
        Arrays.fill(visited, Integer.MAX_VALUE);
        Queue<Integer> queue = new LinkedList<>();
        visited[K] = 0;
        queue.offer(K);
        while (!queue.isEmpty()) {
            int u = queue.poll();
            List<Integer> sub = map.getOrDefault(u, new ArrayList<>()); // 给出的是Index，肯定存在
            for (Integer i : sub) {
                if (times[i][2] + visited[u] < visited[times[i][1]]) {
                    visited[times[i][1]] = times[i][2] + visited[u];
                    queue.offer(times[i][1]);
                }
            }
        }
        int ans = Integer.MIN_VALUE;
        for (int i = 1; i <= N; i++) {
            ans = Math.max(ans, visited[i]);
        }
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
```

### 742 Cloest Leaf in a Binary Tree

```java
// 好吧这一题想的太多，动手得太少。就是你直接上手干也能成功啊。最后刚过时间才提交AC，然而并没有什么卵用
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    boolean flag = false;
    int min = -1;
    int distance = Integer.MAX_VALUE;
    public int findClosestLeaf(TreeNode root, int k) {
        traverse(root, k);
        return min;
    }
    
    private int[] traverse(TreeNode root, int k) {
        if (root == null) return new int[]{-1, Integer.MAX_VALUE};
        int[] left = traverse(root.left, k);
        int[] right = traverse(root.right, k);
        if (flag) {
            if (left[0] != k && right[0] != k) {
                if (left[1] == Integer.MAX_VALUE && right[1] == Integer.MAX_VALUE) {
                    return new int[]{root.val, 1};
                } else if (left[1] < right[1]) {
                    return new int[]{left[0], left[1] + 1};
                } else {
                    return new int[]{right[0], right[1] + 1};
                }
            } else if (left[0] == k) {
                if (right[1] != Integer.MAX_VALUE) {
                    if (left[1] + right[1] < distance) {
                        min = right[0];
                        distance = left[1] + right[1];
                    }
                }
               return new int[]{k, left[1] + 1}; 
            } else {
                // right[0] == k
                if (left[1] != Integer.MAX_VALUE) {
                    if (left[1] + right[1] < distance) {
                        min = left[0];
                        distance = left[1] + right[1];
                    }
                }
                return new int[]{k, right[1] + 1};
            }
        } else {
            if (root.val == k) {
                flag = true;
                if (left[1] == Integer.MAX_VALUE && right[1] == Integer.MAX_VALUE) {
                    min = k;
                    distance = 0;
                    return new int[]{k, 1};
                } else {
                    if (left[1] < right[1]) {
                        min = left[0];
                        distance = left[1];
                    } else {
                        min = right[0];
                        distance = right[1];
                    }
                    return new int[]{k, 1};
                }
            } else {
                if (left[1] == Integer.MAX_VALUE && right[1] == Integer.MAX_VALUE) {
                    return new int[] {root.val, 1};
                } else if (left[1] < right[1]) {
                    return new int[]{left[0], left[1] + 1};
                } else {
                    return new int[]{right[0], right[1] + 1};
                }
            }
        }
    }
}

// 原解法有点累赘，应该考虑简洁一点。不过在第一次做的时候无所谓简洁与否
```

### 745 Prefix and Suffix Search 

```java
class WordFilter {
    class TrieNode{
        String word;
        TrieNode[] children;
        TrieNode(){
            word=null;
            children= new TrieNode[26];
        }
    }
    
   Map<String,Integer> map;
    TrieNode root;
    int ans=-1;
    public WordFilter(String[] words) {
        map= new HashMap<>();
        root= new TrieNode();
        for(int i=0;i<words.length;i++){ map.put(words[i],i);
                             add(words[i],root);          
                                       }
        
    }
    
    public void add(String word,TrieNode node){
        char[] wordc= word.toCharArray();
        for(int i=0;i<word.length();i++){
            int c=wordc[i]-'a';
            if(node.children[c]==null) node.children[c]=new TrieNode();
            node=node.children[c];
        }
        node.word=word;
    }
    
    public int f(String prefix, String suffix) {
        TrieNode node=find(root,prefix);
        if(node==null) return -1;
        ans=-1;
        findf(node,suffix,suffix.length());
        return ans;
    }
    
    public TrieNode find(TrieNode node, String prefix){
        for(int i=0;i<prefix.length();i++){
            int c=prefix.charAt(i)-'a';
            if(node.children[c]==null) return null;
            node=node.children[c];
        }
        return node;
    }
    
    public void findf(TrieNode node,String suffix,int len){
        if(node.word!=null){
            int start= node.word.length()-len;
            if(start>=0 && node.word.substring(start).equals(suffix)){
                if(map.get(node.word)>ans) ans=map.get(node.word);
            }
        }
        for(int i=0;i<26;i++){
            if(node.children[i]!=null) findf(node.children[i],suffix,len);
        }
    }
}
```

## 719 Find K-th Smallest Pair Distance

```java
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        
        int low = nums[1] - nums[0];
        for (int i = 1; i <= n - 2; i++) {
            low = Math.min(low, nums[i+1] - nums[i]);
        }
        
        int high = nums[n-1] - nums[0];
        
        while (low < high) {
            int mid = (low + high) >> 1;
            if (countPairs(nums, n, mid) < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    
    private int countPairs(int[] nums, int n, int mid) {
        int res = 0;
        for (int i = 0; i < n; i++) {
            res += upper_bound(nums, i, n, nums[i]+mid) - (i + 1);
        }
        return res;
    }
    
    private int upper_bound(int[] nums, int start, int size, int key) {
        int first = start, len = size - start;
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
```

## 786 K-th Smallest Prime Fraction

```java
class Solution {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        double low = 0.0, high = 1.0;
        int[] ans = new int[]{0, 1};
        while (high - low > 1e-9) {
            double mid = low + (high - low) / 2.0;
            int[] res = under(mid, A);
            if (res[0] < K) {
                low = mid;
            } else {
                ans[0] = res[1];
                ans[1] = res[2];
                high = mid;
            }
        }
        return ans;
    }
    
    private int[] under(double x, int[] primes) {
        int numer = 0, denom = 1, count = 0, i = -1;
        for (int j = 1; j < primes.length; j++) {
            while (primes[i+1] < primes[j] * x) ++i;
            
            count += i + 1;
            if (i >= 0 && numer * primes[j] < denom * primes[i]) {
                numer = primes[i];
                denom = primes[j];
            }
        }
        return new int[]{count, numer, denom};
    }
}
```

## 668 Kth Smallest Number in multiplication Table

```java
class Solution {
    public int findKthNumber(int m, int n, int k) {
        int low = 1, high = m * n;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = under(mid, m, n);
            if (count < k) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }
    
    private int under(int mid, int m, int n) {
        int ans = 0;
        for (int i = 1; i <= m; i++) {
            ans += Math.min(mid / i, n);
        }
        return ans;
    }
}
```

## 793 Preimage Size of Factorial Zeroes Function

```java
class Solution {
    public int preimageSizeFZF(int K) {
        int low = K, high = 5 * K + 1;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int count = zmta(mid);
            if (count == K) return 5;
            else if (count < K) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return 0;
    }
    
    private int zmta(int x) {
        if (x == 0) return 0;
        
        return x / 5 + zmta(x / 5);
    }
}
```

## 778 Swim in Rising Water

```java
class Solution {
    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int swimInWater(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                low = Math.min(low, grid[i][j]);
                high = Math.max(high, grid[i][j]);
            }
        }
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (canPass(mid, grid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return high;
    }
    
    private boolean canPass(int elevation, int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        dfs(grid, visited, 0, 0, elevation);
        return visited[grid.length-1][grid[0].length-1];
    }
    
    private void dfs(int[][] grid, boolean[][] visited, int i, int j, int elevation) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || visited[i][j] || grid[i][j] > elevation) {
            return;
        }
        visited[i][j] = true;
        for (int[] dir : dirs) {
            dfs(grid, visited, i + dir[0], j + dir[1], elevation);
        }
    }
}
```