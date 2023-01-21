package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class Skip3 {
    private Skip3() {}
    public static void execute(@NonNull final int X, @NonNull final int NN, @NonNull final ProgramState programState) {
        if (programState.compareRegister(X,NN)) {
            programState.incrementCounter();
        }
        programState.incrementCounter();
    }
}
