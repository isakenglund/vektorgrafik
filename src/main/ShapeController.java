package main;

import shapes.Point;
import shapes.Shape;
import shapes.Circle;
import shapes.Rectangle;
import shapes.Triangle;
import shapes.style.StyleFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import static javax.swing.JOptionPane.showMessageDialog;

public class ShapeController {

    public void updateShapes(ShapeApp shapeApp) {
        for(Shape shape: shapeApp.getShapeContainer().getShapes()) {
            Shape actualShape = shape.peel();

            if(actualShape.isMarked()) {
                actualShape.setStyle(shapeApp.getCurrentStyle());
            }
        }
        shapeApp.getShapeContainer().repaint();
    }

    public void createPanelButton(JPanel panel, String label, ActionListener listener) {
        JButton button = new JButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }

    public void createMenuItem(JMenu menu, String label, ActionListener listener)
    {
        JRadioButton menuItem = new JRadioButton(label);
        menuItem.addActionListener(listener);
        menu.add(menuItem);
    }

    public void printComponent(Component component) {
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName("Print Component");

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
        String delimiter = ",";
        String line;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName + ".csv"))) {
            while ((line = br.readLine()) != null) {

                String[] values = line.split(delimiter);

                Shape shape = getShapeFromCsv(values);
                if (shape != null) {
                    shapeContainer.addShape(shape);
                }
            }
            shapeContainer.repaint();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public Shape getShapeFromCsv(String[] csv) {
        switch (csv[0].toLowerCase()) {
            case "triangle":
                double x = Double.parseDouble(csv[1]);
                double y = Double.parseDouble(csv[2]);
                double width = Double.parseDouble(csv[3]);
                double height = Double.parseDouble(csv[4]);
                return new Triangle(new Point(x,y), width, height, StyleFactory.getInstance().getStyle(Color.BLACK, 1));
            default:
                return null;
        }
    }
}
