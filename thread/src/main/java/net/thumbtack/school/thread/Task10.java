package net.thumbtack.school.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class Task10 {

    public static void main(String[] args) {

        ReentrantLock reentrantLock = new ReentrantLock();
        List<Integer> integerArrayList = new ArrayList<>();
        ThreadTaskTenPut threadPut = new ThreadTaskTenPut(integerArrayList, reentrantLock);
        ThreadTaskTenRemove threadRemove = new ThreadTaskTenRemove(integerArrayList, reentrantLock);

        threadPut.start();
        threadRemove.start();
        System.out.println("App thread exiting.");
    }
}

class ThreadTaskTenPut extends Thread {
    private final List<Integer> arrayList;
    private ReentrantLock reentrantLock;
    private Random random = new Random();


    ThreadTaskTenPut(List<Integer> arrayList, ReentrantLock reentrantLock) {
        this.arrayList = arrayList;
        this.reentrantLock = reentrantLock;
    }

    public void run() {

        for (int i = 0; i < 10000; i++) {
            reentrantLock.lock();
            arrayList.add(random.nextInt());
            reentrantLock.unlock();
            System.out.println("add");
        }
        System.out.println("Exiting child thread1.");
    }
}

class ThreadTaskTenRemove extends Thread {
    private final List<Integer> arrayList;
    private ReentrantLock reentrantLock;
    private Random random = new Random();

    ThreadTaskTenRemove(List<Integer> arrayList, ReentrantLock reentrantLock) {
        this.arrayList = arrayList;
        this.reentrantLock = reentrantLock;
    }

    public void run() {
        for (int i = 0; i < 10000; ) {
            if (arrayList.size() != 0) {
                reentrantLock.lock();
                arrayList.remove(random.nextInt(arrayList.size()));
                reentrantLock.unlock();
                i++;
                System.out.println("remove");
            }
        }
        System.out.println("Exiting child thread2.");
    }
}
