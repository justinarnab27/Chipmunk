package org.example;

import java.util.Arrays;
import lombok.NonNull;

import java.util.ArrayList;

import static org.example.Constants.REGISTER_NUMBER;

public class Registers {
    /**
     * Array of 17 registers.
     * First 16 of these registers are 8-bit
     * and are labeled from 0 to F
     * The last register is 16-bit
     * and labelled I.
     */
    private static ArrayList<Character> registerNames = new ArrayList<>(Arrays.asList('0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'I'));
    private ArrayList<Integer> reg;
    public Registers() {
        // Initializes the registers to 0
        this.reg = new ArrayList<>();
        for (int i = 0; i < REGISTER_NUMBER; ++i) {
            this.reg.add(0);
        }
    }

    public int getRegister(@NonNull final int registerName) {
        // Gets the value from register with register name registerName
        return this.reg.get(registerName);
    }

    public void setRegister(@NonNull final int registerName, @NonNull final int value) {
        this.reg.set(registerName, value);
    }

    public void printRegisters() {
        String s = "";
        for (int i = 0; i < this.reg.size(); ++i) {
            s += registerNames.get(i) + ": " + this.reg.get(i) + "    ";
        }
        System.out.println(s);
    }
}
