package com.gabriel.draw.controller;
import com.gabriel.draw.model.Ellipse;
import com.gabriel.draw.model.Line;
import com.gabriel.draw.model.Rectangle;
import com.gabriel.drawfx.DrawMode;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.drawfx.model.Shape;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class DrawingController  implements MouseListener, MouseMotionListener {
    private Point end;
    final private DrawingView drawingView;

    Shape currentShape;
    AppService appService;
    public DrawingController(AppService appService, DrawingView drawingView){
        this.appService = appService;
        this.drawingView = drawingView;
        drawingView.addMouseListener(this);
        drawingView.addMouseMotionListener(this);
        appService.setDrawMode(DrawMode.Idle);
    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point start = e.getPoint();

        if (appService.getDrawMode() == DrawMode.Idle) {
            switch (appService.getShapeMode()) {
                case Line:
                    currentShape = new Line(start, start);
                    break;
                case Ellipse:
                    currentShape = new Ellipse(start, start);
                    break;
                case Rectangle:
                    currentShape = new Rectangle(start, start);
                    break;
                default:
                    return;
            }

            appService.setDrawMode(DrawMode.MousePressed);

            drawingView.setCurrentShape(currentShape);
            drawingView.repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (appService.getDrawMode() == DrawMode.MousePressed) {
            end = e.getPoint();
            appService.scale(currentShape, end);
            appService.create(currentShape);
            appService.setDrawMode(DrawMode.Idle);

            drawingView.setCurrentShape(null);
            drawingView.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (appService.getDrawMode() == DrawMode.MousePressed) {
            end = e.getPoint();
            appService.scale(currentShape, end);

            drawingView.setCurrentShape(currentShape);
            drawingView.repaint();
        }
    }



    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
