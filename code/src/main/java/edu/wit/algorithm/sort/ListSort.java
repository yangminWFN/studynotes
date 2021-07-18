package edu.wit.algorithm.sort;

public class ListSort {

    /**
     * 递归方式自顶向下的链表归并排序
     * 
     * @param head
     * @return 排序后的新链表头节点
     */
    public static ListNode merge_sort(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode midNode = findMid(head);
        ListNode midNext = midNode.next;
        midNode.next = null;
        ListNode left = merge_sort(head);
        ListNode right = merge_sort(midNext);
        ListNode mergedList = mergeTwoSortedList(left, right);
        return mergedList;
    }

    /**
     * 找到链表的中间节点 奇数个节点就是其中间节点，偶数个节点取其中间两个节点的第一个
     * 
     * @param head
     * @return
     */
    public static ListNode findMid(ListNode head) {
        // 在上面判断过head是否是null或者只有一个元素了，所以在这里就不再重复判断
        ListNode slowp = head;
        ListNode fastp = head.next;
        while (fastp != null && fastp.next != null) {
            fastp = fastp.next.next;
            slowp = slowp.next;
        }
        return slowp;
    }

    /**
     * 合并两个有序链表
     * 
     * @param l1
     * @param l2
     * @return 合并两个链表后的头节点
     */
    public static ListNode mergeTwoSortedList(ListNode l1, ListNode l2) {
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

    /**
     * 自底向上迭代方式的链表归并排序
     * 
     * @param head
     * @return
     */
    public static ListNode merge_sort_bottom_to_up(ListNode head) {
        if (head == null || head.next == null)
            return head;
        int n = 0;
        ListNode p = head;
        while (p != null) {
            n++;
            p = p.next;
        }
        ListNode dummy = new ListNode(-1, head);
        for (int size = 1; size < n; size = size << 1) {
            ListNode pre = dummy;
            ListNode cur = dummy.next;
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

    /**
     * 对给定链表，返回切割 n 个节点后的剩余链表的头节点（先记录原始头节点，然后进行切割！！） 对于不足 n 个节点的链表返回 null
     * 注意：首先不考虑节点不够的情况写完逻辑，然后再添加节点不够情况的处理
     * 
     * @param head
     * @param n
     * @return
     */
    public static ListNode cut(ListNode head, int n) {
        ListNode p = head;
        while (n > 1 && p != null) {
            p = p.next;
            n--;
        }
        if (p == null)
            return null;
        ListNode next = p.next;
        p.next = null;
        return next;
    }


    public static void main(String[] args) {
        int n = 100 * 10000;
        ListNode head = ListUtil.generatorRandomList(n, 0, 100);
        // System.out.print("排序前： ");
        // ListUtil.printlnList(head);
        // System.out.print("排序后： ");
        long start = System.currentTimeMillis();
        ListNode newHead = merge_sort_bottom_to_up(head);
        long end = System.currentTimeMillis();
        // ListUtil.printlnList(newHead);
        System.out.println("耗费时间为：" + (end - start) / 1000.0);
    }
}
