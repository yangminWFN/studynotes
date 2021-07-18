package multi_thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

public class LockSupportTest {
    public static void main(String[] args) {
        List list = Arrays.asList(new int[] { 1, 2, 3 }, new int[] { 4, 5, 6 });
        System.out.println(list.size());
        List list2 = Arrays.asList(1, 2, 3, 4, 5, 6);
        System.out.println(list2.size());
        ArrayList<Integer> list3 = new ArrayList<Integer>(list2);
        System.out.println(list3.size());
        Integer[] list4 = list3.toArray(new Integer[0]);
        System.out.println(Arrays.toString(list4));
    }

}
