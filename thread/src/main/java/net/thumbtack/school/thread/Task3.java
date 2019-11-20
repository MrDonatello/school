package net.thumbtack.school.thread;

public class Task3 {

    public static void main(String[] args) {

        ThreadTaskThree1 thread1 = new ThreadTaskThree1();
        ThreadTaskThree2 thread2 = new ThreadTaskThree2();
        ThreadTaskThree3 thread3 = new ThreadTaskThree3();
        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("App thread exiting.");
    }
}

class ThreadTaskThree1 extends Thread {
    public void run() {
        System.out.println("I am child thread1");
        System.out.println("Exiting child thread1.");
    }
}

class ThreadTaskThree2 extends Thread {
    public void run() {
        System.out.println("I am child thread2");
        System.out.println("Exiting child thread2.");
    }
}

class ThreadTaskThree3 extends Thread {
    public void run() {
        System.out.println("I am child thread3");
        System.out.println("Exiting child thread3.");
    }
}

