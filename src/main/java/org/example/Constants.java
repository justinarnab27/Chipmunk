package org.example;

public class Constants {
    /**
     * Constants used throughout the program
     */
    //TODO: Should be modified later to use dependency injection

    private Constants() {};

    public static final int RAM_SIZE = 4096;  //Size of the RAM (duh)
    public static final int PC_START = 512;   //The program must be loaded at memory location 512 (0x200)
    public static final int REGISTER_NUMBER = 17;   // Number of registers (0 through F and I)

    public static final int REGISTER_I = REGISTER_NUMBER - 1;  // Index of register I

    public static final int WIDTH_IN_PIXELS = 64;   //Width of the screen in pixels

    public static final int HEIGHT_IN_PIXELS = 32;   //Height of the screen in pixels


}

