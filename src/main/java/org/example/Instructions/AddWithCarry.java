package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class AddWithCarry {
    private AddWithCarry() {}
    public static void execute(@NonNull final int X, @NonNull final int Y, @NonNull final ProgramState programState) {
        // Sets registers VX to the sum of VX and VY, if result is
        // larger than 255 (overflow) then sets VF (carry flag) to 1 else 0
        int val = programState.getRegister(X) + programState.getRegister(Y);
        programState.setRegister(X, val);
        programState.setRegister(0xF, val > 255 ? 1 : 0);
        programState.incrementCounter();
    }
}
