package org.example;

import lombok.NonNull;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SourceReader {
    //TODO: add final
    public byte[] read(@NonNull String inputFile) {
        /**
         * Takes a binary file as input (inputFile) and returns
         * an array of bytes
         */
        //IBM Logo.ch8
        inputFile = "/Users/arnab/Downloads/Tetris [Fran Dachille, 1991].ch8";
//        inputFile = "/Users/arnab/Downloads/test_opcode.ch8";
        try {
            byte[] array = Files.readAllBytes(Paths.get(inputFile));
            return array;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
