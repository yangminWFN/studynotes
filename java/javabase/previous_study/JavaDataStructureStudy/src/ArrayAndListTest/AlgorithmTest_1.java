package ArrayAndListTest;

import java.util.Arrays;

public class AlgorithmTest_1 {
    public static int myBinarySerach(int key,int[] a){
        int low=0;
        int high=a.length-1;
        while(low<=high)
        {
            int mid=(low+high)/2;
            if(key>a[mid]) low=mid+1;
            else if(key<a[mid]) high=mid-1;
            else return mid;
        }
        return -1;
    }

    public static int count(int[] a)
    {
        //求数组中相反数的对数
        int count=0;
        Arrays.sort(a);
        for(int i=0;i<a.length;i++)
        {
            if(myBinarySerach(-a[i],a)>i)
            {
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args)
    {
        long start,end;
        int[] a=new int[2000000];
        start=System.currentTimeMillis();
        for(int i=1;i<=1000000;i++)
        {
            a[i-1]=i;
            a[i-1+1000000]=-i;
        }
        int count=count(a);
        end=System.currentTimeMillis();
        System.out.println(count+"花费时间："+(end-start));
    }
}
