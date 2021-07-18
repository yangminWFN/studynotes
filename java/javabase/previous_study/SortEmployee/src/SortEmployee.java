import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class Employee implements Comparable<Employee>{
    private String name;
    private Double salary;
    public String getName(){
        return this.name;
    }
    public Double getSalary(){
        return this.salary;
    }
    public Employee(String name,Double salary){
        this.name=name;
        this.salary=salary;
    }

    @Override
    public int compareTo(Employee obj)
    {
        if(this.salary>obj.getSalary()) return 1;
        if(this.salary<obj.getSalary()) return -1;
        return 0;
    }

    @Override
    public String toString(){
        return this.name+":"+this.salary+" ";
    }
}

public class SortEmployee {
    public static void main(String[] args)
    {
        Employee[] staff=new Employee[3];
        staff[0]=new Employee("yangmin",500.0);
        staff[1]=new Employee("cc",400.0);
        staff[2]=new Employee("cy",600.0);
        System.out.println("未排序前");
        for(Employee e:staff)
        {
            System.out.print(e.toString());
        }
        System.out.println("排序后");
        Arrays.sort(staff);
        for(Employee e:staff)
        {
            System.out.print(e.toString());
        }

    }
}
