package com.gabriel.drawfx.service;

import com.gabriel.drawfx.model.Shape;

import java.awt.*;

public final class MoverService {
    public void move(Shape shape, Point newLoc){
        // Calculate the offset
        int dx = newLoc.x - shape.getLocation().x;
        int dy = newLoc.y - shape.getLocation().y;

        // Move both location and end point by the same amount
        shape.setLocation(newLoc);
        Point newEnd = new Point(shape.getEnd().x + dx, shape.getEnd().y + dy);
        shape.setEnd(newEnd);
    }
}