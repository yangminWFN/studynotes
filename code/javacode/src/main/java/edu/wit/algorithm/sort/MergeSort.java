package edu.wit.algorithm.sort;

/**
 * nLogn的数组排序算法
 */
public class MergeSort {
    /**
     * 递归方式自顶向下的归并排序
     * 
     * @param a     要排序的数组
     * @param start
     * @param end
     * @param temp
     */
    public static void merge_sort(int[] a, int start, int end, int[] temp) {
        // if(start >= end) return;
        if (end - start <= 15) {
            insert_sort_between(a, start, end);
            return;
        }
        int mid = start + (end - start) / 2;
        merge_sort(a, start, mid, temp);
        merge_sort(a, mid + 1, end, temp);
        if (a[mid] <= a[mid + 1]) {
            return;
        }
        mergeTwoSortedArray(a, start, mid, end, temp);
    }

    /**
     * 合并两个有序数组
     * 
     * @param a     源数组
     * @param left  左下标
     * @param mid   中间下标
     * @param right 右下标
     * @param temp  临时数组
     */
    public static void mergeTwoSortedArray(int[] a, int left, int mid, int right, int[] temp) {
        System.arraycopy(a, left, temp, left, right - left + 1);
        int i = left;
        int j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                a[k] = temp[j];
                j++;
            } else if (j == right + 1) {
                a[k] = temp[i];
                i++;
            } else if (temp[i] <= temp[j]) {
                a[k] = temp[i];
                i++;
            } else {
                a[k] = temp[j];
                j++;
            }
        }
    }

    /**
     * 对数组 a 中闭区间 [left,right] 进行插入排序
     * 
     * @param a     源数组
     * @param left  左下标
     * @param right 右下标
     */
    public static void insert_sort_between(int[] a, int left, int right) {
        for (int i = left + 1; i <= right; i++) {
            int e = a[i];
            int j;
            for (j = i; j > 0 && a[j - 1] > e; j--) {
                a[j] = a[j - 1];
            }
            a[j] = e;
        }
    }

    /**
     * 迭代方式自底向上的归并排序
     * @param a 源数组
     * @param n 元素个数
     */
    public static void merge_sort_bottom_to_up(int[] a, int n) {
        int[] temp = new int[n];
        for (int size = 1; size <= n; size = size * 2) {
            // 这里之所以要判断 i + size < n，是因为要存在右边的区间才需要进行两个有序数组的合并
            // i + size 是右边区间的起点下标
            for (int i = 0; i + size < n; i = i + 2 * size) {
                // 合并两个有序区间 [i, i + size - 1] 和 [i + size, i + 2*size -1]
                // 注意：这里的 (i + 2 * size - 1)可能大于 n - 1，所以取更小的下标即可
                mergeTwoSortedArray(a, i, i + size - 1, Math.min(i + 2 * size - 1, n - 1), temp);
            }
        }
    }

    public static void main(String[] args) {
        int n = 100 * 10000;
        int[] a = ArrayUtil.generatorRandomArray(n, 0, 10);
        int[] temp = new int[n];
        int[] b = null;

        int testCount = 10;
        long startTime = 0;
        long endTime = 0;
        double sum = 0;
        for (int i = 0; i < testCount; i++) {
            b = ArrayUtil.copy_array(a);
            startTime = System.currentTimeMillis();
            merge_sort(b, 0, n - 1, temp);
            endTime = System.currentTimeMillis();
            sum += (endTime - startTime) / 1000.0;
        }
        System.out.println("merge_sort平均耗费时间为：" + (sum / testCount) + "s");

        // long startTime1 = System.currentTimeMillis();
        // BaseSort.insert_sort_after(b, b.length);
        // long endTime1 = System.currentTimeMillis();
        // System.out.println("insert_sort_after耗费时间为：" + (endTime1 - startTime1) /
        // 1000.0 + " s");
    }
}
