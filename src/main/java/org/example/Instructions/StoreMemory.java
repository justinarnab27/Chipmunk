package org.example.Instructions;

import lombok.NonNull;
import org.example.Constants;
import org.example.ProgramState;

public class StoreMemory {
    //FIXME: Ambiguous instruction, needs to be made configurable
    private StoreMemory() {}

    public static void execute(@NonNull final int X, @NonNull final ProgramState programState) {
        // Stores registers V0 to VX in memory pointed by VI to VI + X
        int VI = programState.getRegister(Constants.REGISTER_I);
        for (int i = 0; i <= X; ++i) {
            programState.storeAddress(VI + i, programState.getRegister(i));
        }
        programState.incrementCounter();
    }
}
