package controller.states.shapes;

import view.ShapeApp;
import model.shapes.Circle;
import model.shapes.Point;
import model.shapes.Shape;
import model.shapes.style.Style;

public class StateInsertCircle extends StateInsert {

    public StateInsertCircle(ShapeApp app) {
        super(app);
    }

    @Override
    protected Shape createShape(Point start, double width, double height, Style style) {
        return new Circle(start, width, height, style);
    }
}
