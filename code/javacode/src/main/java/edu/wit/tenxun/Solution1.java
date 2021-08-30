package edu.wit.tenxun;

import java.util.HashMap;

public class Solution1 {


    public class ListNode {
        int val;
        ListNode next = null;

        ListNode(int val) {
            this.val = val;
        }
    }

    public ListNode[] solve(int m, ListNode a) {
        if(a == null) return null;
        ListNode p = a;
        ListNode[] res = new ListNode[m];
        ListNode[] tails = new ListNode[m];
        while(p.next != null){
            int index = p.val % m;
            if(res[index] != null){
                tails[index].next = new ListNode(p.val);
                tails[index] = tails[index].next;
            }else{
                res[index] = new ListNode(p.val);
                tails[index] = res[index];
            }
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
