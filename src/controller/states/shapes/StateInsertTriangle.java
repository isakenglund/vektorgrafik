package controller.states.shapes;

import view.ShapeApp;
import model.shapes.Point;
import model.shapes.Shape;
import model.shapes.Triangle;
import model.shapes.style.Style;

public class StateInsertTriangle extends StateInsert {

    public StateInsertTriangle(ShapeApp app) {
        super(app);
    }

    @Override
    protected Shape createShape(Point start, double width, double height, Style style) {
        return new Triangle(start, width, height, style);
    }
}
