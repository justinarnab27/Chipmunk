package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class RightShift {
    //FIXME: Ambiguous instruction, needs to be made configurable
    private RightShift() {}
    public static void execute(@NonNull final int X, @NonNull final int Y, @NonNull final ProgramState programState) {
        // Shift the value of registers VX to the right by
        // 1 bit and sets the flag register VF to bit shifted out (last bit)
        int val = programState.getRegister(X);
        programState.setRegister(0xF, val & 1);
        val = val >> 1;
        programState.setRegister(X, val);
        programState.incrementCounter();
    }
}
