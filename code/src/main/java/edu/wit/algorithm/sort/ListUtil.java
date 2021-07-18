package edu.wit.algorithm.sort;

import java.util.Random;

public class ListUtil {
    /**
     * 生成 n 个在区间 [min,max) 的随机链表
     * 
     * @param n
     * @param min
     * @param max
     * @return 链表头节点
     */
    public static ListNode generatorRandomList(int n, int min, int max) {
        int[] a = new Random().ints(min, max).limit(n).toArray();
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for (int i = 0; i < a.length; i++) {
            cur.next = new ListNode(a[i]);
            cur = cur.next;
        }
        return dummy.next;
    }

    /**
     * 打印链表
     * 
     * @param head
     */
    public static void printlnList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println("");
    }
}
