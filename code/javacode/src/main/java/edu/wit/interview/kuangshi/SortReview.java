package edu.wit.interview.kuangshi;

import java.util.Arrays;
import java.util.Random;


public class SortReview {

    static class MinHeap{
        int[] data;
        int capacity;
        int size;

        public MinHeap(int capacity){
            // 定义：data数组的0号下标不用，从 1 开始
            this.capacity = capacity;
            this.data = new int[capacity + 1];
            this.size = 0;
        }

        public void offer(int item){
            assert(size + 1 <= capacity);
            size++;
            data[size] = item;
            shiftUp(size);
        }

        private void shiftUp(int k){
            int parent = k / 2;
            while(parent >= 1 && data[parent] > data[k]){
                swap(parent, k);
                k = parent;
                parent = parent / 2;
            }
        }

        public int poll(){
            assert(size > 0);
            int res = data[1];
            swap(1, size);
            size--;
            shiftDown(1);
            return res;
        }

        private void shiftDown(int k){
            int j = k * 2;
            while(j <= size){
                if(j + 1 <= size && data[j+1] < data[j]){
                    j=j+1;
                }
                if(data[k] > data[j]){
                    swap(k, j);
                    k = j;
                    j = 2 * k;
                }else break;
            }
        }

        public boolean isEmpty(){
            return size == 0;
        }

        private void swap(int i,int j){
            if(i == j) return;
            data[i] = data[i] ^ data[j];
            data[j] = data[i] ^ data[j];
            data[i] = data[i] ^ data[j];
        }
    }

    public static void heapSort1(int[] a){
        MinHeap minHeap = new MinHeap(a.length);
        for (int i = 0; i < a.length; i++) {
            minHeap.offer(a[i]);
        }
        int index = 0;
        while(!minHeap.isEmpty()){
            a[index++] = minHeap.poll();
        }
    }

    public static void baseQuickSort(int[] a, int l, int r) {
        if (r - l <= 15) {
            // 使用插入排序优化
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
        // 定义[l+1,i]之间的元素都是小于等于pivot的，[i+1,j)之间的元素都是大于pivot的
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

    public static void threeQuickSort(int[] a, int l, int r) {
        if (r - l <= 15) {
            insertSort(a, l, r);
            return;
        }
        int randomIndex = new Random().nextInt(r - l + 1) + l;
        swap(a, l, randomIndex);
        int pivot = a[l];
        // 定义：区间[l+1,lt]之间的元素都是小于pivot的，区间[lt+1,i)之间的元素都是等于pivot的，区间[gt,r]之间的元素都是大于pivot的
        int lt = l;
        int i = l + 1;
        int gt = r + 1;
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
        swap(a, lt, l);
        lt--;
        threeQuickSort(a, l, lt);
        threeQuickSort(a, gt, r);
    }

    public static void mergeSort(int[] a, int l, int r, int[] temp) {
        if (r - l <= 15) {
            insertSort(a, l, r);
            return;
        }
        int mid = l + (r - l) / 2;
        mergeSort(a, l, mid, temp);
        mergeSort(a, mid + 1, r, temp);
        if (a[mid] <= a[mid + 1]) {
            return;
        }
        mergeTwoOrderedArray(a, l, mid, r, temp);
    }

    public static void mergeTwoOrderedArray(int[] a, int l, int mid, int r, int[] temp) {
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

    public static void mergeSort2(int[] a, int[] temp) {
        int n = a.length;
        for (int size = 1; size <= n; size = size << 1) {
            // 每次对左右两个区间 [i,i + size - 1] [i + size, i + 2 * size - 1]
            for (int i = 0; i + size < n; i = i + 2 * size) {
                mergeTwoOrderedArray(a, i, i + size - 1, Math.min(i + 2 * size - 1, n - 1), temp);
            }
        }
    }

    public static void insertSort(int[] a, int l, int r) {
        if (l >= r)
            return;
        for (int i = l + 1; i <= r; i++) {
            // 先把这个值拿出来，然后只要前面的一个数比它大那么就将前一个数填到当前位置
            int e = a[i];
            int j;
            for (j = i; j > l && a[j - 1] > e; j--) {
                a[j] = a[j - 1];
            }
            a[j] = e;
        }
    }

    private static void swap(int[] a, int i, int j) {
        if (i == j)
            return;
        a[i] = a[i] ^ a[j];
        a[j] = a[i] ^ a[j];
        a[i] = a[i] ^ a[j];
    }

    private static int[] generateRandomArray(int n, int min, int max) {
        Random r = new Random();
        return r.ints(min, max).limit(n).toArray();
    }

    public static void main(String[] args) {
        int[] a = generateRandomArray(100, 0, 100);

        // int[] a = new int[] { 10, 7, 8, 9, 1, 2, 5, 4, 3 };
        int[] temp = new int[a.length];
        System.out.println("排序前：" + Arrays.toString(a));
        heapSort1(a);
        System.out.println("排序后：" + Arrays.toString(a));

    }
}