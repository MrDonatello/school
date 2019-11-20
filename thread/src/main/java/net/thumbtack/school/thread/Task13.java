package net.thumbtack.school.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Task13 {

    public static void main(String[] args) {
        Formatter formatter = new Formatter();

        Thread13 t1 = new Thread13(formatter);
        Thread13 t2 = new Thread13(formatter);
        Thread13 t3 = new Thread13(formatter);
        Thread13 t4 = new Thread13(formatter);
        Thread13 t5 = new Thread13(formatter);

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Formatter {

    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss:S z");

    public String format(Date date) {

        return simpleDateFormat.format(date);
    }
}

class ThreadLocalHolder {

    private static ThreadLocal<Date> dateThreadLocal = new ThreadLocal<Date>();

    public static void set(Date date) {

        dateThreadLocal.set(date);
    }

    public static Date get() {
        return dateThreadLocal.get();
    }
}

class Thread13 extends Thread {
    private Formatter formatter;


    Thread13(Formatter formatter) {
        this.formatter = formatter;
    }

    public void run() {
        ThreadLocalHolder.set(new Date());
        System.out.println(formatter.format(ThreadLocalHolder.get()));
        System.out.println(this.getName());
    }
}
