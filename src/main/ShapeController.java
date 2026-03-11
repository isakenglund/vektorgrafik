package main;

import shapes.Shape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

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

    public void printComponent(Component component){
        PrinterJob pj = PrinterJob.getPrinterJob();
        pj.setJobName(" Print Component ");

        pj.setPrintable (new Printable() {
            public int print(Graphics pg, PageFormat pf, int pageNum){
                if (pageNum > 0){
                    return Printable.NO_SUCH_PAGE;
                }

                Graphics2D g2 = (Graphics2D) pg;
                g2.translate(pf.getImageableX(), pf.getImageableY());
                component.paint(g2);
                return Printable.PAGE_EXISTS;
            }
        });
        if (pj.printDialog() == false)
            return;

        try {
            pj.print();
        } catch (PrinterException ex) {
            // handle exception
        }
    }
}
