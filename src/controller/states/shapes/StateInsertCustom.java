package controller.states.shapes;

import view.ShapeApp;
import model.shapes.CompositeShape;
import model.shapes.Point;
import model.shapes.Shape;
import model.shapes.style.Style;

import java.util.ArrayList;
import java.util.List;

public class StateInsertCustom extends StateInsert {

    private List<Shape> listOfMarkedShapes;

    public StateInsertCustom(ShapeApp app, List<Shape> savedShapes) {
        super(app);

        listOfMarkedShapes = savedShapes;
    }


    @Override
    protected Shape createShape(Point start, double width, double height, Style style) {
        if (listOfMarkedShapes == null || listOfMarkedShapes.isEmpty()) {
            return null;
        }

        List<Shape> clonesForDrawing = new ArrayList<>();
        for (Shape shape : listOfMarkedShapes) {
            clonesForDrawing.add(shape.clone());
        }

        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = -Double.MAX_VALUE;
        double maxY = -Double.MAX_VALUE;

        for (Shape shape : clonesForDrawing) {
            double halfW = Math.abs(shape.getWidth()) / 2.0;
            double halfH = Math.abs(shape.getHeight()) / 2.0;
            Point pos = shape.getPosition();

            minX = Math.min(minX, pos.getX() - halfW);
            minY = Math.min(minY, pos.getY() - halfH);
            maxX = Math.max(maxX, pos.getX() + halfW);
            maxY = Math.max(maxY, pos.getY() + halfH);
        }

        Point originalTopLeft = new Point(minX, minY);
        double originalWidth = maxX - minX;
        double originalHeight = maxY - minY;

        CompositeShape customShape = new CompositeShape(originalTopLeft, originalWidth, originalHeight, clonesForDrawing, style);

        Point newCenter = new Point(start.getX() + width / 2.0, start.getY() + height / 2.0);
        customShape.moveTo(newCenter);

        Point newCorner = new Point(start.getX() + width, start.getY() + height);
        customShape.resizeTo(newCorner);

        return customShape;
    }
}
