package edu.wit.algorithm.sort;

import java.util.Objects;
import java.util.Scanner;

public class Input {
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        // int[] a = new int[3];
        // for (int i = 0; i < a.length; i++) {
        // a[i] = scanner.nextInt();
        // }
        // System.out.println("新输入的"+scanner.nextInt());
        // scanner.close();
        // System.out.println("结束了:"+Arrays.toString(a));

        // Scanner scanner = new Scanner(System.in);
        // String[] names = new String[3];
        // for(int i = 0; i < names.length; i++){
        // names[i] = scanner.next();
        // }
        // System.out.println("names:" + Arrays.toString(names));
        // String str = scanner.next();
        // System.out.println("另外输入的字符串为：" + str);
        // scanner.close();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String str = scanner.next();
            if (Objects.equals(str, ""))
                break;
            System.out.println("输入的字符串为：" + str);

        }
        scanner.close();
        System.out.println("结束");

    }
}
