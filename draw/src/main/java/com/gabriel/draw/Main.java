package com.gabriel.draw;

import com.gabriel.draw.controller.ActionController;
import com.gabriel.draw.view.DrawingMenuBar;
import com.gabriel.draw.service.DeawingCommandAppService;
import com.gabriel.draw.service.DrawingAppService;
import com.gabriel.draw.controller.DrawingController;
import com.gabriel.draw.view.DrawingToolBar;
import com.gabriel.draw.view.DrawingView;
import com.gabriel.draw.view.DrawingFrame;
import com.gabriel.drawfx.service.AppService;
import com.gabriel.draw.controller.SelectModeController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;


public class Main {
    public static void main(String[] args) {
        AppService drawingAppService = new DrawingAppService();
        DeawingCommandAppService appService = new DeawingCommandAppService(drawingAppService);

        DrawingFrame drawingFrame = new DrawingFrame(appService);
        ActionListener actionListener = new ActionController(appService);
        DrawingMenuBar drawingMenuBar = new DrawingMenuBar(actionListener);
        DrawingToolBar drawingToolBar = new DrawingToolBar(actionListener);
        appService.setDrawingToolBar(drawingToolBar);

        DrawingView drawingView = new DrawingView(appService);
        DrawingController drawingController = new DrawingController(appService, drawingView);
        SelectModeController selectController = new SelectModeController(appService, drawingView);

        drawingView.addMouseMotionListener(drawingController);
        drawingView.addMouseListener(drawingController);
        drawingView.addMouseMotionListener(selectController);
        drawingView.addMouseListener(selectController);

        drawingFrame.setContentPane(drawingView);

        drawingMenuBar.setVisible(true);
        drawingFrame.setJMenuBar(drawingMenuBar);
        drawingFrame.getContentPane().add(drawingToolBar, BorderLayout.PAGE_START);

        drawingFrame.setVisible(true);
        drawingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        drawingFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
}