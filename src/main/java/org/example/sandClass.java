package org.example;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class sandClass {
    int x;
    String y;
    @JsonIgnore
    int[] z;

    SandCar sandCar;

    public sandClass() {
        x = 5;
        y = "Elephants are cool!";
        z = new int[] {4, 5, 7, 9};
        this.sandCar = new SandCar();
    }
}
