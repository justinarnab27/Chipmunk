package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class Clear {
    private Clear() {}
    public static void execute(@NonNull final ProgramState programState) {
        // Clears the screen
        programState.clearDisplayMatrix();
        programState.updateScreen();
        programState.incrementCounter();
    }
}
