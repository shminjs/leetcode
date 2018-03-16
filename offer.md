# 剑指offer专题训练

## 二维数组中的查找

```java
public class Solution {
    public boolean Find(int target, int [][] array) {
        int M = array.length, N = array[0].length;
        int r = 0, c = N - 1;
        while (r < M && c >= 0) {
            if (array[r][c] == target) return true;
            else if (array[r][c] < target) {
                r++;
            } else {
                c--;
            }
        }
        return false;
    }
}
```

## 替换空格

```java
public class Solution {
    public String replaceSpace(StringBuffer str) {
    	StringBuilder sb = new StringBuilder();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == ' ') {
                sb.append("%20");
            } else {
                sb.append(str.charAt(i));
            }
        }
        return sb.toString();
    }
}
```

## 从尾到头打印链表

```java
/**
*    public class ListNode {
*        int val;
*        ListNode next = null;
*
*        ListNode(int val) {
*            this.val = val;
*        }
*    }
*
*/
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;
public class Solution {
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        List<Integer> res = new LinkedList<>();
        while (listNode != null) {
            res.add(0, listNode.val);
            listNode = listNode.next;
        }
        return new ArrayList<>(res);
    }
}
```

## 重建二叉树

```java
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Solution {
    public TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        return generate(pre, 0, pre.length, in, 0, in.length);
    }
    
    private TreeNode generate(int[] pre, int l1, int h1, int[] in, int l2, int h2) {
        if (l1 >= h1 || l2 >= h2) {
            // 不存在， 返回null
            return null;
        } else if (l1 == h1 - 1 && l2 == h2 - 1) {
            // 只有一个节点，返回即可
            return new TreeNode(pre[l1]);
        } else {
            int key = pre[l1];
            int index = -1;
            for (int i = 0; i < in.length; i++) {
                if (key == in[i]) {
                    index = i;
                    break;
                }
            }
            TreeNode head = new TreeNode(pre[l1]);
            head.left = generate(pre, l1 + 1, l1 + 1 + index - l2, in, l2, index);
            head.right = generate(pre, l1 + 1 + index - l2, h1, in, index + 1, h2);
            return head;
        }
    }
}
```

## 用两个栈实现队列

```java
import java.util.Stack;

public class Solution {
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    public void push(int node) {
        stack1.push(node);
    }
    
    public int pop() {
        if (stack2.isEmpty()) {
            // 移动
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
```

## 旋转数组的最小数字

```java
// MISTAKE
import java.util.ArrayList;
public class Solution {
    public int minNumberInRotateArray(int [] array) {
        int lo = 0, hi = array.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if (array[mid] > array[hi]) lo = mid + 1;
            else hi = mid;
        }
        return array[lo];
    }
}
```

## 斐波那契数列

```java
public class Solution {
    public int Fibonacci(int n) {
        if (n <= 1) return n;
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
```

## 跳台阶

```java
import java.util.Arrays;

public class Solution {
    public int JumpFloor(int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            dp[i] = (i>=2 ? dp[i-2] : 0) + dp[i-1];
        }
        return dp[target];
    }
}
```

## 变态跳台阶

```java
public class Solution {
    public int JumpFloorII(int target) {
        int[] dp = new int[target+1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int j = i-1; j>= 0; j--) {
                dp[i] += dp[j];
            }
        }
        return dp[target];
    }
}
```

## 矩形覆盖

```java
// MISTAKEN
public class Solution {
    public int RectCover(int target) {
        if (target <= 0) return 0;
        else if (target == 1) return 1;
        else if (target == 2) return 2;
        int[] dp = new int[target+1];
        dp[1] = 1; dp[2] = 2;
        for (int i = 3; i <= target; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[target];
    }
}
```

## 二进制中1的个数

```java
// MISTAKEN
public class Solution {
    public int NumberOf1(int n) {
        int sum = 0;
        while (n != 0) {
            sum++;
            n &= (n - 1);
        }
        return sum;
    }
}
```

## 数值的整数次方

```java
public class Solution {
    public double Power(double base, int exponent) {
        if (exponent < 0) {
            exponent = -exponent;
            base = 1 / base;
        }
        if (exponent == 0) return 1.0;
        double k = Power(base, exponent / 2);
        if (exponent % 2 == 0) {
            return k * k;
        } else {
            return base * k * k;
        }
  }
}
```

## 调整数组顺序使得奇数位位于偶数前面

```java
public class Solution {
    public void reOrderArray(int [] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 1) {
                // 奇数，向前移动，除非碰到边界或者奇数
                int temp = array[i];
                int j = i - 1;
                for (; j >= 0; j--) {
                    if (array[j] % 2 == 1) {
                        break;
                    } else {
                        array[j+1] = array[j];
                    }
                }
                array[j+1] = temp;
            }
        }
    }
}
```

## 链表中倒数第k个结点

```java
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode FindKthToTail(ListNode head,int k) {
        // 两部完成
        // 第一步：找到第1个节点和第k个节点
        ListNode first = head;
        ListNode second = head;
        while (second != null && k > 0) {
            second = second.next;
            k--;
        }
        if (k > 0) return null;
        // 第二步：迭代，当最后一个节点为null时，第一个节点在倒数第k个位置
        while (first != null && second != null) {
            first = first.next;
            second = second.next;
        }
        return first;
    }
}
```

## 反转链表

```java
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode ReverseList(ListNode head) {
        ListNode p = head;
        ListNode prev = null;
        while (p != null) {
            ListNode next = p.next;
            
            p.next = prev;
            prev = p;
            
            p = next;
        }
        return prev;
    }
}
```

## 合并两个排序的链表

```java
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode Merge(ListNode list1,ListNode list2) {
        ListNode nhead = new ListNode(Integer.MIN_VALUE);
        ListNode p = nhead, p1 = list1, p2 = list2;
        while (p1 != null && p2 != null) {
            if (p1.val < p2.val) {
                p.next = p1;
                p1 = p1.next;
                p = p.next;
                p.next = null;
            } else {
                p.next = p2;
                p2 = p2.next;
                p = p.next;
                p.next = null;
            }
        }
        p.next = p1 == null ? p2 : p1;
        return nhead.next;
    }
}
```

## 树的子结构

```java
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public boolean HasSubtree(TreeNode s,TreeNode t) {
        if (s == null || t == null) return false;
        return isSame(s, t) || HasSubtree(s.left, t) || HasSubtree(s.right, t);
    }
    
    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        else if (s != null && t == null) return true;
        else if (s == null && t != null) return false;
        else return s.val == t.val && isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}
```

## 二叉树的镜像

```java
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public void Mirror(TreeNode root) {
        if (root == null) return;
        TreeNode swap = root.left;
        root.left = root.right;
        root.right = swap;
        Mirror(root.left);
        Mirror(root.right);
    }
}
```

## 顺时针打印矩阵

```java
// MISTAKEN
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        int M = matrix.length, N = matrix[0].length;
        ArrayList<Integer> res = new ArrayList<>();
        boolean[][] visited = new boolean[M][N];
        int x = 0, y = 0;
        int dx = 0, dy = 1;
        int index = 0;
        while (index < M * N) {
            res.add(matrix[x][y]);
            visited[x][y] = true;
            index++;
            if (y + dy == N || x + dx == M || y + dy == -1 || visited[x+dx][y+dy]) {
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

## 包含main函数的栈

```java
import java.util.Stack;

public class Solution {
    Stack<Element> stack = new Stack<>();
    
    public void push(int node) {
        Element elem = new Element(node);
        elem.min = Math.min(node, min());
        stack.push(elem);
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        return stack.peek().value;
    }
    
    public int min() {
        if (stack.isEmpty()) return Integer.MAX_VALUE;
        else return stack.peek().min;
    }
    
    class Element {
        int value;
        int min;
        public Element(int value) {
            this.value = value;
        }
    }
}
```

## 栈的压入、弹出序列

```java
import java.util.ArrayList;
import java.util.Stack;

public class Solution {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        Stack<Integer> stack = new Stack<>();
        int ind = 0;
        for (int i = 0; i < pushA.length; i++) {
            stack.push(pushA[i]);
            while (!stack.isEmpty() && stack.peek() == popA[ind]) {
                stack.pop();
                ind++;
            }
        }
        return stack.isEmpty();
    }
}
```

## 从上往下打印二叉树

```java
import java.util.ArrayList;
import java.util.LinkedList;
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if (root == null) return res;
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode elem = queue.poll();
            res.add(elem.val);
            if (elem.left !=  null) queue.offer(elem.left);
            if (elem.right != null) queue.offer(elem.right);
        }
        return res;
    }
}
```

## 二叉搜索树的后序遍历序列

```java
public class Solution {
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 0) return false;
        return VerifySquenceOfBST(sequence, 0, sequence.length);
    }
    
    private boolean VerifySquenceOfBST(int[] sequence, int lo, int hi) {
        if (lo >= hi - 1) {
            return true;
        } else {
            int i = lo, index = lo;
            for (; i < hi - 1; i++) {
                if (sequence[hi-1] < sequence[i]) break;
            }
            index = i;
            for (; i < hi - 1; i++) {
                if (sequence[hi-1] >= sequence[i]) return false;
            }
            return VerifySquenceOfBST(sequence, lo, index) && VerifySquenceOfBST(sequence, index, hi - 1);
        }
    }
}
```

## 二叉树中和为某一值的路径

```java
// MISTAKEN
import java.util.ArrayList;
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        dfs(root, target, new ArrayList<>(), res);
        return res;
    }
    
    private void dfs(TreeNode root, int target, ArrayList<TreeNode> stack, ArrayList<ArrayList<Integer>> res) {
        if (root == null) return;
        stack.add(root);
        if (root.left == null && root.right == null && target == root.val) {
            ArrayList<Integer> sub = new ArrayList<>();
            for (TreeNode node : stack) {
                sub.add(node.val);
            }
            res.add(sub);
        }
        dfs(root.left, target - root.val, stack, res);
        dfs(root.right, target - root.val, stack, res);
        stack.remove(stack.size()-1);
    }
}
```

## 复杂链表的复制

```java
/*
public class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}
*/
public class Solution {
    public RandomListNode Clone(RandomListNode pHead)
    {
        if (pHead == null) return pHead;
        // First Round: 复制数组
        RandomListNode p1 = pHead;
        while (p1 != null) {
            RandomListNode next = p1.next;
            
            RandomListNode sub = new RandomListNode(p1.label);
            p1.next = sub;
            sub.next = next;
            
            p1 = next;
        }
        // Second Round: 增加随机指针指向
        p1 = pHead;
        while (p1 != null) {
            RandomListNode next = p1.next.next;
            if (p1.random != null) {
                p1.next.random = p1.random.next;
            }
            p1 = next;
        }
        // Third Round: 分裂两个list
        RandomListNode rHead = new RandomListNode(Integer.MIN_VALUE), p2 = rHead;
        p1 = pHead;
        while (p1 != null) {
            RandomListNode next = p1.next.next;
            
            p2.next = p1.next;
            p2 = p2.next;
            p2.next = null;
            p1.next = next;
            
            p1 = next;
            
        }
        return rHead.next;
    }
}
```

## 二叉搜索树与双向链表

```java
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    TreeNode prev = null;
    public TreeNode Convert(TreeNode pRootOfTree) {
        TreeNode head = null, p = pRootOfTree;
        while (p != null) {
            head = p;
            p = p.left;
        }
        traverse(pRootOfTree);
        return head;
    }
    
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        root.left = prev;
        if (prev != null) {
            prev.right = root;
        }
        prev = root;
        traverse(root.right);
    }
}
```

## 字符串的排列

```java
import java.util.ArrayList;
import java.util.Arrays;
public class Solution {
    public ArrayList<String> Permutation(String str) {
       ArrayList<String> res = new ArrayList<>();
       if (str.length() == 0) return res; 
       char[] strs = str.toCharArray();
       boolean[] visited = new boolean[strs.length];
       Arrays.sort(strs);
       backtrack(strs, visited, new StringBuilder(), res);
       return res;
    }
    
    private void backtrack(char[] strs, boolean[] visited, StringBuilder sb, ArrayList<String> res) {
        if (sb.length() == strs.length) {
            res.add(sb.toString());
            return;
        }
        for (int i = 0; i < strs.length; i++) {
            if (visited[i] || i > 0 && strs[i] == strs[i-1] && !visited[i-1]) continue;
            visited[i] = true;
            sb.append(strs[i]);
            backtrack(strs, visited, sb, res);
            sb.deleteCharAt(sb.length()-1);
            visited[i] = false;
        }
    }
}
```

## 数组中出现次数超过一半的数字

```java
public class Solution {
    public int MoreThanHalfNum_Solution(int [] array) {
        int res = array[0], count = 0;
        for (int i = 1; i < array.length; i++) {
            if (count == 0) {
                res = array[i];
                count = 1;
            } else if (res == array[i]) {
                count++;
            } else {
                count--;
            }
        }
        count = 0;
        for (int i = 0; i < array.length; i++) {
            if (res == array[i]) count++;
        }
        return count > array.length / 2 ? res : 0;
    }
}
```

## 最小的k个数

```java
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Collections;
public class Solution {
    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> res = new ArrayList<>();
        if (k > input.length) return res;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for (int i = 0; i < input.length; i++) {
            if (pq.size() < k) {
                pq.offer(input[i]);
            } else {
                pq.offer(input[i]);
                pq.poll();
            }
        }
        while (!pq.isEmpty()) {
            res.add(pq.poll());
        }
        return res;
    }
}
```

## 连续子数组的最大和

```java
public class Solution {
    public int FindGreatestSumOfSubArray(int[] array) {
        int maxSoFar = array[0], max = array[0];
        for (int i = 1; i < array.length; i++) {
            maxSoFar = Math.max(array[i], maxSoFar + array[i]);
            max = Math.max(maxSoFar, max);
        }
        return max;
    }
}
```

## 整数中1出现的次数

```java
// MISTAKEN
public class Solution {
    public int NumberOf1Between1AndN_Solution(int n) {
        int countr = 0;
        for (long i = 1; i <= n; i *= 10) {
            long divider = i * 10;
            countr += (n / divider) * i + Math.min(Math.max(n % divider - i + 1, 0L), i);
        }
        return countr;
    }
}
```

## 把数组排成最小的数

```java
import java.util.ArrayList;
import java.util.Arrays;
public class Solution {
    String res = "";
    public String PrintMinNumber(int [] numbers) {
        Arrays.sort(numbers);
        backtrack(numbers, new boolean[numbers.length], new ArrayList<>());
        return res;
    }
    
    private void backtrack(int[] numbers, boolean[] visited, ArrayList<Integer> sub) {
        if (sub.size() == numbers.length) {
            // TODO: 生成字符串并比较
            String cmp = "";
            for (Integer num : sub) {
                cmp += num.toString();
            }
            if (res.equals("")) res = cmp;
            else if (cmp.compareTo(res) < 0) res = cmp;
        }
        for (int i = 0; i < numbers.length; i++) {
            if (visited[i] || i > 0 && numbers[i] == numbers[i-1] && !visited[i-1]) continue;
            visited[i] = true;
            sub.add(numbers[i]);
            backtrack(numbers, visited, sub);
            sub.remove(sub.size()-1);
            visited[i] = false;
        }
    }
}
```

## 丑数

```java
import java.util.Arrays;
public class Solution {
    public int GetUglyNumber_Solution(int index) {
        if (index == 0) return 0;
        int[] dp = new int[index];
        dp[0] = 1;
        int twoInd = 0;
        int threeInd = 0;
        int fiveInd = 0;
        for (int i = 1; i < index; i++) {
            int v = Math.min(Math.min(dp[twoInd] * 2, dp[threeInd] * 3), dp[fiveInd] * 5);
            dp[i] = v;
            if (dp[twoInd] * 2 == v) {
                twoInd++;
            } 
            if (dp[threeInd] * 3 == v) {
                threeInd++;
            }
            if (dp[fiveInd] * 5 == v) {
                fiveInd++;
            }
        }
        return dp[index-1];
    }
}
```

## 第一个只出现一次的字符

```java
import java.util.Map;
import java.util.HashMap;
public class Solution {
    public int FirstNotRepeatingChar(String str) {
        Map<Character, Integer> map = new HashMap<>();
        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }
        for (int i = 0; i < str.length(); i++) {
            if (map.get(str.charAt(i)) == 1) {
                return i;
            }
        }
        return -1;
    }
}
```

## 数组中的逆序对

```java
public class Solution {
    int MOD = 1000000007;
    public int InversePairs(int [] array) {
        int[] aux = new int[array.length];
        return mergeSort(array, aux, 0, array.length);
    }
    
    private int mergeSort(int[] array, int[] aux, int lo, int hi) {
        if (lo >= hi - 1) {
            return 0;
        }
        int mid = lo + (hi - lo) / 2;
        int left = mergeSort(array, aux, lo, mid) % MOD;
        int right = mergeSort(array, aux, mid, hi) % MOD;
        System.arraycopy(array, lo, aux, lo, hi-lo);
        int count = 0;
        int i = lo, j = mid;
        while (i < mid || j < hi) {
            if (j == hi || i < mid && aux[i] < aux[j]) {
                array[i+j-mid] = aux[i];
                i++;
                count += j - mid;
                count = count % MOD;
            } else {
                array[i+j-mid] = aux[j];
                j++;
            }
        }
        return ((count + left) % MOD + right) % MOD;
    }
}
```

## 两个链表中的第一个公共结点

```java
/*
public class ListNode {
    int val;
    ListNode next = null;

    ListNode(int val) {
        this.val = val;
    }
}*/
public class Solution {
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        ListNode p1 = pHead1, p2 = pHead2;
        while (p1 != null || p2 != null) {
            if (p1 == p2) return p1;
            p1 = p1 == null ? pHead2 : p1.next;
            p2 = p2 == null ? pHead1 : p2.next;
        }
        return null;
    }
}
```

## 数字在排序数组中出现的次数

```java
public class Solution {
    public int GetNumberOfK(int [] array , int k) {
       int left = lower_bound(array, k);
       int right = upper_bound(array, k);
       return right - left;
    }
    
    private int lower_bound(int[] array, int k) {
        int first = 0, len = array.length;
        int half = 0, mid = 0;
        
        while (len > 0) {
            half = len >> 1;
            mid = first + half;
            if (array[mid] < k) {
                first = mid + 1;
                len = len - half - 1;
            } else {
                len = half;
            }
        }
        return first;
    }
    
    private int upper_bound(int[] array, int k) {
        int first = 0, len = array.length;
        int half = 0, mid = 0;
        
        while (len > 0) {
            half = len >> 1;
            mid = first + half;
            if (array[mid] > k) {
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

## 二叉树的深度

```java
/**
public class TreeNode {
    int val = 0;
    TreeNode left = null;
    TreeNode right = null;

    public TreeNode(int val) {
        this.val = val;

    }

}
*/
public class Solution {
    public int TreeDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(TreeDepth(root.left), TreeDepth(root.right));
    }
}
```

## 平衡二叉树

```java
public class Solution {
    boolean flag = true;
    public boolean IsBalanced_Solution(TreeNode root) {
        backtrack(root);
        return flag;
    }
    
    private int backtrack(TreeNode root) {
        if (root == null) return 0;
        int left = backtrack(root.left);
        int right = backtrack(root.right);
        if (Math.abs(left - right) > 1) {
            flag = false;
        }
        return 1 + Math.max(left, right);
    }
}
```

## 数组中只出现一次的数字

```java
//num1,num2分别为长度为1的数组。传出参数
//将num1[0],num2[0]设置为返回结果
public class Solution {
    public void FindNumsAppearOnce(int [] array,int num1[] , int num2[]) {
        int diff = 0;
        for (int num : array) {
            diff ^= num;
        }
        diff &= -diff;
        
        for (int num : array) {
            if ((diff & num) == 0) {
                num1[0] ^= num;
            } else {
                num2[0] ^= num;
            }
        }
    }
}
// 最后剩下a^b
```

## 和为S的连续正数序列

```java
import java.util.ArrayList;
public class Solution {
    public ArrayList<ArrayList<Integer> > FindContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (sum <= 1) return res;
        int[] preSum = new int[(sum+1)/2+1];
        for (int i = 1; i < preSum.length; i++) {
            preSum[i] = preSum[i-1] + i;
        }
        for (int i = preSum.length - 1; i >= 0; i--) {
            if (preSum[i] >= sum) {
                // TODO: 可能存在
                int left = binarySearch(preSum, preSum[i] - sum);
                if (left >= 0) {
                    // 存在，生成
                    ArrayList<Integer> sub =  new ArrayList<>();
                    for (int j = left + 1; j <= i; j++) {
                        sub.add(j);
                    }
                    res.add(0, sub);
                }
            } else {
                // TODO: 已经不存在了，可以直接退出
                break;
            }
        }
        return res;
    }
    
    private int binarySearch(int[] nums, int k) {
        int lo = 0, hi = nums.length - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == k) return mid;
            else if (nums[mid] < k) {
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return -1;
    }
}
// 0 1 3
```

## 和为S的两个数字

```java
import java.util.ArrayList;
public class Solution {
    public ArrayList<Integer> FindNumbersWithSum(int [] array,int sum) {
        int lo = 0, hi = array.length - 1;
        ArrayList<Integer> res = new ArrayList<>();
        while (lo <= hi) {
            if (array[lo] + array[hi] == sum) {
                res.add(array[lo]);
                res.add(array[hi]);
                break;
            } else if (array[lo] + array[hi] < sum) {
                lo++;
            } else {
                hi--;
            }
        }
        return res;
    }
}
```

## 左旋转字符串

```java
public class Solution {
    public String LeftRotateString(String str,int n) {
        if (str.length() == 0) return str;
        char[] strs = str.toCharArray();
        n = n % strs.length;
        if (n == 0) return str;
        reverse(strs, 0, strs.length-1);
        reverse(strs, 0, strs.length-n-1);
        reverse(strs, strs.length-n, strs.length-1);
        return new String(strs);
    }
    
    private void reverse(char[] strs, int lo, int hi) {
        while (lo <= hi) {
            swap(strs, lo++, hi--);
        }
    }
    
    private void swap(char[] strs, int i, int j) {
        char tmp = strs[j];
        strs[j] = strs[i];
        strs[i] = tmp;
    }
}
```

## 反转单词顺序列

```java
public class Solution {
    public String ReverseSentence(String str) {
        String[] strs = str.split(" ");
        String res = "";
        for (int i = strs.length-1; i >= 0; i--) {
            res += strs[i];
            if (i > 0) {
                res += " ";
            }
        }
        if (!res.equals("")) return res;
        else return str;
    }
}
```

## 扑克牌顺子

```java
public class Solution {
    public boolean isContinuous(int [] numbers) {
        if (numbers.length == 0) return false;
        int min = Integer.MAX_VALUE;
        for (int num : numbers) {
            if (num != 0) {
                min = Math.min(min, num);
            }
        }
        int count0 = 0, count1 = 0;
        boolean[] visited = new boolean[numbers.length];
        for (int num : numbers) {
            if (num == 0) {
                count0++;
            }else if (num - min < numbers.length && !visited[num-min]) {
                visited[num-min] = true;
                count1++;
            }
        }
        return count1 + count0 == numbers.length;
    }
}
```