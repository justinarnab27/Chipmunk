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
//        System.out.println("VX: " + VX);
        String[] VXString = VX.toString().split("");
        int[] VXDec = new int[] {0, 0, 0};
        for (int i = VXString.length - 1; i >= 0; --i) {
            VXDec[3 + i - VXString.length] = Integer.parseInt(VXString[i]);
        }
        for (int i = 0; i < VXDec.length; ++i) {
            programState.storeAddress(VI + i, VXDec[i]);
        }
        programState.incrementCounter();
    }
}
