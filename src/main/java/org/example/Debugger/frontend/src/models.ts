export interface ProgramStateUnParsed {
    programCounter: number;
    programSource: number[];
    displayMatrixAsString: string;
    allRegisterNames: string[];
    allRegisters: number[];
    breakPoints: number[];
    playPaused: boolean;
}

export interface ProgramStateParsed {
    lineNumber: number;
    programSource: number[];
    displayMatrix: number[][];
    allRegisterNames: string[];
    allRegisters: number[];
    programCounter: number;
    breakPoints: Set<number>;
}

export type PostAction =
    | "Next"
    | "Auto"
    | "Stop"
    | "Pause"
    | "Reset"
    | "Resume"
    | "ToggleBreakPoint"
    | "KeyUp"
    | "KeyDown"