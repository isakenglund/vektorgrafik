package states;

import main.ShapeApp;
import main.ShapeContainer;
import shapes.Circle;
import shapes.Point;
import shapes.Rectangle;

public class StateInsertCircle extends StateInsert {
    public StateInsertCircle(ShapeApp app) {
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        ShapeContainer shapes = app.getShapeContainer();
        shapes.addShape(new Circle(point, Math.random() * 50.0));
        app.getShapeContainer().repaint();
    }
}
