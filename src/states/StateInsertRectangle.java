package states;

import main.ShapeApp;
import main.ShapeContainer;
import shapes.Point;
import shapes.Rectangle;

public class StateInsertRectangle extends StateInsert{
    public StateInsertRectangle(ShapeApp app) {
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        ShapeContainer shapes = app.getShapeContainer();
        shapes.addShape(new Rectangle(point, Math.random() * 50.0 + 10,Math.random() * 50.0 + 10));
        app.getShapeContainer().repaint();
    }
}
