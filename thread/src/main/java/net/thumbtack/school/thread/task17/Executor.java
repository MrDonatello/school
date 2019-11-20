package net.thumbtack.school.thread.task17;

import java.util.Map;
import java.util.concurrent.BlockingQueue;

class Executor extends Thread {
    private BlockingQueue<Task> queue;
    private Map<Long, Task> taskMap;

    Executor(BlockingQueue<Task> queue, Map<Long, Task> taskMap) {
        this.queue = queue;
        this.taskMap = taskMap;
    }

    public void run() {
        while (true) {
            try {
                Task task = queue.take();
                taskMap.put(this.getId(), task);
                if (!task.getList().get(0).getClass().equals(Poison.class)) {
                    task.getList().get(0).execute();
                    task.getList().remove(0);
                    queue.put(task);
                } else {
                   // System.out.println("poison!!!!!!! " + task.getNameTask());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}