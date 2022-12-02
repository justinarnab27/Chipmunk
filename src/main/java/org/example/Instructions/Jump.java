package org.example.Instructions;

import org.example.ProgramState;

public class Jump {
    private Jump() {}
    public static void execute(int NNN, ProgramState programState) {
        // Jumps to line NNN
        System.out.println("Jumping to: " + NNN);
        programState.setProgramCounter(NNN);
    }
}
