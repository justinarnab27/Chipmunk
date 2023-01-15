package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class JumpWithOffset {
    //FIXME: Ambiguous instruction, needs to be made configurable
    private JumpWithOffset() {}
    public static void execute(@NonNull final int NNN, @NonNull final ProgramState programState) {
        // Jumps to line NNN + V0
        int V0 = programState.getRegister(0);
        System.out.println("Jumping to " + NNN + V0);
        programState.setProgramCounter(NNN + V0);
    }
}
