package net.thumbtack.school.thread.task9;

public class TrainingException extends Exception {

    private TrainingErrorCode trainingErrorCode;

    TrainingException(TrainingErrorCode trainingErrorCode) {

        this.trainingErrorCode = trainingErrorCode;
    }

    public TrainingErrorCode getErrorCode() {

        return trainingErrorCode;
    }
}
