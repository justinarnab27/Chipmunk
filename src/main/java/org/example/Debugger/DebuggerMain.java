package org.example.Debugger;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Objects;
import java.util.Set;
import org.example.Instruction;
import org.example.NibbleExtractor;
import org.example.ProgramState;
import org.example.Utility;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DebuggerMain {
    static ProgramState programState;
    static NibbleExtractor nibbleExtractor;
    static boolean autoPlay = false;
    private static byte[] programSource;
    public void startDebugger(byte[] source) throws Exception {
        programSource = source;
        SpringApplicationBuilder builder = new SpringApplicationBuilder(DebuggerMain.class);
        builder.headless(false).run();
        programState = new ProgramState(programSource, true);
        this.nibbleExtractor = new NibbleExtractor();
        boolean sleepBetweenInstructions = true;  // TODO: Should be moved to console args map
        System.out.println("Started Running the Program...");
        // TODO: Replace with dependency injection
        int MAX_ITER = 100;  // Program stops after MAX_ITER iterations
        if(sleepBetweenInstructions) return;
        while(MAX_ITER > 0) {
            Instruction instruction = programState.getNextInstruction();
            nibbleExtractor.extract(instruction, programState);
            programState.printRegisters();
            if (sleepBetweenInstructions) {
                Thread.sleep(20);
            }
            MAX_ITER--;
        }
    }

    public void handleNext() throws Exception {
        Instruction instruction = programState.getNextInstruction();
        nibbleExtractor.extract(instruction, programState);
        programState.printRegisters();
    }

    public void handleAuto() throws Exception {
        boolean sleepBetweenInstructions = true;
        autoPlay = true;
        programState.setPlayPaused(false);
        while(autoPlay) {
            if(!programState.isPlayPaused()) {
                if (programState.isABreakPoint(Utility.covertPCToLine(programState.getProgramCounter())) && !programState.isGoOverBreakPoint()) {
                    programState.setPlayPaused(true);    // Pauses when break point is hit
                    programState.setGoOverBreakPoint(true);  // Next time it won't be paused by break point
                    continue;
                }
                programState.setGoOverBreakPoint(false);    // Revert back to it
                handleNext();
            }
            if (sleepBetweenInstructions) {
                Thread.sleep(2);
            }
        }
    }

    public void handleReset() {
        // Ensures that the break points don't get reset
        Set<Integer> bps = programState.getBreakPoints();
        programState = new ProgramState(programSource, true);
        programState.setBreakPoints(bps);
    }

    public void handleToggleBreakPoint(int ix) {
        programState.toggleBreakPoint(ix);
    }
//static int count = 0;
    @CrossOrigin
    @GetMapping("/")
    public ProgramState getDisplayMatrix() {
        if (Objects.isNull(programState)) {
            return null;
        } else {
            ObjectMapper objectMapper = new ObjectMapper();
            return programState;
        }
    }

    @CrossOrigin
    @PostMapping("/")
    public void nextInstruction(@RequestBody String actionBody) throws Exception {
        System.out.println("Received Request: " + actionBody);
        String[] actions = actionBody.split(" ");
        String action = actions[0];
        switch (action) {
            case "Next":
                System.out.println("Next");
                handleNext();
                break;
            case "Auto":
                System.out.println("Auto");
                handleAuto();
                break;
            case "FromStart":
                break;
            case "Stop":
                System.out.println("Stop");
                autoPlay = false;
                break;
            case "Pause":
                System.out.println("Pause");
                programState.setPlayPaused(true);
                break;
            case "Resume":
                System.out.println("Resume");
                programState.setPlayPaused(false);
                break;
            case "Reset":
                System.out.println("Reset");
                handleReset();
                break;
            case "ToggleBreakPoint":
                int ix = Integer.parseInt(actions[1]);
                System.out.println("Toggle Break Point " + ix);
                handleToggleBreakPoint(ix);
                break;
            case "KeyUp":
                System.out.println("KeyUp " + actions[1]);
                programState.setKeyBeingPressed(-1);
                break;
            case "KeyDown":
                System.out.println("KeyDown " + actions[1]);
                programState.setKeyBeingPressed(Integer.parseInt(actions[1]));
                break;
            default:
                System.out.println("This is a green apple!");
        }
    }
}
