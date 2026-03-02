package states.shapes;

import main.ShapeApp;
import shapes.Circle;
import shapes.Point;
import shapes.Shape;

public class StateInsertCircle extends StateInsert {
    public StateInsertCircle(ShapeApp app) {
        super(app);
    }

    @Override
    protected Shape createShape(Point start, double width, double height) {
        return new Circle(start, width, height);
    }
}
