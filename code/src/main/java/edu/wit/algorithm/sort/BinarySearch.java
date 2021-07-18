package edu.wit.algorithm.sort;

public class BinarySearch {

    /**
     * 标准二分算法：在没有重复元素的数组中寻找目标元素
     * 
     * @param a
     * @param target
     * @return
     */
    public static int StandardBinarySearch(int[] a, int target) {
        int l = 0;
        int r = a.length - 1;
        while (l <= r) {
            int mid = l + ((r - l) >> 1);
            if (target == a[mid]) {
                return mid;
            } else if (target < a[mid]) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        System.out.println(StandardBinarySearch(a, 3));
    }

}
