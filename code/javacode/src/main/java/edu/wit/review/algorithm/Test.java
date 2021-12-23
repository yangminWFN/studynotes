package edu.wit.review.algorithm;

import javax.sound.sampled.SourceDataLine;

class ListNode {
    int val;
    ListNode next;

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public ListNode(int val) {
        this.val = val;
    }
}

class Test {

    public static ListNode sum(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int x1 = 0, x2 = 0, sum = 0, d = 0;
        while (l1 != null || l2 != null) {
            if (l1 != null) {
                x1 = l1.val;
                l1 = l1.next;
            } else
                x1 = 0;
            if (l2 != null) {
                x2 = l2.val;
                l2 = l2.next;
            } else
                x2 = 0;
            sum = x1 + x2 + d;
            cur.next = new ListNode(sum % 10);
            cur = cur.next;
            d = sum / 10;
        }
        if (d != 0) {
            cur.next = new ListNode(d);
        }
        return dummy.next;
    }

    public static ListNode generateListByArr(int[] a) {
        if (a == null || a.length == 0)
            return null;
        ListNode head = new ListNode(a[0]);
        ListNode cur = head;
        for (int i = 1; i < a.length; i++) {
            cur.next = new ListNode(a[i]);
            cur = cur.next;
        }
        return head;
    }

    
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + " ");
            head = head.next;
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        int[] a = new int[] { 1, 2, 3, 4 };
        int[] b = new int[] { 4, 5 };
        ListNode l1 = generateListByArr(a);
        ListNode l2 = generateListByArr(b);
        printList(l1);
        printList(l2);
        printList(sum(l1, l2));
    }
}