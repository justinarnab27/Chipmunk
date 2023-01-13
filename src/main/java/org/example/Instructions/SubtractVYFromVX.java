package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class SubtractVYFromVX {
    private SubtractVYFromVX() {}
    public static void execute(@NonNull final int X, @NonNull final int Y, @NonNull final ProgramState programState) {
        // Sets registers VX to VX - VY
        int VX = programState.getRegister(X);
        int VY = programState.getRegister(Y);
        int val = VX - VY;
        programState.setRegister(X, val);
        programState.setRegister(0xF, VX > VY ? 1 : 0);
        programState.incrementCounter();
    }
}
