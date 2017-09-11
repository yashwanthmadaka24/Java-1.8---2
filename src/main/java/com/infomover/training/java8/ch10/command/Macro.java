package com.infomover.training.java8.ch10.command;

import java.util.ArrayList;
import java.util.List;

// BEGIN Macro
public class Macro {

    private final List<Command> actions;

    public Macro() {
        actions = new ArrayList<>();
    }

    public void record(Command action) {
        actions.add(action);
    }

    public void run() {
        actions.forEach(Command::execute);
    }

}
// END Macro
