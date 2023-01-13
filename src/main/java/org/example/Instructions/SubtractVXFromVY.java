package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class SubtractVXFromVY {
    private SubtractVXFromVY() {}
    public static void execute(@NonNull final int X, @NonNull final int Y, @NonNull final ProgramState programState) {
        // Sets registers VX to VY - VX
        int VX = programState.getRegister(X);
        int VY = programState.getRegister(Y);
        int val = VY - VX;
        programState.setRegister(X, val);
        programState.setRegister(0xF, VY > VX ? 1 : 0);
        programState.incrementCounter();
    }
}
