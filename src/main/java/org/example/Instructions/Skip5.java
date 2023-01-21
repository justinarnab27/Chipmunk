package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class Skip5 {
    private Skip5() {}
    public static void execute(@NonNull final int X, @NonNull final int Y, @NonNull final ProgramState programState) {
        if (programState.compareRegister(X,programState.getRegister(Y))) {
            programState.incrementCounter();
        }
        programState.incrementCounter();
    }
}
