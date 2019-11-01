package com.mslc.training.java8.ch10.command;

/**
 * Concrete Command
 * 
 * 
 * @author MuhammedShakir
 *
 */
public class Close implements Command {

    private final Editor editor;

    public Close(Editor editor) {
        this.editor = editor;
    }

    @Override
    public void execute() {
        editor.close();
    }

}
