# Hash Table

## 575 Distributed Candies

```java
class Solution {
    public int distributeCandies(int[] candies) {
        Arrays.sort(candies);
        int ans = 1;
        for (int i = 1; i < candies.length; i++) {
            if (candies[i] != candies[i-1]) {
                ans++;
            }
        }
        return Math.min(ans, candies.length / 2);
    }
}
```

## 463 Island Perimeter

```java
class Solution {
    public int islandPerimeter(int[][] grid) {
        int ans = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    // 开始计算，先按照最笨的方式计算
                    ans += calcu(grid, i, j);
                }
            }
        }
        return ans;
    }
    
    private int calcu(int[][] grid, int i, int j) {
        int res = 0;
        if (i - 1 == -1 || grid[i-1][j] == 0) res++;
        if (j - 1 == -1 || grid[i][j-1] == 0) res++;
        if (i + 1 == grid.length || grid[i+1][j] == 0) res++;
        if (j + 1 == grid[0].length || grid[i][j+1] == 0) res++;
        return res;
    }
}

// better solution
public class Solution {
    public int islandPerimeter(int[][] grid) {
        int islands = 0, neighbours = 0;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    islands++; // count islands
                    if (i < grid.length - 1 && grid[i + 1][j] == 1) neighbours++; // count down neighbours
                    if (j < grid[i].length - 1 && grid[i][j + 1] == 1) neighbours++; // count right neighbours
                }
            }
        }

        return islands * 4 - neighbours * 2;
    }
}
```

## 136 Single Number

```java
class Solution {
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int i = 0; i < nums.length; i++) {
            res ^= nums[i];
        }
        return res;
    }
}
```

## 690 Employee Importance

```java
/*
// Employee info
class Employee {
    // It's the unique id of each node;
    // unique id of this employee
    public int id;
    // the importance value of this employee
    public int importance;
    // the id of direct subordinates
    public List<Integer> subordinates;
};
*/
class Solution {
    public int getImportance(List<Employee> employees, int id) {
        Map<Integer, Employee> emMap = new HashMap<>();
        for (int i = 0; i < employees.size(); i++) {
            Employee em = employees.get(i);
            emMap.put(em.id, em);
        }
        int res = 0;
        Queue<Integer> q = new LinkedList<>();
        q.offer(id);
        while (!q.isEmpty()) {
            int i = q.poll();
            res += emMap.get(i).importance;
            for (int k : emMap.get(i).subordinates) {
                q.offer(k);
            }
        }
        return res;
    }
}
```

## 349 Intersection of Two Arrays

```java
class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set1.add(nums1[i]);
        }
        Set<Integer> set2 = new HashSet<>();
        for (int i = 0; i < nums2.length; i++) {
            if (set1.contains(nums2[i])) {
                set2.add(nums2[i]);
            }
        }
        int[] res = new int[set2.size()];
        int index  = 0;
        for (int item : set2) {
            res[index++] = item;
        }
        return res;
    }
}
```

## 387 First Unique Character in a String

```java
class Solution {
    public int firstUniqChar(String s) {
        Map<Character, Boolean> flags = new HashMap<>();
        for (char ch : s.toCharArray()) {
            if (flags.containsKey(ch)) {
                flags.put(ch, true);
            } else {
                flags.put(ch, false);
            }
        }
        for (int i = 0; i < s.length(); i++) {
            if (!flags.get(s.charAt(i))) {
                return i;
            }
        }
        return -1;
    }
}
```

## 242 Valid Anagram

```java
class Solution {
    public boolean isAnagram(String s, String t) {
        int[] sa = new int[26];
        int[] ta = new int[26];
        for (int i = 0; i < s.length(); i++) {
            sa[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < t.length(); i++) {
            ta[t.charAt(i)-'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (sa[i] != ta[i]) {
                return false;
            }
        }
        return true;
    }
}

// better solution
class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] counter = new int[26];

        for (int i = 0; i < s.length(); i++) {
            counter[s.charAt(i)-'a']++;
            counter[t.charAt(i)-'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if (counter[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
```

## 599 Minimum Index Sum of Two Lists

```java
class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        int max = Integer.MAX_VALUE;
        int index = -1;
        List<String> container = new ArrayList<>();
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                if (i + map.get(list2[i]) < max) {
                    max = i + map.get(list2[i]);
                    index = i;
                    container.clear();
                    container.add(list2[i]);
                } else if (i + map.get(list2[i]) == max) {
                    container.add(list2[i]);
                }
            }
        }
        String[] res = new String[container.size()];
        for (int i = 0; i < container.size(); i++) {
            res[i] = container.get(i);
        }
        return res;
    }
}
```

## 447 Number of Boomeranges

```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i != j) {
                    // begin to calculate
                    int d = distance(points, i, j);
                    // System.out.println(i + " | " + j + " | " + d);
                    map.put(d, map.getOrDefault(d, 0) + 1);
                }
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() > 1) {
                    ans += entry.getValue() * (entry.getValue() - 1);
                }
            }
        }
        return ans;
    }
    
    private int distance(int[][] points, int i, int j) {
        return (points[i][0] - points[j][0]) * (points[i][0] - points[j][0]) + (points[i][1] - points[j][1]) * (points[i][1] - points[j][1]);
    }
}
```

## 409 Longest Palindrome

```java
class Solution {
    public int longestPalindrome(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int ans = 0;
        boolean flag = false;
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            int value = entry.getValue();
            if (value % 2 == 1) {
                flag = true;
                ans += value - 1;
            } else {
                ans += value;
            }
        }
        if (flag) return ans + 1;
        else return ans;
    }
}
```

## 600 Non-negative Integers without Consecutive Ones

```java
public class Solution {
    public int findIntegers(int num) {
        int[] f = new int[32];
        f[0] = 1;
        f[1] = 2;
        for (int i = 2; i < f.length; i++)
            f[i] = f[i - 1] + f[i - 2];
        int i = 30, sum = 0, prev_bit = 0;
        while (i >= 0) {
            if ((num & (1 << i)) != 0) {
                sum += f[i];
                if (prev_bit == 1) {
                    sum--;
                    break;
                }
                prev_bit = 1;
            } else
                prev_bit = 0;
            i--;
        }
        return sum + 1;
    }
}
```

## 472 Concatenated Words

```java
class Solution {
    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> result = new ArrayList<>();
        Set<String> preWords = new HashSet<>();
        Arrays.sort(words, new Comparator<String>() {
            public int compare (String s1, String s2) {
                return s1.length() - s2.length();
            }
        });
        
        for (int i = 0; i < words.length; i++) {
            if (canForm(words[i], preWords)) {
                result.add(words[i]);
            }
            preWords.add(words[i]);
        }
        
        return result;
    }
	
    private static boolean canForm(String word, Set<String> dict) {
        if (dict.isEmpty()) return false;
        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= word.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (!dp[j]) continue;
                if (dict.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }
}
```

## 350 Intersection of Two Arrays II

```java
class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        List<Integer> con = new ArrayList<>();
        for (int num : nums2) {
            if (map.getOrDefault(num, 0) > 0) {
                con.add(num);
                map.put(num, map.get(num) - 1);
            }
        }
        int[] res = new int[con.size()];
        for (int i = 0; i < res.length; i++) {
            res[i] = con.get(i);
        }
        return res;
    }
}
```

## 202 Happy Number

```java
class Solution {
    public boolean isHappy(int n) {
        Set<Integer> set = new HashSet<>();
        while (!set.contains(n)) {
            set.add(n);
            n = calculate(n);
            if (n == 1) return true;
        }
        return false;
    }
    
    private int calculate(int n) {
        char[] nums = Integer.toString(n).toCharArray();
        int ans = 0;
        for (int i = 0; i < nums.length; i++)
            ans += (nums[i] - '0') * (nums[i] - '0');
        return ans;
    }
}
```

## Longest Harmonious Subsequence

```java
class Solution {
    public int findLHS(int[] nums) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0)+1);
        }
        Integer prek = null;
        int prev = -1;      // 仅仅表示初始化
        int max = Integer.MIN_VALUE;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (prek != null) {
                if (prek + 1 == entry.getKey()) {
                    if (prev + entry.getValue() > max) {
                        max = prev + entry.getValue();
                    }
                }
            }
            prek = entry.getKey();
            prev = entry.getValue();
        }
        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
// 其实用HashMap可能更好一点把
```

## 645 Set Mismatch

```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] res = {-1, -1};
        for (int i = 0; i < nums.length; i++) {
            int ind = Math.abs(nums[i]) - 1;
            if (nums[ind] < 0) {
                res[0] = ind + 1;
            } else {
                nums[ind] = -nums[ind];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                res[1] = i + 1;
            }
        }
        return res;
    }
}
```

## 205 Isomorphic Strings

```java
class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) return false;
        Map<Character, Character> m1 = new HashMap<>();
        Map<Character, Character> m2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (m1.containsKey(s.charAt(i))) {
                if (m1.get(s.charAt(i)) != t.charAt(i))
                    return false;
            } else {
                m1.put(s.charAt(i), t.charAt(i));
            }
            if (m2.containsKey(t.charAt(i))) {
                if (m2.get(t.charAt(i)) != s.charAt(i))
                    return false;
            } else {
                m2.put(t.charAt(i), s.charAt(i));
            }
        }
        return true;
    }
}
```

## 438 Find All Anagrams in a String

```java
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int count = p.length();
        int[] map = new int[26];
        for (int i = 0; i < p.length(); i++) {
            map[p.charAt(i)-'a']++;
        }
        for (int i = 0; i < p.length() && i < s.length(); i++) {
            if (map[s.charAt(i)-'a'] > 0) {
                count--;
            }
            map[s.charAt(i)-'a']--;
        }
        if (count == 0) res.add(0);
        for (int i = p.length(); i < s.length(); i++) {
            map[s.charAt(i-p.length())-'a']++;
            if (map[s.charAt(i-p.length())-'a'] > 0) count++;
            if (map[s.charAt(i)-'a'] > 0) {
                count--;
            }
            map[s.charAt(i)-'a']--;
            if (count == 0) {
                res.add(i-p.length()+1);
            }
        }
        return res;
    }
}
// more general solution
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int[] map = new int[26];
        List<Integer> res = new ArrayList<>();
        
        if (s == null || p == null || s.length() < p.length())
            return res;
        for (char ch : p.toCharArray()) {
            map[ch-'a']++;
        }
        
        int start = 0, end = 0, count = p.length();
        while (end < s.length()) {
            if (end - start == p.length() && map[s.charAt(start++)-'a']++ >= 0) {
                count++;
            }
            if (--map[s.charAt(end++)-'a'] >= 0) {
                count--;
            }
            if (count == 0) {
                res.add(start);
            }
        }
        return res;
    }
}
// 这里是一个系列的题目，最好我都做一遍
// https://leetcode.com/problems/minimum-window-substring/
// https://leetcode.com/problems/longest-substring-without-repeating-characters/
// https://leetcode.com/problems/substring-with-concatenation-of-all-words/
// https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/
// https://leetcode.com/problems/find-all-anagrams-in-a-string/
```

## 290 Word Pattern

```java
class Solution {
    public boolean wordPattern(String pattern, String str) {
        Map<Character, String> m1 = new HashMap<>();
        Map<String, Character> m2 = new HashMap<>();
        char[] patterns = pattern.toCharArray();
        String[] strs = str.split(" ");
        if (patterns.length != strs.length) return false;
        for (int i = 0; i < patterns.length; i++) {
            if (m1.containsKey(patterns[i])) {
                if (!m1.get(patterns[i]).equals(strs[i])) {
                    return false;
                }
            } else {
                m1.put(patterns[i], strs[i]);
            }
            if (m2.containsKey(strs[i])) {
                if (m2.get(strs[i]) != patterns[i]) {
                    return false;
                }
            } else {
                m2.put(strs[i], patterns[i]);
            }
        }
        return true;
    }
}
```

## 204 Count Primes

```java
class Solution {
    public int countPrimes(int n) {
        if (n <=  0) return 0;
        boolean[] dp = new boolean[n+1];
        dp[1] = true;
        for (int i = 2; i * i < n; i++) {
            if (!dp[i]) {
                for (int j = i; j * i <= n; j++) {
                    dp[j*i] = true; 
                }
            }
        }
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (!dp[i]) res++;
        }
        return res;
    }
}
```

## 451 Sort Character By Frequency

```java
class Solution {
    public String frequencySort(String s) {
        int[][] dp = new int[128][2];
        for (int i = 0; i < dp.length; i++) {
            dp[i][1] = i;
        }
        for (char ch : s.toCharArray()) {
            dp[ch-'\0'][0]++;
        }
        Arrays.sort(dp, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i2[0] - i1[0];
            }
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 128; i++) {
            if (dp[i][0] != 0) {
                for (int j = 0; j < dp[i][0]; j++) {
                    sb.append((char)('\0' + dp[i][1]));
                }
            } else {
                break;
            }
        }
        return sb.toString();
    }
}
// 直接用字符串拼接，效率的确不行。看来还是得用StringBuilder
```

## 535 Encode and Decode URL

```java
public class Codec {

    private static char[] letters = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private Random random = new Random();
    private Map<String, String> map1 = new HashMap<>();
    private Map<String, String> map2 = new HashMap<>();
    
    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        if (map1.containsKey(longUrl)) {
            return map1.get(longUrl);
        } else {
            String shortUrl = "";
            for (int i = 0; i < 6; i++) {
                shortUrl = shortUrl + letters[random.nextInt(letters.length)];
            }
            map1.put(longUrl, "http://tinyurl.com/"+shortUrl);
            map2.put("http://tinyurl.com/"+shortUrl, longUrl);
            return "http://tinyurl.com/"+shortUrl;
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map2.get(shortUrl);
    }
}
```

## 676 Implement Magic Dictionary

```java
class MagicDictionary {
    private Trie trie;
    
    /** Initialize your data structure here. */
    public MagicDictionary() {
        trie = new Trie();
    }
    
    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            trie.add(word);
        }
    }
    
    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        return trie.isExist(word);
    }
}

/**
 * Your MagicDictionary object will be instantiated and called as such:
 * MagicDictionary obj = new MagicDictionary();
 * obj.buildDict(dict);
 * boolean param_2 = obj.search(word);
 */
class Trie {
    private static final int R = 26;

    private Node root;
    private int n;
    
    public Trie() {
    }
    
    private static class Node {
        boolean isString;
        Node[] next = new Node[R];
    }
    
    public void add(String key) {
        root = add(root, key, 0);
    }
    
    private Node add(Node x, String key, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            if (!x.isString) n++;
            x.isString = true;
        } else {
            int c = key.charAt(d) - 'a';
            x.next[c] = add(x.next[c], key, d + 1);
        }
        return x;
    }
    
    public boolean contains(String key) {
        Node x = get(root, key, 0);
        if (x == null) return false;
        return x.isString;
    }
    
    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        int c = key.charAt(d) - 'a';
        return get(x.next[c], key, d + 1);
    }
    
    public boolean isExist(String key) {
        return isExist(root, key, 0, 0);
    }
    
    private boolean isExist(Node x, String key, int d, int times) {
        if (x == null || times > 1) return false;
        if (d == key.length()) {
            return x.isString && times == 1;
        }
        boolean flag = false;
        int c = key.charAt(d) - 'a';
        for (int i = 0; i < 26; i++) {
            if (i != c) {
                flag |= isExist(x.next[i], key, d + 1, times + 1);
            } else {
                flag |= isExist(x.next[c], key, d + 1, times);
            }
        }
        return flag;
    }
}
```

## 347 Top K Frequent Elements

```java
class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Element> pq = new PriorityQueue<>(k);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            pq.add(new Element(entry.getKey(), entry.getValue()));
        }
        List<Integer> res = new ArrayList<>();
        int count = 0;
        while (count < k) {
            Element elem = pq.poll();
            res.add(elem.k);
            count++;
        }
        return res;
    }
}

class Element implements Comparable<Element>{
    int k;
    int v;
    public Element(int k, int v) {
        this.k = k;
        this.v = v;
    }
    
    public int compareTo(Element other) {
        return other.v - this.v;
    }
}
```

## 454 4Sum II

```java
class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                int value = A[i] + B[j];
                map.put(value, map.getOrDefault(value, 0) + 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < C.length; i++) {
            for (int j = 0; j < D.length; j++) {
                int value = -(C[i] + D[j]);
                if (map.containsKey(value)) {
                    ans += map.get(value);
                }
            }
        }
        return ans;
    }
}
```

## 648 Replace Words

```java
class Solution {
    public String replaceWords(List<String> roots, String sentence) {
        TrieNode trie = new TrieNode();
        for (String root: roots) {
            TrieNode cur = trie;
            for (char letter: root.toCharArray()) {
                if (cur.children[letter - 'a'] == null)
                    cur.children[letter - 'a'] = new TrieNode();
                cur = cur.children[letter - 'a'];
            }
            cur.word = root;
        }

        StringBuilder ans = new StringBuilder();

        for (String word: sentence.split("\\s+")) {
            if (ans.length() > 0)
                ans.append(" ");

            TrieNode cur = trie;
            for (char letter: word.toCharArray()) {
                if (cur.children[letter - 'a'] == null || cur.word != null)
                    break;
                cur = cur.children[letter - 'a'];
            }
            ans.append(cur.word != null ? cur.word : word);
        }
        return ans.toString();
    }
}

class TrieNode {
    TrieNode[] children;
    String word;
    TrieNode() {
        children = new TrieNode[26];
    }
}

// my solution
class Solution {
    public String replaceWords(List<String> dict, String sentence) {
        String[] sentences = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        Set<String> set = new HashSet<>();
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int i = 0; i < dict.size(); i++) {
            set.add(dict.get(i));
            min = Math.min(dict.get(i).length(), min);
            max = Math.max(dict.get(i).length(), max);
        }
        System.out.println(set.toString());
        for (int i = 0; i < sentences.length; i++) {
            boolean flag = false;
            for (int j = min; j < sentences[i].length() && j <= max; j++) {
                String sub = sentences[i].substring(0, j);
                if (set.contains(sub)) {
                    flag = true;
                    sb.append(sub).append(" ");
                    break;
                }
            }
            if (!flag) {
                sb.append(sentences[i]).append(" ");
            }
        }
        return sb.toString().trim();
    }
}
```

## 554 Brick Wall

```java
class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        PriorityQueue<Element> pq = new PriorityQueue<>(new Comparator<Element>() {
            public int compare(Element e1, Element e2) {
                return e1.value - e2.value;
            }
        });
        for (int i = 0; i < wall.size(); i++) {
            List<Integer> row = wall.get(i);
            pq.offer(new Element(0, row.get(0), row));
        }
        int res = wall.size();
        while (pq.size() == wall.size()) {
            int min = pq.peek().value;
            int counter = wall.size();
            while (!pq.isEmpty() && min == pq.peek().value) {
                counter--;
                Element elem = pq.poll();
                elem.index++;
                if (elem.index < elem.row.size()) {
                    elem.value += elem.row.get(elem.index);
                    pq.offer(elem);
                }
            }
            if (!pq.isEmpty()) {
                res = Math.min(res, counter);
            }
        }
        return res;
    }
}

class Element {
    int index;
    int value;
    List<Integer> row;
    public Element(int index, int value, List<Integer> row) {
        this.index = index;
        this.value = value;
        this.row = row;
    }
}

// better solution
public class Solution {
    public int leastBricks(List<List<Integer>> wall) {
        if(wall.size() == 0) return 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(List<Integer> list : wall){
            int length = 0;
            for(int i = 0; i < list.size() - 1; i++){
                length += list.get(i);
                map.put(length, map.getOrDefault(length, 0) + 1);
                count = Math.max(count, map.get(length));
            }
        }
        return wall.size() - count;
    }
}
```

## 692 Top K Frequent Words

```java
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> count = new HashMap();
        for (String word: words) {
            count.put(word, count.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> heap = new PriorityQueue<String>(
                (w1, w2) -> count.get(w1) != count.get(w2) ?
                count.get(w1) - count.get(w2) : w2.compareTo(w1) );

        for (String word: count.keySet()) {
            heap.offer(word);
            if (heap.size() > k) heap.poll();
        }

        List<String> ans = new ArrayList();
        while (!heap.isEmpty()) ans.add(heap.poll());
        Collections.reverse(ans);
        return ans;
    }
}

// my solution
class Solution {
    public List<String> topKFrequent(String[] words, int k) {
        List<String> res = new ArrayList<>();
        List<String>[] buckets = new List[words.length];
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (buckets[entry.getValue()-1] == null) {
                buckets[entry.getValue()-1] = new ArrayList<>();
            }
            buckets[entry.getValue()-1].add(entry.getKey());
        }
        for (int i = buckets.length - 1; i >= 0 && res.size() < k; i--) {
            if (buckets[i] != null) {
                Collections.sort(buckets[i], new Comparator<String>() {
                    public int compare(String s1, String s2) {
                        return s1.compareTo(s2);
                    }
                });
                for (int j = 0; j < buckets[i].size() && res.size() < k; j++) {
                    res.add(buckets[i].get(j));
                }
            }
        }
        return res;
    }
}
```

## 525 Contiguous Array

```java
class Solution {
    public int findMaxLength(int[] nums) {
        int[] dp = new int[nums.length];
        int pre = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                pre += -1;
            } else {
                pre += 1;
            }
            dp[i] = pre;
        }
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int res = 0;
        for (int i = 0; i < dp.length; i++) {
            if (map.containsKey(dp[i])) {
                res = Math.max(res, i - map.get(dp[i]));
            } else {
                map.put(dp[i], i);
            }
        }
        return res;
    }
}

// better solution
public class Solution {

    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int maxlen = 0, count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = count + (nums[i] == 1 ? 1 : -1);
            if (map.containsKey(count)) {
                maxlen = Math.max(maxlen, i - map.get(count));
            } else {
                map.put(count, i);
            }
        }
        return maxlen;
    }
}
```

## 36 Valid Sudoku

```java
// my solution
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < 9; ++i) {
            boolean[] row = new boolean[9];
            boolean[] col = new boolean[9];
            boolean[] grid = new boolean[9];
            for (int j = 0; j < 9; ++j) {
                if (board[i][j] != '.') {
                    int rowIndex = board[i][j] - '1';
                    if (!row[rowIndex]) {
                        row[rowIndex] = true;
                    } else {
                        return false;
                    }                    
                }
                if (board[j][i] != '.') {
                    int colIndex = board[j][i] - '1';
                    if (!col[colIndex]) {
                        col[colIndex] = true;
                    } else {
                        return false;
                    }
                }
                if (board[j / 3 + (i / 3) * 3][(i % 3) * 3 + j % 3] != '.') {
                   int gridIndex = board[j / 3 + (i / 3) * 3][(i % 3) * 3 + j % 3] - '1';
                    if (!grid[gridIndex]) {
                        grid[gridIndex] = true;
                    } else {
                        return false;
                    } 
                }
            }
        }
        return true;
    }
}
// better solution
class Solution {
    public boolean isValidSudoku(char[][] board) {
        for (int i =0; i < 9; i++) {
            boolean[] row = new boolean[9];
            boolean[] col = new boolean[9];
            boolean[] grid = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int rowIndex = board[i][j] - '1';
                    if (!row[rowIndex]) {
                        row[rowIndex] = true;
                    } else {
                        return false;
                    }
                }
                if (board[j][i] != '.') {
                    int colIndex = board[j][i] - '1';
                    if (!col[colIndex]) {
                        col[colIndex] = true;
                    } else {
                        return false;
                    }
                }
                if (board[j / 3 + (i / 3) * 3][(i % 3) * 3 + j % 3] != '.') {
                    int gridIndex = board[j / 3 + (i / 3) * 3][(i % 3) * 3 + j % 3] - '1';
                    if (!grid[gridIndex]) { 
                        grid[gridIndex] = true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
```

## 299 Bulls and Cows

```java
class Solution {
    public String getHint(String secret, String guess) {
        int A = 0, B = 0;
        int[] counter = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            char c1 = secret.charAt(i);
            char c2 = guess.charAt(i);
            if (c1 == c2) A++;
            else {
                if (counter[c1-'0'] < 0) B++;
                if (counter[c2-'0'] > 0) B++;
                counter[c1-'0']++;
                counter[c2-'0']--;
            }
        }
        return A + "A" + B + "B";
    }
}
```

## 274 H-Index

```java
class Solution {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0;
        int N = citations.length;
        for (int i = 0; i < N; i++) {
            if (citations[i] >= N - i) {
                h = Math.max(h, N-i);
            }
        }
        return h;
    }
}
```

## 138 Copy List With Random Pointer

```java
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode rhead = new RandomListNode(Integer.MIN_VALUE);
        RandomListNode p1 = rhead;
        RandomListNode p2 = head;
        Map<RandomListNode, Integer> m1 = new HashMap<>();
        int ind = 0;
        while (p2 != null) {
            m1.put(p2, ind++);
            p2 = p2.next;
        }
        Map<Integer, Integer> m2 = new HashMap<>();
        p2 = head;
        ind = 0;
        while (p2 != null) {
            if (p2.random != null) {
                m2.put(ind, m1.get(p2.random));
            }
            ind++;
            p2 = p2.next;
        }
        Map<Integer, RandomListNode> m3 = new HashMap<>();
        p2 = head;
        ind = 0;
        while (p2 != null) {
            RandomListNode tmp = new RandomListNode(p2.label);
            m3.put(ind, tmp);
            p1.next = tmp;
            p1 = p1.next;
            p2 = p2.next;
            ind++;
        }
        ind = 0;
        p1 = rhead.next;
        while (p1 != null) {
            if (m2.get(ind) != null) {
                p1.random = m3.get(m2.get(ind));
            }
            p1 = p1.next;
            ind++;
        }
        return rhead.next;
    }
}

// better solution
/**
 * Definition for singly-linked list with a random pointer.
 * class RandomListNode {
 *     int label;
 *     RandomListNode next, random;
 *     RandomListNode(int x) { this.label = x; }
 * };
 */
public class Solution {
    public RandomListNode copyRandomList(RandomListNode head) {
        RandomListNode p1 = head;
        // copy the list, and linked them in a single list
        while (p1 != null) {
            RandomListNode next = p1.next;
            
            RandomListNode c = new RandomListNode(p1.label);
            p1.next = c;
            c.next = next;
            
            p1 = next;
        }
        
        // fill up random pointer
        p1 = head;
        while (p1 != null) {
            RandomListNode next = p1.next.next;
            
            if (p1.random != null) {
                p1.next.random = p1.random.next;
            }
            
            p1 = next;
        }
        
        // substract two list
        RandomListNode rhead = new RandomListNode(Integer.MAX_VALUE);
        RandomListNode p2 = rhead;
        p1 = head;
        while (p1 != null) {
            RandomListNode next = p1.next.next;
            
            p2.next = p1.next;
            p2 = p1.next;
            
            p1.next = next;
            
            p1 = next;
        }
        return rhead.next;
    }
}
```

## 355 Design Twitter

```java
class Twitter {
    private static int timer = 1;
    private Map<Integer, User> map;

    /** Initialize your data structure here. */
    public Twitter() {
        map = new HashMap<>();
    }
    
    /** Compose a new tweet. */
    public void postTweet(int userId, int tweetId) {
        if (!map.containsKey(userId)) {
            map.put(userId, new User(userId));
        }
        map.get(userId).postTwitter(new TwitterContent(tweetId, timer));
        timer++;
    }
    
    /** Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most recent to least recent. */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> res = new ArrayList<>();
        if (!map.containsKey(userId)) {
            return res;
        }
        PriorityQueue<TwitterContent> pq = new PriorityQueue<>();
        Set<Integer> foloowees = this.map.get(userId).getFollowees();
        for (int uid : foloowees) {
            User user = this.map.get(uid);
            if (user == null) continue;
            List<TwitterContent> tp = user.getNewsFeed();
            for (TwitterContent tc : tp) {
                pq.offer(tc);
            }
        }
        int ac = 0;
        while (!pq.isEmpty() && ac < 10) {
            res.add(pq.poll().tid);
            ac++;
        }
        return res;
    }
    
    /** Follower follows a followee. If the operation is invalid, it should be a no-op. */
    public void follow(int followerId, int followeeId) {
        if (!this.map.containsKey(followerId)) {
            this.map.put(followerId, new User(followerId));
        }
        this.map.get(followerId).follow(followeeId);
    }
    
    /** Follower unfollows a followee. If the operation is invalid, it should be a no-op. */
    public void unfollow(int followerId, int followeeId) {
        if (map.containsKey(followerId)) {
            this.map.get(followerId).unfollow(followeeId);
        }
    }
}

class User {
    private int uid;
    private Set<Integer> followees;
    private LinkedList<TwitterContent> pq = new LinkedList<>();

    public User(int uid) {
        this.uid = uid;
        this.followees = new HashSet<>();
        this.pq = new LinkedList<>();
        // 肯定follow自己
        this.followees.add(uid);
    }

    public void follow(int followeeId) {
        this.followees.add(followeeId);
    }

    public Set<Integer> getFollowees() {
        return this.followees;
    }

    public void unfollow(int followeeId) {
        if (followeeId != uid)
            this.followees.remove(followeeId);
    }

    public void postTwitter(TwitterContent tc) {
        this.pq.add(0, tc);
    }

    public List<TwitterContent> getNewsFeed() {
        List<TwitterContent> ans = new ArrayList<>();
        int ac = 0;
        for (TwitterContent tc : this.pq) {
            ans.add(tc);
            ac++;
            if (ac == 10) {
                break;
            }
        }
        return ans;
    }
}

class TwitterContent implements Comparable<TwitterContent> {
    int tid;
    int timer;
    public TwitterContent(int tid, int timer) {
        this.tid = tid;
        this.timer = timer;
    }

    public int compareTo(TwitterContent other) {
        return other.timer - this.timer;
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
```