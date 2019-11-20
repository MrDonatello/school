package net.thumbtack.school.thread;

import org.apache.commons.cli.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Task15 {

    public static void main(String[] args) throws ParseException {

        Options optionsAll = new Options();
        Option optionWriter = new Option("W", "countWriter", true, "Writer");
        Option optionReader = new Option("R", "countReader", true, "Reader");
        optionsAll.addOption(optionWriter);
        optionsAll.addOption(optionReader);
        CommandLineParser commandLineParser = new DefaultParser();
        CommandLine commandLine = commandLineParser.parse(optionsAll, args);

        int countWriter = Integer.parseInt(commandLine.getOptionValue('W'));
        int countReader = Integer.parseInt(commandLine.getOptionValue('R'));

        BlockingQueue<Data> queue = new LinkedBlockingQueue<>();
        ExecutorService esW = Executors.newFixedThreadPool(countWriter);
        ExecutorService esR = Executors.newFixedThreadPool(countReader);

        for (int i = 0; i < countReader; i++) {
            esR.execute(new Reader1(queue));
        }

        for (int i = 0; i < countWriter; i++) {
            esW.execute(new Writer1(queue));
        }
        esR.shutdown();
        esW.shutdown();
    }
}

class Data {
    private int[] result = new int[10];

    public int[] get() {
        for (int i = 0; i < 10; i++) {
            result[i] = i;
        }
        return result;
    }
}

class Writer1 extends Thread {
    private BlockingQueue<Data> queue;

    Writer1(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                queue.put(new Data());
                System.out.println("put");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Reader1 extends Thread {
    private BlockingQueue<Data> queue;
    private int count[] = new int[10];

    Reader1(BlockingQueue<Data> queue) {
        this.queue = queue;
    }

    public void run() {
        while (true) {
            try {
                count = queue.take().get();
                System.out.println("take");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 10; i++) {
                System.out.println(count[i]);
            }
        }
    }
}