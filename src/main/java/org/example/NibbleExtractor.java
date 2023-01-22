package org.example;

import org.example.Instructions.Add;
import org.example.Instructions.AddToI;
import org.example.Instructions.AddWithCarry;
import org.example.Instructions.BinaryAND;
import org.example.Instructions.BinaryOR;
import org.example.Instructions.CallSubroutine;
import org.example.Instructions.Clear;
import org.example.Instructions.ConvertToDecimal;
import org.example.Instructions.Display;
import org.example.Instructions.Jump;
import org.example.Instructions.JumpWithOffset;
import org.example.Instructions.LeftShift;
import org.example.Instructions.LoadDelayTimer;
import org.example.Instructions.LoadMemory;
import org.example.Instructions.LogicalXOR;
import org.example.Instructions.RandomAnd;
import org.example.Instructions.ReturnFromSubroutine;
import org.example.Instructions.RightShift;
import org.example.Instructions.SetDelayTimer;
import org.example.Instructions.SetIndex;
import org.example.Instructions.SetSoundTimer;
import org.example.Instructions.SetToNN;
import org.example.Instructions.SetToVY;
import org.example.Instructions.Skip3;
import org.example.Instructions.Skip4;
import org.example.Instructions.Skip5;
import org.example.Instructions.Skip9;
import org.example.Instructions.SkipIfKey;
import org.example.Instructions.SkipIfNotKey;
import org.example.Instructions.StoreMemory;
import org.example.Instructions.SubtractVXFromVY;
import org.example.Instructions.SubtractVYFromVX;

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
                        System.out.println("Returning From Subroutine");
                        ReturnFromSubroutine.execute(programState);
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
                System.out.println("Calling Subroutine");
                CallSubroutine.execute(Utility.combineThreeNibbles(nibbles[1], nibbles[2], nibbles[3]), programState);
                break;
            case 0x3:
                System.out.println("Skip3");
                Skip3.execute(nibbles[1], Utility.combineTwoNibbles(nibbles[2], nibbles[3]), programState);
                break;
            case 0x4:
                System.out.println("Skip4");
                Skip4.execute(nibbles[1], Utility.combineTwoNibbles(nibbles[2], nibbles[3]), programState);
                break;
            case 0x5:
                System.out.println("Skip5");
                Skip5.execute(nibbles[1], nibbles[2], programState);
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
                        SetToVY.execute(nibbles[1], nibbles[2], programState);
                        break;
                    case 0x1:
                        System.out.println("Binary OR");
                        BinaryOR.execute(nibbles[1], nibbles[2], programState);
                        break;
                    case 0x2:
                        System.out.println("Binary AND");
                        BinaryAND.execute(nibbles[1], nibbles[2], programState);
                        break;
                    case 0x3:
                        System.out.println("Logical XOR");
                        LogicalXOR.execute(nibbles[1], nibbles[2], programState);
                        break;
                    case 0x4:
                        System.out.println("Add with carry");
                        AddWithCarry.execute(nibbles[1], nibbles[2], programState);
                        break;
                    case 0x5:
                        System.out.println("Subtract VX - VY");
                        SubtractVYFromVX.execute(nibbles[1], nibbles[2], programState);
                        break;
                    case 0x6:
                        System.out.println("Right Shift");
                        RightShift.execute(nibbles[1], nibbles[2], programState);
                        break;
                    case 0x7:
                        System.out.println("Subtract VY - VX");
                        SubtractVXFromVY.execute(nibbles[1], nibbles[2], programState);
                        break;
                    case 0xE:
                        System.out.println("Left Shift");
                        LeftShift.execute(nibbles[1], nibbles[2], programState);
                        break;
                    default:
                        throw new Exception(String.format("Unknown Instruction %02X%02X", firstByte, secondByte));
                }
                break;
            case 0x9:
                System.out.println("Skip9");
                Skip9.execute(nibbles[1], nibbles[2], programState);
                break;
            case 0xA:
                System.out.println("Set index");
                SetIndex.execute(Utility.combineThreeNibbles(nibbles[1], nibbles[2], nibbles[3]), programState);
                break;
            case 0xB:
                System.out.println("Jump with offset");
                JumpWithOffset.execute(Utility.combineThreeNibbles(nibbles[1], nibbles[2], nibbles[3]), programState);
                break;
            case 0xC:
                System.out.println("Random");
                RandomAnd.execute(nibbles[1], Utility.combineTwoNibbles(nibbles[2], nibbles[3]), programState);
                break;
            case 0xD:
                System.out.println("Display");
                Display.execute(nibbles[1], nibbles[2], nibbles[3], programState);
                break;
            case 0xE:
                switch (Utility.combineTwoNibbles(nibbles[2], nibbles[3])) {
                    case 0x9E:
                        System.out.println("Skip if key");
                        SkipIfKey.execute(nibbles[1], programState);
                        break;
                    case 0xA1:
                        System.out.println("Skip if not key");
                        SkipIfNotKey.execute(nibbles[1], programState);
                        break;
                    default:
                        throw new Exception(String.format("Unknown Instruction %02X%02X", firstByte, secondByte));
                }
                break;
            case 0xF:
                switch (Utility.combineTwoNibbles(nibbles[2], nibbles[3])) {
                    case 0x07:
                        System.out.println("Load Delay Timer");
                        LoadDelayTimer.execute(nibbles[1], programState);
                        break;
                    case 0x15:
                        System.out.println("Set Delay Timer");
                        SetDelayTimer.execute(nibbles[1], programState);
                        break;
                    case 0x18:
                        System.out.println("Set Sound Timer");
                        SetSoundTimer.execute(nibbles[1], programState);
                        break;
                    case 0x1E:
                        System.out.println("Add to index");
                        AddToI.execute(nibbles[1], programState);
                        break;
                    case 0x0A:
                        System.out.println("Get key");
                        break;
                    case 0x29:
                        System.out.println("Font character");
                        break;
                    case 0x33:
                        System.out.println("Binary-coded decimal conversion");
                        ConvertToDecimal.execute(nibbles[1], programState);
                        break;
                    case 0x55:
                        System.out.println("Store and load memory");
                        StoreMemory.execute(nibbles[1], programState);
                        break;
                    case 0x65:
                        System.out.println("Store and load memory");
                        LoadMemory.execute(nibbles[1], programState);
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
