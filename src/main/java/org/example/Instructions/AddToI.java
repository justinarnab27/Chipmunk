package org.example.Instructions;

import lombok.NonNull;
import org.example.Constants;
import org.example.ProgramState;

public class AddToI {
    //FIXME: Ambiguous instruction, needs to be made configurable
    private AddToI() {}

    public static void execute(@NonNull final int X, @NonNull final ProgramState programState) {
        // Adds VX to VI, VF not affected
        int VI = programState.getRegister(Constants.REGISTER_I);
        int VX = programState.getRegister(X);
        programState.setRegister(Constants.REGISTER_I, VI + VX);
        programState.incrementCounter();
    }
}
