package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
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
//    @JsonIgnore
    private Registers registers;
//    @JsonIgnore
    private Memory memory;
    @JsonIgnore
    private DisplayCanvas displayCanvas;
//    @Getter
//    @Setter
//    @JsonIgnore
    byte[] programSource;
    //TODO: This might need to be turned into a separate class if it gets sufficiently complicated
    private Stack<Integer> programStack;



    public ProgramState(@NonNull final byte[] source, boolean debugMode) {
        this.programCounter = Constants.PC_START;   // PC starts at 0x200 in memory
        this.registers = new Registers();
        this.memory = new Memory();
        this.programSource = source;
        this.memory.loadProgram(source);      // Loads the program in memory from 0x200 onwards
        this.displayCanvas = new DisplayCanvas();
        this.programStack = new Stack<>();
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

    @JsonIgnore
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

    public ArrayList<Character> getAllRegisterNames() {
        return this.registers.getRegisterNames();
    }

    public ArrayList<Integer> getAllRegisters() {
        return this.registers.getReg();
    }

    public ArrayList<Integer> getProgramSource() {
        ArrayList<Integer> arr = new ArrayList<>();
        for (byte b : this.programSource) {
            arr.add(Utility.byteToInt(b));
        }
        return arr;
    }

    public void pushOnStack(int val) {
        this.programStack.push(val);
    }
    public Integer popFromStack() {
        return this.programStack.pop();
    }
}
