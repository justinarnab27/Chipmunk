package org.example;

public class Instruction {
    /**
     * Each chip8 instruction is two bytes
     */
    int firstBye, secondByte;

    public Instruction(int firstBye, int secondByte) {
        this.firstBye = firstBye;
        this.secondByte = secondByte;
    }
}
