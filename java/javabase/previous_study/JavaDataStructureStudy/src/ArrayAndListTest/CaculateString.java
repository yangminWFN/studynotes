package ArrayAndListTest;
import java.security.InvalidParameterException;
import java.util.Stack;

public class CaculateString {
    public static int caculate(String s)
    {
        Stack<Integer> dataStack=new Stack<Integer>();
        Stack<String> opStack=new Stack<String>();
        char[] charArr=s.toCharArray();
        for(int i=0;i<charArr.length;i++)
        {
            String temp=Character.toString(charArr[i]);
            if("0123456789".contains(temp))
            {
                dataStack.push(Integer.parseInt(temp));
            }
            else if("+-*/".contains(temp))
            {
                opStack.push(temp);
            }
            else if(temp.equals(")"))
            {
                String c=opStack.pop();
                int second=dataStack.pop();
                int first=dataStack.pop();
                switch (c)
                {
                    case "+":dataStack.push(first+second);break;
                    case "-":dataStack.push(first-second);break;
                    case "*":dataStack.push(first*second);break;
                    case "/":dataStack.push(first/second);break;
                }
            }
        }
        return dataStack.pop();
    }

    public static void main(String[] args){
        String s="((1+((3*4)+(2*3))/2))";
        int sum=caculate(s);
        System.out.println(sum);
    }
}
