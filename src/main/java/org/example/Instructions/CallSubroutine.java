package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class CallSubroutine {
    private CallSubroutine() {}
    public static void execute(@NonNull final int NNN, @NonNull final ProgramState programState) {
        // Pushes PC to the stack and jumps to line NNN
        programState.pushOnStack(programState.getProgramCounter());
        System.out.println("Jumping to: " + NNN);
        programState.setProgramCounter(NNN);
    }
}
