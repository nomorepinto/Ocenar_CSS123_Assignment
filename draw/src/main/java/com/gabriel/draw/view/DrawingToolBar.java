package com.gabriel.draw.view;

import com.gabriel.draw.controller.ActionController;
import com.gabriel.drawfx.ActionCommand;
import com.gabriel.drawfx.command.CommandService;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class DrawingToolBar extends JToolBar {
    JButton undoButton;
    JButton redoButton;
    public DrawingToolBar(ActionListener actionListener){


        undoButton = new JButton();
        String undoImgLocation = "undo.png";
        URL undoImageURL = DrawingToolBar.class.getResource(undoImgLocation);
            undoButton.setActionCommand(ActionCommand.UNDO);
            undoButton.setToolTipText("Undo");
            undoButton.addActionListener(actionListener);
            add(undoButton);
            undoButton.setIcon(new ImageIcon(undoImageURL, "Undo"));

            redoButton = new JButton();
        String redoImgLocation = "redo.png";
        URL redoImageURL = DrawingToolBar.class.getResource(redoImgLocation);
            redoButton.setActionCommand(ActionCommand.REDO);
            redoButton.setToolTipText("Redo");
            redoButton.addActionListener(actionListener);
            add(redoButton);
            redoButton.setIcon(new ImageIcon(redoImageURL, "Redo"));


        JButton selectButton = new JButton();
        String selectImgLocation = "select.png";  // You'll need to add this icon
        URL selectImageURL = DrawingToolBar.class.getResource(selectImgLocation);
        selectButton.setActionCommand(ActionCommand.SELECT);
        selectButton.setToolTipText("Select");
        selectButton.addActionListener(actionListener);
        add(selectButton);
        if (selectImageURL != null) {
            selectButton.setIcon(new ImageIcon(selectImageURL, "Select"));
        } else {
            selectButton.setText("Select");  // Fallback if no icon
        }


        JButton drawModeButton = new JButton();
        String drawModeImgLocation = "draw.png";  // You'll need to add this icon
        URL drawModeImageURL = DrawingToolBar.class.getResource(drawModeImgLocation);
        drawModeButton.setActionCommand(ActionCommand.DRAW_MODE);
        drawModeButton.setToolTipText("Draw Mode");
        drawModeButton.addActionListener(actionListener);
        add(drawModeButton);
        if (drawModeImageURL != null) {
            drawModeButton.setIcon(new ImageIcon(drawModeImageURL, "Draw Mode"));
        } else {
            drawModeButton.setText("Draw");
        }

        addSeparator();


        JButton rectButton = new JButton();
        String rectImgLocation = "rect.png";
        URL rectImageURL = DrawingToolBar.class.getResource(rectImgLocation);
            rectButton.setActionCommand(ActionCommand.RECT);
            rectButton.setToolTipText("Rectangle");
            rectButton.addActionListener(actionListener);
            add(rectButton);
            rectButton.setIcon(new ImageIcon(rectImageURL, "Rectangle"));


        JButton lineButton = new JButton();
        String lineImgLocation = "line.png";
        URL lineImageURL = DrawingToolBar.class.getResource(lineImgLocation);
            lineButton.setActionCommand(ActionCommand.LINE);
            lineButton.setToolTipText("Line");
            lineButton.addActionListener(actionListener);
            add(lineButton);
            lineButton.setIcon(new ImageIcon(lineImageURL, "Line"));


        JButton ellipseButton = new JButton();
        String ellipseImgLocation = "oval.png";
        URL ellipseImageURL = DrawingToolBar.class.getResource(ellipseImgLocation);
            ellipseButton.setActionCommand(ActionCommand.ELLIPSE);
            ellipseButton.setToolTipText("Ellipse");
            ellipseButton.addActionListener(actionListener);
            add(ellipseButton);
            ellipseButton.setIcon(new ImageIcon(ellipseImageURL, "Ellipse"));


        JButton colorButton = new JButton();
        String colorImgLocation = "color.png";
        URL colorImageURL = DrawingToolBar.class.getResource(colorImgLocation);
            colorButton.setActionCommand(ActionCommand.SETCOLOR);
            colorButton.setToolTipText("Set Color");
            colorButton.addActionListener(actionListener);
            add(colorButton);
            colorButton.setIcon(new ImageIcon(colorImageURL, "Set Color"));
    }
    public void setUndoEnabled(boolean enabled){
        undoButton.setEnabled(enabled);
    }
    public void setRedoEnabled(boolean enabled){
        redoButton.setEnabled(enabled);
    }

}
