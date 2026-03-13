package controller;
import view.*;
import model.shapes.*;
import model.shapes.Shape;
import javax.swing.*;
import java.awt.*;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.*;
import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

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


    public void printComponent(ShapeContainer component) {
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

            component.unMarkAll();

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

    public void exportFile(ShapeContainer shapeContainer) {
        String fileName = JOptionPane.showInputDialog("Enter file name");
        try {
            FileWriter fileWriter = new FileWriter(fileName+".csv");
            fileWriter.write(shapeContainer.exportShapesToCSV());
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void importFile(ShapeContainer shapeContainer) {
        String fileName = JOptionPane.showInputDialog("Enter file name");
        try (BufferedReader br = new BufferedReader(new FileReader(fileName + ".csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.trim().isEmpty() || line.equals("end")) continue;

                String[] values = line.split(",");
                shapeContainer.addShape(ShapeFactory.createShapeFromCsv(values, br));
            }
            shapeContainer.repaint();
        } catch (IOException e) {
            System.err.println("Fel vid inläsning: " + e.getMessage());
        }
    }


}
