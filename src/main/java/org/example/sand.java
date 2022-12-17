package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;



public class sand {
    public static void main(String[] args) throws IOException {
//        System.out.println("Arnab\\nBala");
        ObjectMapper objectMapper = new ObjectMapper();
        sandClass sand_class = new sandClass();
        objectMapper.writeValue(new File("/Users/arnab/Downloads/car.json"), sand_class);

    }
}
