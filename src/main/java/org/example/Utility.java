package org.example;

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
}
