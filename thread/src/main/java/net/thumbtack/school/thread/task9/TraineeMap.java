package net.thumbtack.school.thread.task9;


import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class TraineeMap {

    private ConcurrentMap<Trainee, String> map;


    public TraineeMap() {

        map = new ConcurrentHashMap<>();
    }

    public void addTraineeInfo(Trainee trainee, String institute) throws TrainingException {

        for (Trainee key : map.keySet()) {
            if (key.equals(trainee)) {
                throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
            }
        }
        map.put(trainee, institute);
    }

    public void replaceTraineeInfo(Trainee trainee, String institute) throws TrainingException {

        for (Trainee key : map.keySet()) {
            if (key.equals(trainee)) {
                map.replace(trainee, map.get(key), institute);
                return;

            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void removeTraineeInfo(Trainee trainee) throws TrainingException {

        for (Trainee key : map.keySet()) {
            if (key.equals(trainee)) {
                map.remove(key);
                return;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }


    public int getTraineesCount() {

        return map.size();

    }

    public String getInstituteByTrainee(Trainee trainee) throws TrainingException {

        String str = null;
        for (Trainee key : map.keySet()) {
            if (key.equals(trainee)) {
                str = map.get(key);
            }
        }
        if (str == null) {

            throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        }
        return str;
    }

    public Set<Trainee> getAllTrainees() {

        return map.keySet();
    }

    public ConcurrentSkipListSet<String> getAllInstitutes() {

        return new ConcurrentSkipListSet<>(map.values());
    }

    public boolean isAnyFromInstitute(String institute) {


        boolean bool = false;
        for (String s : map.values()) {
            if (s.equals(institute)) {
                bool = true;
            }
        }
        return bool;
    }
}
