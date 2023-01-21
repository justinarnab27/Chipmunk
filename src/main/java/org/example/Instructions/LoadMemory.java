package org.example.Instructions;

import lombok.NonNull;
import org.example.Constants;
import org.example.ProgramState;

public class LoadMemory {
    //FIXME: Ambiguous instruction, needs to be made configurable
    private LoadMemory() {}

    public static void execute(@NonNull final int X, @NonNull final ProgramState programState) {
        // Load registers V0 to VX from the memory pointed by VI to VI + X
        int VI = programState.getRegister(Constants.REGISTER_I);
        for (int i = 0; i <= X; ++i) {
            programState.setRegister(i, programState.getAddress(VI + i));
        }
        programState.incrementCounter();
    }
}
