package edu.wit.algorithm.sort;

import java.lang.annotation.Target;
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
        while (i < gt) {
            if (a[i] == target) {
                i++;
            } else if (a[i] < target) {
                lt++;
                swap(a, lt, i);
                i++;
            } else {
                gt--;
                swap(a, gt, i);
            }
        }
        swap(a, l, lt);
        lt--;
        quickSort(a, l, lt);
        quickSort(a, gt, r);
    }

    public static int[] generateRandomArray(int n, int min, int max) {
        Random r = new Random();
        return r.ints(min, max).limit(n).toArray();
    }


    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    public static void baseQuickSort(int[] a, int l, int r) {
        if (l >= r) return;
        int pivot = partition(a, l, r);
        baseQuickSort(a, l, pivot - 1);
        baseQuickSort(a, pivot + 1, r);
    }

    public static int partition(int[] a, int l, int r) {
        int pivot = a[l];
        //定义：[l+1,i]之间的元素都是小于等于pivot的，[i+1,j)之间的元素都是大于等于pivot
        int i = l;
        for(int j = l + 1; j <= r; j++){
            if(a[j] <= pivot){
                i++;
                swap(a, i, j);
            }
        }
        swap(a, l, i);
        return i;
    }


    public static void main(String[] args) {
        int n = 100;
        int[] a = generateRandomArray(n, 0, 100);
        System.out.println("排序前：");
        System.out.println(Arrays.toString(a));
        baseQuickSort(a, 0, a.length - 1);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(a));

    }
}
