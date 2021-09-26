package edu.wit.interview.webank;

import java.time.LocalDate;
import java.util.Scanner;

public class Solution3 {

    public static void main(String[] args) {
        Scanner cin = new Scanner(System.in);
        int a = cin.nextInt();
        int b = cin.nextInt();
        int ans = 0;
        for(int i = a; i <= b; i++){
            for(int j = 1; j <= 12; j++){
                LocalDate date = LocalDate.of(i,j,1);
                if(date.getDayOfWeek().getValue() == 1){
                    ans++;
                }
            }
        }
        System.out.println(ans);
    }
}
