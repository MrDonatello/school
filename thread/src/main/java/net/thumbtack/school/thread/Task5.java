package net.thumbtack.school.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Task5 {

    public static void main(String[] args) {
        PutRemove putRemove = new PutRemove();
        List<Integer> integerArrayList = new ArrayList<>();
        ThreadTaskFive thread1 = new ThreadTaskFive(integerArrayList, putRemove, true);
        ThreadTaskFive thread2 = new ThreadTaskFive(integerArrayList, putRemove, false);

        thread1.start();
        thread2.start();
        System.out.println("App thread exiting.");
    }
}

class ThreadTaskFive extends Thread {
    private boolean flag;
    private PutRemove putRemove;
    private final List<Integer> arrayList;
    private Random random = new Random();

    ThreadTaskFive(List<Integer> arrayList, PutRemove putRemove, boolean flag) {
        this.putRemove = putRemove;
        this.arrayList = arrayList;
        this.flag = flag;
    }

    public void run() {
        if (flag) {
            for (int i = 0; i < 10000; i++) {
                putRemove.put(arrayList, random.nextInt());
                System.out.println("put");
            }
        } else {
            for (int i = 0; i < 10000; ) {
                if (arrayList.size() != 0)
                    putRemove.remove(arrayList, random.nextInt(arrayList.size()));
                System.out.println("remove");
                i++;
            }
            System.out.println("Exiting child thread.");
        }
    }
}

class PutRemove {

    synchronized void put(List<Integer> arrayList, int i) {
        arrayList.add(i);
    }

    synchronized void remove(List<Integer> arrayList, int i) {
        arrayList.remove(i);
    }
}