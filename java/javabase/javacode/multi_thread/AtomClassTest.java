package multi_thread;

import java.util.concurrent.atomic.AtomicStampedReference;

//原子更新基本类型:AtomicBoolean,AtomicInteger,AtomicLong
//原子更新数组里的元素：AtomicIntegerArray,AtomicLongArray,AtomicReferenceArray
//原子更新引用类型：AtomicReference,AtomicStampedReference,AtomicMarkableReference
//原子更新字段类 AtomicIntegerFieldUpdater: AtomicLongFieldUpdater: AtomicStampedFieldUpdater: AtomicReferenceFieldUpdater

/**
 * 使用AtomicStampedRefrence解决ABA问题
 */
public class AtomClassTest {

    private static AtomicStampedReference<Integer> atomicStampedRef = new AtomicStampedReference<>(1, 0);

    public static void main(String[] args) {
        Thread main = new Thread(() -> {
            System.out.println("主线程的初始值为：" + atomicStampedRef.getReference());
            int stamp = atomicStampedRef.getStamp(); // 获取当前标识别
            System.out.println("主线程的stamp为：" + stamp);
            try {
                Thread.sleep(1000); // 等待1秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            boolean isCASSuccess = atomicStampedRef.compareAndSet(1, 2, stamp, stamp + 1); // 此时expectedReference未发生改变，但是stamp已经被修改了,所以CAS失败
            System.out.println("主线程线程的CAS操作结果: " + isCASSuccess);
        });

        Thread other = new Thread(() -> {
            try {
                Thread.sleep(200); // 等待1秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            atomicStampedRef.compareAndSet(1, 2, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            System.out.println("其他操作线程第一次修改后的ref值为：" +atomicStampedRef.getReference()+",stamp值为："+atomicStampedRef.getStamp());
            atomicStampedRef.compareAndSet(2, 1, atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            System.out.println("其他操作线程第二次修改后的ref值为：" +atomicStampedRef.getReference()+",stamp值为："+atomicStampedRef.getStamp());
        });

        main.start();
        other.start();
    }

}
