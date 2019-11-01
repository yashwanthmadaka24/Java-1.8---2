package com.mslc.training.java8.ch10.command;

/**
 * 
 * The concrete command
 * 
 * @author MuhammedShakir
 *
 */
public class Save implements Command {

    private final Editor editor;

    public Save(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.save();
    }
}

