package org.example.Instructions;

import org.example.ProgramState;

public class Add {
    private Add() {}
    public static void execute(int X, int NN, ProgramState programState) {
        // Adds NN to register VX
        int val = programState.getRegister(X);
        programState.setRegister(X, val + NN);
        programState.incrementCounter();
    }
}
