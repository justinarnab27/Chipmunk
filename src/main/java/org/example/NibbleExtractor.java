package org.example;

import org.example.Instructions.Add;
import org.example.Instructions.Clear;
import org.example.Instructions.Display;
import org.example.Instructions.Jump;
import org.example.Instructions.SetIndex;
import org.example.Instructions.SetToNN;

public class NibbleExtractor {
    /**
     * Processes the instructions
     * and calls the necessary functions
     */
    private int[] getNibbleFrom16Bit(int firstByte, int secondByte) {
        // Takes two two-bytes and returns a array of nibbles (4bit)
        // So two two-bytes 0xPQ 0xRS will return [0xP, 0xQ, 0xR, 0xS]
        int nibbles[] = new int[4];
        nibbles[0] = (firstByte & 0xFF) >> 4;
        nibbles[1] = firstByte & 0xF;
        nibbles[2] = (secondByte & 0xFF) >> 4;
        nibbles[3] = secondByte & 0xF;
        return nibbles;
    }

    public void extract(Instruction instruction, ProgramState programState) throws Exception {
        //TODO: Maybe make a separate Instructions class for instructions
        //TODO: Give exception a custom type
        //X Y N
        /** Takes instruction as input and current program state
         * calls the necessary function to excute the instruction
         * and modify the state
         *  List of Instructions:
            <li>00E0: Clear screen</li>
            <li>1NNN: Jump</li>
            <li>00EE and 2NNN: Subroutines</li>
            <li>3XNN, 4XNN, 5XY0 and 9XY0: Skip</li>
            <li>6XNN: Set</li>
            <li>7XNN: Add</li>
            <li>8XY0: Set</li>
            <li>8XY1: Binary OR</li>
            <li>8XY2: Binary AND</li>
            <li>8XY3: Logical XOR</li>
            <li>8XY4: Add</li>
            <li>8XY5 and 8XY7: Subtract</li>
            <li>8XY6 and 8XYE: Shift</li>
            <li>ANNN: Set index</li>
            <li>BNNN: Jump with offset</li>
            <li>CXNN: Random</li>
            <li>DXYN: Display</li>
            <li>EX9E and EXA1: Skip if key</li>
            <li>FX07, FX15 and FX18: Timers</li>
            <li>FX1E: Add to index</li>
            <li>FX0A: Get key</li>
            <li>FX29: Font character</li>
            <li>FX33: Binary-coded decimal conversion</li>
        */
        int firstByte = instruction.firstBye;
        int secondByte = instruction.secondByte;

        int[] nibbles = getNibbleFrom16Bit(firstByte, secondByte);

        switch (nibbles[0]) {
            case 0x0:
                switch (nibbles[3]) {
                    case 0x0:
                        System.out.println("Clear Screen");
                        Clear.execute(programState);
                        break;
                    case 0xE:
                        System.out.println("Subroutines");
                        break;
                    default:
                        throw new Exception(String.format("Unknown Instruction %02X%02X", firstByte, secondByte));
                }
                break;
            case 0x1:
                System.out.println("Jump");
                Jump.execute(Utility.combineThreeNibbles(nibbles[1], nibbles[2], nibbles[3]), programState);
                break;
            case 0x2:
                System.out.println("Subroutines");
                break;
            case 0x3:
                System.out.println("Skip");
                break;
            case 0x4:
                System.out.println("Skip");
                break;
            case 0x5:
                System.out.println("Skip");
                break;
            case 0x6:
                System.out.println("Set");
                SetToNN.execute(nibbles[1], Utility.combineTwoNibbles(nibbles[2], nibbles[3]), programState);
                break;
            case 0x7:
                System.out.println("Add");
                Add.execute(nibbles[1], Utility.combineTwoNibbles(nibbles[2], nibbles[3]), programState);
                break;
            case 0x8:
                switch (nibbles[3]) {
                    case 0x0:
                        System.out.println("Set");
                        break;
                    case 0x1:
                        System.out.println("Binary OR");
                        break;
                    case 0x2:
                        System.out.println("Binary AND");
                        break;
                    case 0x3:
                        System.out.println("Logical XOR");
                        break;
                    case 0x4:
                        System.out.println("Add");
                        break;
                    case 0x5:
                        System.out.println("Subtract");
                        break;
                    case 0x6:
                        System.out.println("Shift");
                        break;
                    case 0x7:
                        System.out.println("Subtract");
                        break;
                    case 0xE:
                        System.out.println("Shift");
                        break;
                    default:
                        throw new Exception(String.format("Unknown Instruction %02X%02X", firstByte, secondByte));
                }
                break;
            case 0x9:
                System.out.println("Skip");
                break;
            case 0xA:
                System.out.println("Set index");
                SetIndex.execute(Utility.combineThreeNibbles(nibbles[1], nibbles[2], nibbles[3]), programState);
                break;
            case 0xB:
                System.out.println("Jump with offset");
                break;
            case 0xC:
                System.out.println("Random");
                break;
            case 0xD:
                System.out.println("Display");
                Display.execute(nibbles[1], nibbles[2], nibbles[3], programState);
                break;
            case 0xE:
                System.out.println("Skip if key");
                break;
            case 0xF:
                switch (Utility.combineTwoNibbles(nibbles[2], nibbles[3])) {
                    case 0x07:
                        System.out.println("Timers");
                        break;
                    case 0x15:
                        System.out.println("Timers");
                        break;
                    case 0x18:
                        System.out.println("Timers");
                        break;
                    case 0x1E:
                        System.out.println("Add to index");
                        break;
                    case 0x0A:
                        System.out.println("Get key");
                        break;
                    case 0x29:
                        System.out.println("Font character");
                        break;
                    case 0x33:
                        System.out.println("Binary-coded decimal conversion");
                        break;
                    case 0x55:
                        System.out.println("Store and load memory");
                        break;
                    case 0x65:
                        System.out.println("Store and load memory");
                        break;
                    default:
                        throw new Exception(String.format("Unknown Instruction %02X%02X", firstByte, secondByte));
                }
                break;
            default:
                throw new Exception(String.format("Unknown Instruction %02X%02X", firstByte, secondByte));
        }
    }
}
