package org.example.Instructions;

import lombok.NonNull;
import org.example.Constants;
import org.example.ProgramState;

import static org.example.Constants.REGISTER_I;

public class Display {
    private Display() {}
    public static void execute(@NonNull final int X, @NonNull final int Y, @NonNull final int N, @NonNull final ProgramState programState) {
        /**
         * Draws the screen.
         * It modifies the display matrix
         * and then redraws the screen.
         * It draws a sprite of width 8 pixel
         * and height N.
         */

        // Gets the x and y coordinate of the top left
        // corner of the sprite from registers VX and VY
        int VX = programState.getRegister(X) % Constants.WIDTH_IN_PIXELS;
        int VY = programState.getRegister(Y) % Constants.HEIGHT_IN_PIXELS;

        // Gets the byte from address in register I
        int address = programState.getRegister(REGISTER_I);
        boolean VFlag = false;
//        System.out.println("Display called with " + VX + " " + VY + " " + N);
        for (int i = 0; i < N; ++i) {
            // Finds each bit of the byte and stores them
            // in lsb_array. The 0th position contains
            // the least significant bit
            int b = programState.getAddress(address + i);
            int[] lsb_array = new int[8];
            for (int ix = 0; ix < lsb_array.length; ++ix) {
                lsb_array[ix] = b % 2;
                b = (b >> 1);
            }
//            System.out.println("Com " + Integer.toBinaryString(b));
            for (int j = 0; j < 8; ++j) {
                if (lsb_array[8 - j - 1] == 1) {     // Reversing the array order to get the msb first
                    if (VY + i >= Constants.HEIGHT_IN_PIXELS || VX + j >= Constants.WIDTH_IN_PIXELS) continue;
                    int prev_pixel = programState.getDisplayMatrixPixel(VY + i, VX + j);
                    // Flips the pixel value if the bit is 1
                    if (prev_pixel == 0) {
                        programState.setDisplayMatrixPixel(VY + i, VX + j, 1);
                    } else {
                        VFlag = true;    // VFlag is true if any pixel turns from 1 to 0
                        programState.setDisplayMatrixPixel(VY + i, VX + j, 0);
                    }
                }
            }
        }
        programState.setRegister(0xF, VFlag ? 1 : 0);   // Stores VFlag in VF register
//        programState.printDisplayMatrix();
        programState.incrementCounter();
        programState.updateScreen();
    }
}
