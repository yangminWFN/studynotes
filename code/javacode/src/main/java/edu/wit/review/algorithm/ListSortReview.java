package edu.wit.review.algorithm;

import java.util.Random;

public class ListSortReview {
    /**
     * 链表节点类
     */
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 生成一条 n 个在区间[min,max)的元素组成的随机数链表
     *
     * @param n
     * @param min
     * @param max
     * @return
     */
    public static ListNode generateRandomList(int n, int min, int max) {
        Random r = new Random();
        int[] a = r.ints(min, max).limit(n).toArray();
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for (int i = 0; i < n; i++) {
            cur.next = new ListNode(a[i]);
            cur = cur.next;
        }
        return dummy.next;
    }

    public static void swap(ListNode a, ListNode b) {
        int t = a.val;
        a.val = b.val;
        b.val = t;
    }

    /**
     * 递归方式链表的归并排序
     *
     * @param head
     * @return
     */
    public static ListNode mergeListSort(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode mid = findMid(head);
        ListNode remain = mid.next;
        mid.next = null;
        ListNode l1 = mergeListSort(head);
        ListNode l2 = mergeListSort(remain);
        return mergeTwoSortedList(l1, l2);
    }

    /**
     * 返回链表的中间节点：[1,2,3]返回2 、[1,2,3,4]返回 2
     *
     * @param head
     * @return
     */
    private static ListNode findMid(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private static ListNode mergeTwoSortedList(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }

    public static String listToString(ListNode head) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            sb.append(" ");
            head = head.next;
        }
        return sb.toString();
    }


    /**
     * 迭代方式：链表的归并排序
     *
     * @param head
     * @return
     */
    public static ListNode mergeListSort2(ListNode head) {
        if (head == null || head.next == null) return head;
        int n = 0;
        ListNode p = head;
        while (p != null) {
            p = p.next;
            n++;
        }
        ListNode dummy = new ListNode(-1, head);
        // pre指向排好序的链表的最后一个节点，cur指向当前正在排序的链表的第一个节点
        ListNode pre = null, cur = null;
        for (int size = 1; size < n; size = size << 1) {
            pre = dummy;
            cur = dummy.next;
            while (cur != null) {
                ListNode left = cur;
                ListNode right = cut(left, size);
                cur = cut(right, size);
                pre.next = mergeTwoSortedList(left, right);
                while (pre.next != null) {
                    pre = pre.next;
                }
            }
        }

        return dummy.next;
    }

    private static ListNode cut(ListNode head, int n) {
        if (head == null || n == 0) return head;
        ListNode p = head;
        while (p.next != null && (--n) > 0) {
            p = p.next;
        }
        ListNode res = p.next;
        p.next = null;
        return res;
    }

    public static void quickListSort(ListNode head, ListNode r) {
        if (head == null || head == r) return;
        int pivot = head.val;
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != r) {
            if (fast.val <= pivot) {
                slow = slow.next;
                swap(slow, fast);
            }
            fast = fast.next;
        }
        swap(head, slow);
        quickListSort(head, slow);
        quickListSort(slow.next, r);
    }

    public static void main(String[] args) {
//        int n = 100, min = 0, max = 100;
//        ListNode head = generateRandomList(n, min, max);
//        System.out.println("排序前：" + listToString(head));
//        quickListSort(head, null);
//        System.out.println("排序后：" + listToString(head));
        int i = Integer.MIN_VALUE;
        int j = - i;
        System.out.println(i + ":" + j);
        System.out.println(i == j);
        int m = Integer.MAX_VALUE + 1;
        System.out.println(m == i);
    }
}
