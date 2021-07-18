package FileStudy;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

public class DirListTest {

    public static FilenameFilter filter(final String regex)
    {
        return new FilenameFilter() {
            private Pattern pattern=Pattern.compile(regex);
            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        };
    }

    public static void main(String[] args)
    {
        String regex="\\w+\\.txt";
        // .代表项目的根目录
        File path=new File("./TestFiles");
        for(String p:path.list(filter(regex)))
        {
            System.out.println(p);
        }
        File[] files= path.listFiles();
        for(File file:files)
        {
            System.out.println(file.getName());
        }
        System.out.println(System.getProperty("user.dir"));
    }
}
