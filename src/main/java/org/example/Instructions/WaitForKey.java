package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class WaitForKey {
    private WaitForKey() {}

    public static void execute(@NonNull final int X, @NonNull final ProgramState programState) {
        // Waits for key to be pressed, puts the key value in VX
        int key = programState.getKeyBeingPressed();
        if (key == -1) return;
        programState.setRegister(X, key);
        programState.incrementCounter();
    }
}
