package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class Jump {
    private Jump() {}
    public static void execute(@NonNull final int NNN, @NonNull final ProgramState programState) {
        // Jumps to line NNN
        System.out.println("Jumping to: " + NNN);
        programState.setProgramCounter(NNN);
    }
}
