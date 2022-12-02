package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class SetToNN {
    private SetToNN() {};
    public static void execute(@NonNull int X, @NonNull int NN, @NonNull ProgramState programState) {
        // Sets register VX to NN
        programState.setRegister(X, NN);
        programState.incrementCounter();
    }
}
