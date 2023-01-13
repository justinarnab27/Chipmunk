package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class BinaryOR {
    private BinaryOR() {}
    public static void execute(@NonNull final int X, @NonNull final int Y, @NonNull final ProgramState programState) {
        // Sets registers VX to the binary OR of VX and VY
        int val = programState.getRegister(X) | programState.getRegister(Y);
        programState.setRegister(X, val);
        programState.incrementCounter();
    }
}
