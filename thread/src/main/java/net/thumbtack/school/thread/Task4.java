package net.thumbtack.school.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task4 {

    public static void main(String[] args) {

        List<Integer> integerArrayList = new ArrayList<>();
        ThreadPut threadPut = new ThreadPut(integerArrayList);
        ThreadRemove threadRemove = new ThreadRemove(integerArrayList);

        threadPut.start();
        threadRemove.start();

        System.out.println("App thread exiting.");
    }
}

class ThreadPut extends Thread {
    private final List<Integer> arrayList;
    private Random random = new Random();

    ThreadPut(List<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public void run() {
        for (int i = 0; i < 10000; i++) {
            synchronized (arrayList) {
                arrayList.add(random.nextInt());
            }
            System.out.println("add");
        }
        System.out.println("Exiting child thread1.");
    }
}

class ThreadRemove extends Thread {
    private final List<Integer> arrayList;
    private Random random = new Random();

    ThreadRemove(List<Integer> arrayList) {
        this.arrayList = arrayList;
    }

    public void run() {
        for (int i = 0; i < 10000; ) {
            if (arrayList.size() != 0) {
                synchronized (arrayList) {
                    arrayList.remove(random.nextInt(arrayList.size()));
                }
                System.out.println("remove");
                i++;
            }
        }
        System.out.println("Exiting child thread2.");
    }
}
