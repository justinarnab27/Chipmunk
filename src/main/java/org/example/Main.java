package org.example;

public class Main {
    //TODO: Change many of the print statements to logs
    public static void main(String[] args) throws Exception {
        SourceReader sc = new SourceReader();
        byte[] array = sc.read("");
        System.out.println("Code length (in bytes) : " + array.length);

        for (int i = 0; i < array.length; i+=2) {
            System.out.println(String.format("%02X %02X ", array[i] , array[i+1]));
        }
        System.out.println("##############################################################");
        ProgramLoop programLoop = new ProgramLoop(array);
        programLoop.run();
    }
}