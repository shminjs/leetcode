# Leetcode Tree专题总结

在leetcode的easy题刷完后，准备做一下tree的专题。考虑到easy题里有关于tree的题目其实还是有不少套路，因此在做tree的中等题和难题之前，有必要把easy题做一下总结。

## easy题分析（参考最优解）

### 617 Merge Two Binary Trees

该题是把两个二叉树合并，可以按照二叉树的中序遍历来完成树的构建，这里需要注意的是，两颗二叉树需要同时进入遍历，然后合并，基本思路如下：

```java
public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
  if (t1 == null) return t2;
  if (t2 == null) return t1;
  t1.val += t2.val;
  t1.left = mergeTrees(t1.left, t2.left);
  t1.right = mergeTrees(t1.right, t2.right);
  return t1;
}
```

这里也是默认的一种数的构建方式，先构建根结点，再构建左子树，后构建右子树。在构建的过程中，自顶向下。

### 606 Construct String from Binary Tree

这一题是以中序遍历的方式遍历二叉树并把按照某种方式输出，使得能从这个字符串重建到二叉树。该题已经明确了使用中序遍历，可以直接使用下列方法：

```java
public String tree2str(TreeNode t) {
  if (t == null) return "";
  if (t.left == null && t.right == null)
    return t.val + "";
  if (t.right == null) 
    return t.val + "(" + tree2str(t.left) + ")";
  return t.val + "(" + tree2str(t.left) + ")(" + tree2str(t.right) + ")";
}
```

主要是注意区别corner case。

### 104 Maximum Depth of Binary Tree

求一颗二叉树的深度：

```java
public int maxDepth(TreeNode root) {
  if (root == null) return 0;
  return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
}
```

### 226 Invert Binary Tree

将一个二叉树左右子树对调：

```java
public TreeNode invertTree(TreeNode root) {
  if (root == null) return null;
  TreeNode right = invertTree(root.right);
  TreeNode left = invertTree(root.left);
  root.left = right;
  root.right = left;
  return root;
}
```

### 563 Binary Tree Tilt

计算二叉树的Tilt，考虑到题目中计算Tilt是所有结点的Tilt和，而上一层又需要上一层的其它信息，因此需要另外的值来保存需要返回的Tilt和。由于需要得到左子树和右子树的相关信息，需要后序遍历来完成。

```java
int tilt = 0;

public int findTilt(TreeNode root) {
  traverse(root);
  return tilt;
}

public int traverse(TreeNode root) {
  if (root == null) return 0;
  int left = traverse(root.left);
  int right = traverse(root.right);
  tilt += Math.abs(left - right);
  return left + right + root.val;
}
```

### 404 Sum of Left Leaves

计算左叶子结点：

```java
public int sumOfLeftLeaves(TreeNode root) {
  if (root == null) return 0;
  int ans = 0;
  if (root.left != null) {
    if (root.left.left == null && root.left.right == null) ans += root.left.val;
    else ans += sumOfLeftLeaves(root.left);
  }
  ans += sumOfLeftLeaves(root.right);
  
  return ans;
}
```

### 100 Same Tree

判断两棵树是否相等：

```java
public boolean isSameTree(TreeNode p, TreeNode q) {
  if (p == null && q == null) return true;
  if (p == null || q == null) return false;
  return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
}
```
### 543 Diameter of Binary Tree

计算一个二叉树中的中最长路径，虽然题目中虽然说了可以不经过根结点，但是其实一定经过某个根结点。所以其实只要计算某个节点的左子树的高度和右子树的高度，如果二者的和大于max，则把它的值赋给max：

```java
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
```

### 108 Convert Sorted Array to Binary Search Tree

把一个已经排序的数组转成一个二分搜索树，主要是利用二分搜索树的特点来生成，比如先找到根结点，再分别生成左子树和右子树，根节点在数组的中间位置：

```java
public TreeNode sortedArrayToBST(int[] num) {
  if (num.length == 0) return null;
  TreeNode head = helper(num, 0, num.length - 1);
  return head;
}

private TreeNode helper(int[] num, int low, int high) {
  if (low > high) return null;
  int mid = low + (high - low)/2;
  TreeNode node = new TreeNode(num[mid]);
  node.left = helper(num, low, mid-1);
  node.right = helper(num, mid+1, high);
  return node;
}
```

###  572 Subtree of Another Tree

这一题是判断t是s的字树，这里涉及到两层遍历，第一层遍历s，以每次遍历时s的结点为起点，再判断以此结点为顶点的子树是否和t相等，方法如下：我做的方法和答案类似，但是答案代码更加优美：

```java
public boolean isSubTree(TreeNode s, TreeNode t) {
  return traverse(s, t);
}

public boolean equals(TreeNode x, TreeNode y) {
  if (x == null && y == null) return true;
  if (x == null || y == null) return false;
  return x.val == y.val && equals(x.left, y.left) && equals(x.right, y.right);
}

public boolean traverse(TreeNode s, TreeNode t) {
  return s != null && (equals(s,t) || traverse(s.left, t) || traverse(s.right, t));
}
```

要充分利用`&&`和`||`的短路特性来避免找到结果继续遍历，做无用功。

### 107 Binary Tree Level Order Traversal II

二叉树的层次遍历：

```java
DFS Solution:
public List<List<Integer>> levelOrderBottom(TreeNode root) {
  Queue<TreeNode> queue = new LinkedList<TreeNode>();
  List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
  
  if (root == null) return wapList;
  
  queue.offer(root);
  while (!queue.isEmtpy()) {
    int levelNum = queue.size();
    List<Integer> subList = new LinkedList<Integer>();
    for (int i = 0; i < levelNum; i++) {
      if (queue.peek().left != null) queue.offer(queu.peek().left);
      if (queue.peek().right != null) queue.offer(queue.peek().right);
      subList.add(queue.poll().val);
    }
    warpList.add(0, subList);
  }
  return wrapList;
}

BFS Solution:
public List<List<integer>> levelOrderBottom(TreeNode root) {
  List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
  levelMaker(wrapList, root, 0);
  return wrapList;
}

private void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
  if (root == null) return;
  if (level >= list.size()) {
    list.add(0, new LinkedList<Integer>());
  }
  levelMaker(list, root.left, level + 1);
  levelMaker(list, root.right, level + 1);
  list.get(list.size() - level - 1).add(root.val);
}
```

### 437 Path Sum III

找到二叉树中等于某个值的结点序列，结点只能从上往下：

```java
public int pathSum(TreeNode root, int sum) {
  if (root == null) return 0;
  return pathSumFrom(root, sum) + pathSumFrom(root.left, sum) + pathSumFrom(root.right, sum);
}

private int pathSumFrom(TreeNode node, int sum) {
  if (node == null) return 0;
  return (node.val == sum ? 1 : 0) + pathSumFrom(node.left, sum - node.val) + PathSumFrom(node.right, sum - node.val);
}
```

### 235 Lowest Common Ancestor of a Binary Search Tree

结合二叉搜索树的特点来做：

```java
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
  while ((root.val - p.val) * (root.val - q.val) > 0) {
    root = p.val < root.val ? root.left : root.right;
  }
  return root;
}
```

### 101 Symmetric tree

判断一棵树左右子树是否是镜像：

```java
public boolean isSymmetric(TreeNode root) {
  return isMirror(root, root);
}

public boolean isMirror(TreeNode t1, TreeNode t2) {
  if (t1 == null && t2 == null) return true;
  if (t1 == null || t2 == null) return false;
  return (t1.val == t2.val) && isMiroor(t1.right, t2.left) && isMirror(t1.left, t2.right);
}
```

### 501 Find Mode in Binary Search Tree

在二叉搜索树中寻找出现次数最多的数：

```java
Integer prev = null;
int count = 1;
int max = 0;
public int[] findMode(TreeNode root) {
  if (root == null) return new int[0];
  
  List<Integer> list = new ArrayList<>();
  traverse(root, list);
  
  int[] res = new int[list.size()];
  for (int i = 0; i < list.size(); i++) res[i] = list.get(i);
  return res;
}

private void traverse(TreeNode root, List<Integer> list) {
  if (root == null) return;
  traverse(root.left, list);
  if (prev != null) {
    if (root.val == prev) {
      count++;
    } else {
      count = 1;
    }
  }
  if (count > max) {
    max = count;
    list.clear();
    list.add(root.val);
  } else if (count == max) {
    list.add(root.val);
  }
  prev = root.val;
  traverse(root.right, list);
}
```

### 257 Binary Tree Paths

给出一个二叉树，输出所有的从根结点到叶子结点的路径。

```java
public List<String> binaryTreePaths(TreeNode root) {
  List<String> paths = new LinkedList<>();
  
  if (root == null) return paths;
  
  if(root.left == null && root.right == null) {
    paths.add(root.val + "");
    return paths;
  }
  
  for (String path : binaryTreePaths(root.left)) {
    paths.add(root.val + "->" + path);
  }
  
  for (String paths : binaryTreePaths(root.right)) {
    paths.add(root.val + "->" + path) ;
  }
  
  return paths;
}
```

### 110 Balanced Binary Tree

判断一个二叉树是否是平衡的：

```java
public int depth(TreeNode root) {
  if (root == null) return 0;
  return Math.max(depth(root.left), depth(root.right)) + 1;
}

public boolean isBalanced(TreeNode root) {
  if (root == null) return true;
  
  int left = depth(root.left);
  int right = depth(root.right);
  
  return Math.abs(left - right) <= 1 && isBalanced(root.left) && isBalanced(root.right);
}
```

### 112 Path Sum

计算从根结点到叶子结点的和是否等于某个值。

```java
public boolean hasPathSum(TreeNode root, int sum) {
  if (root == null) return false;
  if (root.left == null && root.right == null && sum - root.val == 0) return true;
  return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
}
```

### 111 Minimum Depth of Binary Tree

求解二叉树的最小深度。

```java
public int minDepth(TreeNode root) {
  if (root == null) return 0;
  int left = minDepth(root.left);
  int right = minDepth(root.right);
  return (left == 0 || right == 0) ? left + right + 1 : Math.min(left, right) + 1;
}
```

## Middle题

### 513 Find Bottom Left Tree Value

找出最后一行最左边的元素

```java
// 从右到左层次遍历
public int findLeftMostNode(TreeNode root) {
  Queue<TreeNode> queue = new LinkedList<>();
  queue.add(root);
  while (!queue.isEmpty()) {
    root = queue.poll();
    if (root.right != null) {
      queue.add(root.right);
    }
    if (root.left != null) {
      queue.add(root.left);
    }
  }
  return root.val;
}

// my own solution
public int findBottomLeftValue(TreeNode root) {
  Queue<TreeNode> queue = new LinkedList<>();
  
  Integer leftmost = root.val;
  queue.offer(root);
  while (!queue.isEmtpy()) {
    int levelNum = queue.size();
    boolean flag = false;
    for (int i = 0; i < levelNum; i++) {
     if (queue.peek().left != null)
       queue.offer(queue.peek().left);
     if (queue.peek().right != null)
       queue.offer(queue.peek().right);
     int val = queue.poll().val;
     if (!flag) {
       leftmost = val;
       flag = true;
     }
    }
  }
  return leftmost
}
```

### 515 Find Largest Value in Each Tree Row

找出每一行中的最大的数字

```java
// my own solution
public List<Integer> largestValues(TreeNode root) {
  Queue<TreeNode> queue = new LinkedList<>();
  List<Integer> result = new LinkedList<>();
  
  if (root == null) return result;
  queue.offer(root);
  while (!queue.isEmpty()) {
    int levelNum = queue.size();
    Integer max = null;
    for (int i = 0; i < levelNum; i++) {
      TreeNode item = queue.poll();
      if (item.left != null) queue.offer(item.left);
      if (item.right != null) queue.offer(item.right);
      if (max == null || max < item.val) max = item.val;
    }
    result.add(max);
  }
  return results;
}
```

### 538 Convert BST to Greater Tree

把一个BST转换称一个Greater Tree。

```java
/// my own solution
int prev = 0;

public TreeNode convertBST(TreeNode root) {
  traverse(root);
  return root;
}

private void traverse(TreeNode root) {
  if (root == null) return;
  traverse(root.right);
  int tmp = root.val;
  root.val += prev;
  prev += tmp;
  traverse(root.left);
}
```

### 508 Most Frequent Sbutree Sum

找出一棵树的子树中和出现最多的数字

```java
// my own solution
int count = 0;
public int[] findFrequentTreeSum(TreeNode root) {
  Map<Integer, Integer> map = new HashMap<>();
  List<Integer> result = new ArrayList<>();
  traverse(root, map, result);
  int[] res = new int[result.size()];
  for (int i = 0; i < res.length; i++) res[i] = result.get(i);
  return res;
}

private int traverse(TreeNode root, Map<Integer, Integer> map, List<Integer> result) {
  if (root == null) return 0;
  int left = traverse(root.left, map, result);
  int right = traverse(root.right, map, result);
  int val = left + right + root.val;
  if (mpa.containsKey(val)) {
    map.put(val, map.get(val) + 1);
  } else {
    map.put(va, 1);
  }
  if (map.get(val) > count) {
    result.clear();
    reresult.add(val);
    count = map.get(val);
  } else if (map.get(val) == count) {
    result.add(val);
  }
  return val;
}
```

### 623 Add One Row to Tree

给一个二叉树增加一行

```java
public TreeNode addOneRow(TreeNode root, int v, int d) {
  if (d == 0 || d == 1) {
    TreeNode newroot = new TreeNode(v);
    newroot.left = d == 1 ? root : null;
    newroot.right = d == 0 ? root : null;
    return newroot;
  }
  if (root != null && d >= 2) {
    root.left = add(root.left, v, d > 2 ? d - 1 : 1);
    root.right = add(root.right, v, d > 2 ? d - 1 : 1);
  }
  return root;
}

// my own solution
public TreeNode addOneRow(TreeNode root, int v, int d) {
  if (d == 1) {
    TreeNode t = new TreeNode(v);
    t.left = root;
    return t;
  }
  traverse(root, v, d, 1);
  return root;
}

private void traverse(TreeNode root, int v, int d, int depth) {
  if (root == null) return;
  if (depth == (d-1)) {
    TreeNode leftN = new TreeNode(v);
    leftN.left = root.left;
    root.left = leftN;
    TreeNode rightN = new TreeNode(v);
    rightN.right = root.right;
    root.right = rightN;
    return;
  }
  traverse(root.left, v, d, depth + 1);
  traverse(root.right, v, d, depth + 1);
}
```

### 94 Binary Tree Inorder Traversal

非递归中序遍历

```java
// my own solution
public List<Integer> inorderTraversal(TreeNode root) {
  List<Integer> result = new LinkedList<>();
  LinkedList<TreeNode> stack = new LinkedList<>();

  if (root == null) return result;

  TreeNode p = root;
  while (p != null || !stack.isEmpty()) {
    if (p != null) {
      stack.push(p);
      p = p.left;
    } else {
      p = stack.pop();
      result.add(p.val);
      p = p.right;
    }
  }
  return result;
}
```

### 144 Bianry Tree Preorder Traversal

先序遍历

```java
public List<Integer> preorderTraversal(TreeNode root) {
  List<Integer> result = new LinkedList<>();
  LinkedList<TreeNode> stack = new LinkedList<>();

  if (root == null) return result;
  
  TreeNode p = root;
  while (p != null || !stack.isEmpty()) {
    if (p != null) {
      result.add(p.val);
      stack.push(p);
      p = p.left;
    } else {
      p = stack.pop();
      p = p.right;
    }
  }
  return result;
}
```

### 230 Kth Smallest Element in BST

寻找BST中k最小的元素

```java
public int kthSmallest(TreeNode root, int k) {
  int count = countNodes(root.left);
  if (k <= count) {
    return kthSmallest(root.left, k);
  } else if (k > count + 1) {
    return kthSmallest(root.right, k-1-count);
  }

  return root.val;
}

private int countNodes(TreeNode n) {
  if (n == null) return 0;
  return 1 + countNodes(n.left) + countNodes(n.right);
}

// my own solution
int val;
public int kthSmallest(TreeNode root, int k) {
  Map<Integer, Integer> map = new HashMap<>();
  traverse(root, map, k);
  return val;
}

private void traverse(TreeNode root, Map<Integer, Integer> map, int k) {
  if (root == null) return;
  traverse(root.left, map, k);
  if (!map.containsKey(root.val)) {
    map.put(root.val, 1);
    if (map.size() == k) {
      val = root.val;
      return;
    }
  }
  traverse(root.right, map, k);
}
```

### 337 House Robber III

```java
public int rob(TreeNode root) {
  int[] res = robSub(root);
  return Math.max(res[0], res[1]);
}

private int[] robSub(TreeNode root) {
  if (root == null) return new int[2];

  int[] left = robSub(root.left);
  int[] right = robSub(root.right);
  
  int[] res = new int[2];

  res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
  res[1] = root.val + left[0] + right[0];

  return res;
}
```

### 449 Serialize and Deserialize BST

序列化和反序列化BST

```java
// my own solution
public class Codes {
  
  // Encodes a tree to a single string.
  public String searialize(TreeNode root) {
    StringBuilder sb = new StringBuilder();
    LinkedList<TreeNode> queue = new LinkedList<>();
    if (root == null) return sb.toString();
    queue.offer(root);
    while (!queue.isEmpty()) {
      int levelNum = queue.size();
      for (int i = 0; i < levelNum; i++) {
        TreeNode item = queue.poll();
        if (item.left != null) queue.offer(item.left);
        if (item.right != null) queue.offer(item.right);
        sb.append(item.val).append(' ');
      }
    }
    return sb.toString();
  }

  // Decodes your encoded data to tree.
  public TreeNode deserialize(String data) {
    if (data.length() == 0) return null;
    String[] datas = data.split(" ");
    TreeNode head = null;
    for (int i = 0; i < datas.length; i++) {
      head = generate(head, Integer.parseInt(datas[i]));
    }
    return head;
  }

  private TreeNode generate(TreeNode head, int val) {
    if (head == null) return new TreeNode(val);
    TreeNode s = head;
    TreeNode pre = null;
    while (s != null) {
      pre = s;
      if (val <= s.val) {
        s = s.val;
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
```

### 173 Binary Search Tree Iterator

BST Iterator

```java
// my own solution
public class BSTIterator {
  private TreeNode p = null;
  private LinkedList<TreeNode> stack = new LinkedList<>();

  public BSTIterator(TreeNode root) {
    p = root;
  }

  /**
  * @ return whether we have a next smallest number
  */
  public boolean hasNext() {
    return (p != null || !stack.isEmpty());
  }

  /**
  * @return the next smallest number
  */
  public int next() {
    while (p != null) {
      stack.push(p);
      p = p.left;
    }
    p = stack.pop();
    TreeNode result = p;
    p = p.right;
    return result.val;
  }
}
```

### 96 Unique Binary Search Trees

关键是递推公式

```java
public int numTrees(int n) {
  int[] dp = new int[n+1];
  dp[0] = dp[1] = 1;
  for (int i = 2; i <= n; i++) {
    for (int j = 1; j <= i; j++) {
      dp[i] += dp[j-1] * dp[i-j];
    }
  }
}
```

### 199 Binary Tree Right Side View

从右侧查看一个二叉树，找出你所看到的每一个结点。

```java
// my own solution
public List<Integer> rightSideView(TreeNode root) {
  Queue<TreeNode> queue = new LinkedList<>();
  List<Integer> result = new LinkedList<>();

  if (root == null) return result;
  queue.offer(root);
  while (!queue.isEmpty()) {
    int levelNum = queue.size();
    for (int i = 0; i < levelNum; i++) {
      TreeNode item = queue.poll();
      if (item.left != null) queue.offer(item.left);
      if (item.right != null) queue.offer(item.right);
      if (i == levelNum - 1) {
        result.add(item.val);
      }
    }
    return result;
  }
}

// another solution may inspire me
public List<Integer> rightSideView(TreeNode root) {
  List<Integer> result = new ArrayList<>();
  rightView(root, result, 0);
  return result;
}

private void rightView(TreeNode curr, List<Integer> result, int currDepth) {
  if (curr == null) return;
  if (currDepth == result.size()) result.add(curr.val);

  rightView(root.right, result, currDepth + 1);
  rightView(root.left, result, currDepth + 1);
}
```

### 102 Binary Tree Level Order Traversal

二叉树层次遍历

```java
// my own solution
public List<List<Integer>> levelOrder(TreeNode root) {
  List<List<Integer>> result = new LinkedList<>();
  Queue<TreeNode> queue = new LinkedList<>();

  if (root == null) return result;

  queue.offer(root);
  while (!queue.isEmtpy()) {
    int levelNum = queue.size();
    List<Integer> container = new LinkedList<>();
    for (int i = 0; i < levelNum; i++) {
      TreeNode item = queue.poll();
      if (item.left != null) queue.offer(item.left);
      if (item.right != null) queue.offer(item.right);
      container.add(item.val);
    }
    result.add(container);
  }
  return result;
}
```

### 116 Populating Next Right Pointers in Each Node

```java
public void connect(TreeLinkNode root) {
  if (root == null) return;
  TreeLinkNode pre = root;
  TreeLinkNode cur = null;
  while (pre.left != null) {
    cur = pre;
    while (cur) {
      cur.left.next = cur.right;
      if (cur.next != null) cur.right.next = cur.next.left;
      cur = cur.next;
    }
    pre = pre.left;
  }
}

// my own solution
public void connect(TreeLinkNode root) {
  Queue<TreeLinkNode> queue = new LinkedList<>();
  if (root == null) return;
  queue.offer(root);
  while (!queue.isEmtpy) {
    int levelNum = queue.size();
    for (int i = 0; i < levelNum; i++) {
      TreeNode item = queue.poll();
      if (item.left != null) queue.offer(item.left);
      if (item.right != null) queue.offer(item.right);
      if (i + 1 < levelNum) {
        item.next = queue.peek();
      }
    }
  }
}
```

### 129 Sum Root to Leaf Numbers

```java
public int sumNumbers(TreeNode root) {
  retur sum(root, 0);
}

public int sum(TreeNode n, int s) {
  if (n == null) return 0;
  if (n.right == null && n.right == null) return s * 10 + n.val;
  return sum(n.left, s * 10 + n.val) + sum(n.right, s * 10 + n.val);
}

// my own solution
int sum = 0;
public int sumNumbers(TreeNode root) {
  traversal(root, 0);
  return sum;
}

private traversal(TreeNode root, int num) {
  if (root == null) return;
  if (root.left == null && root.right == null) {
    num = num * 10 + root.val;
    sum += num;
    return;
  }
  traversal(root.left, num * 10 + root.val);
  traversal(root.right, num * 10 + root.val);
}
```

### 450 Delete Node in a BST

```java
public TreeNode deleteNode(TreeNode root, int key) {
  if (root == null) return null;
  if (key < root.val) root.left = deleteNode(root.left, key);
  else if (key > root.val) root.right = deleteNode(root.right, key);
  else {
    if (root.left == null) return root.right;
    else if (root.right == null) return root.left;

    TreeNode minNode = findMin(root.right);
    root.val = minNode.val;
    root.right = deleteNode(root.right, root.val);
  }
  return root;
}
```

### 114 Flattern Binary Tree to Linked List

```java
TreeNode pre = null;
public void flattern(TreeNode root) {
  if (root == null) return;
  flattern(root.right);
  flattern(root.left);
  root.right = prev;
  root.left = null;
  prev = root;
}
```

### 103 Bianry Tree Zigzag Level Order Traversal

```java
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
  List<List<Integer>> sol = new ArrayList<>();
  travel(root, sol, 0);
  return sol;
}

private void travel(TreeNode curr, List<List<Integer>> sol, int level) {
  if (curr == null) return;

  if (sol.size() <= level) {
    List<Integer> newLevel = new LinkedList<>();
    sol.add(newLevle);
  }

  List<Integer> collection = sol.get(level);
  if (level % 2 == 0) collection.add(curr.val);
  else collection.add(0, curr.val);

  travel(curr.left, sol, level + 1);
  travel(curr.right, sol, level + 1);
}

// my own solution
public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
  List<List<Integer>> result = new LinkedList<>();
  Queue<TreeNode> queue = new LinkedList<>();

  if (root == null) return result;

  queue.offer(root);
  boolean flag = true;
  while (!queue.isEmtpy()) {
    int levelNum = queue.size();
    List<Integer> container = new LinkedList<>();
    for (int i = 0; i < levelNum; i++) {
      TreeNode item = queue.poll();
      if (item.left != null) queue.offer(item.left);
      if (item.right != null) queue.offer(item.right);
      if (flag) container.add(item.val);
      else container.add(0, item.val);
    }
    flag = !flag;
    result.add(container);
  }
  return result;
}
```

### 117 Populating Next Right Pointer in Each Node II

```java
// just like the I solution
```

### 113 Path Sum II

```java
// my own solution
List<List<Integer>> result = new LinkedList<>();

public List<List<Integer>> pathSum(TreeNode root, int sum) {
  if (root == null) return result;
  traverse(root, sum, new LinkedList<>());
  return result;
}

private void traverse(TreeNode root, int sum, LinkedList<Integer> stack) {
  if (root == null) return;
  stack.push(root.val);
  if (root.left == null && root.right == null && root.val == sum) {
    List<Integer> tmp = new LinkedList<Integer>();
    for (Integer item : stack) {
      tmp.add(0, item);
    }
    result.add(tmp);
  }
  traverse(root.left, sum - root.val, stack);
  traverse(root.right, sum - root.val, stack);
  stack.pop();
}
```

### 106 Construct Binary Tree from Inorder and Postorder Traversal

```java
public TreeNode buildTreePostIn(int[] inorder, int[] postorder) {
  if (inorder == null || postorder == null || inorder.length != postorder.length) return null;
  HashMap<Integer, Integer> hm = new HashMap<>();
  for (int i = 0; i < inorder.length; ++i) {
    hm.put(inorder[i], i);
  }
  return buildTreePostIn(inorder, 0, inroder.length - 1, postorder, 0, postorder.length -1 , hm);
}

private TreeNode buildTreePostIn(int[] inorder, int is, int ie, int[] postorder, int ps, int pe, HashMap<Integer, Integer> hm) {
  if (ps > pe || is > ie) return null;
  TreeNode root = new TreeNode(postorder[pe]);
  int ri = hm.get(postorder[pe]);
  TreeNode leftChild = buildTreePostIn(inorder, is, ri - 1, postorder, ps, ps + ri -is - 1, hm);
  TreeNode rightChild = buildTreePostIn(inorder, ri + 1, ie, postorder, ps + ri -is, pe - 1, hm);
  root.left = leftChild;
  root.right = rightChild;
  return root;
}
```

### 105 Construct Binary Tre rom Perorder and Inorder Traversal

```java
public TreeNode buildTree(int[] preorder, int[] inorder) {
  return helper(0, 0, inorder.length - 1, preorder, inorder);
}

private TreeNode helper(int preStart, int inStart, in inEnd, int[] preorder, int[] inorder) {
  if (preStart > preorder.length - 1 || inStart > inEnd) return null;
  TreeNode root = new TreeNode(preorder[preStart]);
  int inIndex = 0;
  for (int i = inStart; i <= inEnd; i++) {
    if (inorder[i] == root.val)
      inIndex = i;
  }
  root.left = helper(preStart + 1, inStart, inIndex - 1， preorder, inorder);
  root.right = helper(preStart + inIndex - inStart + 1, inIndex + 1, inEnd, preorder, inorder);
  return root;
}
```

### 95 Unique Binary Search Trees II

```java
public List<TreeNode> generateTrees(int n) {
  return genTrees(1, n);
}

public List<TreeNode> genTrees(int start, int end) {
  List<TreeNode> list = new ArrayList<TreeNode>();

  if (start > end) {
    list.add(null);
    return list;
  }

  if (start == end) {
    liad.add(new TreeNode(start));
    return list;
  }

  List<TreeNode> left, right;
  for (int i = start; i <= end; i++) {
    left = genTrees(start, i - 1);
    right = genTrees(i + 1, end);
    for (TreeNode lnode : left) {
      for (TreeNode rnode : right) {
        TreeNode root = new TreeNode(i);
        root.left = lnode;
        root.right = rnode;
        list.add(root);
      }
    }
  }
  return list;
}
```

### 236 Lowest Common Ancestor of a Binary Tree

```java
public TreeNode lowestComonAncestor(TreeNode root, TreeNode p, TreeNode q) {
  if (root == null || root == p || root == q) reutrn root;
  TreeNode left = lowestCommonAncestor(root.left, p, q);
  TreeNode right = lowestCommonAncestor(root.right, p, q);
  if (left != null && right != null) return root;
  return left != null ? left : right;
}
```

### 222 Count Complete Tree Nodes

```java
public int countNodes(TreeNode root) {
  if (root == null) return 0;
  int lHeight = getDepth(root.left);
  int rHeight = getDepth(root.right);

  if (lHeight <= rHeight) {
    return (1 << lHeight) + countNodes(root.right);
  } else {
    return (1 << rHeight) + countNodes(root.left);
  }
}

private int getDepth(TreeNode root) {
  if (root == null) return 0;

  int hegith = 0;
  while (root != null) {
    height++;
    root = root.left;
  }
  return height;
}
```

### 98 Validate Binary Search Tree

```java
public boolean isValidBST(TreeNode root) {
  return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
}

private boolean isValidBST(TreeNode root, long minVal, long maxVal) {
  if (root == null) return true;
  if (root.val >= maxVal || root.val <= minVal) return false;
  return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
}
```

## Hard

### 145 Binary Tree Postorder Traversal

```java
// my own solution
public List<Integer> postorderTraversal(TreeNode root) {
  List<Integer> result = new LinkedList<>();
  LinkedList<TreeNode> stack = new LinkedList<>();

  if (root == null) return result;

  TreeNode p = root;
  TreeNode pre = null;
  while (p != null || !stack.isEmpty()) {
    if (p != null) {
      stack.push(p);
      p = p.left;
    } else {
      p = stack.peek();
      if (p.right == null || p.right == pre) {
        result.add(p.val);
        pre = p;
        stack.pop();
        p = null;
      } else {
        p = p.right;
      }
    }
  }
  return result;
}
```

### 297 Serialize and Deserlize Binary Tree

```java
// my own solutin
public String serialize(TreeNode root) {
  StringBuilder sb = new StringBuilder();
  LinkedList<TreeNode> queue = new LinkedList<TreeNode>();
  if (root == null) return sb.toString();
  queue.offer(root);
  sb.append(root.val).append(' ');
  while (!queue.isEmpty()) {
    int levelNum = queue.size();
    StringBuilder tmp = new StringBuilder();
    for (int i = 0; i < levelNum; i++) {
      TreeNode item = queue.poll();
      if (item.left != null) {
        queue.offer(item.left);
        tmp.append(item.left.val).append(' ');
      } else {
        tmp.append('#').append(' ');
      }
      if (item.right != null) {
        queue.offer(item.right);
        tmp.append(item.right.val).append(' ');
      } else {
        tmp.append('#').append(' ');
      }
    }
    sb.append(tmp.toString());
  }
}

public TreeNode deserialize(String data) {
  if (data.length() == 0) return null;
  String[] datas = data.split(" ");
  LinkedList<TreeNode> queue = new LinkedList<>();
  TreeNode head = new TreeNode(Integer.parseInt(datas[0]));
  queue.offer(head);
  int ind = 1;
  while (!queue.isEmpty() && ind < datas.length) {
    int levelNum = queue.size();
    for (int i = 0; i < levelNum; i++) {
      TreeNode item = queue.poll();
      if (ind < datas.length && !datas[ind].equals("#")) {
        item.left = new TreeNode(Integer.parseInt(datas[ind]));
        queue.offer(item.left);
      }
      ind++;
      if (ind < datas.length && !datas[ind].equals("#")) {
        item.right = new TreeNode(Integer.parseInt(datas[ind]));
        queue.offer(item.right);
      }
      ind++;
    }
  }
  return head;
}
```

### 99 Recover Binary Search Tree

```java
// my own solution
public void recoverTree(TreeNode root) {
  TreeNode first = null, seconde = null;
  TreeNode pre = null;
  LinkedList<TreeNode> stack = new LinkedList<>();
  TreeNode p = root;
  while (p != null || !stack.isEmtpy()) {
    if (p != null) {
      stack.push(p);
      p = p.left;
    } else {
      p = stack.pop();
      // 访问p
      if (pre != null) {
        if (pre.val > p.val) {
          if (first == null) {
            first = pre;
            second = p;
          } else {
            seconde = p;
          }
        }
      }
      pre = p;
      p = p.right;
    }
  }
  int tmp = first.val;
  first.val = second.val;
  second.val = tmp;
}
```

### 124 Binar Tree Maximum Path Sum

```java
// my own solution
Integer maxsofar = null;
Integer maxendinghere = null;
public int maxPathSum(TreeNode root) {
  traversal(root);
  return maxsofar;
}

private int traversal(TreeNode root) {
  if (root == null) return 0;
  int left = traversal(root.left);
  int right = traversal(root.right);
  if (maxendinghere == null && maxsofar == null) {
    maxendinghere = root.val;
    maxsofar = root.val;
  } else {
    maxendinghere = Math.max(Math.max(left, right) + root.val, root.val);
    maxsofar = Math.max(Math.max(maxendinghere, maxsofar), left + right + root.val);
  }
  return maxendinghere;
}

// another solution
int maxValue;
public int maxPathSum(TreeNode root) {
  maxValue = Integer.MIN_VALUE;
  maxPathDown(root);
  return maxValue;
}

private int maxPathDown(TreeNode node) {
  if (node == null) return 0;
  int left = Math.max(0, maxPathDown(node.lfet));
  int right = Math.max(0, maxPathDown(node.right));
  maxValue = Math.max(maxValue, left + right + node.val);
  return Math.max(left, right) + node.val;
}
```