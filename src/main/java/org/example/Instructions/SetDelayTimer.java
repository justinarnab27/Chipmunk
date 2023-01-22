package org.example.Instructions;

import lombok.NonNull;
import org.example.Constants;
import org.example.ProgramState;

public class SetDelayTimer {
    private SetDelayTimer() {}

    public static void execute(@NonNull final int X, @NonNull final ProgramState programState) {
        // Sets delay timer to VX
        int VX = programState.getRegister(X);
        programState.setDelayTimer(VX);
        programState.incrementCounter();
    }
}
