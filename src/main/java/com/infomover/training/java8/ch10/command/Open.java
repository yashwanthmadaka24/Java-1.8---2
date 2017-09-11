package com.infomover.training.java8.ch10.command;

/**
 * Concrete Command
 * 
 * @author MuhammedShakir
 *
 */
public class Open implements Command {

    private final Editor editor;

    public Open(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.open();
    }
}
