package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class Add {
    private Add() {}
    public static void execute(@NonNull final int X, @NonNull final int NN, @NonNull final ProgramState programState) {
        // Adds NN to register VX
        int val = programState.getRegister(X);
        programState.setRegister(X, val + NN);
        programState.incrementCounter();
    }
}
