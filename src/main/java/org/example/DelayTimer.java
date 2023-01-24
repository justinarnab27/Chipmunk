package org.example;

import java.util.TimerTask;
import lombok.Getter;
import lombok.Setter;

public class DelayTimer extends TimerTask {
    @Getter
    @Setter
    private static int count = 0;

    @Override
    public void run() {
        if (count > 0) {
            count--;
        }
    }
}
