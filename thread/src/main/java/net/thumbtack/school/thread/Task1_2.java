package net.thumbtack.school.thread;

public class Task1_2 {

    public static void main(String[] args) {

        Thread thread = Thread.currentThread();
        System.out.println(thread.getName());
        System.out.println(thread.getId());
        System.out.println(thread.isAlive());
        System.out.println(thread.getPriority());

        ThreadTaskTwo myThread = new ThreadTaskTwo();
        myThread.start();
        try {
            myThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("App thread exiting.");
    }
}

class ThreadTaskTwo extends Thread {

    public void run() {
        System.out.println("I am child thread");
        System.out.println("Exiting child thread.");
    }
}