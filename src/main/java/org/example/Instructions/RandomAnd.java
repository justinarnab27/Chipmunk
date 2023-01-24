package org.example.Instructions;

import java.util.Random;
import lombok.NonNull;
import org.example.ProgramState;

public class RandomAnd {
    private RandomAnd() {}
    public static void execute(@NonNull final int X, @NonNull final int NN, @NonNull final ProgramState programState) {
        // Generates a random number and ANDs it with NN and
        // puts the result in VX
        Random random = new Random();
        int randVal = random.nextInt(256);
        programState.setRegister(X, NN & randVal);
        programState.incrementCounter();
    }
}
