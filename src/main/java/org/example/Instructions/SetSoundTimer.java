package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class SetSoundTimer {
    private SetSoundTimer() {}

    public static void execute(@NonNull final int X, @NonNull final ProgramState programState) {
        // Sets sound timer to VX
        int VX = programState.getRegister(X);
        programState.setSoundTimer(VX);
        programState.incrementCounter();
    }
}
