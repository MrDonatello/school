package net.thumbtack.school.thread.task17;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

class Developer extends Thread {
    private BlockingQueue<Task> queue;

    Developer(BlockingQueue<Task> queue) {
        this.queue = queue;
    }

    public void run() {
        Random random = new Random();
        while (true) {
            try {
                List<Executable> stageList = new LinkedList<>();
                stageList.clear();
                for (int i = 0; i < 3; i++) {
                    stageList.add(new Stage());
                }
                stageList.add(new Poison());
                queue.put(new Task(stageList,  random.nextInt()));
                sleep(0,1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
