package edu.wit.interview.tenxun2;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Solution2 {
    static HashMap<Integer,Integer> map = new HashMap<>();

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int N = Integer.parseInt(cin.nextLine());
        String[] paramsa = cin.nextLine().split(" ");
        String[] paramsb = cin.nextLine().split(" ");
        int[] a = new int[N];
        int[] b = new int[N];
        for (int i = 0; i < N; i++) {
            int tempA = Integer.parseInt(paramsa[i]);
            a[i] = yinzi(tempA);
            int tempB = Integer.parseInt(paramsb[i]);
            b[i] = yinzi(tempB);
        }
        Arrays.sort(a);
        Arrays.sort(b);

        int count = 0;
        int l =0, r = 0;
        while(r <N && l < N){
            while(l < N && a[l] <= b[r]){
                l++;
            }
            if(l < N){
                count++;
                r++;
            }
        }
        System.out.println(count);
    }

    public static int findMinBetter(int[] a, int target){
        int l = 0, r = a.length - 1;
        while(l < r){
            int mid = l + ((r - l) >> 1);
            if(a[mid] <= target){
                l = mid + 1;
            }else{
                r = mid;
            }
        }
        if(r == a.length - 1 && a[r] <= target){
            return -1;
        }
        return r;
    }

    public static int yinzi(int a){
        if(map.containsKey(a)){
            return map.get(a);
        }
        int max = (int)Math.sqrt(a);
        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= max; i++) {
            if(a % i == 0){
                int temp = a / i;
                set.add(i);
                set.add(temp);
            }
        }
        map.put(a,set.size());
        return set.size();
    }
}
