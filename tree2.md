## 111 Minimum Depth of Binary Tree

```java
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
    public int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        int left = Integer.MAX_VALUE;
        int right = Integer.MAX_VALUE;
        if (root.left != null) left = minDepth(root.left);
        if (root.right != null) right = minDepth(root.right);
        return Math.min(left, right) + 1;
    }
}

// better solution
public class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;
    }
}
```

## 107 Binary Tree Level Order Traversal II

```java
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
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            List<Integer> tmp = new LinkedList<>();
            for (int i = 0; i < levelNum; i++) {
                TreeNode item = queue.poll();
                tmp.add(item.val);
                if (item.left != null) queue.offer(item.left);
                if (item.right != null) queue.offer(item.right);
            }
            res.add(0, tmp);
        }
        return res;
    }
}
```

## 404 Sum of Left Leaves

```java
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
    public int sumOfLeftLeaves(TreeNode root) {
        return sumOfLeaves(root, false);
    }
    
    private int sumOfLeaves(TreeNode root, boolean side) {
        if (root == null) return 0;
        if (side && root.left == null && root.right == null) return root.val;
        return sumOfLeaves(root.left, true) + sumOfLeaves(root.right, false);
    }
}
```

## 687 Longest Univalue Path

```java
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
    int result = 0;
    public int longestUnivaluePath(TreeNode root) {
        traverse(root);
        return result;
    }

    private void traverse(TreeNode root) {
        if (root == null) return;
        int left = pathThroughThisNode(root.left, root.val);
        int right = pathThroughThisNode(root.right, root.val);
        result = Math.max(result, left + right);
        traverse(root.left);
        traverse(root.right);
    }

    private int pathThroughThisNode(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        if (root.val == val) {
            int left = pathThroughThisNode(root.left, val) + 1;
            int right = pathThroughThisNode(root.right, val) + 1;
            return Math.max(left, right);
        } else {
            return 0;
        }
    }
}

// better solution
// 重复利用已有的值
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
    public int longestUnivaluePath(TreeNode root) {
        int[] res = new int[1];
        if (root != null) traverse(root, res);
        return res[0];
    }

    private int traverse(TreeNode root, int[] res) {
        int l = root.left == null ? 0 : traverse(root.left, res);
        int r = root.right == null ? 0 : traverse(root.right, res);
        int rl = root.left != null && root.val == root.left.val ? l + 1 : 0;
        int rr = root.right != null && root.val == root.right.val ? r + 1 : 0;
        res[0] = Math.max(res[0], rl + rr);
        return Math.max(rl, rr);
    }
}
```

## 669 Trim a Binary Search Tree

```java
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
    public TreeNode trimBST(TreeNode root, int L, int R) {
        if (root == null) return root;
        if (root.val > R) {
            return trimBST(root.left, L, R);
        }else if (root.val < L) {
            return trimBST(root.right, L, R);
        } else {
            root.left = trimBST(root.left, L, R);
            root.right = trimBST(root.right, L, R);
            return root;
        }
    }
}
```

## 637 Average of Levels in Binary Tree

```java
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
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> res = new LinkedList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            double sum = 0.0;
            for (int i = 0; i < levelNum; i++) {
                TreeNode item = queue.poll();
                sum += item.val;
                if (item.left != null) queue.offer(item.left);
                if (item.right != null) queue.offer(item.right);
            }
            res.add(sum / levelNum);
        }
        return res;
    }
}
```

## 606 Construct String from Binary Tree

```java
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
    public String tree2str(TreeNode t) {
        if (t == null) return "";
        String l = tree2str(t.left);
        String r = tree2str(t.right);
        if (l.equals("") && r.equals("")) {
            return t.val + "";
        } else if (r.equals("")) {
            return t.val + "(" + l + ")";
        } else {
            return t.val + "(" + l + ")" + "(" + r + ")";
        }
    }
}

// better solution
class Solution {
  public String tree2str(TreeNode t) {
    StringBuilder sb = new StringBuilder();
    helper(sb, t);
    return sb.toString();
  }

  public void helper(StringBuilder sb, TreeNode t) {
    if (t != null) {
      sb.append(t.val);
      if (t.left != null || t.right != null) {
        sb.append("(");
        helper(sb, t.left);
        sb.append(")");
        if (t.right != null) {
          sb.append("(");
          sb.append(sb.t.right);
          sb.append(")");
        }
      }
    }
  }
}
```

## 108 Convert Sorted Array to Binary Search Tree

```java
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
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length);
    }
    
    private TreeNode sortedArrayToBST(int[] nums, int l, int h) {
        if (h <= l) return null;
        else if (h == l + 1) return new TreeNode(nums[l]);
        else {
            int m = l + (h - l) / 2;
            TreeNode head = new TreeNode(nums[m]);
            head.left = sortedArrayToBST(nums, l, m);
            head.right = sortedArrayToBST(nums, m + 1, h);
            return head;
        }
    }
}
```

## 543 Diameter of Binary Tree

```java
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
    public int diameterOfBinaryTree(TreeNode root) {
        int[] res = new int[1];
        if (root != null) traverse(root, res);
        return res[0];
    }
    
    private int traverse(TreeNode root, int[] res) {
        int l = root.left == null ? 0 : traverse(root.left, res) + 1;
        int r = root.right == null ? 0 : traverse(root.right, res) + 1;
        res[0] = Math.max(res[0], l + r);
        return Math.max(l, r);
    }
}

// other solution
public class Solution {
    int max = 0;
    
    public int diameterOfBinaryTree(TreeNode root) {
        maxDepth(root);
        return max;
    }
    
    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        
        max = Math.max(max, left + right);
        
        return Math.max(left, right) + 1;
    }
}
```

## 572 Subtree of Another Tree

```java
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
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null) return false;
        return isSame(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    private boolean isSame(TreeNode s, TreeNode t) {
        if (s == null && t == null) return true;
        else if (s == null || t == null) return false;
        else return s.val == t.val && isSame(s.left, t.left) && isSame(s.right, t.right);
    }
}
```

## 100 Same Tree

```java
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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p == null || q == null) return false;
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
}
```

## 437 Path Sum II

```java
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
    public int pathSum(TreeNode root, int sum) {
        if (root == null) return 0;
        int left = pathSum(root.left, sum);
        int right = pathSum(root.right, sum);
        int res = pathSumHelper(root, sum);
        return res + right + left;
    }
    
    private int pathSumHelper(TreeNode root, int sum) {
        if (root == null) {
            return 0;
        }
        int res = 0;
        if (sum == root.val) res++;
        return res + pathSumHelper(root.left, sum - root.val) + pathSumHelper(root.right, sum - root.val);
    }
}

// better solution
// preSum
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
    public int pathSum(TreeNode root, int sum) {
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);
        return helper(root, 0, sum, preSum);
    }
    
    public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {
        if (root == null) {
            return 0;
        }
        
        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
        
        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);
        return res;
    }
}
```

## 617 Merge Two Binary Trees

```java
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
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) return null;
        if (t1 == null) return t2;
        if (t2 == null) return t1;
        t1.val += t2.val;
        t1.left = mergeTrees(t1.left, t2.left);
        t1.right = mergeTrees(t1.right, t2.right);
        return t1;
    }
}
```

## 563 Binary Tree Tilt

```java
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
    int tilt = 0;
    public int findTilt(TreeNode root) {
        traverse(root);
        return tilt;
    }
    
    private int traverse(TreeNode root) {
        if (root == null) return 0;
        int left = traverse(root.left);
        int right = traverse(root.right);
        tilt += Math.abs(left - right);
        return root.val + left + right;
    }
}
```

## 235 Lowest Common Ancestor of Binary Search Tree

```java
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (p.val < root.val && q.val < root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
    }
}
```

## 101 Symmetric Tree

```java
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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }
    
    private boolean isSymmetric(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        else if (p == null || q == null) return false;
        else return p.val == q.val && isSymmetric(p.left, q.right) && isSymmetric(p.right, q.left);
    }
}
```

## 104 Maximum Depth of Binary Tree

```java
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
    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
```

## 110 Balanced Binary Tree

```java
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
    public boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        int lh = maxDepth(root.left);
        int rh = maxDepth(root.right);
        return Math.abs(lh-rh)<=1 && isBalanced(root.left) && isBalanced(root.right);
    }
    
    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}

// better solution
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
    public boolean isBalanced(TreeNode root) {
        return dfs(root) != -1;
    }
    
    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int left = dfs(root.left);
        if (left == -1) return -1;
        int right = dfs(root.right);
        if (right == -1) return -1;
        
        if (Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }
}
```

## 12 Path Sum

```java
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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) {
            return false;
        }
        return (root.left == null && root.right == null && root.val == sum) || hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
```

## 226 Invert Binary Tree

```java
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
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.right);
        TreeNode right = invertTree(root.left);
        root.left = left;
        root.right = right;
        return root;
    }
}
```

## 257 Binary Tree Paths

```java
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new LinkedList<>();
        traverse(root, res, new Stack<>());
        return res;
    }
    
    private void traverse(TreeNode root, List<String> res, Stack<TreeNode> stack) {
        if (root == null) return;
        stack.push(root);
        if (root.left == null && root.right == null) {
            // generate string
            int flag = stack.size();
            int ind = 0;
            StringBuilder sb = new StringBuilder();
            for (TreeNode i : stack) {
                ind++;
                sb.append(i.val);
                if (ind != flag) {
                    sb.append("->");
                }
            }
            res.add(sb.toString());
        }
        traverse(root.left, res, stack);
        traverse(root.right, res, stack);
        stack.pop();
    }
}

// another solution
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
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> answer = new ArrayList<>();
        if (root != null) searchBST(root, "", answer);
        return answer;
    }
    
    private void searchBST(TreeNode root, String path, List<String> answer) {
        if (root.left == null && root.right == null) answer.add(path + root.val);
        if (root.left != null) searchBST(root.left, path + root.val + "->", answer);
        if (root.right != null) searchBST(root.right, path + root.val + "->", answer);
    }
}
```

## Find Mode in Binary Search Tree

```java
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
    Integer prev = null;
    int count = 1;
    int max = 0;
    public int[] findMode(TreeNode root) {
        if (root == null) return new int[0];
        List<Integer> container = new ArrayList<>();
        traverse(root, container);
        int[] res = new int[container.size()];
        for (int i = 0; i < container.size(); i++) {
            res[i] = container.get(i);
        }
        return res;
    }
    
    private void traverse(TreeNode root, List<Integer> container) {
        if (root == null) return;
        traverse(root.left, container);
        if (prev != null) {
            if (prev == root.val)
                count++;
            else count = 1;
        }
        if (count > max) {
            max = count;
            container.clear();
            container.add(root.val);
        } else if (count == max) {
            container.add(root.val);
        }
        prev = root.val;
        traverse(root.right, container);
    }
}
```

## 538 Convert BST to Greater Tree

```java
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
    int sum = 0;
    public TreeNode convertBST(TreeNode root) {
        if (root == null) return null;
        traverse(root);
        return root;
    }
    
    private void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.right);
        int tmp = sum;
        sum += root.val;
        root.val = tmp + root.val;
        traverse(root.left);
    }
}
```

## 653 Two Sum IV - Input is a BST

```java
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
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return findTarget(root, k, set);
    }
    
    private boolean findTarget(TreeNode root, int k, Set<Integer> set) {
        if (root == null) return false;
        if (set.contains(k - root.val)) return true;
        set.add(root.val);
        return findTarget(root.left, k, set) || findTarget(root.right, k, set);
    }
}
```

## 671

```java
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
    Integer m1 = null;
    Integer m2 = null;
    public int findSecondMinimumValue(TreeNode root) {
        traverse(root);
        if (m2 == null) return -1;
        else return m2;
    }
    
    private void traverse(TreeNode root) {
        if (root == null) return;
        traverse(root.left);
        if (m1 == null) {
            m1 = root.val;
        } else if (root.val < m1) {
            m2 = m1;
            m1 = root.val;
        } else if (root.val != m1 && (m2 == null || root.val < m2)) {
            m2 = root.val;
        }
        traverse(root.right);
    }
}

// other solution
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
    public int findSecondMinimumValue(TreeNode root) {
        if (root == null) return -1;
        if (root.left == null && root.right == null) return -1;
        
        int left = root.left.val;
        int right = root.right.val;
        
        if (root.left.val == root.val) {
            left = findSecondMinimumValue(root.left);
        }
        if (root.right.val == root.val) {
            right = findSecondMinimumValue(root.right);
        }
        
        if (left != -1 && right != -1) {
            return Math.min(left, right);
        } else if (left != -1) {
            return left;
        } else {
            return right;
        }
    }
}
```

## 654 Maximum Binary Tree

```java
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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        return constructMaximumBinaryTree(nums, 0, nums.length);
    }
    
    private TreeNode constructMaximumBinaryTree(int[] nums, int l, int h) {
        if (h <= l) return null;
        else if (h == l + 1) return new TreeNode(nums[l]);
        else {
            int maxInd = l;
            for (int i = l + 1; i < h; i++) {
                if (nums[i] > nums[maxInd]) {
                    maxInd = i;
                }
            }
            TreeNode head = new TreeNode(nums[maxInd]);
            head.left = constructMaximumBinaryTree(nums, l, maxInd);
            head.right = constructMaximumBinaryTree(nums, maxInd + 1, h);
            return head;
        }
    }
}
```

## 513 Find Bottom Left Tree Value

```java
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
    int mostdepth = -1;
    int mostleft = 0;
    public int findBottomLeftValue(TreeNode root) {
        traverse(root, 0);
        return mostleft;
    }
    
    private void traverse(TreeNode root, int depth) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            if (depth > mostdepth) {
                mostleft = root.val;
                mostdepth = depth;
            }
        }
        traverse(root.left, depth + 1);
        traverse(root.right, depth + 1);
    }
}

// 当然这一题如果尝试层次遍历，其实从道理上来讲更加清晰。
```

## 515 Find Largest Value in Each Tree Row

```java
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
    int mostdepth = -1;
    int mostleft = 0;
    public int findBottomLeftValue(TreeNode root) {
        traverse(root, 0);
        return mostleft;
    }
    
    private void traverse(TreeNode root, int depth) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            if (depth > mostdepth) {
                mostleft = root.val;
                mostdepth = depth;
            }
        }
        traverse(root.left, depth + 1);
        traverse(root.right, depth + 1);
    }
}
```

## 508 Most Frequent Subtree Sum

```java
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
    int maxCount = 0;
    int maxNum = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        traverse(root, map);
        int[] res = new int[maxNum];
        int ind = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() == maxCount) {
                res[ind++] = entry.getKey();
            }
        }
        return res;
    }
    
    private int traverse(TreeNode root, Map<Integer, Integer> map) {
        if (root == null) return 0;
        int left = traverse(root.left, map);
        int right = traverse(root.right, map);
        int res = left + right + root.val;
        map.put(res, map.getOrDefault(res, 0) + 1);
        if (map.get(res) > maxCount) {
            maxCount = map.get(res);
            maxNum = 1;
        } else if (map.get(res) == maxCount) {
            maxNum++;
        }
        return res;
    }
}

// 可行的解决方案是直接在traverse里就把数取出来
```

## 655 Print Binary Tree

```java
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
    public List<List<String>> printTree(TreeNode root) {
        List<List<String>> res = new ArrayList<>();
        if (root == null) return res;
        int width = getWidth(root);
        int height = getHeight(root);
        for (int i = 0; i < height; i++) {
            List<String> sub = new ArrayList<>();
            for (int j = 0; j < width; j++) {
                sub.add("");
            }
            res.add(sub);
        }
        traverse(root, 0, 0, width, res);
        return res;
    }
    
    private void traverse(TreeNode root, int depth, int l, int h, List<List<String>> res) {
        if (root == null) return;
        int ind = (l + h) / 2;
        res.get(depth).set(ind, "" + root.val);
        traverse(root.left, depth + 1, l, ind, res);
        traverse(root.right, depth + 1, ind + 1, h, res);
    }
    
    private int getWidth(TreeNode root) {
        if (root == null) return 0;
        else if (root.left == null && root.right == null) return 1;
        else {
            return Math.max(getWidth(root.left), getWidth(root.right)) * 2 + 1;
        }
    }
    
    private int getHeight(TreeNode root) {
        if (root == null) return 0;
        return Math.max(getHeight(root.left), getHeight(root.right)) + 1;
    }
}
```

## 94 Binary Tree Inorder Traversal

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
 // 中序遍历，先左子树，再根，后右子树
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.pop();
                res.add(p.val);
                p = p.right;
            }
        }
        return res;
    }
}
```

## 623 Add One Row to Tree

```java
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
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        return addOneRow(root, 0, v, d);
    }
    
    private TreeNode addOneRow(TreeNode root, int depth, int v, int d) {
        if (root == null) return null;
        if (d == 1) {
            TreeNode newHead = new TreeNode(v);
            newHead.left = root;
            return newHead;
        } 
        if (depth == d - 2) {
            TreeNode nl = new TreeNode(v);
            TreeNode nr = new TreeNode(v);
            nl.left = root.left;
            nr.right = root.right;
            root.left = nl;
            root.right = nr;
            return root;
        } else {
            root.left = addOneRow(root.left, depth + 1, v, d);
            root.right = addOneRow(root.right, depth + 1, v, d);
            return root;
        }
    }
}
```

## 144 Binary Tree Preorder Traversal

```java
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        TreeNode p = root;
        Stack<TreeNode> stack = new Stack<>();
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                res.add(p.val);
                p = p.left;
            } else {
                p = stack.pop();
                p = p.right;
            }
        }
        return res;
    }
}
```

## 230 Kth Smallest Element in a BST

```java
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
    int kitem = 0;
    int count = 0;
    Integer pre = null;
    public int kthSmallest(TreeNode root, int k) {
        traverse(root, k);
        return kitem;
    }
    
    private void traverse(TreeNode root, int k) {
        if (root == null) return;
        traverse(root.left, k);
        if (pre != null) {
            if (pre != root.val) count++;
        } else {
            count++;
        }
        pre = root.val;
        if (count == k) kitem = pre;
        traverse(root.right, k);
    }
}
```

## 337 House Robber III

```java
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
    public int rob(TreeNode root) {
        int[] res = traverse(root);
        return Math.max(res[0], res[1]);
    }
    
    private int[] traverse(TreeNode root) {
        if (root == null) return new int[]{0, 0};
        int[] left = traverse(root.left);
        int[] right = traverse(root.right);
        int[] res = new int[2];
        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = left[0] + right[0] + root.val;
        return res;
    }
}
// 0 包括该节点 1 不包括该节点
```

## 449 Serialize and Deserialize BST

```java
// 需要注意BST的性质，这一题再一次做的时候确实没注意到这一点。
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return sb.toString();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            for (int i = 0; i < levelNum; i++) {
                TreeNode item = queue.poll();
                if (item.left != null) {
                    queue.offer(item.left);
                }
                if (item.right != null) {
                    queue.offer(item.right);
                }
                sb.append(item.val).append(",");
            }
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.equals("")) return null;
        String[] datas = data.split(",");
        TreeNode head = null;
        for (int i = 0; i < datas.length; i++) {
            head = generate(head, Integer.parseInt(datas[i]));
        }
        return head;
    }
    
    private TreeNode generate(TreeNode head, int val) {
        if (head == null) return new TreeNode(val);
        else {
            TreeNode s = head;
            TreeNode pre = null;
            while (s != null) {
                pre = s;
                if (val < s.val) {
                    s = s.left;
                } else {
                    s = s.right;
                }
            }
            if (val <= pre.val) {
                pre.left = new TreeNode(val);
            } else {
                pre.right = new TreeNode(val);
            }
            return head;
        }
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```

## 173 Binary Search Tree Iterator

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

public class BSTIterator {
    private TreeNode p;
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        p = root;
        stack = new Stack<>();
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return p != null || !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        while (p != null) {
            stack.push(p);
            p = p.left;
        }
        p = stack.pop();
        TreeNode res = p;
        p = p.right;
        return res.val;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
```

## 199 Binary Tree Right Side View

```java
// 当然层次遍历很清晰的可以解决这个问题
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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        traverse(root, 0, res);
        return res;
    }
    
    private void traverse(TreeNode root, int depth, List<Integer> res) {
        if (root == null) return;
        if (depth == res.size()) {
            res.add(root.val);
        } else {
            res.set(depth, root.val);
        }
        traverse(root.left, depth + 1, res);
        traverse(root.right, depth + 1, res);
    }
}
```

## 662 Maximum Width of Binary Tree

```java
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
    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(root, 1));
        int res = 1;
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            int start = 0, end = 0;
            for (int i = 0; i < levelNum; i++) {
                Node item = queue.poll();
                if (i == 0) {
                    start = item.no;
                }
                if (i == levelNum-1) {
                    end = item.no;
                }
                if (item.item.left != null) {
                    queue.offer(new Node(item.item.left, item.no * 2));
                }
                if (item.item.right != null) {
                    queue.offer(new Node(item.item.right, item.no * 2 + 1));
                }
            }
            res = Math.max(res, end - start + 1);
        }
        return res;
    }
}

class Node {
    TreeNode item;
    int no;
    
    public Node(TreeNode item, int no) {
        this.item = item;
        this.no = no;
    }
}
```

## 450 Delete Node in a BST

```java
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
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            if (root.left == null) return root.right;
            else if (root.right == null) return root.left;

            TreeNode minNode = findMin(root.right);
            root.val = minNode.val;
            root.right = deleteNode(root.right, root.val);
        }
        return root;
    }

    private TreeNode findMin(TreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }
}
```

## 129 Sum Root to Leaf Numbers

```java
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
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        traverse(root, 0);
        return sum;
    }
    
    private void traverse(TreeNode root, int pre) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            sum += pre * 10 + root.val;
        }
        traverse(root.left, pre * 10 + root.val);
        traverse(root.right, pre * 10 + root.val);
    }
}
```

## 116 Polulating the Next Pointer in Each Node

```java
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        Queue<TreeLinkNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            TreeLinkNode pre = null;
            for (int i = 0; i < levelNum; i++) {
                TreeLinkNode item = queue.poll();
                if (pre != null) {
                    pre.next = item;
                }
                pre = item;
                if (item.left != null) queue.offer(item.left);
                if (item.right != null) queue.offer(item.right);
            }
        }
    }
}
```

## 114 Flatten Binary Tree to Linked List

```java
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
    TreeNode pre = null;
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }
}
```

## 113 Binary Tree Zigzag Level Order Traversal

```java
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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        traverse(root, 0, res);
        return res;
    }
    
    private void traverse(TreeNode root, int depth, List<List<Integer>> res) {
        if (root == null) return;
        if (depth == res.size()) {
            res.add(new LinkedList<>());
        }
        if (depth % 2 == 0) {
            res.get(depth).add(0, root.val);
        } else {
            res.get(depth).add(root.val);
        }
        traverse(root.right, depth + 1, res);
        traverse(root.left, depth + 1, res);
    }
}
```

## 684 Redundant Connection

```java
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int max = edges[0][0];
        for (int i = 0; i < n; i++) {
            max = Math.max(max, edges[i][0]);
            max = Math.max(max, edges[i][1]);
        }
        UF uf = new UF(max);
        int m1 = 0, m2 = 0;
        for (int i = 0; i < n; i++) {
            if (uf.connected(edges[i][0]-1, edges[i][1]-1)) {
                m1 = edges[i][0]; m2 = edges[i][1];
            }
            uf.union(edges[i][0]-1, edges[i][1]-1);
        }
        return new int[] {m1, m2};
    }
}

class UF {
    private int[] id;
    private int count;
    private int[] sz;
    
    public UF(int N) {
        this.count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            sz[i] = 1;
        }
    }
    
    public int count() { return count; }
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
    private int find(int p) {
        while (p != id[p]) p = id[p];
        return p;
    }
    public void union(int p, int q) {
        int i = find(p);
        int j = find(q);
        if (i == j) return;
        
        count--;
        if (sz[i] < sz[j]) {
            id[i] = j;
            sz[j] += sz[i];
        } else {
            id[j] = i;
            sz[i] += sz[j];
        }
    }
}
```

## 652 Find Duplicate Subtres

```java
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
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        List<TreeNode> res = new LinkedList<>();
        if (root == null) return res;
        Map<String, Integer> map = new HashMap<>();
        traverse(root, map, res);
        return res;
    }
    
    private String traverse(TreeNode root, Map<String, Integer> map, List<TreeNode> res) {
        if (root == null) return "#";
        String left = traverse(root.left, map, res);
        String right = traverse(root.right, map, res);
        String middle = left + right + root.val;
        if (map.containsKey(middle)) {
            if (map.get(middle) == 1) res.add(root);
        }
        map.put(middle, map.getOrDefault(middle, 0) + 1);
        return middle;
    }
}
```

## 113 Path Sum II

```java
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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        traverse(root, new LinkedList<>(), res, sum);
        return res;
    }
    
    private void traverse(TreeNode root, LinkedList<TreeNode> stack, List<List<Integer>> res, int sum) {
        if (root == null) {
            return;
        }
        stack.push(root);
        if (root.left == null && root.right == null && sum == root.val) {
            // 生成
            List<Integer> sub = new LinkedList<>();
            for (TreeNode t : stack) {
                sub.add(0, t.val);
            }
            res.add(sub);
        }
        traverse(root.left, stack, res, sum - root.val);
        traverse(root.right, stack, res, sum - root.val);
        stack.pop();
    }
}
```

## 117 Populating Next Right Pointers in Each Node II

```java
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
public class Solution {
    public void connect(TreeLinkNode root) {
        while (root != null) {
            TreeLinkNode tmp = new TreeLinkNode(0);
            TreeLinkNode cur = tmp;
            while (root != null) {
                if (root.left != null) { cur.next = root.left; cur = cur.next; }
                if (root.right != null) { cur.next = root.right; cur = cur.next; }
                root = root.next;
            }
            root = tmp.next;
        }
    }
}
```

## 105 Construct Binary Tree from Preorder and Inorder Traversal

```java
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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTree(preorder, 0, preorder.length, inorder, 0, inorder.length);
    }
    
    private TreeNode buildTree(int[] preorder, int ps, int pe, int[] inorder, int is, int ie) {
        if (pe <= ps) return null;
        else if (pe == ps + 1) return new TreeNode(preorder[ps]);
        else {
            TreeNode head = new TreeNode(preorder[ps]);
            int i = is;
            for (; i < ie; i++) {
                if (preorder[ps] == inorder[i]) break;
            }
            head.left = buildTree(preorder, ps + 1, ps + 1 + i - is, inorder, is, i);
            head.right = buildTree(preorder, ps + 1 + i - is, pe, inorder, i + 1, ie);
            return head;
        }
    }
}
```

## 96 Unique Binary Search Trees

```java
class Solution {
    public int numTrees(int n) {
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dp[i] += dp[j-1] * dp[i-j];
            }
        }
        return dp[n];
    }
}
```

## 98 Validate Binary Search Tree

```java
// 当然这一题也可以直接先序遍历，查看是否是有序的。不过这种方法更加巧妙
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
    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    
    private boolean isValidBST(TreeNode root, long min, long max) {
        if (root == null) return true;
        else if (root.val <= min || root.val >= max) return false;
        else return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
}
```

## 95 Unique Binary Search Tree II

```java
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
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode> res = new LinkedList<>();
        if (n == 0) return res;
        int[] array = new int[n];
        for (int i = 0; i < n; i++) array[i] = i + 1;
        return generateTree(array, 0, array.length);
    }
    
    private List<TreeNode> generateTree(int[] array, int lo, int hi) {
        if (hi <= lo) {
            List<TreeNode> sub = new ArrayList<>();
            sub.add(null);
            return sub;
        }
        else if (hi == lo + 1) {
            List<TreeNode> sub = new ArrayList<>();
            sub.add(new TreeNode(array[lo]));
            return sub;
        }
        else {
            List<TreeNode> sub = new ArrayList<>();
            for (int i = lo; i < hi; i++) {
                List<TreeNode> left = generateTree(array, lo, i);
                List<TreeNode> right = generateTree(array, i+1, hi);
                for (TreeNode l : left) {
                    for (TreeNode r : right) {
                        TreeNode h = new TreeNode(array[i]);
                        h.left = l;
                        h.right = r;
                        sub.add(h);
                    }
                }
            }
            return sub;
        }
    }
}

// better solution
// 可以用DP来做，未尝不是一种办法
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
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[] result = new List[n+1];
        result[0] = new ArrayList<>();
        if (n == 0) return result[0];
        
        result[0].add(null);
        for (int len = 1; len <= n; len++) {
            result[len] = new ArrayList<>();
            for (int j = 0; j < len; j++) {
                for (TreeNode nodeL : result[j]) {
                    for (TreeNode nodeR : result[len-j-1]) {
                        TreeNode node = new TreeNode(j+1);
                        node.left = nodeL;
                        node.right = clone(nodeR, j+1);
                        result[len].add(node);
                    }
                }
            }
        }
        return result[n];
    }
    
    private TreeNode clone(TreeNode n, int offset) {
        if (n == null) return null;
        TreeNode node = new TreeNode(n.val + offset);
        node.left = clone(n.left, offset);
        node.right = clone(n.right, offset);
        return node;
    }
}
```

## 236 Lowest Common Ancestor of a Binary Tree

```java
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return null;
        else if (root == p) return root;
        else if (root == q) return root;
        else {
            TreeNode left = lowestCommonAncestor(root.left, p, q);
            TreeNode right = lowestCommonAncestor(root.right, p, q);
            if (root == p || root == q) {
                if (left != null || right != null) return root;
            }
            if (left != null && right != null) return root;
            else if (left != null) return left;
            else return right;
        }
    }
}

// better solution for look
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    if (root == null || root == p || root == q) return root;
    TreeNode left = lowestCommonAncestor(root.left, p, q);
    TreeNode right = lowestCommonAncestor(root.right, p, q);
    return left == null ? right : right == null ? left : root; 
}
```

## Count Complete TreeNodes

```java
// 需要注意的点是(1 << lH) +  countNodes(root.right)
// 而不是 1 << lH + countNodes(root.right)
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
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int lH = height(root.left);
        int rH = height(root.right);
        if (lH == rH) {
            return (1 << lH) + countNodes(root.right);
        } else {
            return (1 << rH) + countNodes(root.left);
        }
    }
    
    private int height(TreeNode root) {
        int res = 0;
        while (root != null) {
            root = root.left;
            res++;
        }
        return res;
    }
}
```

## 145 Binary Tree Postorder Traversal

```java
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        TreeNode pre = null;
        while (p != null || !stack.isEmpty()) {
            if (p != null) {
                stack.push(p);
                p = p.left;
            } else {
                p = stack.peek();
                if (p.right == null || p.right == pre) {
                    res.add(p.val);
                    pre = p;
                    stack.pop();
                    p = null;
                } else {
                    p = p.right;
                }
            }
        }
        return res;
    }
}
```

## 297 Serialize and Deserialize Binary Tree

```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
public class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return sb.toString();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        sb.append(root.val).append(" ");
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            for (int i = 0; i < levelNum; i++) {
                TreeNode item = queue.poll();
                if (item.left == null) {
                    sb.append("#").append(" ");
                } else {
                    sb.append(item.left.val).append(" ");
                    queue.offer(item.left);
                }
                if (item.right == null) {
                    sb.append("#").append(" ");
                } else {
                    sb.append(item.right.val).append(" ");
                    queue.offer(item.right);
                }
            }
        }
        return sb.toString();
    }
    // 1 3 # # 4

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) return null;
        String[] nodes = data.split(" ");
        TreeNode head = new TreeNode(Integer.parseInt(nodes[0]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(head);
        int ind = 1;
        while (!queue.isEmpty()) {
            int levelNum = queue.size();
            for (int i = 0; i < levelNum; i++) {
                TreeNode item = queue.poll();
                if (nodes[ind].equals("#")) {
                    item.left = null;
                } else {
                    item.left = new TreeNode(Integer.parseInt(nodes[ind]));
                    queue.offer(item.left);
                }
                ind++;
                if (ind < nodes.length && nodes[ind].equals("#")) {
                    item.right = null;
                } else {
                    item.right = new TreeNode(Integer.parseInt(nodes[ind]));
                    queue.offer(item.right);
                }
                ind++;
            }
        }
        return head;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));
```

## 99 Recover Binary Search Tree

```java
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
    TreeNode pre = null;
    TreeNode first = null;
    TreeNode second = null;
    public void recoverTree(TreeNode root) {
        traverse(root);
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;
    }
    
    private void traverse(TreeNode root) {
        if (root == null) {
            return;
        }
        traverse(root.left);
        if (pre != null) {
            if (first == null) {
                if (pre.val > root.val) {
                    first = pre;
                    second = root;
                }
            } else {
                if (pre.val > root.val) {
                    second = root;
                }
            }
        }
        pre = root;
        traverse(root.right);
    }
}
```

## 124 Binary Tree Maximum Path Sum

```java
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
    Integer maxsofar = null;
    public int maxPathSum(TreeNode root) {
        traverse(root);
        return maxsofar;
    }
    
    private int traverse(TreeNode root) {
        if (root == null) return 0;
        int left = traverse(root.left);
        int right = traverse(root.right);
        if (maxsofar == null) {
            maxsofar = root.val;
            return root.val;
        } else {
            int res = Math.max(Math.max(left, right) + root.val, root.val);
            int maxendinghere = Math.max(root.val, Math.max(left + right, Math.max(left, right)) + root.val);
            maxsofar = Math.max(maxsofar, maxendinghere);
            return res;
        }
    }
}
```

## 685 Redundant Connection II

```java
class Solution {
    public int[] findRedundantDirectedConnection(int[][] edges) {
        int[] can1 = {-1, -1};
        int[] can2 = {-1, -1};
        int[] parent = new int[edges.length+1];
        for (int i = 0; i < edges.length; i++) {
            if (parent[edges[i][1]] == 0) {
                parent[edges[i][1]] = edges[i][0];
            } else {
                can2 = new int[]{edges[i][0], edges[i][1]};
                can1 = new int[]{parent[edges[i][1]], edges[i][1]};
                edges[i][1] = 0;
            }
        }
        for (int i = 0; i < edges.length; i++) {
            parent[i] = i;
        }
        
        for (int i = 0; i < edges.length; i++) {
            if (edges[i][1] == 0) {
                continue;
            }
            int child = edges[i][1], father = edges[i][0];
            if (root(parent, father) == child) {
                if (can1[0] == -1) {
                    return edges[i];
                } else {
                    return can1;
                }
            }
            parent[child] = father;
        }
        return can2;
    }
    
    int root(int[] parent, int i) {
        while (i != parent[i]) {
            parent[i] = parent[parent[i]];
            i = parent[i];
        }
        return i;
    }
}
```