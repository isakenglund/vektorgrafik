package states.shapes;

import main.ShapeApp;
import shapes.Line;
import shapes.Point;
import shapes.Shape;

public class StateInsertLine extends StateInsert {


    public StateInsertLine(ShapeApp app) {
        super(app);
    }

    @Override
    protected Shape createShape(Point start, double width, double height) {
        return new Line(start, width, height);
    }
}