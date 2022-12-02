package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;


import static org.example.Constants.REGISTER_I;

public class SetIndex {
    private SetIndex() {};
    public static void execute(@NonNull final int NNN, @NonNull final ProgramState programState) {
        // Sets register I to NNN
        programState.setRegister(REGISTER_I, NNN);
        programState.incrementCounter();
    }
}
