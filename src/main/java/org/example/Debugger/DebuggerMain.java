package org.example.Debugger;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;
import org.example.Instruction;
import org.example.NibbleExtractor;
import org.example.ProgramState;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DebuggerMain {
    static ProgramState programState;
    private static byte[] programSource;
    public void startDebugger(byte[] source) throws Exception {
        programSource = source;
        SpringApplicationBuilder builder = new SpringApplicationBuilder(DebuggerMain.class);
        builder.headless(false).run();
        programState = new ProgramState(programSource, true);
        NibbleExtractor nibbleExtractor = new NibbleExtractor();


//        sandClass sand_class = new sandClass();


        boolean sleepBetweenInstructions = true;  // TODO: Should be moved to console args map
        System.out.println("Started Running the Program...");
//        System.out.println("Creating Program State...");
        // TODO: Replace with dependency injection
//        ProgramState programState = new ProgramState(this.programSource, false);
//        NibbleExtractor nibbleExtractor = new NibbleExtractor();
        int MAX_ITER = 100;  // Program stops after MAX_ITER iterations
        while(MAX_ITER > 0) {
            Instruction instruction = programState.getNextInstruction();
            nibbleExtractor.extract(instruction, programState);
//            System.out.println("Program Counter: " + programState.getProgramCounter());
            programState.printRegisters();
            if (sleepBetweenInstructions) {
                Thread.sleep(1500);
            }
            MAX_ITER--;
//            count++;
//            System.out.println("A " + count);
        }
    }
//static int count = 0;
    @CrossOrigin
    @GetMapping("/")
    public ProgramState getDisplayMatrix() throws JsonProcessingException {
//        programState
//        count++;
//        System.out.println("B " + count);
//        return count;
//        System.out.println("HAD BEEN INVOKED!!!!!");
        if (Objects.isNull(programState)) {
            return null;
//            return new JSONObject("Nothing!");
//            return new JSONObject("{val: Nothing!}");
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            return programState;
//            return new JSONObject(objectMapper.writeValueAsString(programState));
//            return programState.getDisplayMatrixAsString();
//            return new JSONObject("{val: HIIII}");
        }
    }
}
