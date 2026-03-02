package states.shapes;

import main.ShapeApp;
import main.ShapeContainer;
import shapes.Circle;
import shapes.Point;
import shapes.Shape;
import shapes.Triangle;

public class StateInsertTriangle extends StateInsert {

    public StateInsertTriangle(ShapeApp app) {
        super(app);
    }

    @Override
    protected Shape createShape(Point start, double width, double height) {
        return new Triangle(start, width, height);
    }
}
