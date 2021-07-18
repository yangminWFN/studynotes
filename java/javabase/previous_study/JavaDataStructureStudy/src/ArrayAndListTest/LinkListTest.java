package ArrayAndListTest;
class MyStack{
    private class Node{
        public String data;
        public Node next;
    }
    private Node top;
    private int N;
    public int size(){return N;}
    public boolean isEmpty(){return top==null;}
    public void push(String s){
        Node oldNode=top;
        top=new Node();
        top.data=s;
        top.next=oldNode;
        N++;
    }
    public String pop(){
        String s=top.data;
        top=top.next;
        N--;
        return s;
    }
}

class MyQueue{
    private class Node{
        public String data;
        public Node next;
    }
    private Node head;
    private Node tail;
    private int N;
    public int size(){return N;}
    public boolean isEmpty(){return N==0;}
    public void push(String s)
    {
        Node temp=new Node();
        temp.data=s;
        if(head==null)
        {
            head=temp;
        }
        if(tail==null)
        {
            tail=temp;
        }
        else
        {
            tail.next=temp;
            tail=temp;
        }
        N++;
    }
    public String pop()
    {
        String s=head.data;
        head=head.next;
        if(head==null)
        {
            tail=null;
        }
        N--;
        return s;
    }
}

public class LinkListTest {
    public static void main(String[] args)
    {
//        MyStack stack=new MyStack();
//        stack.push("yangmin");
//        System.out.println(stack.size());
//        stack.push("chenyu");
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.isEmpty());
//        MyQueue queue=new MyQueue();
//        queue.push("yangmin");
//        System.out.println(queue.size());
//        queue.push("chenyu");
//        System.out.println(queue.size());
//        System.out.println(queue.pop());
//        System.out.println(queue.pop());
//        System.out.println(queue.isEmpty());

        //栈的应用：10进制数转2进制
        MyStack stack=new MyStack();
        int n=13;
        while(n!=0)
        {
            stack.push(n%2+"");
            n=n/2;
        }
        while(!stack.isEmpty())
        {
            System.out.print(stack.pop());
        }
    }
}
