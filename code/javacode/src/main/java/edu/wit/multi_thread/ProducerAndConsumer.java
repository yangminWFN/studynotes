package edu.wit.multi_thread;

import java.util.ArrayList;
import java.util.concurrent.Semaphore;

/**
 * Java使用Semaphore方式实现的生产者消费者模型
 */
class Cache {
    //产品数量
    private int productSize = 0;
    //记录空缓冲区的数量
    private Semaphore empty;
    //记录非空缓冲区的数量
    private Semaphore full;
    //互斥量
    private Semaphore mutex;

    public Cache(int n) {
        this.empty = new Semaphore(n);
        this.full = new Semaphore(0);
        this.mutex = new Semaphore(1);
    }

    public void produce() throws InterruptedException {
        empty.acquire();
        mutex.acquire();
        productSize++;
        System.out.println("生产了一个产品,当前产品数量为：" + productSize);
        mutex.release();
        full.release();
    }

    public void consume() throws InterruptedException {
        full.acquire();
        mutex.acquire();
        productSize--;
        System.out.println("消费了一个产品,当前产品数量为：" + productSize);
        mutex.release();
        empty.release();
    }
}

class Producer implements Runnable {

    private Cache cache;

    public Producer(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
        while (true) {
            try {
                cache.produce();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

class Consumer implements Runnable {

    private Cache cache;

    public Consumer(Cache cache) {
        this.cache = cache;
    }

    @Override
    public void run() {
        while (true) {
            try {
                cache.consume();
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

public class ProducerAndConsumer {

    public static void main(String[] args) {
        Cache cache = new Cache(10);
        int producerCount = 20, consumerCount = 20;
        for (int i = 0; i < producerCount; i++) {
            new Thread(new Producer(cache)).start();
        }
        for (int i = 0; i < consumerCount; i++) {
            new Thread(new Consumer(cache)).start();
        }
        
    }
}
