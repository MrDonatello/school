package net.thumbtack.school.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task11 {

    public static void main(String[] args) {

        PingPong11 pingPong = new PingPong11();
        ThreadPing11 ping11 = new ThreadPing11(pingPong);
        ThreadPong11 pong11 = new ThreadPong11(pingPong);

        pong11.start();
        ping11.start();
    }
}

class PingPong11 {
    private Lock lock = new ReentrantLock();
    private Condition i = lock.newCondition();
    private Condition j = lock.newCondition();
    private String str ="Pong";

    void sayPong() {
        lock.lock();
        try {

            while (str.equals("Ping"))
                i.await();
            System.out.println(str);
            str = "Ping";
            j.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    void sayPing() {
        lock.lock();
        try {
            while (str.equals("Pong"))
                j.await();
            System.out.println(str);
            str = "Pong";
            i.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }
}

class ThreadPing11 extends Thread {
    private PingPong11 pingPongt;

    ThreadPing11(PingPong11 pingPong) {

        this.pingPongt = pingPong;
    }

    public void run() {
        while (true) {
            pingPongt.sayPing();
        }
    }
}

class ThreadPong11 extends Thread {
    private PingPong11 pingPong;


    ThreadPong11(PingPong11 pingPong) {
        this.pingPong = pingPong;
    }

    public void run() {
        while (true) {
            pingPong.sayPong();
        }
    }
}
