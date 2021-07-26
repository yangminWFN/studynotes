# [剑指 Offer 63. 股票的最大利润](https://leetcode-cn.com/problems/gu-piao-de-zui-da-li-run-lcof/)

```java
class Solution {
    public int maxProfit(int[] prices) {
        //使用贪心算法求解问题
        if(prices == null || prices.length <= 1) return 0;
        int minPrice = prices[0];
        int res = 0;
        for(int i = 1; i < prices.length; i++){
            if(prices[i] < minPrice){
                minPrice = prices[i];
            }else{
                res = Math.max(prices[i] - minPrice, res);
            }
        }
        return res;
    }
}
```

时间复杂度：O(n)，空间复杂度O(1);