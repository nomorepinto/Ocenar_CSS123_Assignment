package com.gabriel.draw.view;

import com.gabriel.draw.controller.DrawingController;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.*;

public class DrawingView extends JPanel {
    private final AppService appService;
    private Shape currentShape;

    public DrawingView(AppService appService) {
        this.appService = appService;

        new DrawingController(appService, this);
    }

    public void setCurrentShape(Shape shape) {
        this.currentShape = shape;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);  // Clears the screen

        Drawing drawing = (Drawing) appService.getModel();
        for (Shape shape : drawing.getShapes()) {
            shape.getRendererService().render(g, shape, false);
        }

        if (currentShape != null) {
            currentShape.getRendererService().render(g, currentShape, false);
        }
    }
}
