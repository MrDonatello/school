package net.thumbtack.school.thread.task17;

import org.apache.commons.cli.*;

import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class App {

    public static void main(String[] args) throws ParseException {

        int countDeveloper;
        int countExecutor;
        Options optionsAll = new Options();
        Option optionDeveloper = new Option("D", "countDeveloper", true, "Developer");
        Option optionExecutor = new Option("E", "countExecutor", true, "Executor");
        optionsAll.addOption(optionDeveloper);
        optionsAll.addOption(optionExecutor);
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(optionsAll, args);

        if (commandLine.hasOption("D") && commandLine.hasOption("E")) {
            countDeveloper = Integer.parseInt(commandLine.getOptionValue("D"));
            countExecutor = Integer.parseInt(commandLine.getOptionValue("E"));
        } else {
            countDeveloper = 10;
            countExecutor = 10;
        }
        BlockingQueue<Task> queue = new LinkedBlockingQueue<>();
        ExecutorService esD = Executors.newFixedThreadPool(countDeveloper);
        ExecutorService esE = Executors.newFixedThreadPool(countExecutor);
        Map<Long, Task> map = new HashMap<>();
        Map<Long, Task> taskMap = Collections.synchronizedMap(map);
        Observer observer = new Observer(taskMap);
        observer.start();

        for (int i = 0; i < countDeveloper; i++) {
            esD.execute(new Developer(queue));
        }

        for (int i = 0; i < countExecutor; i++) {
            esE.execute(new Executor(queue, taskMap));
        }

        esD.shutdown();
        esE.shutdown();
    }
}


