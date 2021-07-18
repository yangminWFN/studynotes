package ArrayAndListTest;


public class ArrayAndListTest {

    public static int binarySearch(int key,int[] a)
    {
        int low=0,high=a.length-1;
        while(low<=high)
        {
            int mid=(low+high)/2;
            if(key<a[mid]) high=mid-1;
            else if(key>a[mid]) low=mid+1;
            else return mid;
        }
        return -1;
    }

    public static int dgBinarySearch(int key,int[] a,int low,int high)
    {
        if(low>high) return -1;
        int mid=(low+high)/2;
        if(key<a[mid]) return dgBinarySearch(key,a,low,mid-1);
        else if(key>a[mid]) return dgBinarySearch(key,a,mid+1,high);
        else return mid;
    }

    public static void main(String[] args)
    {
        int[] a={0,1,2,3,4,5,6,7,8,9};
        int index=dgBinarySearch(3,a,0,a.length-1);
        System.out.println(index);
    }
}
