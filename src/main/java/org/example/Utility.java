package org.example;

import lombok.NonNull;

public class Utility {
    /**
     * General Utilities
     */
    private Utility() {}

    public static int byteToInt(byte b) {
        //Converts a byte to int
        return b & 0xFF;
    }

    public static void displayBytesInBinary(int b) {
        // Prints binary representation of int b of length 8-bit
        System.out.println(Integer.toBinaryString(b & 0xFF));
    }

    public static void displayBytesInHex(int b) {
        // Prints hexadecimal representation of int b of length 8-bit
        System.out.println(String.format("%01X", b));
    }

    public static int combineTwoNibbles(int byte1, int byte2) {
        // Combines two 4-bit nibbles into a int
        return ((byte1 & 0xff) << 4) | (byte2 & 0xff);
    }

    public static int combineThreeNibbles(int byte1, int byte2, int byte3) {
        // Combines three 4-bit nibbles into a int
        return ((byte1 & 0xff) << 8) | (byte2 & 0xff) << 4 | (byte3 & 0xff);
    }

    public static int covertPCToLine(int PC) {
        return (PC - 512) / 2;
    }

    public static int binarySanitizer8(@NonNull final int value) {
        // Takes input of a 8-bit number and ensures value is
        // always within bounds (-128 to 127)
        int val;
        val = value % 256;
        if (val >= 256 / 2) val = val - 256;
        if(val < - 256 / 2) val += 256;
        return val;
    }

    public static int binarySanitizer16(@NonNull final int value) {
        // Takes input of a 16-bit number and ensures value is
        // always within bounds (-32768 to 32767)
        int val;
        val = value % 65536;
        if (val >= 65536 / 2) val = val - 65536;
        if(val < - 65536 / 2) val += 65536;
        return val;
    }
}
