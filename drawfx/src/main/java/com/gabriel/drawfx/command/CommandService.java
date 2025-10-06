package com.gabriel.drawfx.command;

import java.util.Stack;
public class CommandService {
    public static Stack<Command> undoStack = new Stack<Command>();
    public static Stack<Command> redoStack = new Stack<Command>();

    public static void ExecuteCommand(Command command) {
        command.execute();
        undoStack.push(command);
    }

    public static void undo() {
        System.out.println("undo done");
        if (undoStack.empty())
            return;
        Command command = undoStack.pop();
        command.undo();
        redoStack.push(command);
    }

    public static void redo() {
        System.out.println("redo done");
        if (redoStack.empty())
            return;
        Command command = redoStack.pop();
        command.execute();
        undoStack.push(command);
    }

    public static boolean canUndo(){
        return !undoStack.empty();
    }

    public static boolean canRedo(){
        return !redoStack.empty();
    }

}