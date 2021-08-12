package edu.wit.javabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTest {
    public static void main(String[] args) {
        Pattern pattern = Pattern.compile("\\d{4}");
        Matcher matcher = pattern.matcher("1024");
        boolean b = matcher.matches();
        System.out.println(b);
        int c = matcher.groupCount();
        System.out.println(c);

        boolean flag = Pattern.matches("\\d+","1021212");
        System.out.println(flag);
    }
}