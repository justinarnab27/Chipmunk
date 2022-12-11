package org.example;

import javax.swing.SwingUtilities;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;


public class ProgramState {
    /**
     * Contains all the information
     * for the current program.
     * This includes memory, program counter,
     * registers, display etc.
     */
    @Getter
    @Setter
    private int programCounter;
    private Registers registers;
    private Memory memory;
    private DisplayCanvas displayCanvas;

    public ProgramState(@NonNull final byte[] source, boolean debugMode) {
        this.programCounter = Constants.PC_START;   // PC starts at 0x200 in memory
        this.registers = new Registers();
        this.memory = new Memory();
        this.memory.loadProgram(source);      // Loads the program in memory from 0x200 onwards
        this.displayCanvas = new DisplayCanvas();
        if (!debugMode) {
            SwingUtilities.invokeLater(() -> this.displayCanvas.createAndShowGui());    // Creates gui
        }
        //TODO: Also needs stack
    }

    public void incrementCounter() {
        // Increases the program counter by
        // one line that is two bytes
        this.programCounter += 2;
    }

    public void setRegister(@NonNull final int registerName, @NonNull final int value) {
        // Sets register registerName
        // with value
        this.registers.setRegister(registerName, value);
    }

    public int getRegister(@NonNull final int registerName) {
        //  Gets value of register registerName
        return this.registers.getRegister(registerName);
    }

    public void printRegisters() {
        // Prints the values of all register
        this.registers.printRegisters();
    }

    public String getDisplayMatrixAsString() {
        return displayCanvas.getDisplayMatrixAsString();
    }

    public Instruction getNextInstruction() {
        // Fetches the next instruction to execute
        int firstByte = this.memory.getAddress(this.programCounter);
        int secondByte = this.memory.getAddress(this.programCounter + 1);
        return new Instruction(firstByte, secondByte);
    }

    public int getAddress(@NonNull final int address) {
        return this.memory.getAddress(address);
    }

    public void printDisplayMatrix() {
        this.displayCanvas.printDisplayMatrix();
    }

    public int[][] getDisplayMatrix() {
        return this.displayCanvas.getDisplayMatrix();
    }

    public int getDisplayMatrixPixel(int r, int c) {
        return this.displayCanvas.getDisplayMatrixPixel(r, c);
    }

    public void setDisplayMatrixPixel(int r, int c, int val) {
        this.displayCanvas.setDisplayMatrixPixel(r, c, val);
    }

    public void clearDisplayMatrix() {
        this.displayCanvas.clearDisplayMatrix();
    }

    public void updateScreen() {
        this.displayCanvas.updateScreen();
    }
}
