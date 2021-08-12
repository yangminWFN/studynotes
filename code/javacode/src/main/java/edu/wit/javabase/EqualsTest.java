package edu.wit.javabase;

class Employee {
    private String name;
    private int age;

    public Employee(String name, int age){
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        Employee other = (Employee) obj;
        return (name == null ? name == other.name : name.equals(other.name)) && age == other.age;
    }

}

public class EqualsTest {
    public static void main(String[] args) {
        Employee e1 = new Employee(null, 19);
        Employee e2 = new Employee(null, 19);
        System.out.println(e1.equals(e2));
    }
}