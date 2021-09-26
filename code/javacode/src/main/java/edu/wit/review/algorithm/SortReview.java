package edu.wit.review.algorithm;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class SortReview {

    public static int[] generateRandomArray(int n, int min, int max) {
        Random r = new Random();
        return r.ints(min, max).limit(n).toArray();
    }

    public static void swap(int[] a, int i, int j) {
        if (i == j) return;
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }

    public static void insertSort(int[] a, int l, int r) {
        if (l >= r) return;
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
//        if (l >= r) return;
        // 47 是 Arrays.sort()中使用快排来解决时，使用插入排序的阈值
        if (r - l <= 47) {
            insertSort(a, l, r);
            return;
        }
        int mid = l + ((r - l) >> 1);
        mergeSort(a, l, mid, temp);
        mergeSort(a, mid + 1, r, temp);
        mergeTwoSortedArray(a, l, mid, r, temp);
    }

    public static void mergeTwoSortedArray(int[] a, int l, int mid, int r, int[] temp) {
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

    /**
     * 迭代方式求解归并排序
     *
     * @param a
     */
    public static void mergeSort2(int[] a) {
        int n = a.length;
        int[] temp = new int[n];
        for (int size = 1; size < n; size = size << 1) {
            // 左边区间范围 [i, i + size - 1] 、 右边区间范围 [i + size, i + 2 * size - 1]
            for (int i = 0; i + size < n; i = i + 2 * size) {
                mergeTwoSortedArray(a, i, i + size - 1, Math.min(i + 2 * size - 1, n - 1), temp);
            }
        }
    }

    public static void baseQuickSort(int[] a, int l, int r) {
//        if (l >= r) return;
        if (r - l <= 47) {
            insertSort(a, l, r);
            return;
        }
        int pivot = basePartition(a, l, r);
        baseQuickSort(a, l, pivot - 1);
        baseQuickSort(a, pivot + 1, r);
    }

    public static int basePartition(int[] a, int l, int r) {
        int randomIndex = new Random().nextInt(r - l + 1) + l;
        swap(a, l, randomIndex);
        int pivot = a[l];
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

    public static void doubleQuickSort(int[] a, int l, int r) {
        if (r - l <= 47) {
            insertSort(a, l, r);
            return;
        }
        int pivot = doublePartition(a, l, r);
        doubleQuickSort(a, l, pivot - 1);
        doubleQuickSort(a, pivot + 1, r);

    }

    public static int doublePartition(int[] a, int l, int r) {
        int randomIndex = new Random().nextInt(r - l + 1) + l;
        swap(a, l, randomIndex);
        int pivot = a[l];
        // 定义：区间[l + 1, i)之间的元素都是小于等于pivot的，区间(j, r]之间的元素都是大于等于pivot的
        int i = l + 1;
        int j = r;
        while (true) {
            while (i <= j && a[i] < pivot) i++;
            while (i <= j && a[j] > pivot) j--;
            if (i > j) break;
            swap(a, i, j);
            i++;
            j--;
        }
        swap(a, l, j);
        return j;
    }

    public static void threeQuickSort(int[] a, int l, int r) {
        if (r - l <= 47) {
            insertSort(a, l, r);
            return;
        }
        int randomIndex = new Random().nextInt(r - l + 1) + l;
        swap(a, randomIndex, l);
        int pivot = a[l];
        // 定义：区间[l + 1, lt]之间的元素都是小于pivot的，区间[lt + 1, i)之间的元素都是等于pivot的，区间[gt,r]之间的元素都是大于pivot的
        int lt = l;
        int gt = r + 1;
        int i = l + 1;
        while (i < gt) {
            if (a[i] == pivot) {
                i++;
            } else if (a[i] < pivot) {
                lt++;
                swap(a, i, lt);
                i++;
            } else {
                gt--;
                swap(a, i, gt);
            }
        }
        swap(a, l, lt);
        lt--;
        threeQuickSort(a, l, lt);
        threeQuickSort(a, gt, r);
    }

    static class MaxHeap {
        private int[] data;
        private int size;
        private int capacity;

        public MaxHeap(int capacity) {
            this.capacity = capacity;
            this.data = new int[capacity + 1];
        }

        public void offer(int item) {
            assert (size + 1 <= capacity);
            size++;
            data[size] = item;
            shiftUp(size);
        }

        public int poll() {
            assert (size > 0);
            int res = data[1];
            swap(1, size);
            size--;
            shiftDown(1);
            return res;
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
                    j = k * 2;
                } else break;
            }
        }

        private void shiftUp(int k) {
            int parent = k / 2;
            while (parent >= 1 && data[k] > data[parent]) {
                swap(k, parent);
                k = parent;
                parent = k / 2;
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

        private void swap(int i, int j) {
            if (i == j) return;
            data[i] = data[i] ^ data[j];
            data[j] = data[i] ^ data[j];
            data[i] = data[i] ^ data[j];
        }
    }

    /**
     * 测试堆排序
     *
     * @param a
     */
    public static void testMaxHeapSort(int[] a) {
        System.out.println("排序前：" + Arrays.toString(a));
        MaxHeap maxHeap = new MaxHeap(a.length);
        for (int i = 0; i < a.length; i++) {
            maxHeap.offer(a[i]);
        }
        for (int i = a.length - 1; i >= 0; i--) {
            a[i] = maxHeap.poll();
        }
        System.out.println("排序后：" + Arrays.toString(a));
    }

    public static void heapSort(int[] a) {
        int n = a.length;
        heapify(a);
        for (int i = n - 1; i > 0; i--) {
            swap(a, i, 0);
            shiftDown(a, i, 0);
        }
    }

    private static void heapify(int[] a) {
        int parent = (a.length - 1) / 2;
        for (int i = parent; i >= 0; i--) {
            shiftDown(a, a.length, i);
        }
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

    public static void main(String[] args) {
//        int n = 100;
//        int min = 0;
//        int max = 100;
//        int[] a = generateRandomArray(n, min, max);
//        int[] temp = new int[a.length];
//        System.out.println("排序前：" + Arrays.toString(a));
//        heapSort(a);
//        System.out.println("排序后：" + Arrays.toString(a));
        
    }
}
