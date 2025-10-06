package com.gabriel.draw.controller;

import com.gabriel.drawfx.DrawMode;
import com.gabriel.drawfx.Handle;
import com.gabriel.drawfx.model.Drawing;
import com.gabriel.drawfx.model.Shape;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.draw.view.DrawingView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class SelectModeController implements MouseListener, MouseMotionListener {
    private final AppService appService;
    private final DrawingView drawingView;

    private Shape selectedShape = null;
    private Point dragStart = null;
    private Point originalLocation = null;
    private Point originalEnd = null;
    private Handle activeHandle = Handle.NONE;

    private static final int HANDLE_SIZE = 8;

    public SelectModeController(AppService appService, DrawingView drawingView) {
        this.appService = appService;
        this.drawingView = drawingView;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (appService.getDrawMode() != DrawMode.SelectMode) return;

        Point clickPoint = e.getPoint();
        Drawing drawing = (Drawing) appService.getModel();


        if (selectedShape != null) {
            activeHandle = getHandleAt(selectedShape, clickPoint);
            System.out.println("Active handle: " + activeHandle);
            if (activeHandle != Handle.NONE) {
                dragStart = clickPoint;
                originalLocation = new Point(selectedShape.getLocation());
                originalEnd = new Point(selectedShape.getEnd());
                return;
            }
        }


        if (selectedShape != null) {
            selectedShape.setSelected(false);
        }


        selectedShape = null;
        for (int i = drawing.getShapes().size() - 1; i >= 0; i--) {
            Shape shape = drawing.getShapes().get(i);
            if (contains(shape, clickPoint)) {
                selectedShape = shape;
                selectedShape.setSelected(true);
                dragStart = clickPoint;
                originalLocation = new Point(shape.getLocation());
                originalEnd = new Point(shape.getEnd());
                activeHandle = Handle.NONE; // Moving, not scaling
                break;
            }
        }

        appService.repaint();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (appService.getDrawMode() != DrawMode.SelectMode) return;
        if (selectedShape == null || dragStart == null) return;

        Point currentPoint = e.getPoint();

        if (activeHandle != Handle.NONE) {

            scaleShape(currentPoint);
        } else {

            int dx = currentPoint.x - dragStart.x;
            int dy = currentPoint.y - dragStart.y;

            Point newLocation = new Point(
                    originalLocation.x + dx,
                    originalLocation.y + dy
            );

            appService.move(selectedShape, newLocation);
        }

        appService.repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        dragStart = null;
        originalLocation = null;
        originalEnd = null;
        activeHandle = Handle.NONE;
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (appService.getDrawMode() != DrawMode.SelectMode) return;
        if (selectedShape == null) return;

        // Change cursor based on handle hover
        Handle hoveredHandle = getHandleAt(selectedShape, e.getPoint());
        updateCursor(hoveredHandle);
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {
        drawingView.setCursor(Cursor.getDefaultCursor());
    }


    private boolean contains(Shape shape, Point p) {
        Point loc = shape.getLocation();
        Point end = shape.getEnd();

        int minX = Math.min(loc.x, end.x);
        int maxX = Math.max(loc.x, end.x);
        int minY = Math.min(loc.y, end.y);
        int maxY = Math.max(loc.y, end.y);

        return p.x >= minX && p.x <= maxX && p.y >= minY && p.y <= maxY;
    }


    private Handle getHandleAt(Shape shape, Point p) {
        Point loc = shape.getLocation();
        Point end = shape.getEnd();

        int minX = Math.min(loc.x, end.x);
        int maxX = Math.max(loc.x, end.x);
        int minY = Math.min(loc.y, end.y);
        int maxY = Math.max(loc.y, end.y);


        if (isPointInHandle(p, minX, minY)) return Handle.TOP_LEFT;
        if (isPointInHandle(p, maxX, minY)) return Handle.TOP_RIGHT;
        if (isPointInHandle(p, minX, maxY)) return Handle.BOTTOM_LEFT;
        if (isPointInHandle(p, maxX, maxY)) return Handle.BOTTOM_RIGHT;

        return Handle.NONE;
    }


    private boolean isPointInHandle(Point p, int hx, int hy) {
        int hitArea = HANDLE_SIZE;
        return Math.abs(p.x - hx) <= hitArea &&
                Math.abs(p.y - hy) <= hitArea;
    }


    private void updateCursor(Handle handle) {
        Cursor cursor;
        switch (handle) {
            case TOP_LEFT:
            case BOTTOM_RIGHT:
                cursor = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR);
                break;
            case TOP_RIGHT:
            case BOTTOM_LEFT:
                cursor = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR);
                break;
            default:
                cursor = Cursor.getDefaultCursor();
                break;
        }
        drawingView.setCursor(cursor);
    }


    private void scaleShape(Point currentPoint) {
        int dx = currentPoint.x - dragStart.x;
        int dy = currentPoint.y - dragStart.y;

        Point newLocation = new Point(originalLocation);
        Point newEnd = new Point(originalEnd);

        switch (activeHandle) {
            case TOP_LEFT:
                newLocation.x = originalLocation.x + dx;
                newLocation.y = originalLocation.y + dy;
                break;
            case TOP_RIGHT:
                newEnd.x = originalEnd.x + dx;
                newLocation.y = originalLocation.y + dy;
                break;
            case BOTTOM_LEFT:
                newLocation.x = originalLocation.x + dx;
                newEnd.y = originalEnd.y + dy;
                break;
            case BOTTOM_RIGHT:
                newEnd.x = originalEnd.x + dx;
                newEnd.y = originalEnd.y + dy;
                break;
        }


        selectedShape.setLocation(newLocation);
        appService.scale(selectedShape, newEnd);
    }


    public static int getHandleSize() {
        return HANDLE_SIZE;
    }
}