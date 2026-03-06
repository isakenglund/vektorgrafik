package states.shapes;

import main.ShapeApp;
import shapes.Circle;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.style.Style;

public class StateInsertRectangle extends StateInsert{
    public StateInsertRectangle(ShapeApp app) {
        super(app);    }

    @Override
    protected Shape createShape(Point start, double width, double height, Style style) {
        return new Rectangle(start, width, height, style);
    }
}
