package edu.wit.algorithm.sort;

import java.util.Arrays;

public class MaxHeap {
    // 堆中存放的数据，数组下标从 1 开始
    private int[] data;
    // 堆中元素的个数
    private int count;
    // 堆的容量
    private int capacity;

    /**
     * 将第 k 个元素进行上浮操作
     * 
     * @param k
     */
    private void shiftUp(int k) {
        int parent = k / 2;
        while (parent > 0) {
            if (data[k] > data[parent]) {
                ArrayUtil.swap(data, k, parent);
            } else {
                break;
            }
            k = parent;
            parent = parent / 2;
        }
    }

    /**
     * 将第 k 个元素进行下沉操作
     * 
     * @param k
     */
    private void shiftDown(int k) {
        int leftChild = k * 2;
        int rightChild = k * 2 + 1;
        while (leftChild <= count) {
            //取左右孩子节点中更小的元素下标赋给左孩子节点
            if (rightChild <= count && data[rightChild] > data[leftChild]) {
                leftChild = rightChild;
            }
            if (data[leftChild] > data[k]) {
                ArrayUtil.swap(data, leftChild, k);
                k = leftChild;
                leftChild = k * 2;
                rightChild = k * 2 + 1;
            } else {
                break;
            }
        }
    }

    public MaxHeap(int capacity) {
        this.capacity = capacity;
        count = 0;
        data = new int[capacity + 1];
    }

    /**
     * 元素插入优先队列中：放到数组最后一个位置，然后使用上浮操作维持堆结构
     * 
     * @param item
     */
    public void offer(int item) {
        assert (count + 1 <= capacity);
        count++;
        data[count] = item;
        shiftUp(count);
    }
    
    /**
     * 出队：返回堆顶元素
     */
    public int poll() {
        assert (count > 0);
        int root = data[1];
        ArrayUtil.swap(data, count, 1);
        count--;
        shiftDown(1);
        return root;
    }

    public void print() {
        System.out.println(Arrays.toString(data));
    }



    public static void main(String[] args) {
        int n = 10;
        MaxHeap maxHeap = new MaxHeap(n);
        int[] a = ArrayUtil.generatorRandomArray(n, 0, 100);
        System.out.println(Arrays.toString(a));
        for (int i = 0; i <= a.length - 1; i++) {
            maxHeap.offer(a[i]);
        }
        maxHeap.print();
        for (int i = 0; i <= a.length - 1; i++) {
            System.out.print(maxHeap.poll()+" ");
        }

        System.out.println("");
        // PriorityQueue<Integer> queue = new PriorityQueue<>(Arrays.stream(a).boxed().collect(Collectors.toList()));
        // while(!queue.isEmpty()){
        //     System.out.print(queue.poll()+" "); 
        // }
        
    }
}
