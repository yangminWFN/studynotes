# [剑指 Offer II 026. 重排链表](https://leetcode-cn.com/problems/LGjMqU/)

```java
class Solution {
    public void reorderList(ListNode head) {
        if(head == null || head.next == null) return;
        ListNode midNode = findMidNode(head);
        ListNode right = midNode.next;
        midNode.next = null;
        right = reverse(right);
        ListNode left = head;
        while(left != null && right != null){
            ListNode leftNext = left.next;
            ListNode rightTemp = right.next;
            left.next = right;
            right.next = leftNext;
            left = leftNext;
            right = rightTemp;
        }
    }

    public ListNode findMidNode(ListNode head){
        ListNode slow = head;
        ListNode fast = head.next;
        while(fast != null && fast.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public ListNode reverse(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        ListNode q = null;
        while (cur != null) {
            q = cur.next;
            cur.next = pre;
            pre = cur;
            cur = q;
        }
        return pre;
    }
}
```

思路：首先找到链表中点将链表分为左右两个链表，然后将右边的链表进行反转，然后依次连接到第一条链表上