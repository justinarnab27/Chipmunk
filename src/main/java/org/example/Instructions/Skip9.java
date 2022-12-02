package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class Skip9 {
    private Skip9() {}
    public static void execute(@NonNull final int X, @NonNull final int Y, @NonNull final ProgramState programState) {
        if (programState.getRegister(X) != programState.getRegister(Y)) {
            programState.incrementCounter();
        }
        programState.incrementCounter();
    }
}
