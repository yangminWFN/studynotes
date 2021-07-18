import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JavaRegexTest {
    public static void main(String[] args)
    {
//        String testStr="this is my  1st test 2st string";
        String testStr="1st d1";
        String regexStr="(\\d\\w+)(\\s\\w)";
        Pattern p=Pattern.compile(regexStr);
        Matcher matcher=p.matcher(testStr);
//        if(matcher.find())
//        {
//            String matchedText=matcher.group();
//            int start=matcher.start();
//            int end=matcher.end();
//            System.out.println("matchedText:"+matchedText+",from:"+start+" to "+end);
//            System.out.println(matcher.find());
//            String matchedText2=matcher.group();
//            int start_2=matcher.start();
//            int end_2=matcher.end();
//            System.out.println("matchedText_2:"+matchedText2+",from:"+start_2+" to "+end_2);
//        }
//        else
//        {
//            System.out.println("not matched");
//        }
//        System.out.println(p);
//        while(matcher.find())
//        {
//            System.out.println(matcher.group());
//        }
//        matcher.reset();
//        System.out.println(matcher.replaceFirst("yangmin"));
//        while(matcher.find())
//        {
//            System.out.println(matcher.group());
//        }
//        System.out.println(matcher.find());
//        matcher.region(0,testStr.length()-1);
        System.out.println(matcher.matches());
        System.out.println(matcher.find());
        System.out.println(matcher.regionStart());
        System.out.println(matcher.regionEnd());


    }
}
//调用matcher的replaceAll或者replaceFirst方法都会导致内部的游标变化，过程就像先调用matcher.find()然后再替换。
//matcher.region(0,testStr.length()-1);matcher.matches();可以测试在给定范围内的字符串是否完全匹配正则表达式；若匹配则为true
//如果调用matcher.matches()，不管返回是否为true，都会将游标指向最末尾的位置。所以在后面再调用find方法就总会返回false了