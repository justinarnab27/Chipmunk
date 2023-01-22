package org.example.Instructions;

import lombok.NonNull;
import org.example.Constants;
import org.example.ProgramState;

public class SkipIfKey {
    private SkipIfKey() {}

    public static void execute(@NonNull final int X, @NonNull final ProgramState programState) {
        if (programState.getRegister(X) == programState.getKeyBeingPressed()) {
            programState.incrementCounter();
        }
        programState.incrementCounter();
    }
}
