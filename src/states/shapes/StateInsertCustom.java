package states.shapes;

import main.ShapeApp;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.style.Style;

public class StateInsertCustom extends StateInsert {
    Shape selected;

    public StateInsertCustom(ShapeApp app, Shape selected) {
        super(app);
        this.selected = selected;
    }

    @Override
    protected Shape createShape(Point start, double width, double height, Style style) {
        return new Rectangle(start, width, height, style);
    }
}
