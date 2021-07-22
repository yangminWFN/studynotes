# [剑指 Offer 62. 圆圈中最后剩下的数字](https://leetcode-cn.com/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/)

```java
//迭代
class Solution {
    public int lastRemaining(int n, int m) {
        if(n == 1) return 0;
        else{
            return (lastRemaining(n - 1, m) + m) % n;
        } 
    }
}

//迭代
class Solution {
    public int lastRemaining(int n, int m) {
        int f = 0;
        for(int i = 2; i <= n; i++){
            f = (f + m) % i;
        }
        return f;
    }
}
```

详解见约瑟夫环问题