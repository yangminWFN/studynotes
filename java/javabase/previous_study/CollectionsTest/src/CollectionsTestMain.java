import oracle.jrockit.jfr.StringConstantPool;

import javax.swing.*;
import java.util.*;
enum Weekday{
    monday,tuesday,wedesday,thursday,friday
}

class Person{
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;
    private String name;

    public Person(String name,int age)
    {
        this.name=name;
        this.age=age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return age == person.age &&
                Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(age, name);
    }

    @Override
    public String toString() {
        return "name: "+name+",age: "+age;
    }

//    @Override
//    public int compareTo(Person o) {
//        if(age>o.getAge()) return 1;
//        if(age<o.getAge()) return -1;
//        return 0;
//    }
}

public class CollectionsTestMain {
    public static void main(String[] args) {
        listTest();
    }
    public static void listTest()
    {
//       List<String> list=new LinkedList<String>();
//       list.add("yangmin");
//       list.add("cc");
//       list.add("cjh");
//       System.out.println(list.toString());
//       ListIterator<String> listIterator=list.listIterator();
//       listIterator.next();
//       listIterator.remove();
//       listIterator.add("我在第二个");
//       listIterator.add("我在第三个");
//
//        System.out.println(list.toString());
//        List<Integer> list=new LinkedList<Integer>(Arrays.asList(1,3,2,4,6,8,6,7,0,9,2,5,2,8,9));
//        System.out.println(list);
//        //使用listIterator在每个元素值为2的前面添加一个1000
//        ListIterator<Integer> listIterator=list.listIterator();
//        while(listIterator.hasNext()) listIterator.next();
//        while(listIterator.hasPrevious())
//        {
//            if(listIterator.previous()==2)
//            {
//                listIterator.add(1000);
//            }
//        }
//        System.out.println(list);
//        List<String> list=new LinkedList<String>(Arrays.asList("yangmin","hh","cy"));
//        Iterator<String> iterator_1=list.iterator();
//        Iterator<String> iterator_2=list.iterator();
//        iterator_1.next();
////        iterator_1.remove();
//        System.out.println(list);
//        iterator_2.next();
//        System.out.println(list);

//        List<String> list=new LinkedList<String>(Arrays.asList("yangmin","hh","cy"));
//        ListIterator<String> listIterator=list.listIterator();
//        list.add("zgh");
//        listIterator.next();

//        List<String> list=new LinkedList<String>(Arrays.asList("yangmin","hh","cy"));
//        ListIterator<String> listIterator_1=list.listIterator();
//        ListIterator<String> listIterator_2=list.listIterator();
//        listIterator_1.next();
//        listIterator_1.set("yangmin_1");
//        System.out.println(list);
//        listIterator_2.next();
//        listIterator_2.set("yangmin_2");
//        System.out.println(list);

//        List<String> list=new LinkedList<String>(Arrays.asList("yangmin","hh","cy"));
//        ListIterator<String> listIterator=list.listIterator(list.size());
//        System.out.println(listIterator.nextIndex());
//        System.out.println(listIterator.previousIndex());
//        while(listIterator.hasPrevious())
//        {
//            System.out.print(listIterator.previous()+" ");
//        }

//        System.out.println("yangmin's hashCode: "+"yangmin".hashCode());
//        System.out.println("cy's hashCode: "+"cy".hashCode());
//        SortedSet<Integer> sortedSet=new TreeSet<Integer>();
//        sortedSet.add(2);
//        sortedSet.add(3);
//        sortedSet.add(1);
//        System.out.println(sortedSet);
//
//        SortedSet<Person> personSortedSet=new TreeSet<Person>(new Comparator<Person>(){
//            @Override
//            public int compare(Person o1, Person o2) {
//                if(o1.getName().compareTo(o2.getName())>0) return 1;
//                if(o1.getName().compareTo(o2.getName())<0) return -1;
//                return 0;
//            }
//        });
//        personSortedSet.add(new Person("A",19));
//        personSortedSet.add(new Person("C",20));
//        personSortedSet.add(new Person("B",30));
//        personSortedSet.add(new Person("D",25));
//        System.out.println(personSortedSet);

//        ArrayDeque<Integer> integerArrayDeque=new ArrayDeque<>();
//        integerArrayDeque.offerFirst(1);
//        integerArrayDeque.offer(2);
//        integerArrayDeque.offer(3);
//        System.out.println(integerArrayDeque);
//        integerArrayDeque.poll();
//        System.out.println(integerArrayDeque);
//        integerArrayDeque.peek();
//        System.out.println(integerArrayDeque);

//        PriorityQueue<Integer> pq=new PriorityQueue<Integer>();
//        pq.offer(6);
//        pq.offer(2);
//        pq.offer(10);
//        pq.offer(7);
//        pq.offer(8);
//        pq.offer(20);
//        System.out.println(pq);
//        System.out.println(pq.poll());
//        System.out.println(pq.poll());
//        System.out.println(pq.poll());
//        System.out.println(pq);
//        Map<String,Person> map=new HashMap<String,Person>();
//        map.put("yangmin",new Person("yangmin",19));
//        System.out.println( map.put("zgh",new Person("ZGH",23)));
//        map.put("lx",new Person("LX",26));
//        map.put("cc",new Person("CC",19));
//        System.out.println(map);
//        System.out.println("KeySet:");
//        for(String key:map.keySet())
//        {
//            System.out.print(" "+key);
//        }
//        System.out.println();
//        System.out.println("ValueCollection:");
//        for(Person p:map.values())
//        {
//            System.out.print(p.toString()+" ");
//        }
//        for(Map.Entry<String,Person> entry:map.entrySet())
//        {
//            String key=entry.getKey();
//            Person p=entry.getValue();
//            System.out.println("Key:"+key+",Value: "+p.toString());
//            System.out.println(entry.getClass().getName());
//        }
//        Map<String,Person> cache=new LinkedHashMap<String,Person>(100,0.75F,true){
//            @Override
//            protected boolean removeEldestEntry(Map.Entry<String,Person> entry)
//            {
//                return size()>3;
//            }
//        };
//        cache.put("1",new Person("1",1));
//        cache.put("2",new Person("2",2));
//        cache.put("3",new Person("3",3));
//        System.out.println(cache);
//        cache.put("4",new Person("4",4));
//        System.out.println(cache);
//        System.out.println(Weekday.monday.ordinal());
//        System.out.println(Weekday.values()[0]);
//        EnumMap<Weekday,Person> enumMap=new EnumMap<Weekday,Person>(Weekday.class);
//        enumMap.put(Weekday.monday,new Person("周一",15));
//        enumMap.put(Weekday.friday,new Person("周五",16));
//        System.out.println(enumMap);
//
//        EnumSet<Weekday> enumSet=EnumSet.allOf(Weekday.class);
//        for(Weekday k:enumSet)
//        {
//            System.out.print(" "+k);
//        }
//        enumSet=EnumSet.range(Weekday.monday,Weekday.wedesday);
//        for(Weekday k:enumSet)
//        {
//            System.out.print(" "+k);
//        }
//        enumSet=EnumSet.of(Weekday.wedesday,Weekday.friday);
//        System.out.println(enumSet);
//        System.out.println(new ArrayList<>() instanceof RandomAccess);
        Set<String> strings=new HashSet<String>(Arrays.asList("yangmin","cc","cjh"));
        String[] arr=strings.toArray(new String[10]);
        for(String a:arr)
        {
            System.out.print(" "+a);
        }
        System.out.println();
        System.out.println(arr.length);



        
    }
}
