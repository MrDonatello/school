package net.thumbtack.school.thread.task16;

import java.util.concurrent.BlockingQueue;

class Executor extends Thread {
    private BlockingQueue<Task> queue;

    Executor(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                 queue.take().execute();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
