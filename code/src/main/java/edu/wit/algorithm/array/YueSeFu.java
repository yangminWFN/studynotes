package edu.wit.algorithm.array;

import java.util.LinkedList;
import java.util.List;

/**
 * 约瑟夫环问题
 * 有 N个人围成一个圈，从第一个人开始报数，每次报到 m 的时候这个人就出圈，
 * 后面的人接着从 1 开始报数，直到最后一个人出圈，输出出圈的人的顺序列表
 */
public class YueSeFu {

    /**
     * 使用一个数组来记录每个人，数组下标 i 表示 第 i+1 个人
     * 第 i + 1个人在圈里则 a[i] = 1 ,否则等于 0
     * 时间复杂度是 O(n * m), 空间复杂度是 O(n);
     *
     * @param n
     * @param m
     * @return
     */
    public static List<Integer> getResult(int n, int m) {
        //每一个人用数组的一个元素表示，第 i + 1个人的下标为 i
        //a[i] = 1，第 i + 1 个人还在圈中
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = 1;
        }
        //初始时在圈中的人数
        int alive = n;
        //用来记录每次出圈的人序号的列表
        List<Integer> res = new LinkedList<>();
        //当前报数的人的下标
        int cur = 0;
        while (alive > 0) {
            //计数，当计数达到 sum == m时表示找到最后一个报数的人
            int sum = a[cur];
            while (sum < m) {
                cur = (cur + 1) % n;
                sum += a[cur];
            }
            //人数减一
            alive--;
            //当前位置的人出圈
            a[cur] = 0;
            res.add(cur + 1);
        }
        return res;
    }

    /**
     * 链表方式解决约瑟夫环问题
     */
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 使用一个循环链表来解决约瑟夫问题，每次找到最后报数节点的前一个节点，然后将报数的节点删除
     * 注意：在得到所有出圈元素后，循环链表的最后一个节点会自己引用自己，即 node.next = node，
     * 在使用引用计数对象死亡判断的GC算法中会导致这个对象不会被回收，造成内存泄露，所以在最后要将 node.next = null
     * 时间复杂度是 O(n * m), 空间复杂度是 O(n);
     *
     * @param n
     * @param m
     * @return
     */
    public static List<Integer> getResultByListNode(int n, int m) {
        List<Integer> res = new LinkedList<>();
        //使用一个循环链表将 n 个人存起来，每个节点的值就是这个人的序号：从 1 - n
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        for (int i = 1; i <= n; i++) {
            cur.next = new ListNode(i);
            cur = cur.next;
        }
        cur.next = dummy.next;
        //当前在圈里的人数
        int alive = n;
        //当前报数的节点的前一个节点
        ListNode node = dummy;
        while (alive > 0) {
            int sum = 0;
            while (sum < m - 1) {
                node = node.next;
                sum++;
            }
            res.add(node.next.val);
            //删除这个节点
            node.next = node.next.next;
            alive--;
        }
        //最后一个元素的 node.next = node，循环引用自身，所以要断开引用
        node.next = null;
        return res;
    }

    //递归方式：计算得到最后报数的人的序号，范围在 1 - n
    //f(n,m)表示在 n 个人的情况下，每次报 m个数，最后存活人的序号
    //f(n-1,m)表示在 n - 1个人的情况下，每次报 m 个数，最后存活的人的序号
    //每次有人被移除圈时，就将剩余所有人进行重新编号，此时最后存活的那个人的序号也会变化
    //时间复杂度为：O(n)，空间复杂度为O(n)
    public static int f(int n, int m) {
        //只有一个人的时候，最后存活的那个人的序号肯定是 1
        if (n == 1) return 1;
        else {
            //f(n,m)情况下所有人的序号：1，2，···，m-2，m-1，m，m+1，m+2，···，n
            //f(n-1,m)情况下所有人的序号：······，n-2，n-1，null，1，2，···
            //从f(n-1,m)推出f(n,m)，就是f(n,m) = (f(n-1,m)+m-1)%n + 1
            //这里之所以要将 f(n,m) = (f(n-1,m)+m-1)%n + 1，而不是f(n,m) = (f(n-1,m)+m)%n
            //  是由于下标是从1开始的，不可能取余后为 0；如果下标从 0 开始则可以直接 f(n,m) = (f(n-1,m)+m)%n
            return (f(n - 1, m) + m - 1) % n + 1;
        }
    }

    //迭代方式求解最后存活的人的序号
    public static int fd(int n, int m) {
        int res = 1;
        for (int i = 2; i <= n; i++) {
            //注意：这里取余的是每次新的元素个数，也就是 i 个元素
            //只有当有 2 个元素的时候，才需要进行计算，而且要一直计算到 n
            res = (res + m - 1) % i + 1;
        }
        return res;
    }

    public static void main(String[] args) {
        int n = 7;
        int m = 2;
        System.out.println(getResultByListNode(n, m));
        System.out.println(f(n, m));
        System.out.println(fd(n, m));
    }
}
