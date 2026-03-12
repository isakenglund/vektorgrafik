package controller.states.shapes;

import view.ShapeApp;
import model.shapes.CompositeShape;
import model.shapes.Point;
import model.shapes.Shape;
import model.shapes.style.Style;

import java.util.ArrayList;
import java.util.List;

public class StateInsertCustom extends StateInsert {

    public StateInsertCustom(ShapeApp app) {
        super(app);
    }


    @Override
    protected Shape createShape(Point start, double width, double height, Style style) {
        List<Shape> markedShapes = app.getShapeContainer().getIsMarked();

        if (markedShapes == null || markedShapes.isEmpty()) {
            return null; // Säkerhet om inget är markerat
        }

        // 1. Klona figurerna och räkna ut deras ursprungliga position och storlek!
        List<Shape> clones = new ArrayList<>();
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = -Double.MAX_VALUE;
        double maxY = -Double.MAX_VALUE;

        for (Shape shape : markedShapes) {
            Shape clone = shape.peel().clone(); // Klonar för att inte förstöra originalen på ytan!
            clones.add(clone);

            double halfW = Math.abs(clone.getWidth()) / 2.0;
            double halfH = Math.abs(clone.getHeight()) / 2.0;
            Point pos = clone.getPosition();

            minX = Math.min(minX, pos.getX() - halfW);
            minY = Math.min(minY, pos.getY() - halfH);
            maxX = Math.max(maxX, pos.getX() + halfW);
            maxY = Math.max(maxY, pos.getY() + halfH);
        }

        // 2. Skapa gruppen på sin originalplats först.
        // I din Shape-klass hanteras 'p' som övre vänstra hörnet.
        Point originalTopLeft = new Point(minX, minY);
        double originalWidth = maxX - minX;
        double originalHeight = maxY - minY;

        CompositeShape customShape = new CompositeShape(originalTopLeft, originalWidth, originalHeight, clones, style);

        // 3. Nu utnyttjar vi dina egna metoder för att tvinga in dem i den ruta du drar med musen!

        // Flytta till musens mittpunkt...
        Point newCenter = new Point(start.getX() + width / 2.0, start.getY() + height / 2.0);
        customShape.moveTo(newCenter);

        // ...och dra i hörnet så den skalas korrekt!
        Point newCorner = new Point(start.getX() + width, start.getY() + height);
        customShape.resizeTo(newCorner);

        return customShape;
    }
}
