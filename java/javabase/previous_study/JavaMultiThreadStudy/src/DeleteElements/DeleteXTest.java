package DeleteElements;

import java.util.Arrays;

public class DeleteXTest {
    public static int deleteX(int[] arr,int x){
        int i,k=0;
        for(i=0;i<arr.length;i++)
        {
            if(arr[i]!=x)
            {
                arr[k++]=arr[i];
            }
        }
        return k;
    }

    public static void main(String[] args)
    {
        int[] a=new int[]{1,1,3,4,5,6,7,8,5,43,5,6,7};
        int len=deleteX(a,5);
        for(int i=0;i<len;i++)
        {
            System.out.print(a[i]+" ");
        }
    }
}
