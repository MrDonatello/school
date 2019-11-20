package net.thumbtack.school.thread;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Task6 {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        PutRemoveWithCollection putRemove = new PutRemoveWithCollection();
        List<Integer> integerList = Collections.synchronizedList(list);
        ThreadTaskSix thread1 = new ThreadTaskSix(integerList, putRemove, true);
        ThreadTaskSix thread2 = new ThreadTaskSix(integerList, putRemove, false);

        thread1.start();
        thread2.start();
        System.out.println("App thread exiting.");
    }
}

class ThreadTaskSix extends Thread {
    private boolean flag;
    private PutRemoveWithCollection putRemove;
    private final List<Integer> integerList;
    private Random random = new Random();

    ThreadTaskSix(List<Integer> integerList, PutRemoveWithCollection putRemove, boolean flag) {
        this.putRemove = putRemove;
        this.integerList = integerList;
        this.flag = flag;
    }

    public void run() {
        if (flag) {
            for (int i = 0; i < 10000; i++) {
                putRemove.put(integerList, random.nextInt());
                System.out.println("put");
            }
        } else {
            for (int i = 0; i < 10000; ) {
                if (integerList.size() != 0)
                    putRemove.remove(integerList, random.nextInt(integerList.size()));
                System.out.println("remove");
                i++;
            }
            System.out.println("Exiting child thread.");
        }
    }
}

class PutRemoveWithCollection {

    synchronized void put(List<Integer> integerList, int i) {
        integerList.add(i);
    }

    synchronized void remove(List<Integer> integerList, int i) {
        integerList.remove(i);
    }
}