package states.shapes;

import main.ShapeApp;
import shapes.Circle;
import shapes.Point;
import shapes.Shape;
import shapes.style.Style;

public class StateInsertCircle extends StateInsert {

    public StateInsertCircle(ShapeApp app) {
        super(app);
    }

    @Override
    protected Shape createShape(Point start, double width, double height, Style style) {
        return new Circle(start, width, height, style);
    }
}
