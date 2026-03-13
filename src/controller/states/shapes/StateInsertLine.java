package controller.states.shapes;

import view.ShapeApp;
import model.shapes.Line;
import model.shapes.Point;
import model.shapes.Shape;
import model.shapes.style.Style;

public class StateInsertLine extends StateInsert {

    public StateInsertLine(ShapeApp app) {
        super(app);
    }

    @Override
    protected Shape createShape(Point start, double width, double height, Style style) {
        return new Line(start, width, height, style);
    }
}