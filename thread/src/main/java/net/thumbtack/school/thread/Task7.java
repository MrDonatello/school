package net.thumbtack.school.thread;

import java.util.concurrent.Semaphore;

public class Task7 {

    public static void main(String[] args) {

        PingPong pingPong = new PingPong();
        Semaphore semaphore = new Semaphore(1);
        Semaphore semaphore2 = new Semaphore(0);
        new ThreadPing(pingPong, semaphore, semaphore2).start();
        new ThreadPong(pingPong, semaphore, semaphore2).start();
    }
}

class PingPong {

     void sayPong() {
        System.out.println("Pong");
    }

     void sayPing() {
        System.out.println("Ping");
    }
}

class ThreadPing extends Thread {
    private PingPong pingPong;
    private Semaphore sem;
    private Semaphore sem2;


    ThreadPing(PingPong pingPong, Semaphore semaphore, Semaphore semaphore2) {
        this.sem = semaphore;
        this.sem2 = semaphore2;
        this.pingPong = pingPong;
    }

    public void run() {
        while (true) {
            try {
                sem.acquire();
                pingPong.sayPing();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sem2.release();
            }
        }
    }
}

class ThreadPong extends Thread {
    private PingPong pingPong;
    private Semaphore sem;
    private Semaphore sem2;

    ThreadPong(PingPong pingPong, Semaphore semaphore, Semaphore semaphore2) {
        this.pingPong = pingPong;
        this.sem2 = semaphore2;
        this.sem = semaphore;
    }

    public void run() {
        while (true) {
            try {
                sem2.acquire();
                pingPong.sayPong();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                sem.release();
            }
        }
    }
}