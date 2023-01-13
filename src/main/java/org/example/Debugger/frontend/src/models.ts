export interface ProgramStateUnParsed {
    programCounter: number;
    programSource: number[];
    displayMatrixAsString: string;
    allRegisterNames: string[];
    allRegisters: number[];
}

export interface ProgramStateParsed {
    lineNumber: number;
    programSource: number[];
    displayMatrix: number[][];
    allRegisterNames: string[];
    allRegisters: number[];
}

export type PostAction =
    | "Next"
    | "Auto"
    | "Stop"
    | "Pause"
    | "Reset"
    | "Resume"
// export interface ProgramStateParsed {
//     programSource: number[];
// }