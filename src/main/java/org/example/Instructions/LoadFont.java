package org.example.Instructions;

import lombok.NonNull;
import org.example.Constants;
import org.example.ProgramState;

public class LoadFont {
    private LoadFont() {}

    public static void execute(@NonNull final int X, @NonNull final ProgramState programState) {
        // Sets register I to the memory location of font for character in VX
        int VX = programState.getRegister(X);
        programState.setRegister(Constants.REGISTER_I, Constants.FONT_MEMORY_LOCATION + 5 * VX);
        programState.incrementCounter();
    }
}
