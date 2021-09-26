package edu.wit.review.algorithm;

import java.util.Arrays;
import java.util.Random;

public class SortTest {

    static class MaxHeap {
        // 数组第一个位置不用，下标从 1 开始
        private int[] data;

        private int capatity;
        private int size;

        public MaxHeap(int capatity) {
            this.capatity = capatity;
            this.data = new int[capatity + 1];
            this.size = 0;
        }

        public boolean offer(int item) {
            assert (size + 1 <= capatity);
            size++;
            data[size] = item;
            shiftUp(size);
            return true;
        }

        public int poll() {
            assert (size > 0);
            int res = data[1];
            swap(1, size);
            size--;
            shiftDown(1);
            return res;
        }

        private void shiftUp(int k) {
            int j = k / 2;
            while (j > 0 && data[j] < data[k]) {
                swap(j, k);
                k = j;
                j = k / 2;
            }
        }

        private void shiftDown(int k) {
            int j = 2 * k;
            while (j <= size) {
                if (j + 1 <= size && data[j + 1] > data[j]) {
                    j = j + 1;
                }
                if (data[k] < data[j]) {
                    swap(k, j);
                    k = j;
                    j = 2 * k;
                } else break;
            }
        }

        public boolean isEmpty() {
            return this.size == 0;
        }

        private void swap(int i, int j) {
            if (i == j) return;
            data[i] = data[i] ^ data[j];
            data[j] = data[i] ^ data[j];
            data[i] = data[i] ^ data[j];
        }

        @Override
        public String toString() {
            return Arrays.toString(data);
        }

    }

    public static int[] generateRandomArray(int n, int min, int max) {
        Random r = new Random();
        return r.ints(min, max).limit(n).toArray();
    }

    /**
     * 判断数组的前 n 个元素是不是有序的
     *
     * @param a
     * @param n
     * @return
     */
    public static boolean arrayIsOrdered(int[] a, int n) {
        boolean flag = true;
        for (int i = 1; i < n; i++) {
            if (a[i] < a[i - 1]) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    /**
     * 在区间[l,r]之间使用插入排序
     *
     * @param a
     * @param l
     * @param r
     */
    public static void insertSort(int[] a, int l, int r) {
        for (int i = l + 1; i <= r; i++) {
            int e = a[i];
            int j;
            for (j = i; j > l && a[j - 1] > e; j--) {
                a[j] = a[j - 1];
            }
            a[j] = e;
        }
    }

    public static void mergeSort(int[] a, int l, int r, int[] temp) {
        if (r - l <= 15) {
            insertSort(a, l, r);
            return;
        }
        int mid = l + ((r - l) >> 1);
        mergeSort(a, l, mid, temp);
        mergeSort(a, mid + 1, r, temp);
        if (a[mid + 1] >= a[mid]) {
            return;
        }
        mergeTwoSortedArray(a, l, mid, r, temp);
    }

    private static void mergeTwoSortedArray(int[] a, int l, int mid, int r, int[] temp) {
        System.arraycopy(a, l, temp, l, r - l + 1);
        int i = l;
        int j = mid + 1;
        for (int k = l; k <= r; k++) {
            if (i == mid + 1) {
                a[k] = temp[j];
                j++;
            } else if (j == r + 1) {
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

    public static void quickSort(int[] a, int l, int r) {
//        if (l >= r) return;
        if (r - l <= 15) {
            insertSort(a, l, r);
            return;
        }
        int pivot = partition(a, l, r);
        quickSort(a, l, pivot - 1);
        quickSort(a, pivot + 1, r);
    }

    private static int partition(int[] a, int l, int r) {
        int randomIndex = new Random().nextInt(r - l + 1) + l;
        swap(a, l, randomIndex);
        int pivot = a[l];
        // 定义：区间[l + 1, i]之间的元素都是小于等于pivot的，区间[i + 1,j)之间的元素都是大于pivot的
        int i = l;
        for (int j = l + 1; j <= r; j++) {
            if (a[j] <= pivot) {
                i++;
                swap(a, i, j);
            }
        }
        swap(a, l, i);
        return i;
    }

    private static void swap(int[] a, int i, int j) {
        if (i == j) return;
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }


    private static void shiftDown(int[] a, int n, int k) {
        int j = 2 * k + 1;
        while (j < n) {
            if (j + 1 < n && a[j + 1] > a[j]) {
                j = j + 1;
            }
            if (a[k] < a[j]) {
                swap(a, k, j);
                k = j;
                j = 2 * k + 1;
            } else break;
        }
    }

    private static void heapify(int[] a, int n) {
        for (int k = (n - 1) / 2; k >= 0; k--) {
            shiftDown(a, n, k);
        }
    }

    public static void heapSort(int[] a) {
        int n = a.length;
        heapify(a, n);
        for (int i = n - 1; i > 0; i--) {
            swap(a, i, 0);
            shiftDown(a, i, 0);
        }
    }

    /**
     * 测试插入排序
     *
     * @param a
     */
    public static void testInsertSort(int[] a) {
        System.out.println("插入排序前：" + Arrays.toString(a));
        System.out.println("插入排序前数组是否是有序的：" + arrayIsOrdered(a, a.length));
        insertSort(a, 0, a.length - 1);
        System.out.println("插入排序后：" + Arrays.toString(a));
        System.out.println("插入排序前数组是否是有序的：" + arrayIsOrdered(a, a.length));
    }

    /**
     * 测试归并排序
     *
     * @param a
     */
    public static void testMergeSort(int[] a) {
        int[] temp = new int[a.length];
        System.out.println("归并排序前：" + Arrays.toString(a));
        System.out.println("归并排序前数组是否是有序的：" + arrayIsOrdered(a, a.length));
        mergeSort(a, 0, a.length - 1, temp);
        System.out.println("归并排序后：" + Arrays.toString(a));
        System.out.println("归并排序前数组是否是有序的：" + arrayIsOrdered(a, a.length));
    }

    /**
     * 测试快速排序
     *
     * @param a
     */
    public static void testQuickSort(int[] a) {
        System.out.println("快速排序前：" + Arrays.toString(a));
        System.out.println("快速排序前数组是否是有序的：" + arrayIsOrdered(a, a.length));
        quickSort(a, 0, a.length - 1);
        System.out.println("快速排序后：" + Arrays.toString(a));
        System.out.println("快速排序前数组是否是有序的：" + arrayIsOrdered(a, a.length));
    }

    /**
     * 测试堆排序
     *
     * @param a
     */
    public static void testHeapSort(int[] a) {
        System.out.println("堆排序前：" + Arrays.toString(a));
        System.out.println("堆排序前数组是否是有序的：" + arrayIsOrdered(a, a.length));
        heapSort(a);
        System.out.println("堆排序后：" + Arrays.toString(a));
        System.out.println("堆排序前数组是否是有序的：" + arrayIsOrdered(a, a.length));
    }

    public static void testMaxHeap(int[] a) {
        int n = a.length;
        MaxHeap maxHeap = new MaxHeap(n);
        System.out.println("堆排序前：" + Arrays.toString(a));
        System.out.println("堆排序前数组是否是有序的：" + arrayIsOrdered(a, a.length));
        for (int i = 0; i < n; i++) {
            maxHeap.offer(a[i]);
        }
        for (int i = n - 1; i >= 0; i--) {
            a[i] = maxHeap.poll();
        }
        System.out.println("堆排序后：" + Arrays.toString(a));
        System.out.println("堆排序前数组是否是有序的：" + arrayIsOrdered(a, a.length));
    }


    public static void main(String[] args) {
        int n = 1000;
        int min = 0;
        int max = 100;
        int[] a = generateRandomArray(n, min, max);
        testMaxHeap(a);
    }
}
