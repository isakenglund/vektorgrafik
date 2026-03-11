package states.shapes;

import main.ShapeApp;
import shapes.Circle;
import shapes.Line;
import shapes.Point;
import shapes.Shape;
import shapes.style.Style;

public class StateInsertLine extends StateInsert {

    public StateInsertLine(ShapeApp app) {
        super(app);    }

    @Override
    protected Shape createShape(Point start, double width, double height, Style style) {
        return new Line(start, width, height, style);
    }
}