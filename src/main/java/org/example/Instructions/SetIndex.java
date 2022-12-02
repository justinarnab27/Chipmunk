package org.example.Instructions;

import org.example.ProgramState;


import static org.example.Constants.REGISTER_I;

public class SetIndex {
    private SetIndex() {};
    public static void execute(int NNN, ProgramState programState) {
        // Sets register I to NNN
        programState.setRegister(REGISTER_I, NNN);
        programState.incrementCounter();
    }
}
