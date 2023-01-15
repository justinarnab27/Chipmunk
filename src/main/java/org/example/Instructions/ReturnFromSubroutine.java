package org.example.Instructions;

import lombok.NonNull;
import org.example.ProgramState;

public class ReturnFromSubroutine {
    private ReturnFromSubroutine() {}
    public static void execute(@NonNull final ProgramState programState) {
        // Sets PC to the value on top of the stack and pops the stack
        int val = programState.popFromStack();
        System.out.println("Jumping to: " + val);
        programState.setProgramCounter(val);
        programState.incrementCounter();
    }
}
