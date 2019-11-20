package net.thumbtack.school.thread;

import java.util.concurrent.Semaphore;

public class Task8 {

    public static void main(String args[]) {
        Book book = new Book();
        new Reader(book).start();
        new Writer(book).start();
    }
}

class Book {
    private int n;

    private static Semaphore semRead = new Semaphore(0);
    private static Semaphore semWrite = new Semaphore(1);

    public void read() {
        try {
            semRead.acquire();
            System.out.println("Read the book: " + n);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        } finally {
            semWrite.release();
        }
    }

    public void write(int n) {
        try {
            semWrite.acquire();
            this.n = n;
            System.out.println("Write a book: " + n);
        } catch (InterruptedException e) {
            System.out.println("InterruptedException caught");
        } finally {
            semRead.release();
        }
    }
}

class Writer extends Thread {
    private Book book;

    Writer(Book book) {
        this.book = book;
    }

    public void run() {
        for (int i = 0; i < 10; i++)
            book.write(i);
    }
}

class Reader extends Thread {
    private Book book;

    Reader(Book book) {
        this.book = book;
    }

    public void run() {
        for (int i = 0; i < 10; i++)
            book.read();
    }
}
