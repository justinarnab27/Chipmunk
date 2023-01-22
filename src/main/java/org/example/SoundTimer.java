package org.example;

import java.util.TimerTask;
import lombok.Getter;
import lombok.Setter;

public class SoundTimer extends TimerTask {
    @Setter
    private static int count = 0;

    @Override
    public void run() {
        if (count > 0) {
            count--;
        }
        System.out.println("Sound Timer Value: " + count);
    }
}
