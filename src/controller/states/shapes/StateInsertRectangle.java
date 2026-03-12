package controller.states.shapes;

import view.ShapeApp;
import model.shapes.Point;
import model.shapes.Rectangle;
import model.shapes.Shape;
import model.shapes.style.Style;

public class StateInsertRectangle extends StateInsert{
    public StateInsertRectangle(ShapeApp app) {
        super(app);    }

    @Override
    protected Shape createShape(Point start, double width, double height, Style style) {
        return new Rectangle(start, width, height, style);
    }
}
