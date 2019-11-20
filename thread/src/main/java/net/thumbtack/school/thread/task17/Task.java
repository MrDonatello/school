package net.thumbtack.school.thread.task17;


import java.util.List;

class Task {

    private int nameTask;
    private int numberOfStages;
    private int size;
    private volatile List<Executable> list;


    public Task(List<Executable> list, int nameTask) {
        this.nameTask = nameTask;
        this.list = list;
        this.numberOfStages = list.size();
    }

    public int getNumberOfStages() {
        return numberOfStages;
    }

    public int getSize() {
        this.size = list.size();
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Executable> getList() {
        return list;
    }

    public int getNameTask() {
        return nameTask;
    }
}
