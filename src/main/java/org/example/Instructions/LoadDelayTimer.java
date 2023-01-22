package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class LoadDelayTimer {
    private LoadDelayTimer() {}

    public static void execute(@NonNull final int X, @NonNull final ProgramState programState) {
        // Sets VX to delay timer
        int delay = programState.getDelayTimer();
        programState.setRegister(X, delay);
        programState.incrementCounter();
    }
}
