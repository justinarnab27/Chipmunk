package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Stack;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.SwingUtilities;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.example.Debugger.BreakPoints;


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
//    @Getter
    private BreakPoints breakPoints;
//    @JsonIgnore
    private Registers registers;
//    @JsonIgnore
    private Memory memory;
    @JsonIgnore
    private DisplayCanvas displayCanvas;
    @Getter
    @Setter
    private boolean playPaused;
    // Set to false initially, becomes true when hitting a breakpoint
    // becomes false after going over the break point
    @Getter
    @Setter
    @JsonIgnore
    private boolean goOverBreakPoint;
//    @Getter
//    @Setter
//    @JsonIgnore
    byte[] programSource;
    //TODO: This might need to be turned into a separate class if it gets sufficiently complicated
    private Stack<Integer> programStack;

    private DelayTimer delayTimer;

    private SoundTimer soundTimer;

    public ProgramState(@NonNull final byte[] source, boolean debugMode) {
        this.programCounter = Constants.PC_START;   // PC starts at 0x200 in memory
        this.registers = new Registers();
        this.memory = new Memory();
        this.programSource = source;
        this.memory.loadProgram(source);      // Loads the program in memory from 0x200 onwards
        this.displayCanvas = new DisplayCanvas();
        this.programStack = new Stack<>();
        this.breakPoints = new BreakPoints();
        this.playPaused = false;
        loadFont();
        Timer timer = new Timer();
        this.delayTimer = new DelayTimer();
        this.soundTimer = new SoundTimer();
        timer.schedule(delayTimer, 0, 17);
        timer.schedule(soundTimer, 0, 5);
        if (!debugMode) {
            SwingUtilities.invokeLater(() -> this.displayCanvas.createAndShowGui());    // Creates gui
        }
        //TODO: Also needs stack
    }

    private void loadFont() {
        int[] fonts = new int[]{0xF0, 0x90, 0x90, 0x90, 0xF0, // 0
                0x20, 0x60, 0x20, 0x20, 0x70, // 1
                0xF0, 0x10, 0xF0, 0x80, 0xF0, // 2
                0xF0, 0x10, 0xF0, 0x10, 0xF0, // 3
                0x90, 0x90, 0xF0, 0x10, 0x10, // 4
                0xF0, 0x80, 0xF0, 0x10, 0xF0, // 5
                0xF0, 0x80, 0xF0, 0x90, 0xF0, // 6
                0xF0, 0x10, 0x20, 0x40, 0x40, // 7
                0xF0, 0x90, 0xF0, 0x90, 0xF0, // 8
                0xF0, 0x90, 0xF0, 0x10, 0xF0, // 9
                0xF0, 0x90, 0xF0, 0x90, 0x90, // A
                0xE0, 0x90, 0xE0, 0x90, 0xE0, // B
                0xF0, 0x80, 0x80, 0x80, 0xF0, // C
                0xE0, 0x90, 0x90, 0x90, 0xE0, // D
                0xF0, 0x80, 0xF0, 0x80, 0xF0, // E
                0xF0, 0x80, 0xF0, 0x80, 0x80  // F]
        };

        for (int i = 0; i < fonts.length; ++i) {
            this.memory.storeAddress(Constants.FONT_MEMORY_LOCATION + i, fonts[i]);
        }
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

    public void storeAddress(@NonNull final int address, @NonNull final int val) {
        this.memory.storeAddress(address, val);
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

    public Set<Integer> getBreakPoints() {
        return this.breakPoints.getBps();
    }

    public void toggleBreakPoint(int ix) {
        this.breakPoints.toggleBreakPoint(ix);
    }

    public void setBreakPoints(Set<Integer> bps) {
        this.breakPoints.setBps(bps);
    }

    public boolean isABreakPoint(int lineNumber) {
        return this.breakPoints.isABreakPoint(lineNumber);
    }

    public boolean compareRegister(@NonNull final int registerName, @NonNull final int value) {
        return this.registers.equals(registerName, value);
    }

    public void setDelayTimer(@NonNull final int val) {
        DelayTimer.setCount(val);
    }

    public int getDelayTimer() {
        return DelayTimer.getCount();
    }

    public void setSoundTimer(@NonNull final int val) {
        SoundTimer.setCount(val);
    }

    public int getKeyBeingPressed() {
        return this.displayCanvas.getKeyBeingPressed();
    }

    public void setKeyBeingPressed(int key) {
        this.displayCanvas.setKeyBeingPressed(key);
    }
}
