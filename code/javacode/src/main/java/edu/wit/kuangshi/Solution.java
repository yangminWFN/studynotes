package edu.wit.kuangshi;

public class Solution {


    public static int nextGreaterElement(int n) {
        String s = String.valueOf(n);
        char[] arr = s.toCharArray();
        int i;
        for (i = arr.length - 1; i > 0; i--) {
            if (arr[i] > arr[i - 1]) {
                swap(arr, i, i - 1);
                break;
            }
        }
        if(i == 0){
            return -1;
        }else{
            int res = -1;
            try {
                res = new Integer(String.valueOf(arr));
                return res;
            }catch (Exception e){
                return res;
            }
        }

    }

    public static void swap(char[] a, int i, int j) {
        char t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void main(String[] args) {
        int n = Integer.MAX_VALUE;
        System.out.println(n);
        System.out.println(nextGreaterElement(n));
    }
}
