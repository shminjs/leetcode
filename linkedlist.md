# Linked List

## Reverse Linked List

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
    public ListNode reverseList(ListNode head) {
        ListNode p = head;
        ListNode pre = null;
        while (p != null) {
            ListNode t = p.next;
            
            p.next = pre;
            pre = p;
            
            p = t;
        }
        return pre;
    }
}
```

## 21 Merge Two Sorted Lists

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode rhead = new ListNode(Integer.MIN_VALUE);
        ListNode p = rhead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                p.next = l1;
                p = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                p = l2;
                l2 = l2.next;
            }
        }
        p.next = l1 == null ? (l2 == null ? null : l2) : l1;
        return rhead.next;
    }
}
```

## 83 Remove Duplicates from Sorted List

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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) return head;
        ListNode rhead = new ListNode(Integer.MIN_VALUE);
        rhead.next = head;
        ListNode p = rhead;
        ListNode p1 = head;
        ListNode prev = null;
        while (p1 != null) {
            if (prev != null) {
                if (prev.val != p1.val) {
                    p.next = p1;
                    p = p1;
                    prev = p1;
                }
            } else {
                p.next = p1;
                p = p1;
                prev = p1;
            }
            p1 = p1.next;
        }
        prev.next = null;
        return rhead.next;
    }
}
```

## 141 Linked List Cycle

```java
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null) return false;
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && slow != null) {
            if (fast == slow) return true;
            
            slow = slow.next;
            
            fast = fast.next == null ? null : fast.next.next;
        }
        return false;
    }
}
```

## 234 Palindrome Linked List

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
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        int len = 0;
        ListNode p1 = head;
        while (p1 != null) {
            p1 = p1.next;
            len++;
        }
        boolean even = len % 2 == 1 ? true : false;
        len /= 2;
        p1 = head;
        ListNode pre = null;
        while (len-- != 0) {
            ListNode next = p1.next;
            
            p1.next = pre;
            pre = p1;
            
            p1 = next;
        }
        ListNode p2 = p1;
        p1 = pre;
        pre = p2;
        p2 = even ? p2.next : p2;
        boolean flag = true;
        while (p1 != null && p2 != null) {
            if (p1.val != p2.val) {
                flag = false;
            }
            ListNode next = p1.next;
            
            p1.next = pre;
            pre = p1;
            
            p1 = next;
            
            p2 = p2.next;
        }
        return flag;
    }
}
// 用快慢指针来做更巧妙啊
// 基本思想是找到中间位置
// 将链表反转然后再比较值
// 在比较的过程中顺便再反转过来
// 最后判断是否正确

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode fast = head, slow = head, pre = null;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            
            ListNode next = slow.next;
            
            slow.next = pre;
            pre = slow;
            
            slow = next;
        }
        ListNode t = slow;
        fast = fast != null ? slow.next : slow;
        slow = pre;
        pre = t;
        boolean flag = true;
        while (fast != null && slow != null) {
            if (fast.val != slow.val) {
                flag = false;
            }
            ListNode next = slow.next;
            
            slow.next = pre;
            pre = slow;
            
            slow = next;
            
            fast = fast.next;
        }
        return flag;
    }
}
```

## 203 Remove Linked List Elements

```java
public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
}

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode rhead = new ListNode(Integer.MIN_VALUE);
        rhead.next = head;
        ListNode p1 = rhead;
        ListNode p2 = head;
        while (p2 != null) {
            ListNode next = p2.next;
            
            if (p2.val != val) {
                p1.next = p2;
                p1 = p2;
            }
            
            p2 = next;
        }
        p1.next = null;
        return rhead.next;
    }
}
```

## 160 Intesection of Two Linked Lists

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode p1 = headA;
        ListNode p2 = headB;
        int i1 = 0;
        int i2 = 0;
        while (p1 != null && p2 != null) {
            if (p1 == p2) {
                return p1;
            }
            p1 = p1.next == null ? (i1++ == 0 ? headB : null) : p1.next;
            p2 = p2.next == null ? (i2++ == 0 ? headA : null) : p2.next;
        }
        return null;
    }
}

public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
  if (headA == null || headB = null) return null;
  
  ListNode a = headA;
  ListNode b = headB;

  while (a != b) {
    a = a == null ? headB : a.next;
    b = b == null ? headA : b.next;
  }
  return a;
}
```

## 445 Add Two Number II

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }
        ListNode head = null;
        int acc = 0;
        while (!s1.isEmpty() && !s2.isEmpty()) {
            int a = s1.pop(), b = s2.pop();
            ListNode t = new ListNode((acc + a + b) % 10);
            t.next = head;
            head = t;
            acc = (acc + a + b) / 10;
        }
        while (!s1.isEmpty()) {
            int a = s1.pop();
            ListNode t = new ListNode((acc + a) % 10);
            t.next = head;
            head = t;
            acc = (acc + a) / 10;
        }
        while (!s2.isEmpty()) {
            int b = s2.pop();
            ListNode t = new ListNode((acc + b) % 10);
            t.next = head;
            head = t;
            acc = (acc + b) / 10;
        }
        if (acc != 0) {
            ListNode t = new ListNode(acc);
            t.next = head;
            head = t;
        }
        return head;
    }
}
```

## 238 Odd Even Linked List

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
    public ListNode oddEvenList(ListNode head) {
        ListNode l1 = new ListNode(Integer.MIN_VALUE);
        ListNode l2 = new ListNode(Integer.MIN_VALUE);
        ListNode p1 = l1, p2 = l2;
        ListNode p = head;
        int ind = 0;
        while (p != null) {
            ListNode next = p.next;
            p.next = null;
            if (ind % 2 == 0) {
                p1.next = p;
                p1 = p;
            } else {
                p2.next = p;
                p2 = p;
            }
            ind++;
            p = next;
        }
        p1.next = l2.next;
        return l1.next;
    }
}
```

## 24 Swap Nodes in Pairs

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
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode rHead = new ListNode(Integer.MIN_VALUE);
        ListNode p1 = rHead;
        ListNode p2 = head;
        while (p2 != null && p2.next != null) {
            ListNode next = p2.next.next;
            
            p1.next = p2.next;
            p1 = p2.next;
            p1.next = p2;
            p1 = p2;
            
            p2 = next;
        }
        p1.next = p2;
        return rHead.next;
    }
}
```

## 19 Remove Nth Node From End of List

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
    public ListNode removeNthFromEnd(ListNode head, int n) {
       ListNode rHead = new ListNode(Integer.MIN_VALUE);
        rHead.next = head;
        ListNode p1 = rHead;
        ListNode p2 = rHead;
        for (int i = 0; i < n; i++) {
            p2 = p2.next;
        }
        while (p2 != null && p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        p1.next = p1.next.next;
        return rHead.next;
    }
}
```

## 147 Insertion Sort List

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
    public ListNode insertionSortList(ListNode head) {
        ListNode rHead = new ListNode(Integer.MIN_VALUE);
        while (head != null) {
            ListNode next = head.next;
            
            // begin to insert
            head.next = null;
            ListNode p = rHead;
            while (p.next != null && p.next.val < head.val) {
                p = p.next;
            }
            head.next = p.next;
            p.next = head;
            
            head = next;
        }
        return rHead.next;
    }
}
```

## 86 Partition List

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
    public ListNode partition(ListNode head, int x) {
        if (head == null || head.next == null) return head;
        ListNode rh1 = new ListNode(Integer.MIN_VALUE);
        ListNode rh2 = new ListNode(Integer.MIN_VALUE);
        ListNode p1 = rh1;
        ListNode p2 = rh2;
        while (head != null) {
            ListNode next = head.next;
            
            head.next = null;
            if (head.val < x) {
                p1.next = head;
                p1 = p1.next;
            } else {
                p2.next = head;
                p2 = p2.next;
            }
            
            head = next;
        }
        if (rh1.next != null) {
            p1.next = rh2.next;
            return rh1.next;
        } else {
            return rh2.next;
        }
    }
}
````

## 92 Reverse Linked List II

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
    public ListNode reverseBetween(ListNode head, int m, int n) {
        boolean flag = m == 1 ? true : false;
        ListNode p = head;
        ListNode pre = null;
        int ind = 1;
        while (p != null) {
            // 判断是否到了合适的位置
            // 1->2->3->4->5
            if (ind == m) {
                ListNode f = pre;
                ListNode l = p;
                pre = null;
                while (p != null) {
                    ListNode next = p.next;
                    
                    p.next = pre;
                    pre = p;
                    
                    p = next;
                    if (ind == n) {
                        break;
                    }
                    ind++;
                }
                if (f != null) f.next = pre;
                l.next = p;
                if (m == 1) return pre;
                pre = l;
                if (p == null) break;
            }
            pre = p;
            p = p.next;
            ind++;
        }
        if (!flag) return head;
        else return pre;
    }
}
```

## 82 Remove Dupliates from Sorted List II

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
    public ListNode deleteDuplicates(ListNode head) {
        ListNode rHead = new ListNode(Integer.MIN_VALUE);
        ListNode p1 = rHead;
        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                // 存在重复值，不允许加入
                int val = head.val;
                while (head != null && head.val == val) {
                    head = head.next;
                }
            } else {
                ListNode next = head.next;
                head.next = null;
                p1.next = head;
                p1 = p1.next;
                head = next;
            }
        }
        return rHead.next;
    }
}
```

## 148 Sort List

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
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode p1 = head, p2 = head;
        ListNode pre = null;
        while (p2 != null && p2.next != null) {
            pre = p1;
            p1 = p1.next;
            p2 = p2.next.next;
        }
        pre.next = null;
        ListNode h1 = sortList(head), h2 = sortList(p1);
        return mergeList(h1, h2);
    }
    
    private ListNode mergeList(ListNode h1, ListNode h2) {
        ListNode rHead = new ListNode(Integer.MIN_VALUE);
        ListNode p = rHead;
        while (h1 != null && h2 != null) {
            if (h1.val < h2.val) {
                p.next = h1;
                p = p.next;
                h1 = h1.next;
            } else {
                p.next = h2;
                p = p.next;
                h2 = h2.next;
            }
        }
        p.next = h1 == null ? h2 : h1;
        return rHead.next;
    }
}
```

## 2 Add Two Numbers

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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode rHead = new ListNode(-1);
        ListNode p = rHead;
        int acc = 0;
        while (l1 != null && l2 != null) {
            int val = (l1.val + l2.val + acc) % 10;
            acc = (l1.val + l2.val + acc) / 10;
            p.next = new ListNode(val);
            p = p.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        ListNode l = l1 == null ? l2 : l1;
        while (l != null) {
            int val = (l.val + acc) % 10;
            acc = (l.val + acc) / 10;
            p.next = new ListNode(val);
            p = p.next;
            l = l.next;
        }
        if (acc != 0) {
            p.next = new ListNode(acc);
        }
        return rHead.next;
    }
}
```

## 143 Reorder List

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
    public void reorderList(ListNode head) {
        if (head == null || head.next == null) return;
        ListNode p1 = head, p2 = head;
        ListNode pre = null;
        // split the list into half
        while (p2 != null && p2.next != null) {
            pre = p1;
            p1 = p1.next;
            p2 = p2.next.next;
        }
        pre.next = null;
        // printList(head);
        // printList(p1);
        // reverse the second half list
        pre = null;
        while (p1 != null) {
            ListNode next = p1.next;
            
            p1.next = pre;
            pre = p1;
            
            p1 = next;
        }
        // printList(pre);
        p1 = head.next; p2 = pre; pre = head;
        // printList(p1);printList(p2);
        boolean flap = false;
        int ind = 0;
        while (p1 != null && p2 != null) {
            ind++;
            if (flap) {
                ListNode next = p1.next;
                
                pre.next = p1;
                pre = pre.next;
                
                p1 = next;
                flap = false;
            } else {
                ListNode next = p2.next;
                
                pre.next = p2;
                pre = pre.next;
                
                p2 = next;
                flap = true;
            }
        }
        pre.next = p1 == null ? p2 : p1;
    }
    
    private void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + (head.next == null ? "" : "->"));
            head = head.next;
        }
        System.out.println();
    }
}
// 1 2 3 4
```

## 61 Rotate List

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
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        int length = getLength(head);
        int p = k % length;
        if (p == 0) return head;
        // First reverse the whole list
        ListNode prev = null, pl = head;
        while (pl != null) {
            ListNode next = pl.next;
            
            pl.next = prev;
            prev = pl;
            
            pl = next;
        }
        // Second reverse the first p list
        ListNode nhead = prev, flast = nhead;
        prev = null;
        pl = nhead;
        int count = 0;
        while (count != p) {
            ListNode next = pl.next;
            
            pl.next = prev;
            prev = pl;
            
            pl = next;
            
            count++;
        } 
        nhead = prev;
        prev = null;
        // Last reverse the last list
        while (pl != null) {
            ListNode next = pl.next;
            
            pl.next = prev;
            prev = pl;
            
            pl = next;
        }
        flast.next = prev;
        return nhead;
    }
    
    private int getLength(ListNode head) {
        int len = 0;
        while (head != null) {
            len++;
            head = head.next;
        }
        return len;
    }
}
```

## 25 Reverse Nodes in k-Group

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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null || k == 1) return head;
        int length = getLength(head);
        int count = length / k;
        if (count == 0) return head;
        ListNode ahead = null;
        ListNode p = head, pre = null, phead = null, ptail = null, nhead = null, ntail = null;
        while (count != 0) {
            pre = null;
            int flag = k;
            ntail = p;
            while (flag != 0) {
                ListNode next = p.next;
                
                p.next = pre;
                pre = p;
                
                p = next;
                
                flag--;
            }
            if (count == (length / k)) {
                ahead = pre;
            }
            nhead = pre;
            if (ptail != null) {
                ptail.next = nhead;
            }
            phead = nhead;
            ptail = ntail;
            count--;
        }
        ptail.next = p;
        // printList(ahead);
        return ahead;
    }
    
    private int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            head = head.next;
            length++;
        }
        return length;
    }
    
    private void printList(ListNode head) {
        System.out.println("******ListNodes->start******");
        while (head != null) {
            System.out.print(head.val + (head.next == null ? "" : "->"));
            head = head.next;
        }
        System.out.println();
        System.out.println("******ListNodes->end******");
    }
}
```

## 23 Merge k Sorted Lists

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
    public ListNode mergeKLists(ListNode[] lists) {
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
           public int compare(ListNode node1, ListNode node2) {
               return node1.val - node2.val;
           } 
        });
        for (ListNode node : lists) {
            if (node != null) pq.offer(node);
        }
        ListNode ahead = new ListNode(-1);
        ListNode p = ahead;
        while (!pq.isEmpty()) {
            ListNode item = pq.poll();
            if (item.next != null) {
                pq.offer(item.next);
            }
            item.next = null;
            p.next = item;
            p = item;
        }
        return ahead.next;
    }
}
```