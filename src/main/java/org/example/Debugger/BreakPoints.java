package org.example.Debugger;

import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;

public class BreakPoints {
    @Getter
    @Setter
    private Set<Integer> bps; // Set of all breakpoints

    public BreakPoints() {
        this.bps = new HashSet<>();
    }

    public void toggleBreakPoint(int ix) {
        if (this.bps.contains(ix)) {
            this.bps.remove(ix);
        } else {
            this.bps.add(ix);
        }
    }

    public boolean isABreakPoint(int lineNumber) {
        return this.bps.contains(lineNumber);
    }
}
