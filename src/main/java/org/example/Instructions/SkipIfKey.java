package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class SkipIfKey {
    private SkipIfKey() {}

    public static void execute(@NonNull final int X, @NonNull final ProgramState programState) {
        // Skips the next instruction, if key being pressed is equal to VX
        if (programState.getRegister(X) == programState.getKeyBeingPressed()) {
            programState.incrementCounter();
        }
        programState.incrementCounter();
    }
}
