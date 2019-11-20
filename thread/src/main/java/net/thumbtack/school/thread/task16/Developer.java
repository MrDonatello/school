package net.thumbtack.school.thread.task16;

import java.util.concurrent.BlockingQueue;

class Developer extends Thread {
    private BlockingQueue<Task> queue;

    Developer(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                queue.put(new Task());
                System.out.println("Add Task");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
