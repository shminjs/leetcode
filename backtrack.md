# Backtrack

## 784 Letter Case Permutaion

```java
class Solution {
    public List<String> letterCasePermutation(String S) {
        List<String> res = new ArrayList<>();
        dfs(S, 0, "", res);
        return res;
    }
    
    private void dfs(String S, int start, String holder, List<String> res) {
        if (start == S.length()) {
            res.add(holder);
            return;
        } else {
            if (Character.isDigit(S.charAt(start))) {
                dfs(S, start + 1, holder + S.charAt(start), res);
            } else if (Character.isUpperCase(S.charAt(start))) {
                dfs(S, start + 1, holder + S.charAt(start), res);
                dfs(S, start + 1, holder + Character.toLowerCase(S.charAt(start)), res);
            } else {
                dfs(S, start + 1, holder + S.charAt(start), res);
                dfs(S, start + 1, holder + Character.toUpperCase(S.charAt(start)), res);
            }
        }
    }
}
```

## 401 Binary Watch

```java
class Solution {
    static int[] h = {1, 2, 4, 8};
    static int[] m = {1, 2, 4, 8, 16, 32};
    public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i <= 3 && i <= num; i++) {
            List<String> hours = new ArrayList<>();
            dfsHour(i, 0, 0, hours);
            List<String> minutes = new ArrayList<>();
            dfsMinutes(Math.max(0, num-i), 0, 0, minutes);
            for (String hour : hours) {
                for (String minute : minutes) {
                    res.add(hour + ":" + minute);
                }
            }
        }
        return res;
    }
    
    private void dfsHour(int num, int start, int current, List<String> res) {
        if (num < 0 || start > h.length) {
            return;
        } else if (num == 0) {
            if (current < 12) res.add(Integer.toString(current));
            return;
        } else if (start < h.length) {
            dfsHour(num-1, start + 1, current + h[start], res);
            dfsHour(num, start + 1, current, res);
        }
    }
    
    private void dfsMinutes(int num, int start, int current, List<String> res) {
        if (num < 0 || start > m.length) {
            return;
        } else if (num == 0) {
            if (current < 60) res.add((current < 10 ? "0" : "") + Integer.toString(current));
            return;
        } else if (start < m.length) {
            dfsMinutes(num-1, start + 1, current + m[start], res);
            dfsMinutes(num, start + 1, current, res);
        }
    }
}
```

## 526 Beautiful Arrangement

```java
class Solution {
    int count = 0;
    public int countArrangement(int N) {
        boolean[] visited = new boolean[1 + N];
        dfs(N, 0, visited);
        return count;
    }
    
    private void dfs(int N, int start, boolean[] visited) {
        if (start == N) {
            count++;
            return;
        } else {
            for (int i = 1; i <= N; i++) {
                if (!visited[i]) {
                    if (i % (start+1) == 0 || (start+1) % i == 0) {
                        visited[i] = true;
                        dfs(N, start + 1, visited);
                        visited[i] = false;
                    }
                }
            }
        }
    }
}
```

## 89 Gray Code

```java
class Solution {
    public List<Integer> grayCode(int n) {
        if (n == 0) return new ArrayList<Integer>(){{ add(0);}};
        if (n == 1) return new ArrayList<Integer>() {{ add(0); add(1); }};
        List<Integer> sub = grayCode(n-1);
        List<Integer> res = new ArrayList<>();
        res.addAll(sub);
        int base = (int) Math.pow(2, n-1);
        for (int i = sub.size()-1; i >= 0; i--) {
            res.add(base + sub.get(i));
        }
        return res;
    }
}
```

## 77 Combinations

```java
class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> res = new ArrayList<>();
        backtrack(n, k, 1, new ArrayList<>(), res);
        return res;
    }
    
    private void backtrack(int n, int k, int start, List<Integer> sub, List<List<Integer>> res) {
        if (sub.size() > k) {
            return;
        } else if (sub.size() == k) {
            res.add(new ArrayList<>(sub));
            return;
        }
        for (int i = start; i <= n; i++) {
            sub.add(i);
            backtrack(n, k, i + 1, sub, res);
            sub.remove(sub.size()-1);
        }
    }
}
```

## 47 Permutations II

```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        backtrack(nums, new boolean[nums.length], new ArrayList<>(), res);
        return res;
    }
    
    private void backtrack(int[] nums, boolean[] used, List<Integer> sub, List<List<Integer>> res) {
        if (sub.size() == nums.length) {
            res.add(new ArrayList<>(sub));
            return;
        }
        for (int i = 0; i < nums.length; i++)  {
            if (used[i] || i > 0 && nums[i] == nums[i-1] && !used[i-1]) continue;
            used[i] = true;
            sub.add(nums[i]);
            backtrack(nums, used, sub, res);
            sub.remove(sub.size()-1);
            used[i] = false;
        }
    }
}
```

## 51 N-Queens

```java
class Solution {
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> res = new ArrayList<>();
        backtrack(n, 0, new ArrayList<>(), res);
        return res;
    }
    
    private void backtrack(int n, int start, List<Index> sub, List<List<String>> res) {
        if (start == n) {
            // TODO: 针对sub里面的数字转换String
            List<String> c = new ArrayList<>();
            String base = "";
            for (int i = 0; i < n; i++) base += '.';
            for (int i = 0; i < sub.size(); i++) {
                StringBuilder sb = new StringBuilder(base);
                sb.setCharAt(sub.get(i).j, 'Q');
                c.add(sb.toString());
            }
            res.add(c);
            return;
        }
        for (int i = 0; i < n; i++) {
            // 判断是否冲突
            boolean flag = true;
            for (int j = sub.size()-1; j >= 0; j--) {
                Index cmp = sub.get(j);
                if (cmp.j == i || Math.abs(cmp.i-start) == Math.abs(cmp.j-i)) {
                    flag = false;
                    break;
                }
            }
            if (!flag) continue;
            sub.add(new Index(start, i));
            backtrack(n, start + 1, sub, res);
            sub.remove(sub.size()-1);
        }
    }
}

class Index {
    int i;
    int j;
    public Index(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
```

## 52 N-Queens II

```java
class Solution {
    int count = 0;
    public int totalNQueens(int n) {
        backtrack(n, 0, new ArrayList<>());
        return count;
    }
    
    private void backtrack(int n, int start, List<Index> sub) {
        if (start == n) {
            count++;
            return;
        }
        for (int i = 0; i < n; i++) {
            // 判断是否冲突
            boolean flag = true;
            for (int j = sub.size()-1; j >= 0; j--) {
                Index cmp = sub.get(j);
                if (cmp.j == i || Math.abs(cmp.i-start) == Math.abs(cmp.j-i)) {
                    flag = false;
                    break;
                }
            }
            if (!flag) continue;
            sub.add(new Index(start, i));
            backtrack(n, start + 1, sub);
            sub.remove(sub.size()-1);
        }
    }
}

class Index {
    int i;
    int j;
    public Index(int i, int j) {
        this.i = i;
        this.j = j;
    }
}
```

## 37 Sudoku Solver

```java
class Solution {
    static int N = 9;
    public void solveSudoku(char[][] board) {
        List<int[]> blank = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == '.') {
                    blank.add(new int[]{i, j});
                }
            }
        }
        List<Integer> res = new ArrayList<>();
        dfs(board, blank, 0);
    }
    
    private boolean dfs(char[][] board, List<int[]> blank, int start) {
        if (start == blank.size()) {
            return true;
        }
        int x = blank.get(start)[0], y = blank.get(start)[1];
        boolean[] visited = new boolean[N+1];
        // 上面
        for (int i = 0; i < 9; i++) {
            if (board[i][y] != '.') {
                visited[board[i][y]-'0'] = true;
            }
        }
        // 左边
        for (int j = 0; j < 9; j++) {
            if (board[x][j] != '.') {
                visited[board[x][j]-'0'] = true;
            }
        }
        // 方格内
        int xm = x / 3 * 3, ym = y / 3 * 3;
        for (int i = xm; i < xm + 3; i++) {
            for (int j = ym; j < ym + 3; j++) {
                if (board[i][j] != '.') {
                    visited[board[i][j]-'0'] = true;
                }
            }
        }
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                board[x][y] = (char )('0' + i);
                if (dfs(board, blank, start + 1)) return true;
                board[x][y] = '.';
            }
        }
        return false;
    }
}
// 倒不是难做，关键点在于找到一个 . 的位置来快速确定某个数是否合适
// 如果到最后都不合适的话，回退时是否能把握正确的状态
// 所以这些东西怎么做
```

## 60 Permuation Sequence 

```java
class Solution {
    public String getPermutation(int n, int k) {
        String res = "";
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }
        int[] dp = new int[n+1];
        dp[0] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = i * dp[i-1];
        }
        k--;
        for (int i = n; i >= 1; i--) {
            int index = k / dp[i-1];
            res += Integer.toString(nums.get(index));
            nums.remove(index);
            k = k - index * dp[i-1];
        }
        return res;
    }
}
```

## 211 Add and Search Word - Data structure design

```java
class WordDictionary {
    
    private Trie trie;

    /** Initialize your data structure here. */
    public WordDictionary() {
        trie = new Trie();
    }
    
    /** Adds a word into the data structure. */
    public void addWord(String word) {
        trie.put(word);
    }
    
    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        return trie.search(word);
    }
}

class Trie {
    private static int R = 26;
    
    private Node root;
    
    private static class Node {
        Boolean val;
        Node[] next = new Node[R];
    }
    
    public Trie() {}
    
    public void put(String key) {
        root = put(root, key, 0);
    }
    
    public Node put(Node x, String key, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.val = true;
            return x;
        }
        int index = key.charAt(d) - 'a';
        x.next[index] = put(x.next[index], key, d+1);
        return x;
    }
    
    public Boolean get(String key) {
        Boolean val = get(root, key, 0);
        if (val == null) {
            return false;
        } else {
            return true;
        }
    }
    
    private Boolean get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) {
            return x.val;
        }
        int index = key.charAt(d) - 'a';
        return get(x.next[index], key, d+1);
    }
    
    public boolean search(String key) {
        return search(root, key, 0);
    }
    
    private Boolean search(Node x, String key, int d) {
        if (x == null) return false;
        if (d == key.length()) {
            if (x.val == null) return false;
            else return x.val;
        }
        char ch = key.charAt(d);
        if (ch == '.') {
            for (int i = 0; i < 26; i++) {
                if (search(x.next[i], key, d+1)) {
                    return true;
                }
            }
            return false;
        } else {
            return search(x.next[ch-'a'], key, d+1);
        }
    }
}

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
```

## 212 Word Search II

```java
class Solution {
    static int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        Map<Character, List<int[]>> map = new HashMap<>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0;j < board[0].length; j++) {
                if (!map.containsKey(board[i][j])) {
                    map.put(board[i][j], new ArrayList<>());
                }
                map.get(board[i][j]).add(new int[]{i, j});
            }
        }
        Set<String> set = new HashSet<>();
        for (String word : words) {
            set.add(word);
        }
        for (String word : set) {
            if (map.containsKey(word.charAt(0))) {
                    for (int[] inds : map.get(word.charAt(0))) {
                    boolean[][] visited = new boolean[board.length][board[0].length];
                    if (dfs(board, visited, word, 0, inds[0], inds[1])) {
                        res.add(word);
                        break;
                    }
                }
            }
        }
        return res;
    }
    
    private boolean dfs(char[][] board, boolean[][] visited, String word, int d, int i, int j) {
        if (d == word.length()) return true;
        else if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j] || d < word.length() && word.charAt(d) != board[i][j]) return false;
        else {
            visited[i][j] = true;
            for (int[] dir : dirs) {
                if (dfs(board, visited, word, d + 1, i + dir[0], j + dir[1])) {
                    return true;
                }
            }
            visited[i][j] = false;
        }
        return false;
    }
}
```