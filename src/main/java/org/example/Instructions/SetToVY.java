package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class SetToVY {
    private SetToVY() {};
    public static void execute(@NonNull int X, @NonNull int Y, @NonNull ProgramState programState) {
        // Sets register VX to register VY
        programState.setRegister(X, programState.getRegister(Y));
        programState.incrementCounter();
    }
}
