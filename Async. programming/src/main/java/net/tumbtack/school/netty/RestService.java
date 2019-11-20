package net.tumbtack.school.netty;


public class RestService {

   // private final static String REQUEST =

    public String process (String inputJson) {
        System.out.println(inputJson);
        return "{\"result\": 42}";
    }

}
