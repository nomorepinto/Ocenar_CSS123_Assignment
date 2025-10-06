package com.gabriel.draw.controller;

import com.gabriel.drawfx.ActionCommand;
import com.gabriel.drawfx.ShapeMode;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.DrawMode;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionController implements ActionListener {
    AppService appService;
    public  ActionController(AppService appService){
        this.appService = appService;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();
        if(ActionCommand.UNDO.equals(cmd)) {
            appService.undo();
        }
        else if(ActionCommand.REDO.equals(cmd)) {
            appService.redo();
        }
        else if(ActionCommand.LINE.equals(cmd)) {
            appService.setShapeMode(ShapeMode.Line);
            appService.setDrawMode(DrawMode.Idle);
        }
        else if(ActionCommand.RECT.equals(cmd)) {
            appService.setShapeMode(ShapeMode.Rectangle);
            appService.setDrawMode(DrawMode.Idle);
        }
        else if(ActionCommand.ELLIPSE.equals(cmd)) {
            appService.setShapeMode(ShapeMode.Ellipse);
            appService.setDrawMode(DrawMode.Idle);
        }
        else if(ActionCommand.SELECT.equals(cmd)) {
            appService.setDrawMode(DrawMode.SelectMode);
        }
        else if(ActionCommand.DRAW_MODE.equals(cmd)) {
            appService.setDrawMode(DrawMode.Idle);

            appService.setShapeMode(ShapeMode.Line);
        }
        else if(ActionCommand.SETCOLOR.equals(cmd)){
            Color selectedColor = JColorChooser.showDialog(null, "Choose a Color", Color.WHITE);
            appService.setColor(selectedColor);
        }
    }
}

