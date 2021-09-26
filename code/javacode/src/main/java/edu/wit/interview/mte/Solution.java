//package edu.wit.interview.mte;
//
//import java.util.ArrayList;
//import java.util.LinkedList;
//
//public class Solution {
//
//
//    static class TreeNode {
//        int val = 0;
//        TreeNode left = null;
//        TreeNode right = null;
//
//        public TreeNode(int val) {
//            this.val = val;
//        }
//    }
//
//
//    public TreeNode cyclicShiftTree(TreeNode root, int k) {
//        ArrayList<TreeNode> res = new ArrayList<>();
//        LinkedList<TreeNode> queue = new LinkedList<>();
//        if (root != null) {
//            queue.offer(root);
//        } else {
//            return null;
//        }
//        int level = getLevel(root);
//        int index = 0;
//        while (!queue.isEmpty() && index <= level) {
//            index++;
//            int size = queue.size();
//            for (int i = 0; i < size; i++) {
//                TreeNode node = queue.poll();
//                res.add(node);
//                if (node != null) {
//                    queue.offer(node.left);
//                    queue.offer(node.right);
//                } else {
//                    queue.offer(null);
//                    queue.offer(null);
//                }
//            }
//        }
//        int m = 0;
//        ArrayList<TreeNode> resCopy = new ArrayList<>(res);
//        for (int size = 1; size < res.size(); size += size * 2){
//            for(int i = 0; i < size; i++){
//                res.set(m,)
//                m++;
//            }
//        }
//        return getTree(res);
//    }
//
//    public int getLevel(TreeNode root) {
//        if (root == null) {
//            return 0;
//        }
//        return 1 + Math.max(getLevel(root.left), getLevel(root.right));
//    }
//
//    public TreeNode getTree(ArrayList<TreeNode> list, int m) {
//        TreeNode root = null;
//        if (list.size() != 0) {
//            root = list.get(0);
//        }
//
//        for (int i = 0; i < list.size() - m; i++) {
//            TreeNode node = list.get(i);
//            if (node != null) {
//                node.left = list.get(i * 2 + 1);
//                node.right = list.get(i * 2 + 2);
//            }
//        }
//        return root;
//    }
//
//
//    public void reverseK(ArrayList<TreeNode> list, int k) {
//        int n = list.size();
//        ArrayList<TreeNode> copy = new ArrayList<>(list);
//        for (int i = 0; i < n; i++) {
//            list.set(i, copy.get((i + k) % n));
//        }
//    }
////
////    public String maxLexicographical(String num) {
////        if (num == null) return num;
////        char[] a = num.toCharArray();
////        int start = -1;
////        int end = -1;
////        for(int i = 0; i < a.length; i++){
////            if(a[i] == '0'){
////                start = i;
////                break;
////            }
////        }
////        if(start == -1){
////            return num;
////        }
////        end = start;
////        for(int i = end; i < a.length; i++){
////            if(a[i] == '0'){
////                end = i;
////            }else{
////                break;
////            }
////        }
////        for (int i = start; i <= end; i++) {
////            a[i] = '1';
////        }
////        return new String(a);
////    }
//
//    public static void main(String[] args) {
//        int level = (int) Math.log(4);
//        System.out.println(level);
//    }
//}
