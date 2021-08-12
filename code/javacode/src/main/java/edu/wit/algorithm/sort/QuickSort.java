package edu.wit.algorithm.sort;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static void base_quick_sort(int[] a, int start, int end) {
        if (start >= end) {
            return;
        }
        int pivot = base_partition(a, start, end);
        base_quick_sort(a, start, pivot - 1);
        base_quick_sort(a, pivot + 1, end);
    }

    /**
     * 对区间[l,r]内的元素进行分区，[l+1,j]中的元素都是小于等于pivot的，[j+1,i)中的元素都是大于 v 的
     * 重复元素比较多时，且数据量比较大时，会产生栈溢出错误
     * 
     * @param a
     * @param l
     * @param r
     * @return
     */
    public static int base_partition(int[] a, int l, int r) {
        // 随机快速排序
        int k = new Random().nextInt(r - l + 1) + l;
        ArrayUtil.swap(a, k, l);
        // 添加以上代码，不添加就是基本快排

        int pivot = a[l];
        int j = l;
        for (int i = l + 1; i <= r; i++) {
            if (a[i] <= pivot) {
                j++;
                ArrayUtil.swap(a, i, j);
            }
        }
        ArrayUtil.swap(a, l, j);
        return j;
    }

    public static void double_quick_sort(int[] a, int start, int end) {
        if (start >= end)
            return;
        int pivot = double_partition(a, start, end);
        double_quick_sort(a, start, pivot - 1);
        double_quick_sort(a, pivot + 1, end);
    }

    /**
     * 双路快排：使得等于pivot的元素尽可能均匀地分布在分割点两端，避免重复元素过多时导致两边区间大小差异过大 区间
     * [l+1,i)中的元素都是小于等于pivot的，区间 (j,r]之间的元素都是大于等于pivot的
     * 
     * @param a
     * @param l
     * @param r
     * @return
     */
    public static int double_partition(int[] a, int l, int r) {
        int k = new Random().nextInt(r - l + 1) + l;
        ArrayUtil.swap(a, l, k);

        int pivot = a[l];
        int i = l + 1;
        int j = r;
        while (true) {
            while (i <= j && a[i] < pivot)
                i++;
            while (i <= j && a[j] > pivot)
                j--;
            if (i > j)
                break;
            ArrayUtil.swap(a, i, j);
            i++;
            j--;
        }
        ArrayUtil.swap(a, l, j);
        return j;
    }

    // 赢时胜

    /**
     * 三路快速排序：[l+1,left]区间里的元素都是小于pivot的 ,[left+1,i)区间里的元素都是等于pivot的
     * ,[right,r]区间里的元素都是大于pivot的
     * 
     * @param a
     * @param l
     * @param r
     */
    public static void three_quick_sort(int[] a, int l, int r) {
        if (r - l <= 15) {
            insert_sort(a, l, r);
            return;
        }
        // if (l >= r)
        // return;
        int randomIndex = new Random().nextInt(r - l + 1) + l;
        ArrayUtil.swap(a, l, randomIndex);
        int pivot = a[l];
        int lt = l;
        int gt = r + 1;
        int i = l + 1;
        while (i < gt) {
            if (a[i] == pivot) {
                i++;
            } else if (a[i] < pivot) {
                lt++;
                ArrayUtil.swap(a, lt, i);
                i++;
            } else {
                gt--;
                ArrayUtil.swap(a, gt, i);
            }
        }
        // l与left交换
        ArrayUtil.swap(a, l, lt);
        three_quick_sort(a, l, lt - 1);
        three_quick_sort(a, gt, r);
    }

    /**
     * 对区间数组a中[l,r]进行插入排序
     * 
     * @param a
     * @param l
     * @param r
     */
    public static void insert_sort(int[] a, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int e = a[i];
            int j;
            for (j = i; j > 0 && a[j - 1] > e; j--) {
                a[j] = a[j - 1];
            }
            a[j] = e;
        }
    }

    public static void main(String[] args) {
        int n = 100;
        int[] a = ArrayUtil.generatorRandomArray(n, 0, 100);
        System.out.println("排序前：" + Arrays.toString(a));

        // int[] b = ArrayUtil.copy_array(a);
        long startTime = System.currentTimeMillis();
        three_quick_sort(a, 0, n - 1);
        long endTime = System.currentTimeMillis();
        System.out.println("排序后：" + Arrays.toString(a)+a.length);
        System.out.println("耗费时间为：" + (endTime - startTime) / 1000.0 + " s");

//        long startTime1 = System.currentTimeMillis();
//        // base_quick_sort(b, 0, n - 1);
//        long endTime1 = System.currentTimeMillis();
//        System.out.println("耗费时间为：" + (endTime1 - startTime1) / 1000.0 + " s");
    }

}
