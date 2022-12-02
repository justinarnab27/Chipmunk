package org.example;

public class ProgramLoop {
    private byte[] programSource;

    public ProgramLoop(byte[] source) {
        this.programSource = source;
    }

    public void run() throws Exception {
        boolean sleepBetweenInstructions = true;  // TODO: Should be moved to console args map
        System.out.println("Started Running the Program...");
        System.out.println("Creating Program State...");
        //TODO: Replace with dependency injection
        ProgramState programState = new ProgramState(this.programSource);
        NibbleExtractor nibbleExtractor = new NibbleExtractor();
        int MAX_ITER = 100;  // Program stops after MAX_ITER iterations
        while(MAX_ITER > 0) {
            Instruction instruction = programState.getNextInstruction();
            nibbleExtractor.extract(instruction, programState);
            System.out.println("Program Counter: " + programState.getProgramCounter());
            programState.printRegisters();
            if (sleepBetweenInstructions) {
                Thread.sleep(500);
            }
            MAX_ITER--;
        }
    }
}
