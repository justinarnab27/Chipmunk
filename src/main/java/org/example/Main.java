package org.example;

import java.net.URI;
import org.example.Debugger.DebuggerMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@SpringBootApplication
//@RestController
public class Main {
    //TODO: Change many of the print statements to logs
    public static void main(String[] args) throws Exception {
        boolean debugMode = false;

        SourceReader sc = new SourceReader();
        byte[] array = sc.read("");
        System.out.println("Code length (in bytes) : " + array.length);

        for (int i = 0; i < array.length; i+=2) {
            System.out.println(String.format("%02X %02X ", array[i] , array[i+1]));
        }
        System.out.println("##############################################################");
        if (debugMode) {
            DebuggerMain debugger = new DebuggerMain();
            debugger.startDebugger(array);
//            SpringApplicationBuilder builder = new SpringApplicationBuilder(Main.class);
//            builder.headless(false).run(args);
//            SpringApplication.run(Main.class, args);
        } else {
            ProgramLoop programLoop = new ProgramLoop(array);
            programLoop.run();
        }
    }

//    @GetMapping("/")
//    public String hello() {
//        return "<html><head><title>Spring</title></head><body><h1>Hello, Chipmunk!</h1></body></html>";
//    }
}