package edu.wit.interview.tenxun2;

public class Solution4 {

    public static String getNum(int n) {
        if (n == 0)
            return "0";
        if (n == 1)
            return "1";
        if (n == 2)
            return "101";
        if (n == 3)
            return "111";
        int t = n / 2;
        String str = getNum(t);
        return str + (n & 1) + str;
    }

    public static String getNumDp(int n) {
        String[] dp = new String[n + 1];
        dp[0] = "0";
        dp[1] = "1";
        dp[2] = "101";
        dp[3] = "111";
        for (int i = 4; i <= n; i++) {
            int t = (i >> 1);
            dp[i] = dp[t] + (i & 1) + dp[t];
        }
        return dp[n];
    }

    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);
        // int n = scanner.nextInt();
        // int l = scanner.nextInt() - 1;
        // int r = scanner.nextInt() - 1;
        // String list = getNumDp(n);
        // System.out.println(list);
        // int count = 0;
        // for (int i = l; i <= r; i++) {
        //     if (list.charAt(i) == '1') {
        //         count++;
        //     }
        // }
        // System.out.println(count);
        
    }
}
