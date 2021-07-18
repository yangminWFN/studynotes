package AlgorithmTasks;

public class Task_1 {

    public static int f(int n)
    {
        if(n<=0) return 0;
        else return f(n-1)+(int)(2*Math.pow(10,n-1));
    }
    public static int sum( int n)
    {
        int sum=0;
        for(int i=1;i<=n;i++)
        {
            sum+=f(i);
        }
        return sum;
    }

    public static int f2(int n)
    {
        int sum=0;
        int temp=0;
        for(int i=0;i<n;i++)
        {
            temp+=2*(int)Math.pow(10,i);
            sum+=temp;
        }
        return sum;
    }

    public static int divide(int n,int m)
    {
        if(n==1||m==1) return 1;
        else
        {
            if(m==n) return 1+divide(n,m-1);
            if(m<n) return divide(n,m-1)+divide(n-m,m);
            else return divide(n,n);
        }
    }



    public static void main(String[] args)
    {
        System.out.println(sum(3));
        System.out.println(f2(3));
        System.out.println(divide(4,4));
    }
}
