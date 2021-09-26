package edu.wit.review.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 求解约瑟夫环问题
 * n 个小朋友围成一圈从 1 开始报数，数到 m 的小朋友出圈，求出圈的小朋友所构成的序列
 */
public class YueSeFuTest {

    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    public static List<Integer> getNumByArr(int n, int m) {
        ArrayList<Integer> res = new ArrayList<>();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = 1;
        }
        int alive = n;
        int sum = 0;
        int cur = -1;
        while (alive > 0) {
            while (sum != m) {
                cur = (cur + 1) % n;
                sum += a[cur];
            }
            sum = 0;
            res.add(cur + 1);
            a[cur] = 0;
            alive--;
        }
        return res;
    }

    public static List<Integer> getNumByList(int n, int m) {
        ListNode head = getListNode(n);
        // 指向要删除节点的前一个节点
        ListNode cur = head;
        ArrayList<Integer> res = new ArrayList<>();
        int sum = 1;
        int alive = n;
        while (alive > 0) {
            while (sum != m - 1) {
                cur = cur.next;
                sum += 1;
            }
            sum = 0;
            res.add(cur.next.val);
            cur.next = cur.next.next;
            alive--;
        }
        cur.next = null;
        return res;
    }

    public static ListNode getListNode(int n) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for (int i = 1; i <= n; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        cur.next = dummy.next;
        return dummy.next;
    }

    public static int getLastNum(int n, int m){
        if(n == 0 || n == 1) return 1;
        else{
            return (getLastNum(n - 1, m) + m - 1) % n + 1;
        }
    }

    public static int getLastNum2(int n, int m){
        int res = 1;
        for(int i = 2; i <= n; i++){
            res = (res + m - 1) % i + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 10;
        int m = 4;
        System.out.println(getNumByArr(n, m));
        System.out.println(getNumByList(n, m));
        System.out.println(getLastNum(n, m));
        System.out.println(getLastNum2(n, m));
    }
}
