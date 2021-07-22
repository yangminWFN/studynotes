package edu.wit;

import java.util.Arrays;
import java.util.Random;

public class QuickSortTest {
    public static void quickSort(int[] a, int l, int r) {
        if (l >= r) return;
        int target = a[l];
        //定义[l+1,lt]之间的元素都是小于target的，[lt+1,i)之间的元素都是等于target的，[gt,r]之间的元素都是大于target的
        int lt = l;
        int gt = r + 1;
        int i = l + 1;
        while(i < gt){
            if(a[i] == target){
                i++;
            }else if(a[i] < target){
                lt++;
                swap(a, lt, i);
                i++;
            }else{
                gt--;
                swap(a, gt, i);
            }
        }
        swap(a, l ,lt);
        lt--;
        quickSort(a, l, lt);
        quickSort(a, gt, r);
    }

    public static int[] generateRandomArray(int n, int min, int max){
        Random r = new Random();
        return r.ints(min,max).limit(n).toArray();
    }


    public static void swap(int[] a, int i, int j){
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }


    public static void main(String[] args) {
        int n = 100;
        int[] a = generateRandomArray(n,0,100);
        System.out.println("排序前：");
        System.out.println(Arrays.toString(a));
        quickSort(a,0,a.length - 1);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(a));

    }
}
