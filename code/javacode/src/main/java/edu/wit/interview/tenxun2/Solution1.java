package edu.wit.interview.tenxun2;


import java.util.ArrayList;
import java.util.Iterator;

class ListNode {
    int val;
    ListNode next = null;

    public ListNode(int val) {
        this.val = val;
    }
}


public class Solution1 {

    public ListNode solve(ListNode[] a) {
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;

        ArrayList<ListNode> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if(a[i] != null){
                list.add(a[i]);
            }
        }

        while(list.size() != 0){
            Iterator<ListNode> iterator = list.iterator();
            int index = -1;
            while(iterator.hasNext()){
                ListNode temp = iterator.next();
                if(temp == null){
                    iterator.remove();
                }else{
                    index++;
                    cur.next = temp;
                    list.set(index,temp.next);
                    cur = cur.next;
                }
            }
        }

        return dummy.next;
    }

    public static void main(String[] args) {

    }
}
