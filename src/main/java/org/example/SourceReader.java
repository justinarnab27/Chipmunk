package org.example;

import java.nio.file.Path;
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
        String romName = "Pong [Paul Vervalin, 1990].ch8";
//        String romName = "Tetris [Fran Dachille, 1991].ch8";
//        String romName = "test_opcode.ch8";
//        String romName = "IBM Logo.ch8";
//        String romName = "Tic-Tac-Toe [David Winter].ch8";
//        String romName = "Chip8 Picture.ch8";

        String romDir = "./rom/";
        Path path = Paths.get(romDir, romName);
        try {
            byte[] array = Files.readAllBytes(path);
            return array;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
