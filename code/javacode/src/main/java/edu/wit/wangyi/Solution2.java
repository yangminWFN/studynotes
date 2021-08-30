package edu.wit.wangyi;

public class Solution2 {

    public static char findKthBit(int n, int k) {
        return getSi(n).charAt(k - 1);
    }

    public static String getSi(int i) {
        if (i == 1) return "a";
        else {
            String last = getSi(i - 1);
            return last + (char)(i + 96) + invertStr(last);
        }
    }

    public static char invert(char c) {
        char res = (char) (219 - c);
        return res;
    }

    public static String invertStr(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            arr[i] = invert(arr[i]);
        }
        int i = 0;
        int j = arr.length - 1;
        while(i < j){
            swap(arr,i,j);
            i++;
            j--;
        }
        return new String(arr);
    }

    public static  void swap(char[] arr,int i,int j){
        if( i == j) return;
        arr[i] = (char) (arr[i] ^ arr[j]);
        arr[j] = (char) (arr[i] ^ arr[j]);
        arr[i] = (char) (arr[i] ^ arr[j]);
    }

    public static void main(String[] args) {
        System.out.println(findKthBit(3,3));
    }
}
