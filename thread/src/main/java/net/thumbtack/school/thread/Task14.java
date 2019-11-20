
package net.thumbtack.school.thread;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Task14 {

    public static void main(String[] args) {

        ExecutorService es = Executors.newFixedThreadPool(5);
        Transport transport = new Transport();
        String strLine;

        try {
            FileInputStream stream = new FileInputStream("D:/file.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));
            while ((strLine = br.readLine()) != null) {
                es.execute(new Thread14(strLine, transport));
                System.out.println(strLine);
            }
        } catch (IOException e) {
            System.out.println("error");
        }
        es.shutdown();
    }
}

class Message {

    String emailAddress;
    String sender;
    String subject;
    String body;

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public void setBody(String body) {
        this.body = body;
    }
}

class Transport {

    public void send(Message message) {

        try {
            FileWriter writer = new FileWriter("D:/file1.txt", true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(message.subject + "\n" + message.sender + "\n"
                    + message.emailAddress + "\n" + message.body + "\n");
            System.out.println("send" + message.emailAddress);
            bufferWriter.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

class Thread14 extends Thread {
    private String emailAddress;
    private Transport transport;

    Thread14(String emailAddress, Transport transport) {
        this.transport = transport;
        this.emailAddress = emailAddress;
    }

    @Override
    public void run() {
        Message message = new Message();
        message.setEmailAddress(emailAddress);
        message.setBody("Text");
        message.setSender("sender");
        message.setSubject("subject");
        transport.send(message);
    }
}


