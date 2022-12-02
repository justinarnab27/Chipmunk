package org.example.Instructions;

import org.example.ProgramState;

public class Clear {
    private Clear() {}
    public static void execute(ProgramState programState) {
        // Clears the screen
        programState.clearDisplayMatrix();
        programState.updateScreen();
        programState.incrementCounter();
    }
}
