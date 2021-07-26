package edu.wit.algorithm.sort;

class BaseSort {
  
  // 冒泡排序
  public static void bubble_sort(int[] a, int n) {
    for (int i = 0; i < n - 1; i++) {
      boolean changed = false;
      for (int j = 0; j < n - i - 1; j++) {
        if (a[j] > a[j + 1]) {
          ArrayUtil.swap(a, j, j + 1);
          changed = true;
        }
      }
      if (!changed)
        break;
    }
  }

  // 选择排序
  public static void select_sort(int[] a, int n) {
    for (int i = 0; i < n; i++) {
      int minIndex = i;
      for (int j = i + 1; j < n; j++) {
        if (a[j] < a[minIndex]) {
          minIndex = j;
        }
      }
      ArrayUtil.swap(a, i, minIndex);
    }
  }

  // 插入排序优化前:相邻两个元素不断交换，直到找到合适的位置
  public static void insert_sort_before(int[] a, int n) {
    for (int i = 1; i < n; i++) {
      for (int j = i; j > 0 && a[j] < a[j - 1]; j--) {
        ArrayUtil.swap(a, j - 1, j);
      }
    }
  }

  // 插入排序优化后:相邻两个元素不断比较并覆盖，先找到合适的位置再进行赋值
  public static void insert_sort_after(int[] a, int n) {
    for (int i = 1; i < n; i++) {
      int temp = a[i];
      // j 表示当前元素要插入的位置
      int j;
      for (j = i; j > 0 && temp < a[j - 1]; j--) {
        a[j] = a[j - 1];
      }
      a[j] = temp;
    }
  }
  
  public static void main(String[] args) {
    int n = 100000;
    int[] a = ArrayUtil.generatorRandomArray(n, 0, 20);
    int[] b = ArrayUtil.copy_array(a);
    long startTime = System.currentTimeMillis();
    insert_sort_before(a, a.length);
    long endTime = System.currentTimeMillis();
    // System.out.println(Arrays.toString(a));
    System.out.println("耗费时间为：" + (endTime - startTime) / 1000.0 + " s");

    long startTime1 = System.currentTimeMillis();
    insert_sort_after(b, b.length);
    long endTime1 = System.currentTimeMillis();
    System.out.println("耗费时间为：" + (endTime1 - startTime1) / 1000.0 + " s");
  }
}
