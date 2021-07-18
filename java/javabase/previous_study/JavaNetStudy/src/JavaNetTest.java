import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

class Person implements Serializable{
    public String name;
}
public class JavaNetTest {
    public static void main(String[] args) throws MalformedURLException, IOException,ClassNotFoundException
    {
        Person p1=new Person();
        p1.name="yangmin";
        ByteArrayOutputStream bout=new ByteArrayOutputStream();
        ObjectOutputStream oout=new ObjectOutputStream(bout);
        oout.writeObject(p1);
        oout.close();
        ByteArrayInputStream bin=new ByteArrayInputStream(bout.toByteArray());
        ObjectInputStream oin=new ObjectInputStream(bin);
        Person p2=(Person)oin.readObject();
        p1.name="chenyu";
        System.out.println(p2.name);
        oin.close();
    }
}
