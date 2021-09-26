package edu.wit.interview.ali;

import java.util.HashMap;
import java.util.Scanner;
public class Solution2 {

    static int P = (int)(Math.pow(10,9) + 7);

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int T = Integer.parseInt(cin.nextLine());
        while(T > 0){
            String[] params = cin.nextLine().split(" ");
            int N = Integer.parseInt(params[0]);
            int K = Integer.parseInt(params[1]);
            if(N < K){
                System.out.println(0);
                T--;
                continue;
            }
            HashMap<Integer,Integer> map = new HashMap<>();
            long result = N - K;
            for (int i = K + 1; i <= N; i++) {
                int temp = i - K;
                if(i % temp == K){
                    if(map.containsKey(temp)){
                        result += map.get(temp);
                        continue;
                    }
                    int tempResult = 1;
                    for (int j = K + 1; i < Math.sqrt(temp) + 1; j++) {
                        if(temp % j == 0){
                            int m = temp / j;
                            if(m > K){
                                tempResult += 2;
                            }else{
                                tempResult += 1;
                            }
                        }
                    }
                    map.put(temp,tempResult);
                    result += tempResult;
                }
            }
            System.out.println(result);
            T--;
        }
    }
}
