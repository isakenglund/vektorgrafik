package controller;

import view.ShapeApp;
import model.shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.*;
import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

public class ShapeController {

    public void updateShapes(ShapeApp shapeApp) {
        for (Shape shape : shapeApp.getShapeContainer().getShapes()) {
            Shape actualShape = shape.peel();

            if (actualShape.isMarked()) {
                actualShape.setStyle(shapeApp.getCurrentStyle());
            }
        }
        shapeApp.getShapeContainer().repaint();
    }


    public void printComponent(Component component) {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName("Print Canvas");

        pj.setPrintable((pg, pf, pageNum) -> {

            if (pageNum > 0) {
                return Printable.NO_SUCH_PAGE;
            }

            Graphics2D g2 = (Graphics2D) pg;

            double pageWidth = pf.getImageableWidth();
            double pageHeight = pf.getImageableHeight();

            double compWidth = component.getWidth();
            double compHeight = component.getHeight();

            double scale = pageWidth / compWidth;

            g2.translate(pf.getImageableX(), pf.getImageableY());
            g2.scale(scale, scale);

            component.paint(g2);

            return Printable.PAGE_EXISTS;
        });

        if (!pj.printDialog()) {
            return;
        }

        try {
            pj.print();
        } catch (PrinterException e) {
            showMessageDialog(null, "Error printing component", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public List<Shape> getMarkedShapes (ShapeApp app){

        List<Shape> savedShapes = new ArrayList<>();
        List<Shape> currentlyMarked = app.getShapeContainer().getIsMarked();

        for (Shape shape : currentlyMarked) {
            savedShapes.add(shape.peel().clone());
        }

        return savedShapes;
    }
}
