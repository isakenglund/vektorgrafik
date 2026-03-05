package states.shapes;

import main.ShapeApp;
import main.ShapeContainer;
import shapes.Circle;
import shapes.Point;
import shapes.Shape;
import shapes.Triangle;
import shapes.style.Style;

public class StateInsertTriangle extends StateInsert {

    public StateInsertTriangle(ShapeApp app) {
        super(app);    }

    @Override
    protected Shape createShape(Point start, double width, double height, Style style) {
        return new Triangle(start.getX(),start.getY(), width, height, style);
    }
}
