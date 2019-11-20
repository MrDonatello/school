package net.thumbtack.school.thread.task9;

import java.util.concurrent.ConcurrentLinkedQueue;

public class TraineeQueue {


    private ConcurrentLinkedQueue<Trainee> queue;

    public TraineeQueue() {

        queue = new ConcurrentLinkedQueue<>();
    }

    public void addTrainee(Trainee trainee) {

        queue.add(trainee);
    }

    public Trainee removeTrainee() throws TrainingException {

        if (queue.isEmpty()) {
            throw new TrainingException(TrainingErrorCode.EMPTY_TRAINEE_QUEUE);
        }
        Trainee trainee = queue.poll();
        queue.remove(trainee);
        return trainee;
    }
}
