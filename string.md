# String

## 657 Judge Route Circle

```java
class Solution {
    public boolean judgeCircle(String moves) {
        int x = 0, y = 0;
        for (int i = 0; i < moves.length(); i++) {
            switch (moves.charAt(i)) {
                case 'U':
                    y -= 1;
                    break;
                case 'D':
                    y += 1;
                    break;
                case 'L':
                    x -= 1;
                    break;
                case 'R':
                    x += 1;
                    break;
            }
        }
        if (x == 0 && y == 0) {
            return true;
        }
        return false;
    }
}
```

## 557 Reverse Words in a String III

```java
class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s.length()+1);
        int last = -1;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' || i == s.length()-1) {
                int end = i-1;
                if (i == s.length()-1) end = i;
                for (int j = end; j > last; j--) {
                    sb.append(s.charAt(j));
                }
                if (i != s.length()-1) sb.append(' ');
                last = i;
            }
        }
        return sb.toString();
    }
}
```

## 344 Reverse String

```java
class Solution {
    public String reverseString(String s) {
        return new StringBuilder(s).reverse().toString();
    }
}
```

## 521 Longest Uncommon Subsequence I

```java
class Solution {
    public int findLUSlength(String a, String b) {
        if (a.equals(b)) {
            return -1;
        } else {
            return Math.max(a.length(), b.length());
        }
    }
}
```

## 520 Detect Capital

```java
class Solution {
    public boolean detectCapitalUse(String word) {
        // non-empty
        // then we can check the first letter
        boolean flag = Character.isUpperCase(word.charAt(0));
        int count = 0;
        for (int i = 1; i < word.length(); i++) {
            if (Character.isUpperCase(word.charAt(i))) count++;
        }
        if (flag) {
            // first letter is UpperCase
            if (count == 0 || count == word.length() - 1) return true;
            else return false;
        } else {
            // fist letter is LowerCase
            if (count == 0) return true;
            else return false;
        }
    }
}
```

## 696 Count Binary Substrings

```java
class Solution {
    public int countBinarySubstrings(String s) {
        int res = 0;
        char current = s.charAt(0);
        int count = 0;
        for (int i = 0; i < s.length(); ) {
            if (s.charAt(i) == current) {
                count++;
                i++;
            } else {
                int cp = 0;
                while (i < s.length() && s.charAt(i) != current && count > 0) {
                    cp++;
                    res += 1;
                    i++;
                    count--;
                }
                if (i < s.length()) {
                    current = s.charAt(i-1);
                    count = cp;
                }
            }
        }
        return res;
    }
}

// better solution, more consice, and more short
class Solution {
    public int countBinarySubstrings(String s) {
        int ans = 0, prev = 0, cur = 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i-1) != s.charAt(i)) {
                ans += Math.min(prev, cur);
                prev = cur;
                cur = 1;
            } else {
                cur++;
            }
        }
        return ans + Math.min(cur,prev);
    }
}
```

## 383 Ransom Note

```java
class Solution {
    public boolean canConstruct(String ransomNote, String magazine) {
        int[] rchs = new int[26];
        int[] mchs = new int[26];
        for (int i = 0; i < ransomNote.length(); i++) {
            rchs[ransomNote.charAt(i)-'a']++;
        }
        for (int i = 0; i < magazine.length(); i++) {
            mchs[magazine.charAt(i)-'a']++;
        }
        for (int i = 0; i < 26; i++) {
            if (mchs[i] < rchs[i]) {
                return false;
            }
        }
        return true;
    }
}
```

## Roman to Integer

```java
class Solution {
    Map<Character, Integer> map = new HashMap<Character, Integer>() {{
        put('I', 1);
        put('V', 5);
        put('X', 10);
        put('L', 50);
        put('C', 100);
        put('D', 500);
        put('M', 1000);
    }};
    public int romanToInt(String s) {
        int res = 0;
        int prev = map.get(s.charAt(0));
        for (int i = 1; i < s.length(); i++) {
            if (map.get(s.charAt(i)) > prev) {
                char ch = s.charAt(i-1);
                if (ch == 'I' || ch == 'X' || ch == 'C') {
                    res -= prev;
                }
            } else {
                res += prev;
            }
            prev = map.get(s.charAt(i));
        }
        return res + prev;
    }
}
```

## 551 Student Attendance Record I

```java
class Solution {
    public boolean checkRecord(String s) {
        int ac = 0;
        int pc = 0;
        for (int i = 0; i < s.length();i++) {
            if (s.charAt(i) == 'A') {
                ac++;
                pc = 0;
            } else if (s.charAt(i) == 'L') {
                pc++;
            } else {
                pc = 0;
            }
            
            if (ac > 1 || pc > 2) {
                return false;
            }
        }
        return true;
    }
}
```

## 541 Reverse String II

```java
class Solution {
    public String reverseStr(String s, int k) {
        StringBuilder sb = new StringBuilder();
        int index = 0;
        for(; index + k < s.length(); index = index + 2 * k) {
            for (int i = index + k - 1; i >= index; i--) {
                sb.append(s.charAt(i));
            }
            for (int i= index + k; i < index + 2 * k && i < s.length(); i++) {
                sb.append(s.charAt(i));
            }
        }
        if (index < s.length()) {
            for (int i = s.length() - 1; i >= index; i--) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}

// Another solution
public class Solution {
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int n = arr.length;
        int i = 0;
        while(i < n) {
            int j = Math.min(i + k - 1, n - 1);
            swap(arr, i, j);
            i += 2 * k;
        }
        return String.valueOf(arr);
    }
    private void swap(char[] arr, int l, int r) {
        while (l < r) {
            char temp = arr[l];
            arr[l++] = arr[r];
            arr[r--] = temp;
        }
    }
}
```

## 345 Reverse Vowels of a String

```java
class Solution {
    public String reverseVowels(String s) {
        char[] chs = s.toCharArray();
        int low = 0, high = chs.length - 1;
        while (low < high) {
            if (!isVowels(chs[low])) {
                low++;
            } else if (!isVowels(chs[high])) {
                high--;
            } else {
                swap(chs, low, high);
                low++; high--;
            }
        }
        return new String(chs);
    }
    
    private void swap(char[] chs, int i, int j) {
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;
    }
    
    private boolean isVowels(char ch) {
        ch = Character.toLowerCase(ch);
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') return true;
        return false;
    }
}
```

## 459 Repeated Substring Pattern

```java
// bruteforce solution
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        for (int i = 1; i <= s.length() / 2; i++) {
            int ind = 0;
            boolean flag = false;
            for (int j = i; j < s.length(); j++) {
                if (s.charAt(ind) != s.charAt(j)) {
                    flag = true;
                    break;
                }
                ind = (ind + 1) % (i - 0);
            }
            if (!flag && ind == 0) return true;
        }
        return false;
    }
}

// a better solution
class Solution {
    public boolean repeatedSubstringPattern(String s) {
        String ss = (s + s).substring(1, s.length() * 2 - 1);
        return ss.indexOf(s) != -1;
    }
}
```

## 434 Number of Segments in a String

```java
class Solution {
    public int countSegments(String s) {
        int ans = 0;
        boolean flag = true;
        for (int i = 0; i < s.length(); i++) {
            if (flag && s.charAt(i) != ' ') {
                flag = false;
                ans++;
            } else if (s.charAt(i) == ' ') {
                flag = true;
            }
        }
        return ans;
    }
}
```

## Count and Say

```java
class Solution {
    public String countAndSay(int n) {
        String pre = "1";
        for (int i = 1; i < n; i++) {
            String current = "";
            int count = 1;
            for (int j = 0; j < pre.length(); ) {
                char ch = pre.charAt(j);
                while (j + 1 < pre.length() && pre.charAt(j+1) == pre.charAt(j)) {
                    j++;
                    count++;
                }
                j++;
                current += Integer.valueOf(count);
                current += ch;
                count = 1;
            }
            pre = current;
        }
        return pre;
    }
}
```

## 20 Valid Parentheses

```java
class Solution {
    public boolean isValid(String s) {
        Stack<Character> st = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            if (st.isEmpty() || s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[') {
                st.push(s.charAt(i));
            } else if (s.charAt(i) == ']') {
                if (!st.isEmpty() && st.peek() == '[') {
                    st.pop();
                } else {
                    return false;
                }
            } else if (s.charAt(i) == '}') {
                if (!st.isEmpty() && st.peek() == '{') {
                    st.pop();
                } else {
                    return false;
                }
            } else if (s.charAt(i) == ')') {
                if (!st.isEmpty() && st.peek() == '(') {
                    st.pop();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return st.size() == 0;
    }
}
```

## 67 Add Binary

```java
class Solution {
    public String addBinary(String a, String b) {
        char carry = '0';
        String ans = "";
        int ai = a.length() - 1, bi = b.length() - 1;
        while (ai >= 0 && bi >= 0) {
            ans = ((a.charAt(ai) - '0') ^ (b.charAt(bi) - '0') ^ (carry - '0')) + ans;
            if ((a.charAt(ai) - '0' + b.charAt(bi) - '0' + carry - '0') > 1) {
                carry = '1';
            } else {
                carry = '0';
            }
            ai--;bi--;
        }
        while (ai >= 0) {
            ans = ((a.charAt(ai) - '0') ^ (carry - '0')) + ans;
            if ((a.charAt(ai) - '0' + carry - '0') > 1) {
                carry = '1';
            } else {
                carry = '0';
            }
            ai--;
        }
        while (bi >= 0) {
            ans = ((b.charAt(bi) - '0') ^ (carry - '0')) + ans;
            if ((b.charAt(bi) - '0' + carry - '0') > 1) {
                carry = '1';
            } else {
                carry = '0';
            }
            bi--;
        }
        if (carry == '1') ans = '1' + ans;
        return ans;
    }
}
```

## 686 Repeated String Match

```java
// A little tricky solution
class Solution {
    public int repeatedStringMatch(String A, String B) {
        int m = A.length(), n = B.length();
        int k = Math.max(n / m, 1);
        String copy1 = "";
        String copy2 = "";
        for (int i = 0; i < k; i++) {
            copy1 += A;
            copy2 += A;
        }
        copy2 += A;
        if (copy1.indexOf(B) != -1) {
            return k;
        } else if (copy2.indexOf(B) != -1) {
            return k + 1;
        } else {
            return -1;
        }
    }
}

// A better solution， 优化点在于判断找子串是linear时间。
```

## 58 Length of Last Word

```java
class Solution {
    public int lengthOfLastWord(String s) {
        int count = 0;
        boolean flag = false;
        for (int i = s.length() - 1; i >= 0; i--) {
            if (flag && s.charAt(i) == ' ') {
                break;
            } else if (s.charAt(i) != ' ') {
                flag = true;
                count++;
          }
        }
        return count;
    }
}
```

## 680 Valid Palindrome II

```java
class Solution {
    public boolean validPalindrome(String s) {
        return validPalindrome(s, 0, s.length() - 1, false);
    }
    
    private boolean validPalindrome(String s, int lo, int hi, boolean flag) {
        if (lo == hi) {
            return true;
        } else if (hi < lo) {
            return false;
        } else {
            while (lo < hi) {
                if (s.charAt(lo) != s.charAt(hi)) {
                    if (!flag) {
                        // 没有使用
                        return validPalindrome(s, lo + 1, hi, true) || validPalindrome(s, lo, hi - 1, true);
                    } else {
                        // 已经使用
                        return false;
                    }
                }
                lo++;hi--;
            }
            return true;
        }
    }
}
```

## 14 Longest Common Prefix

```java
class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) return "";
        String res = strs[0];
        for (int i = 1; i < strs.length; i++) {
            res = res.substring(0, Math.min(res.length(), strs[i].length()));
            for (int j = 0; j < res.length() && j < strs[i].length(); j++) {
                if (res.charAt(j) != strs[i].charAt(j)) {
                    res = res.substring(0, j);
                    break;
                }
            }
            if (res.length() == 0) break;
        }
        return res;
    }
}
```

## 28 Implement strStr

```java
class Solution {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
```

## 125 Valid Palindrome

```java
class Solution {
    public boolean isPalindrome(String s) {
        int lo = 0, hi = s.length() - 1;
        while (lo < hi) {
            if (!isAlphanumeric(s.charAt(lo))) {
                lo++;
            } else if (!isAlphanumeric(s.charAt(hi))) {
                hi--;
            } else {
                char c1 = Character.toLowerCase(s.charAt(lo));
                char c2 = Character.toLowerCase(s.charAt(hi));
                if (c1 != c2) return false;
                lo++;hi--;
            }
        }
        return true;
    }
    
    private boolean isAlphanumeric(char ch) {
        return Character.isLetter(ch) || Character.isDigit(ch);
    }
}
```

## 537 Complex Number Multiplication

```java
class Solution {
    public String complexNumberMultiply(String a, String b) {
        int p1 = a.indexOf("+");
        int p2 = a.indexOf("i");
        int p3 = b.indexOf("+");
        int p4 = b.indexOf("i");
        int ar = Integer.parseInt(a.substring(0, p1));
        int ac = Integer.parseInt(a.substring(p1+1, p2));
        int br = Integer.parseInt(b.substring(0, p3));
        int bc = Integer.parseInt(b.substring(p3+1, p4));
        int rr = ar * br - ac * bc;
        int rc = ar * bc + br * ac;
        return rr + "+" + rc + "i";
    }
}
```

## 647 Palindromic Substrings

```java
class Solution {
    public int countSubstrings(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans += countSubstrings(s, i, i);
            ans += countSubstrings(s, i, i+1);
        }
        return ans;
    }
    
    private int countSubstrings(String s, int l, int h) {
        int count = 0;
        while (l >= 0 && h < s.length()) {
            if (s.charAt(l) == s.charAt(h)) {
                count++;
            } else {
                break;
            }
            l--; h++;
        }
        return count;
    }
}
```

## 553 Optimal Division

```java
class Solution {
    public String optimalDivision(int[] nums) {
        if (nums.length == 1) return Integer.toString(nums[0]);
        if (nums.length == 2) return nums[0] + "/" + nums[1];
        StringBuilder sb = new StringBuilder();
        sb.append(nums[0]).append("/").append("(");
        for (int i = 1; i < nums.length; i++) {
            sb.append(nums[i]).append("/");
        }
        sb.deleteCharAt(sb.length()-1);
        sb.append(")");
        return sb.toString();
    }
}
```

## 609 Find Duplicate File in System

```java
class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        Map<String, List<String>> map = new HashMap<>();
        for (String path : paths) {
            String[] files = path.split(" ");
            for (int i = 1; i < files.length; i++) {
                int p1 = files[i].indexOf("(");
                int p2 = files[i].indexOf(")");
                String content = files[i].substring(p1+1, p2);
                if (!map.containsKey(content)) {
                    map.put(content, new ArrayList<>());
                }
                map.get(content).add(files[0] + "/" + files[i].substring(0, p1));
            }
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            if (entry.getValue().size() > 1) {
                res.add(entry.getValue());
            }
        }
        return res;
    }
}
```

## 22 Generate Parentheses

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        Stack<Character> stack = new Stack<>();
        backtrack(n, stack, "", res);
        return res;
    }
    
    private void backtrack(int n, Stack<Character> stack, String holder, List<String> res) {
        if (stack.size() > n) {
            return;
        } else if (holder.length() == n * 2) {
            if (stack.isEmpty()) {
                res.add(holder);
            }
        } else {
            if (stack.isEmpty()) {
                stack.add('(');
                backtrack(n, stack, holder + "(", res);
                stack.pop();
            } else {
                stack.add('(');
                backtrack(n, stack, holder + '(', res);
                stack.pop();
                stack.pop();
                backtrack(n, stack, holder + ')', res);
                stack.add('(');
            }
        }
    }
}

// a better solution
class Solution {
  public List<String> generateParenthesis(int n) {
    List<String> res = new LinkedList<>();
    generate(n, 0, "", res);
    return res;
  }

  private void generate(int n, int left, String prefix, List<String> res) {
    if (n == 0 && left == 0) {
      res.add(prefix);
    } else {
      if (left > 0) {
        if (n > 0) generate(n-1, left + 1, prefix + "(", res);
        generate(n, left - 1, prefix + ")", res);
      } else {
        generate(n-1, left + 1, prefix + "(", res);
      }
    }
  }
}
```

## 539 Minimum Time Difference

```java
class Solution {
    public int findMinDifference(List<String> timePoints) {
        Collections.sort(timePoints);
        int res = Integer.MAX_VALUE;
        for (int i = 0; i < timePoints.size(); i++) {
            String prev = timePoints.get((i - 1 + timePoints.size()) % timePoints.size());
            String cur = timePoints.get(i);
            String next = timePoints.get((i + 1 + timePoints.size()) % timePoints.size());
            res = Math.min(res, Math.min(timeabs(prev, cur), timeabs(cur, next)));
        }
        return res;
    }
    
    private int timeabs(String pre, String cur) {
        int preI = Integer.parseInt(pre.substring(0, 2)) * 60 + Integer.parseInt(pre.substring(3, 5));
        int curI = Integer.parseInt(cur.substring(0, 2)) * 60 + Integer.parseInt(cur.substring(3, 5));
        return Math.min(Math.abs(curI - preI), Math.abs(curI + 1440 - preI));
    }
}
```

## 583 Delete Operation for Two Strings

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        int max = 0;
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
                max = Math.max(max, dp[i][j]);
            }
        }
        return word1.length() + word2.length() - 2 * max;
    }
}
```

## 49 Group Anagrams

```java
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] keys = strs[i].toCharArray();
            Arrays.sort(keys);
            String key = Arrays.toString(keys);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(strs[i]);
        }
        List<List<String>> res = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : map.entrySet()) {
            res.add(entry.getValue());
        }
        return res;
    }
}
```

## 17 Letter Combinations of a Phone Number

```java
class Solution {
    Map<Character, String> map = new HashMap<Character, String>() {{
        put('2', "abc");
        put('3', "def");
        put('4', "ghi");
        put('5', "jkl");
        put('6', "mno");
        put('7', "pqrs");
        put('8', "tuv");
        put('9', "wxyz");
    }};
    
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        if (digits.equals("")) return res;
        backtrack(digits, 0, new StringBuilder(), res);
        return res;
    }
    
    private void backtrack(String digits, int start, StringBuilder sb, List<String> res) {
        if (start == digits.length()) {
            res.add(sb.toString());
            return;
        } else {
            String choosen = map.get(digits.charAt(start));
            for (int i = 0; i < choosen.length(); i++) {
                sb.append(choosen.charAt(i));
                backtrack(digits, start + 1, sb, res);
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
}
```

## 385 Mini Parser

```java
class Solution {
    public NestedInteger deserialize(String s) {
        if (!s.contains("[")) {
            return new NestedInteger(Integer.parseInt(s));
        } else {
            NestedInteger ni = new NestedInteger();
            int ind = 1;
            int count = 0;
            int pre = 1;
            while (ind < s.length() - 1) {
                if (s.charAt(ind) == '[') {
                    count++;
                } else if (s.charAt(ind) == ']') {
                    count--;
                } else if (s.charAt(ind) == ',') {
                    if (count == 0) {
                        // 一个组
                        ni.add(deserialize(s.substring(pre, ind)));
                        pre = ++ind;
                        continue;
                    }
                }
                if (count == 0 && ind == s.length() - 2) {
                    ni.add(deserialize(s.substring(pre, ind+1))); 
                }
                ind++;
            }
            return ni;
        }
    }
}
```

## 522 Longest Uncommon Subsequence II

```java
class Solution {
    public int findLUSlength(String[] strs) {
        if (strs.length == 0) return 0;
        else if (strs.length == 1) return strs[0].length();
        Arrays.sort(strs, new Comparator<String>(){
            public int compare(String s1, String s2) {
                if (s1.length() == s2.length()) return s1.compareTo(s2);
                else return s1.length() - s2.length();
            }
        });
        for (int i = strs.length - 1; i >= 0; i--) {
            boolean flag = true;
            for (int j = i + 1; j < strs.length; j++) {
                if (isSubsenquence(strs[i], strs[j])) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (i == 0 || !strs[i].equals(strs[i-1])) {
                    return strs[i].length();
                }
            }
        }
        return -1;
    }
    
    private boolean isSubsenquence(String s1, String s2) {
        for (int i = 0, j = 0; i < s2.length(); i++) {
            if (s2.charAt(i) == s1.charAt(j)) j++;
            if (j == s1.length()) return true;
        }
        return false;
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

## 678 Valid Parenthesis String

```java
// brute force solution
class Solution {
    public boolean checkValidString(String s) {
        return checkValidString(s, 0, 0);
    }
    
    private boolean checkValidString(String s, int count, int ind) {
        if (count < 0) {
            return false;
        } else if (ind == s.length()) {
            if (count == 0) return true;
            else return false;
        } else {
            if (s.charAt(ind) == '(') {
                return checkValidString(s, count + 1, ind + 1);
            } else if (s.charAt(ind) == ')') {
                return checkValidString(s, count - 1, ind + 1);
            } else if (s.charAt(ind) == '*') {
                return checkValidString(s, count + 1, ind + 1) || checkValidString(s, count, ind + 1) || checkValidString(s, count - 1, ind + 1);
            } else {
                return checkValidString(s, count, ind + 1);
            }
        }
    }
}

// A great solution
// 其实就是判断(的上限和下限，上限一旦小于0，肯定是错的，最后给出下限是否等于0
class Solution {
    public boolean checkValidString(String s) {
        int low = 0;
        int high = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                low++;
                high++;
            } else if (s.charAt(i) == ')') {
                if (low > 0) low--;
                high--;
            } else if(s.charAt(i) == '*') {
                if (low > 0) low--;
                high++;
            }
            if (high < 0) {
                return false;
            }
        }
        return low == 0;
    }
}
```

## 556 Next Greater Element III

```java
class Solution {
    public int nextGreaterElement(int n) {
        char[] nums = Integer.toString(n).toCharArray();
        boolean flag = false;
        int ind = nums.length - 1;
        int pre = -1;
        while (ind >= 0) {
            if (pre != -1) {
                if (nums[ind] < nums[pre]) {
                    flag = true;
                    int i = nums.length - 1;
                    for (; i > ind; i--) {
                        if (nums[i] > nums[ind]) {
                            break;
                        }
                    }
                    swap(nums, i, ind);
                    reverse(nums, pre, nums.length-1);
                    break;
                } else {
                    pre = ind;
                }
            } else {
                pre = ind;
            }
            ind--;
        }
        if (!flag) return -1;
        else {
            int res = 0;
            int prev = 0;
            for (int i = 0; i < nums.length; i++) {
                res = res * 10 + nums[i] - '0';
                if ((res - (nums[i] - '0')) / 10 != prev) return -1;
                prev = res;
            }
            return res;
        }
    }
    
    private void swap(char[] nums, int i, int j) {
        char tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
    
    private void reverse(char[] nums, int lo, int hi) {
        while (lo < hi) {
            swap(nums, lo++, hi--);
        }
    }
}
```

## Restore IP Addresses

```java
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        backtrack(s, 4, 0, "", res);
        return res;
    }
    
    private void backtrack(String s, int num, int start, String holder, List<String> res) {
        if (num == 0) {
            if (start == s.length()) {
                res.add(holder.substring(1, holder.length()));
            }
            return;
        } else {
            for (int i = start; i < start + 3 && i < s.length(); i++) {
                String sub = s.substring(start, i + 1);
                if (!(sub.charAt(0) == '0' && sub.length() > 1) && Integer.parseInt(sub) <= 255) {
                    backtrack(s, num - 1, i + 1, holder + "." + s.substring(start, i + 1), res);
                }
            }
        }
    }
}

// another solution
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new LinkedList<>();
        int len = s.length();
        for (int i = 1; i < 4 && i < len - 2; i++) {
            for (int j = i + 1; j < i + 4 && j < len - 1; j++) {
                for (int k = j + 1; k < j + 4 && k < len; k++) {
                    String ip1 = s.substring(0, i), ip2 = s.substring(i, j), ip3 = s.substring(j, k), ip4 = s.substring(k, len);
                    if (isValid(ip1) && isValid(ip2) && isValid(ip3) && isValid(ip4)) {
                        res.add(ip1 + "." + ip2 + "." + ip3 + "." + ip4);
                    }
                }
            }
        }
        return res;
    }
    
    private boolean isValid(String ip) {
        if ((ip.charAt(0) == '0' && ip.length() > 1) || Integer.parseInt(ip) > 255)
            return false;
        return true;
    }
}
```

## 43 Multiply Strings

```java
class Solution {
    public String multiply(String num1, String num2) {
        int[] result = new int[num1.length() + num2.length()];
        for (int i = 0; i < num1.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                result[i + j + 1] += (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
            }
        }
        
        for (int i = result.length - 1; i > 0; i--) {
            if (result[i] >= 10) {
                result[i-1] += result[i] / 10;
                result[i] %= 10;
            }
        }
        String ans = "";
        boolean flag = false;
        for (int i = 0; i < result.length; i++) {
            if (flag || result[i] != 0) {
                flag = true;
                ans += result[i];
            }
        }
        return ans.equals("") ? "0" : ans;
    }
}
```

## 6 Zigzag Conversion

```java
class Solution {
    public String convert(String s, int numRows) {
        StringBuilder[] sbs = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            sbs[i] = new StringBuilder();
        }
        int ind = 0;
        while (ind < s.length()) {
            for (int i = 0; i < numRows && ind < s.length(); i++, ind++) {
                sbs[i].append(s.charAt(ind));
            }
            for (int i = numRows - 2; i > 0 && ind < s.length(); i--, ind++) {
                sbs[i].append(s.charAt(ind));
            }
        }
        for (int i = 1; i < numRows; i++) {
            sbs[0].append(sbs[i]);
        }
        return sbs[0].toString();
    }
}
```

## 71 Simplify Path

```java
class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();
        int ind = 0;
        while (ind < path.length()) {
            if (path.charAt(ind) == '/') {
                ind++;
                int start = ind;
                while (ind < path.length() && path.charAt(ind) != '/') {
                    ind++;
                }
                String sub = path.substring(start, ind);
                if (sub.equals("..") ) {
                    if (!stack.isEmpty()) stack.pop();
                } else if (sub.equals(".")) {
                    // do nothing
                } else if (sub.length() > 0) {
                    stack.push(sub);
                }
            }
        }
        String ans = "";
        while (!stack.isEmpty()) {
            ans = "/" + stack.pop() + ans;
        }
        return ans.equals("") ? "/" : ans;
    }
}
```

## 3 Longest Substring Without Repeating Characters

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int ans = 0;
        int start = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                if (map.get(s.charAt(i)) >= start) {
                    // 意味着正常情况
                    ans = Math.max(ans, i - start);
                    start = map.get(s.charAt(i)) + 1;
                } else {
                    ans = Math.max(ans, i - start + 1);
                }
            } else {
                ans = Math.max(ans, i - start + 1);
            }
            map.put(s.charAt(i), i);
        }
        return ans;
    }
}
```

## 468 Validate IP Address

```java
class Solution {
    public String validIPAddress(String IP) {
        if (IP.indexOf(".") != -1) {
            // IPv4
            if (validIPv4Address(IP)) {
                return "IPv4";
            } else {
                return "Neither";
            }
        } else if (IP.indexOf(":") != -1) {
            // IPv6
            if (validIPv6Address(IP)) {
                return "IPv6";
            } else {
                return "Neither";
            }
        } else {
            // Neither
            return "Neither";
        }
    }
    
    private boolean validIPv4Address(String IP) {
        if ((IP.length() - IP.replace(".", "").length()) != 3) return false;
        String[] ips = IP.split("\\.");
        if (ips.length != 4) return false;
        for (int i = 0; i < ips.length; i++) {
            if (ips[i].length() == 0 || ips[i].length() > 3 || !isNumeric(ips[i]) || (ips[i].charAt(0) == '0' && ips[i].length() > 1) || Integer.parseInt(ips[i]) > 255) return false;
        }
        return true;
    }
    
    private boolean isNumeric(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (!Character.isDigit(s.charAt(i))) return false;
        }
        return true;
    }
    
    private boolean validIPv6Address(String IP) {
        if ((IP.length() - IP.replace(":", "").length()) != 7) return false;
        String[] ips = IP.split(":");
        if (ips.length != 8) return false;
        for (int i = 0; i < ips.length; i++) {
            if (ips[i].length() == 0 || ips[i].length() > 4) return false;
            if (!validCaseConsistent(ips[i])) {
                return false;
            }
        }
        return true;
    }
    
    private boolean validCaseConsistent(String s) {
        boolean upperCase = false;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isUpperCase(ch)) {
                if (ch > 'F') return false;
            } else if (Character.isLowerCase(ch)) {
                if (ch > 'f') return false;
            } else if (!Character.isDigit(ch)) {
                return false;
            }
        }
        return true;
    }
}
```

## 165 Compare Version Number

```java
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] v1 = version1.split("\\.");
        String[] v2 = version2.split("\\.");
        int i = 0;
        for (; i < v1.length && i < v2.length; i++) {
            int vi1 = Integer.parseInt(v1[i]), vi2 = Integer.parseInt(v2[i]);
            if (vi1 < vi2) {
                return -1;
            } else if (vi1 > vi2) {
                return 1;
            }
        }
        for (; i < v1.length; i++) {
            if (Integer.parseInt(v1[i]) != 0) {
                return 1;
            }
        }
        for (; i < v2.length; i++) {
            if (Integer.parseInt(v2[i]) != 0) {
                return -1;
            }
        }
        return 0;
    }
}
```

## 91 Decode Ways

```java
class Solution {
    public int numDecodings(String s) {
        if (s.length() == 0) return 0;
        int[][] dp = new int[s.length() + 1][2];
        dp[0][0] = 1;
        dp[0][1] = 0;
        char pre = '0';
        for (int i = 1; i <= s.length(); i++) {
            if (s.charAt(i-1) == '0') {
                if (pre == '1' || pre == '2') {
                    dp[i][0] = dp[i-1][1];
                } else {
                    return 0;
                }
            } else if (pre == '1' || (pre == '2' && s.charAt(i-1) < '7')) {
                dp[i][0] = dp[i-1][1];
                dp[i][1] = dp[i-1][0] + dp[i-1][1];
            } else {
                dp[i][1] = dp[i-1][0] + dp[i-1][1];
            }
            pre = s.charAt(i-1);
        }
        return dp[s.length()][0] + dp[s.length()][1];
    }
}
```

## 151 Reverse Words in a String

```java
public class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder(s.length() + 1);
        int ind = s.length() - 1;
        int end = s.length();
        while (ind >= 0) {
            if (s.charAt(ind) != ' ') {
                // 减去
                while (ind >= 0 && s.charAt(ind) != ' ') {
                    ind--;
                }
                sb.append(s.substring(ind + 1, end)).append(" ");
            } else {
                end = ind;
                ind--;
            }
        }
        return sb.toString().trim();
    }
}
```

## String to Integer (atoi)

```java
class Solution {
    public int myAtoi(String str) {
        long res = 0;
        int ind = 0;
        boolean flag = true;
        boolean isPositive = true;
        boolean isFrontWhiteSpace = true;
        while (ind < str.length()) {
            char ch = str.charAt(ind);
            if (flag && ch == '+') {
                flag = false;
                isFrontWhiteSpace = false;
                ind++;
            } else if (flag && ch == '-') {
                isPositive = false;
                isFrontWhiteSpace = false;
                flag = false;
                ind++;
            } else if (Character.isDigit(ch)) {
                // 直接计算了
                isFrontWhiteSpace = false;
                while (ind < str.length() && Character.isDigit(str.charAt(ind))) {
                    res = res * 10 + str.charAt(ind) - '0';
                    ind++;
                    if (res > 2147483648L) break;
                }
                break;
            } else if (isFrontWhiteSpace && ch == ' ') {
                ind++;
            } else {
                break;
            }
        }
        if (isPositive) {
            if (res > 2147483647L) return Integer.MAX_VALUE;
            else return (int) res;
        } else {
            if (res > 2147483648L) return Integer.MIN_VALUE;
            else return (int) -res;
        }
    }
}

// better solution
class Solution {
    public int myAtoi(String str) {
        String num = str.trim();
        if (num.equals("") || (num.length() == 1 && !Character.isDigit(num.charAt(0)))) return 0;
        boolean pn = true; // 正数
        boolean overflow = false; // 是否溢出
        int res = 0;    // 结果
        int pre = 0;    // 保存之前的结果
        int start = 0;
        if (num.charAt(0) == '-')  {
            pn = false; start = 1;
        }
        if (num.charAt(0) == '+') start = 1;
        for (int i = start; i < num.length(); i++) {
            if (!Character.isDigit(num.charAt(i))) break;
            int d = num.charAt(i) - '0';
            res = res * 10 + d;
            if (res < 0 || (res - d) / 10 != pre) {
                overflow = true;
                break;
            }
            pre = res;
        }
        if (overflow) {
            if (pn) return Integer.MAX_VALUE;
            else return Integer.MIN_VALUE;
        } else {
            if (pn) return res;
            else return -res;
        }
    }
}
```

## 632 Smallest Range

```java
class Solution {
    public int[] smallestRange(List<List<Integer>> nums) {
        int p1 = -1, p2 = -1;
        PriorityQueue<Element> pq = new PriorityQueue<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.size(); i++) {
            pq.offer(new Element(nums.get(i).get(0), i, 0));
            max = Math.max(max, nums.get(i).get(0));
        }
        p1 = pq.peek().val;
        p2 = max;
        while (pq.size() == nums.size()) {
            Element elem = pq.poll();
            if (elem.ind + 1 < nums.get(elem.k).size()) {
                pq.offer(new Element(nums.get(elem.k).get(elem.ind+1), elem.k, elem.ind + 1));
                max = Math.max(max, nums.get(elem.k).get(elem.ind+1));
                if (max - pq.peek().val < p2 - p1) {
                    p2 = max;
                    p1 = pq.peek().val;
                }
            }
        }
        return new int[]{p1, p2};
    }
}

class Element implements Comparable<Element> {
    int val;
    int k;
    int ind;
    
    public Element(int val, int k, int ind) {
        this.val = val;
        this.k = k;
        this.ind = ind;
    }
    
    public int compareTo(Element that) {
        return this.val - that.val;
    }
}
```

## 72 Edit Distance

```java
class Solution {
    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length() + 1][word2.length() + 1];
        for (int i = 0; i <= word1.length(); i++) dp[i][0] = i;
        for (int j = 0; j <= word2.length(); j++) dp[0][j] = j;
        for (int i = 1; i <= word1.length(); i++) {
            for (int j = 1; j <= word2.length(); j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]);
                }
            }
        }
        return dp[word1.length()][word2.length()];
    }
}
```

## 115 Distinct Subsequences

```java
// broute force solution TLE
class Solution {
    int ans = 0;
    
    public int numDistinct(String s, String t) {
        backtrack(s, t, s.length() - 1, t.length() - 1);
        return ans;
    }
    
    private void backtrack(String s, String t, int ps, int pt) {
        if (pt == -1) {
            ans++;
            return;
        } else if (ps < 0) {
            return;
        } else {
            if (s.charAt(ps) == t.charAt(pt)) {
                backtrack(s, t, ps - 1, pt - 1);
                backtrack(s, t, ps - 1, pt);
            } else {
                backtrack(s, t, ps - 1, pt);
            }
        }
    }
}

// DP solution
class Solution {  
    public int numDistinct(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];
        for (int i = 0; i <= s.length(); i++) dp[i][0] = 1;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 1; j <= t.length(); j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + dp[i-1][j];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        return dp[s.length()][t.length()];
    }
}
```

## 591 Tag Validator

```java
class Solution {
    public boolean isValid(String code) {
        if (code.length() < 3) return false;
        Stack<String> stack = new Stack<>();
        if (code.charAt(0) != '<' || code.charAt(1) == '/' || code.charAt(1) == '!') return false;
        boolean isCDATA = false;
        int ind = 0;
        while (ind < code.length()) {
            if (code.charAt(ind) == '<') {
                // 需要查看是否满足某种情况，是否是start tag, closed tag或者<![CDATA[content]]>
                if (ind + 1 < code.length() && code.charAt(ind+1) == '/') {
                    // closed tag
                    ind += 2;
                    int begin = ind;
                    while (ind < code.length() && code.charAt(ind) != '>') {
                        ind++;
                    }
                    String CTAG = code.substring(begin, ind);
                    ind++;
                    if (CTAG.length() == 0 || CTAG.length() > 9 || !isUpper(CTAG) || stack.isEmpty() || !stack.peek().equals(CTAG)) return false;
                    stack.pop();
                    if (ind != code.length() && stack.isEmpty()) return false;
                } else if (ind + 9 < code.length() && code.substring(ind, ind+9).equals("<![CDATA[")) {
                    // cdata-content
                    ind = ind + 9;
                    boolean closed = false;
                    while (ind + 3 < code.length()) {
                        if (code.substring(ind, ind+3).equals("]]>")) {
                            closed = true;
                            break;
                        }
                        ind++;
                    }
                    if (!closed) {
                        return false;
                    }
                    ind = ind + 3;
                } else {
                    // maybe start tag
                    ind++;
                    int begin = ind;
                    while (ind < code.length() && code.charAt(ind) != '>') {
                        ind++;
                    }
                    String TAG = code.substring(begin, ind);
                    ind++;
                    if (TAG.length() == 0 || TAG.length() > 9 || !isUpper(TAG)) return false;
                    stack.push(TAG);
                }
            } else {
                if (ind == code.length() - 1) {
                    return false;
                }
                ind++;
            }
        }
        return stack.isEmpty();
    }
    
    private boolean isUpper(String tag) {
        if (tag.equals("")) return false;
        for (int i = 0; i < tag.length(); i++) {
            if (!Character.isUpperCase(tag.charAt(i))) return false;
        }
        return true;
    }
}

// another solution
class Solution {
    public boolean isValid(String code) {
        Stack<String> stack = new Stack<>();
        for (int i = 0; i < code.length(); ) {
            if (i > 0 && stack.isEmpty()) return false;
            if (code.startsWith("<![CDATA[", i)) {
                int j = i + 9;
                i = code.indexOf("]]>", j);
                if (i < 0) return false;
                i += 3;
            } else if (code.startsWith("</", i)) {
                int j = i + 2;
                i = code.indexOf('>', j);
                if (i < 0 || i == j || i - j > 9) return false;
                for (int k = j; k < i; k++) {
                    if (!Character.isUpperCase(code.charAt(k))) return false;
                }
                String s = code.substring(j, i++);
                if (stack.isEmpty() || !stack.pop().equals(s)) return false;
            } else if (code.startsWith("<", i)) {
                int j = i + 1;
                i = code.indexOf('>', j);
                if (i < 0 || i == j || i - j > 9) return false;
                for (int k = j; k < i; k++) {
                    if (!Character.isUpperCase(code.charAt(k))) return false;
                }
                String s = code.substring(j, i++);
                stack.push(s);
            } else {
                i++;
            }
        }
        return stack.isEmpty();
    }
}
```

## 730 Count Different Palindromic Subsequences

```java
class Solution {
    int[][] memo, prv, nxt;
    byte[] A;
    int MOD = 1_000_000_007;
    
    public int countPalindromicSubsequences(String S) {
        int N = S.length();
        prv = new int[N][4];
        nxt = new int[N][4];
        memo = new int[N][N];
        for (int[] row : prv) Arrays.fill(row, -1);
        for (int[] row : nxt) Arrays.fill(row, -1);
        
        A = new byte[N];
        for (int i = 0; i < S.length(); i++) {
            A[i] = (byte)(S.charAt(i) - 'a');
        }
        
        int[] last = new int[4];
        Arrays.fill(last, -1);
        for (int i = 0; i < N; i++) {
            last[A[i]] = i;
            for (int k = 0; k < 4; k++) {
                prv[i][k] = last[k];
            }
        }
        
        Arrays.fill(last, -1);
        for (int i = N - 1; i >= 0; i--) {
            last[A[i]] = i;
            for (int k = 0; k < 4; k++) {
                nxt[i][k] = last[k];
            }
        }
        
        return dp(0, N - 1) - 1;
    }
    
    private int dp(int i, int j) {
        if (memo[i][j] > 0) return memo[i][j];
        int ans = 1;
        if (i <= j) {
            for (int k = 0; k < 4; k++) {
                int i0 = nxt[i][k];
                int j0 = prv[j][k];
                if (i <= i0 && i0 <= j) ans++;
                if (-1 < i0 && i0 < j0) ans += dp(i0 + 1, j0 - 1);
                if (ans > MOD) ans -= MOD;
            }
        }
        memo[i][j] = ans;
        return ans;
    }
}
```

## 336 Palindrome Pairs

```java
class Solution {
    public List<List<Integer>> palindromePairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < words.length; i++) {
            int l = 0, r = 0;
            while (l <= r) {
                String s = words[i].substring(l, r);
                Integer j = map.get(new StringBuilder(s).reverse().toString());
                if (j != null && i != j && isPalindrome(words[i].substring(l == 0 ? r : 0, l == 0 ? words[i].length() : l))) {
                    res.add(Arrays.asList(l == 0 ? new Integer[] {i, j} : new Integer[]{j, i}));
                }
                if (r < words[i].length()) ++r;
                else ++l;
            }
        }
        return res;
    }
    
    private boolean isPalindrome(String s) {
        int l = 0, h = s.length() - 1;
        while (l <= h) {
            if (s.charAt(l) != s.charAt(h)) return false;
            l++; h--;
        }
        return true;
    }
}
```

## 87 Scramble String

```java
class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1.equals(s2)) return true;
        
        int[] c1 = new int[26];
        int[] c2 = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            c1[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            c2[s2.charAt(i) - 'a']++;
        }
        
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) return false;
        }
        
        for (int i = 1; i < s1.length(); i++) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) && isScramble(s1.substring(i), s2.substring(i))) return true;
            if (isScramble(s1.substring(0, i), s2.substring(s2.length() - i)) && isScramble(s1.substring(i), s2.substring(0, s2.length() - i))) return true;
        }
        return false;
    }
}
```

## 736 Parse Lisp Expression

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

## 76 Minimum Window Substring

```java
class Solution {
    public String minWindow(String s, String t) {
        int len = t.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) + 1);
        }
        int begin = -1;
        int left = 0;
        int end = s.length();
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (map.containsKey(ch)) {
                int num = map.get(ch);
                if (num > 0) {
                    count++;
                }
                map.put(ch, num - 1);
            }
            while (count == len) {
                if (map.containsKey(s.charAt(left))) {
                    if (i + 1 - left < end - begin) {
                        begin = left;
                        end = i + 1;
                    }
                    map.put(s.charAt(left), map.get(s.charAt(left)) + 1);
                    if (map.get(s.charAt(left)) > 0) count--;
                    left++;
                } else {
                    left++;
                }
            }
        }
        if (begin != -1) {
            return s.substring(begin, end);
        } else {
            return "";
        }
    }
}

// 这一题可以参考一下答案，可能写的更加完美把。
// 不知为何，这样子写的更快，难道是取消了HashMap.
class Solution {
    public String minWindow(String s, String t) {
        char[] ss = s.toCharArray();
        char[] tt = t.toCharArray();
        int[] map = new int[128];
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < tt.length; i++) {
            map[(int)tt[i]]++;
        }
        int head = -1;
        int left = 0;
        int count = t.length();
        for (int i = 0; i < ss.length; i++) {
            if (map[(int)ss[i]]-- > 0) {
                count--;
            }
            while (count == 0) {
                if (i + 1 - left < distance) {
                    distance = i + 1 - (head = left);
                }
                if (map[(int)ss[left++]]++ == 0) {
                    count++;
                }
            }
        }
        return distance == Integer.MAX_VALUE ? "" : s.substring(head, head + distance);
    }
}
```

## Interleaving String

```java
class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if (s3.length() != s1.length() + s2.length()) return false;
        boolean[][] matrix = new boolean[s2.length()+1][s1.length()+1];
        matrix[0][0] = true;
        
        for (int i = 1; i < matrix[0].length; i++) {
            matrix[0][i] = matrix[0][i-1] && (s1.charAt(i-1) == s3.charAt(i-1));
        }
        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] = matrix[i-1][0] && (s2.charAt(i-1) == s3.charAt(i-1));
        }
        
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                matrix[i][j] = (matrix[i-1][j] && (s2.charAt(i-1) == s3.charAt(i+j-1)) ||
                               (matrix[i][j-1] && (s1.charAt(j-1) == s3.charAt(i+j-1))));
            }
        }
        return matrix[s2.length()][s1.length()];
    }
}
```

## 214 Shortest Palindrome

```java
// KMP解法线性时间，不过KMP本身就忘记了，后面需要看一下。
class Solution {
    public String shortestPalindrome(String s) {
        if (s.equals("")) return s;
        String ans = "";
        for (int i = s.length() / 2; i >= 0; i--) {
            boolean flag = false;
            if (isPalindrome(s, i, i)) {
                // 可以求出这个值了
                StringBuilder sb = new StringBuilder(s.substring(i));
                ans = sb.toString().substring(1);
                ans = sb.reverse().toString() + ans;
                flag = true;
            }
            if (isPalindrome(s, i, i+1)) {
                //同理可以求出值
                StringBuilder sb = new StringBuilder(s.substring(i+1));
                ans = sb.toString();
                ans = sb.reverse().toString() + ans;
                flag = true;
            }
            if (flag) break;
        }
        return ans;
    }
    
    private boolean isPalindrome(String s, int l, int r) {
        while (l >= 0 && r < s.length()) {
            if (s.charAt(l) != s.charAt(r)) return false;
            l--; r++;
        }
        return l < 0;
    }
}
```

## 32 Longest Valid Parentheses

```java
// 更多解法可以探索，其中DP暂时没看懂，双指针这个有点意思，感觉之前碰到过。
class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (stack.isEmpty()) {
                    stack.push(i);
                } else {
                    max = Math.max(max, i - stack.peek());
                }
            }
        }
        return max;
    }
}
```

## 30 Substring with Concatenation of All Words

```java
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        final Map<String, Integer> counts = new HashMap<>();
        for (final String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        final List<Integer> indexes = new ArrayList<>();
        final int n = s.length(), num = words.length, len = words[0].length();
        for (int i = 0; i < n - num * len + 1; i++) {
            final Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < num) {
                final String word = s.substring(i + j * len, i + (j + 1) * len);
                if (counts.containsKey(word)) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    if (seen.get(word) > counts.getOrDefault(word, 0)) {
                        break;
                    }
                } else {
                    break;
                }
                j++;
            }
            if (j == num) {
                indexes.add(i);
            }
        }
        return indexes;
    }
}

// A better solution
class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        int N = s.length();
        List<Integer> indexes = new ArrayList<>(s.length());
        if (words.length == 0) return indexes;
        int M = words[0].length();
        if (N < M * words.length) return indexes;
        int last = N - M + 1;
        
        // map each string in words array to some index and compute target counter
        Map<String, Integer> mapping = new HashMap<>(words.length);
        int[][] table = new int[2][words.length];
        int failures = 0, index = 0;
        for (int i = 0; i < words.length; i++) {
            Integer mapped = mapping.get(words[i]);
            if (mapped == null) {
                ++failures;
                mapping.put(words[i], index);
                mapped = index++;
            }
            ++table[0][mapped];
        }
        
        // find all occurances at string S and map them to their ihnter, -1 means no such string in words array
        int[] smapping = new int[last];
        for (int i = 0; i < last; i++) {
            String section = s.substring(i, i + M);
            Integer mapped = mapping.get(section);
            if (mapped == null) {
                smapping[i] = -1;
            } else {
                smapping[i] = mapped;
            }
        }
        
        // fix the number of linear scan
        for (int i = 0; i < M; ++i) {
            // reset scan variables
            int currentFailures = failures;     // number of current mismatches
            int left = i, right = i;
            Arrays.fill(table[1], 0);
            while (right < last) {
                while (currentFailures > 0 && right < last) {
                    int target = smapping[right];
                    if (target != -1 && ++table[1][target] == table[0][target]) {
                        --currentFailures;
                    }
                    right += M;
                }
                while (currentFailures == 0 && left < right) {
                    int target = smapping[left];
                    if (target != -1 && --table[1][target] == table[0][target] - 1) {
                        int length = right - left;
                        if ((length / M) == words.length) {
                            indexes.add(left);
                        }
                        ++currentFailures;
                    }
                    left += M;
                }
            }
        }
        return indexes;
    }
}
```

## 10 Regular Expression Matching

```java
// 红宝书解法
class Solution {
    public boolean isMatch(String s, String p) {
        NFA nfa = new NFA(p);
        return nfa.recongnize(s);
    }
}

class Digraph {
    private final int V;
    private int E;
    private List<Integer>[] adj;
    private int[] indegree;
    
    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = E;
        indegree = new int[V];
        adj = (List<Integer>[]) new List[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<>();
        }
    }
    
    public int V() { return V; }
    public int E() { return E; }
    public void addEdge(int v, int w) {
        adj[v].add(w);
        indegree[w]++;
        E++;
    }
    
    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
    
    public int outdegree(int v) {
        return adj[v].size();
    }
    
    public int indegree(int v) {
        return indegree[v];
    }
}

class DirectedDFS {
    private boolean[] marked;
    private int count;
    
    public DirectedDFS(Digraph G, int s) {
        marked = new boolean[G.V()];
        dfs(G, s);
    }
    
    public DirectedDFS(Digraph G, Iterable<Integer> sources) {
        marked = new boolean[G.V()];
        for (int v : sources) {
            if (!marked[v]) dfs(G, v);
        }
    }
    
    private void dfs(Digraph G, int v) {
        count++;
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) dfs(G, w);
        }
    }
    
    public boolean marked(int v) {
        return marked[v];
    }
    
    public int count() { return count; }
}

class NFA {
    private Digraph graph; // digraph of epsilon transitions
    private String regexp; // regular expression
    private int m;         // number of character in regular expression
    
    public NFA(String regexp) {
        this.regexp = regexp;
        m = regexp.length();
        Stack<Integer> ops = new Stack<>();
        graph = new Digraph(m+1);
        for (int i = 0; i < m; i++) {
            int lp = i;
            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '|') {
                ops.push(i);
            }
            else if (regexp.charAt(i) == ')') {
                int or = ops.pop();
                
                // 2-way operator
                if (regexp.charAt(or) == '|') {
                    lp = ops.pop();
                    graph.addEdge(lp, or+1);
                    graph.addEdge(or, i);
                } else if (regexp.charAt(or) == '(') {
                    lp = or;
                }
            }
            
            // closure operator (use 1-character lookahead)
            if (i < m-1 && regexp.charAt(i+1) == '*') {
                graph.addEdge(lp, i+1);
                graph.addEdge(i+1, lp);
            }
            if (regexp.charAt(i) == '(' || regexp.charAt(i) == '*' || regexp.charAt(i) == ')') {
                graph.addEdge(i, i+1);
            }
        }
        if (ops.size() != 0) {
            throw new IllegalArgumentException("Invalid regular expression");
        }
    }
    
    public boolean recongnize(String txt) {
        DirectedDFS dfs = new DirectedDFS(graph, 0);
        List<Integer> pc = new ArrayList<>();
        for (int v = 0; v < graph.V(); v++) {
            if (dfs.marked(v)) pc.add(v);
        }
        
        // compute possible NFA states for txt[i]
        for (int i = 0; i < txt.length(); i++) {
            if (txt.charAt(i) == '*' || txt.charAt(i) == '|' || txt.charAt(i) == '(' || txt.charAt(i) == ')') {
                throw new IllegalArgumentException("text contains the metacharacter `" + txt.charAt(i) + "`");
            }
            
            List<Integer> match = new ArrayList<>();
            for (int v : pc) {
                if (v == m) continue;
                if ((regexp.charAt(v) == txt.charAt(i)) || regexp.charAt(v) == '.') {
                    match.add(v + 1);
                }
            }
            dfs = new DirectedDFS(graph, match);
            pc = new ArrayList<>();
            for (int v = 0; v < graph.V(); v++) {
                if (dfs.marked(v)) pc.add(v);
            }
            if (pc.size() == 0) return false;
        }
        
        // check for accept state
        for (int v : pc) 
            if (v == m) return true;
        return false;
    }
}

// brute force solution
class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        boolean first_match = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.'));
        if (p.length() >= 2 && p.charAt(1) == '*') {
            return (isMatch(s, p.substring(2)) || (first_match && isMatch(s.substring(1), p)));
        } else {
            return first_match && isMatch(s.substring(1), p.substring(1));
        }
    }
}

// dp solution
class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[s.length()][p.length()] = true;
        for (int i = s.length(); i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                boolean first_match = (i < s.length() && (p.charAt(j) == s.charAt(i) || p.charAt(j) == '.'));
                if (j + 1 < p.length() && p.charAt(j+1) == '*') {
                    dp[i][j] = dp[i][j+2] || first_match && dp[i+1][j];
                } else {
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
```

## Wildcard Matching

```java
// TLE solution
class Solution {
    public boolean isMatch(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (s.isEmpty()) {
            for (int i = 0; i < p.length(); i++) {
                if (p.charAt(i) != '*') return false;
            }
            return true;
        }
        if (p.charAt(0) == '*') {
            // 直接替代了
            return isMatch(s, substring(p, 1)) || isMatch(substring(s, 1), p) || isMatch(substring(s, 1), substring(p, 1));
        } else {
            boolean first_match = (!s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '?'));
            return first_match && isMatch(substring(s, 1), p.substring(1));
        }
    }
    
    private String substring(String s, int beginIndex) {
        if (beginIndex >= s.length()) return "";
        else return s.substring(beginIndex);
    }
}

// DP solution
// 需要注意，一般来说先写出递归做法，再写出DP写法，主要是这样子容易推导出递推式。
class Solution {
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[s.length()][p.length()] = true;
        // 初始化
        for (int i = s.length() - 1; i >= 0; i--) {
            dp[i][p.length()] = false;
        }
        boolean flag = true;
        for (int j = p.length() - 1; j >= 0; j--) {
            if (flag && p.charAt(j) == '*') {
               dp[s.length()][j] = true; 
            } else {
                flag = false;
                dp[s.length()][j] = false;
            }
        }
        for (int i = s.length() - 1; i >= 0; i--) {
            for (int j = p.length() - 1; j >= 0; j--) {
                if (p.charAt(j) == '*') {
                    dp[i][j] = dp[i][j+1] || dp[i+1][j] || dp[i+1][j+1];
                } else {
                    boolean first_match = p.charAt(j) == s.charAt(i) || p.charAt(j) == '?';
                    dp[i][j] = first_match && dp[i+1][j+1];
                }
            }
        }
        return dp[0][0];
    }
}
```

## 273 Integer to English Words

```java
class Solution {
    Map<Integer, String> m1 =  new HashMap<Integer, String>(){{
        put(0, "");
        put(1, "One");
        put(2, "Two");
        put(3, "Three");
        put(4, "Four");
        put(5, "Five");
        put(6, "Six");
        put(7, "Seven");
        put(8, "Eight");
        put(9, "Nine");
    }};
    Map<Integer, String> m2 = new HashMap<Integer, String>(){{
        put(0, "");
        put(2, "Twenty");
        put(3, "Thirty");
        put(4, "Forty");
        put(5, "Fifty");
        put(6, "Sixty");
        put(7, "Seventy");
        put(8, "Eighty");
        put(9, "Ninety");
    }};
    Map<Integer, String> m3 = new HashMap<Integer, String>(){{
        put(10, "Ten");
        put(11, "Eleven");
        put(12, "Twelve");
        put(13, "Thirteen");
        put(14, "Fourteen");
        put(15, "Fifteen");
        put(16, "Sixteen");
        put(17, "Seventeen");
        put(18, "Eighteen");
        put(19, "Nineteen");
    }};
    
    public String numberToWords(int num) {
        if (num == 0) return "Zero";
        List<String> sub = toWords(num);
        String ans = "";
        for (String str : sub) {
            ans += str + " ";
        }
        return ans.trim();
    }
    
    public List<String> toWords(int num) {
        List<String> ans = new ArrayList<>();
        if (num >= 1_000_000_000) {
            ans.addAll(toWords(num / 1_000_000_000));
            ans.add("Billion");
        }
        num = num - (num / 1_000_000_000) * 1_000_000_000;
        if (num >= 1_000_000) {
            ans.addAll(toWords(num / 1_000_000));
            ans.add("Million");
        }
        num = num - (num / 1_000_000) * 1_000_000;
        if (num >= 1_000) {
            ans.addAll(toWords(num / 1_000));
            ans.add("Thousand");
        }
        num = num - (num / 1_000) * 1_000;
        if (num >= 100) {
            ans.addAll(toWords(num / 100));
            ans.add("Hundred");
        }
        num = num - (num / 100) * 100;
        // 现在开始处理十分位和个分位
        if (num >= 10 && num < 20) {
            ans.add(m3.get(num));
        } else {
            int p2 = num / 10, p1 = num % 10;
            String s2 = m2.get(p2), s1 = m1.get(p1);
            if (!s2.equals("")) ans.add(s2);
            if (!s1.equals("")) ans.add(s1);
        }
        return ans;
    }
}
```

## 68 Text Justification

```java
class Solution {
    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> todo = new ArrayList<>(words.length + 1);
        List<String> res = new ArrayList<>();
        int width = 0;
        int num = -1;
        for (int i = 0; i < words.length; ) {
            if (width == 0) {
                todo.add(words[i]);
                width += words[i].length();
                num++;
                i++;
            } else {
                if (width + num + 1 + words[i].length() > maxWidth) {
                    // 开始协调
                    res.add(generate(todo, width, num, maxWidth));
                    // 添加完毕，清空
                    todo.clear();
                    width = 0;
                    num = -1;
                } else {
                    // 可以继续添加
                    todo.add(words[i]);
                    width += words[i].length();
                    num++;
                    i++;
                }
            }
        }
        // 最后一行肯定存在，单独处理
        String s = "";
        for (int i = 0; i < todo.size(); i++) {
            s += todo.get(i);
            if (s.length() < maxWidth) s += " ";
        }
        for (int i = s.length(); i < maxWidth; i++) {
            s += " ";
        }
        res.add(s);
        return res;
    }
    
    private String generate(List<String> todo, int width, int num, int maxWidth) {
        if (todo.size() == 1) {
            String s = todo.get(0);
            for (int i = s.length(); i < maxWidth; i++) {
                s += " ";
            }
            return s;
        } else {
            // 该值肯定大于等于1
            int step = (maxWidth - width) / num;
            int more = maxWidth - width - step * num;
            // System.out.println(width + " " + num + " " + step + " " + more);
            String s = "";
            for (int i = 0; i < todo.size(); i++) {
                s += todo.get(i);
                if (i != todo.size() - 1) {
                    for (int j = 0; j < step; j++) {
                        s += " ";
                    }
                    if (more > 0) {
                        s += " ";
                        more--;
                    }
                }
            }
            return s;
        }
    }
}
```

## 564 Find the Cloest Palindrome

```java
class Solution {
    private String mirroring(String s) {
        String x = s.substring(0, s.length() / 2);
        return x + (s.length() % 2 == 1 ? s.charAt(s.length() / 2) : "") + new StringBuilder(x).reverse().toString();
    }
    
    public String nearestPalindromic(String n) {
        if (n.length() == 1) return Integer.toString(n.charAt(0) - '0' - 1);
        
        String a = mirroring(n);
        long diff1 = Long.MAX_VALUE;
        diff1 = Math.abs(Long.parseLong(n) - Long.parseLong(a));
        if (diff1 == 0) {
            diff1 = Long.MAX_VALUE;
        }
        
        StringBuilder s = new StringBuilder(n);
        int i = (s.length() - 1) / 2;
        while (i >= 0 && s.charAt(i) == '0') {
            s.replace(i, i+1, "9");
            i--;
        }
        if (i == 0 && s.charAt(i) == '1') {
            s.delete(0, 1);
            int mid = (s.length() - 1) / 2;
            s.replace(mid, mid+1, "9");
        } else {
            s.replace(i, i+1, "" + (char)(s.charAt(i)-1));
        }
        
        String b = mirroring(s.toString());
        long diff2 = Math.abs(Long.parseLong(n) - Long.parseLong(b));
        
        s = new StringBuilder(n);
        i = (s.length() - 1) / 2;
        while (i >= 0 && s.charAt(i) == '9') {
            s.replace(i, i+1, "0");
            i--;
        }
        if (i < 0) {
            s.insert(0, "1");
        } else {
            s.replace(i, i+1, "" + (char)(s.charAt(i)+1));
        }
        String c = mirroring(s.toString());
        long diff3 = Math.abs(Long.parseLong(n) - Long.parseLong(c));
        if (diff2 <= diff1 && diff2 <= diff3) {
            return b;
        }
        if (diff1 <= diff3 && diff1 <= diff2) {
            return a;
        } else {
            return c;
        }
    }
}
```

## 65 Valid Number

```java
class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        if (isNumeric(s)) {
            // 可能是一个数字
            // 此时先判断啥，是否有e?
            int ei = s.indexOf("e");
            if (ei != -1) {
                // 有科学记数法
                System.out.println(s.substring(0, ei) + " " + s.substring(ei+1));
                return (isInteger(s.substring(0, ei)) || isFloat(s.substring(0, ei))) && isInteger(s.substring(ei+1));
            } else {
                // 没有科学记数法，此时怎么判断是否是合理的数字
                return isInteger(s) || isFloat(s);
            }
        } else {
            return false;   
        }
    }
    
    private boolean isNumeric(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!Character.isDigit(ch) && !(ch == '+' || ch == '-' || ch == 'e' || ch == '.')) return false;
        }
        return true;
    }
    
    private boolean isInteger(String s) {
        if (s.length() == 0 || s.indexOf(".") != -1) return false;
        else if (s.length() == 1 && (s.charAt(0) == '+' || s.charAt(0) == '-')) return false;
        else {
            int i = 0;
            if (s.charAt(0) == '+' || s.charAt(i) == '-') i++;
            for (; i < s.length(); i++) {
               if (!Character.isDigit(s.charAt(i))) return false; 
            }
            return true;
        }
    }
    
    private boolean isFloat(String s) {
        int ind = s.indexOf(".");
        if (ind != -1) {
            // 存在
            if (s.indexOf(".", ind + 1) != -1) return false;
            for (int i = ind + 1; i < s.length(); i++) {
                if (!Character.isDigit(s.charAt(i))) return false;
            }
            return (ind == 0 && ind != s.length()-1) || (ind == 1 && (s.charAt(0) == '+' || s.charAt(0) == '-') && ind != s.length()-1) || isInteger(s.substring(0, ind));
        } else {
            return false;
        }
    }
}

// A better solution
class Solution {
    public boolean isNumber(String s) {
        s = s.trim();
        boolean pointSeen = false;
        boolean eSeen = false;
        boolean numberSeen = false;
        boolean numberAfterE = true;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isDigit(s.charAt(i))) {
                numberSeen = true;
                numberAfterE = true;
            } else if (s.charAt(i) == '.') {
                if (eSeen || pointSeen) {
                    return false;
                }
                pointSeen = true;
            } else if (s.charAt(i) == 'e') {
                if (eSeen || !numberSeen) {
                    return false;
                }
                numberAfterE = false;
                eSeen = true;
            } else if (s.charAt(i) == '-' || s.charAt(i) == '+') {
                if (i != 0 && s.charAt(i-1) != 'e') {
                    return false;
                }
            } else {
                return false;
            }
        }
        return numberSeen && numberAfterE;
    }
}
```