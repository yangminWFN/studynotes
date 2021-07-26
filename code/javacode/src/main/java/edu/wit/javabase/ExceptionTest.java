package edu.wit.javabase;
public class ExceptionTest {

    public static String say(){
        try {
            System.out.println("我在try中");
            // int i = 1 / 0;
            return "try";
            
        } catch (Exception e) {
            //TODO: handle exception
            System.out.println("我在catch中");
            return "catch";
        }finally{
            System.out.println("我在finally中");
            // return "finally";     
        }
    }

    public static void NPETest(){
        try {
            Object obj = new Object();
            obj = null;
            obj.getClass();
        } catch (NullPointerException e) {
            //TODO: handle exception
            System.out.println("null pointer");
        }catch (Exception e) {
            //TODO: handle exception
            System.out.println("name");
        }
    }

    public static void main(String[] args) {
        System.out.println(say());        
    }
}
