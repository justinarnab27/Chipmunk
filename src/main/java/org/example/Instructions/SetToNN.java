package org.example.Instructions;

import org.example.ProgramState;

public class SetToNN {
    private SetToNN() {};
    public static void execute(int X, int NN, ProgramState programState) {
        // Sets register VX to NN
        programState.setRegister(X, NN);
        programState.incrementCounter();
    }
}
