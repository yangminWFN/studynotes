import javax.swing.*;
import java.util.Arrays;
import java.util.Date;


public class CloneTest{



    public static void main(String[] args)
    {
        final int[] counter=new int[1];
        Date[] dates=new Date[100];
        for(int i=0;i<100;i++)
        {
            dates[i]=new Date(){
                @Override
                public int compareTo(Date anotherDate) {
                    counter[0]++;
                    return super.compareTo(anotherDate);
                }
            };
        }
        Arrays.sort(dates);
        System.out.println(counter[0]);
    }
}
