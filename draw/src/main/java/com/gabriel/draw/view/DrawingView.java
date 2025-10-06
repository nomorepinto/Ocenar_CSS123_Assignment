package com.gabriel.draw.view;

import com.gabriel.draw.controller.DrawingController;
import com.gabriel.draw.controller.DrawingWindowController;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;

import javax.swing.*;
import java.awt.*;

public class DrawingView extends JPanel {
    AppService appService;

    public DrawingView(AppService appService){

        this.appService = appService;
        appService.setView(this);

    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Drawing drawing = (Drawing) appService.getModel();

        for(Shape shape : drawing.getShapes()){
            shape.getRendererService().render(g, shape, false);


            if(shape.isSelected()) {
                Graphics2D g2d = (Graphics2D) g;
                Stroke oldStroke = g2d.getStroke();
                Color oldColor = g2d.getColor();

                Point loc = shape.getLocation();
                Point end = shape.getEnd();
                int minX = Math.min(loc.x, end.x);
                int maxX = Math.max(loc.x, end.x);
                int minY = Math.min(loc.y, end.y);
                int maxY = Math.max(loc.y, end.y);
                int width = Math.abs(end.x - loc.x);
                int height = Math.abs(end.y - loc.y);


                g2d.setColor(Color.BLUE);
                g2d.setStroke(new BasicStroke(2,
                        BasicStroke.CAP_BUTT,
                        BasicStroke.JOIN_BEVEL,
                        0,
                        new float[]{5},
                        0));

                g2d.drawRect(minX - 2, minY - 2, width + 4, height + 4);

                g2d.setStroke(new BasicStroke(1));
                int handleSize = 8;
                int halfHandle = handleSize / 2;


                g2d.setColor(Color.WHITE);
                g2d.fillRect(minX - halfHandle, minY - halfHandle, handleSize, handleSize);
                g2d.setColor(Color.BLUE);
                g2d.drawRect(minX - halfHandle, minY - halfHandle, handleSize, handleSize);

                g2d.setColor(Color.WHITE);
                g2d.fillRect(maxX - halfHandle, minY - halfHandle, handleSize, handleSize);
                g2d.setColor(Color.BLUE);
                g2d.drawRect(maxX - halfHandle, minY - halfHandle, handleSize, handleSize);

                g2d.setColor(Color.WHITE);
                g2d.fillRect(minX - halfHandle, maxY - halfHandle, handleSize, handleSize);
                g2d.setColor(Color.BLUE);
                g2d.drawRect(minX - halfHandle, maxY - halfHandle, handleSize, handleSize);

                g2d.setColor(Color.WHITE);
                g2d.fillRect(maxX - halfHandle, maxY - halfHandle, handleSize, handleSize);
                g2d.setColor(Color.BLUE);
                g2d.drawRect(maxX - halfHandle, maxY - halfHandle, handleSize, handleSize);

                g2d.setStroke(oldStroke);
                g2d.setColor(oldColor);
            }
        }
    }
}
