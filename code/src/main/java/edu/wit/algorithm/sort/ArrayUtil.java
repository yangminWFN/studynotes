package edu.wit.algorithm.sort;
import java.util.Random;

/**
 * 数组操作工具类
 */
public class ArrayUtil {
    /**
     * 生成 n 个元素的在 [min,max)区间的随机数组
     * 
     * @param n   元素个数
     * @param min 最小值
     * @param max 最大值（不在内）
     * @return 包含 n 个元素的在 [min,max)区间的随机数组
     */
    public static int[] generatorRandomArray(int n, int min, int max) {
        return new Random().ints(min, max).limit(n).toArray();
    }

    /**
     * 交换数组中两个索引位置的值
     * 
     * @param a 数组
     * @param i 下标1
     * @param j 下标2
     */
    public static void swap(int[] a, int i, int j) {
        int t = a[i];
        a[i] = a[j];
        a[j] = t;
    }

    /**
     * 拷贝数组
     * 
     * @param a 源数组
     * @return 拷贝后得到的数组
     */
    public static int[] copy_array(int[] a) {
        int[] b = new int[a.length];
        System.arraycopy(a, 0, b, 0, a.length);
        return b;
    }
}
