package net.thumbtack.school.thread.task17;

import java.util.Map;

public class Observer extends Thread {

    private Map<Long, Task> taskMap;

    Observer( Map<Long, Task> taskMap) {
        this.taskMap = taskMap;
    }

    public void run() {

        while (true){

            taskMap.forEach((aLong, task) -> System.out.println("Task name: " + task.getNameTask() +"\nCurrent stage number: "+ task.getSize()+ "\nNumber of stages: "+ task.getNumberOfStages()+"\n") );
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
