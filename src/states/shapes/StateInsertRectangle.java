package states.shapes;

import main.ShapeApp;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

public class StateInsertRectangle extends StateInsert{
    public StateInsertRectangle(ShapeApp app) {
        super(app);
    }

    @Override
    protected Shape createShape(Point start, double width, double height) {
        return new Rectangle(start, width, height);
    }
}
