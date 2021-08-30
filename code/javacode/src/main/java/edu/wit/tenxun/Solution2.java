package edu.wit.tenxun;

import java.util.*;

public class Solution2 {
    public static int solve(int[] a) {
        Arrays.sort(a);
        long ans = 0;
        long add = (int) Math.pow(2,a.length - 1);
        for (int i = a.length - 1; i >= 0; i--) {
            ans += a[i] * add;
            add = add >> 1;
        }
        return (int)(ans % 1000000007);
    }

    public static int[] generate(){
        Random r = new Random();
        return r.ints(0,10).limit(10).toArray();
    }

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int groupCount = scanner.nextInt();
//        List<int[]> inputs = new ArrayList<>();
//        for (int i = 0; i < groupCount; i++) {
//            int len = scanner.nextInt();
//            int[] temp = new int[len];
//            for (int j = 0; j < len; j++) {
//                temp[j] = scanner.nextInt();
//            }
//            inputs.add(temp);
//        }
//        for(int i = 0; i < inputs.size(); i++){
//            System.out.println(solve(inputs.get(i)));
//        }

        long start = System.currentTimeMillis();
        System.out.println(solve(generate()));
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0);
    }
}
