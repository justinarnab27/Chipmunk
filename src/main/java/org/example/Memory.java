package org.example;

import lombok.NonNull;

public class Memory {
    /**
     * Chip8 memory
     * Loads the program to memory starting from 0x200
     */
    byte[] ram = new byte[Constants.RAM_SIZE];
    public void loadProgram(@NonNull final byte[] programSource) {
        // Loads the program in memory starting at location 0x200
        for (int i = 0; i < programSource.length; ++i) {
            this.ram[Constants.PC_START + i] = programSource[i];
        }
    }

    public int getAddress(int address) {
        // Returns the byte stored at given address
        return Utility.byteToInt(this.ram[address]);
    }
}
