package org.example.Instructions;

import java.util.ArrayList;
import lombok.NonNull;
import org.example.Constants;
import org.example.ProgramState;
import org.example.Utility;

public class ConvertToDecimal {
    private ConvertToDecimal() {}

    public static void execute(@NonNull final int X, @NonNull final ProgramState programState) {
        // Converts VX value to decimal stores them in address I, I + 1, ... starting from
        // the most significant digit in address I etc...
        int VI = programState.getRegister(Constants.REGISTER_I);
        Integer VX = programState.getRegister(X);
        VX = VX < 0 ? 256 + VX : VX;
        System.out.println("VX: " + VX);
        String[] VXString = VX.toString().split("");
//        System.out.println("VX String: " + VXString);
        for (int i = 0; i < VXString.length; ++i) {
            System.out.println(VXString[i]);
            programState.storeAddress(VI + i, Integer.parseInt(VXString[i]));
        }
        programState.incrementCounter();
    }
}
