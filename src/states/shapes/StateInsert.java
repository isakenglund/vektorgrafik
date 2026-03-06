package states.shapes;

import main.ShapeApp;
import main.ShapeContainer;
import shapes.Point;
import shapes.Shape;
import shapes.style.Style;
import states.State;

public abstract class StateInsert extends State {

    private Point startPoint;
    private Shape tempShape;

    public StateInsert(ShapeApp app) {
        super(app);
    }

    protected abstract Shape createShape(Point start, double width, double height, Style style);

    @Override
    public void pointerDown(Point point) {
        startPoint = point;
    }

    @Override
    public void pointerMoved(Point point, boolean pointerDown) {
        if (pointerDown && startPoint != null) {
            ShapeContainer shapes = app.getShapeContainer();

            if (tempShape != null) {
                shapes.removeShape(tempShape);
            }

            double width = point.getX() - startPoint.getX();
            double height = point.getY() - startPoint.getY();

            Style currentStyle = app.getCurrentStyle();
            tempShape = createShape(startPoint, width, height, currentStyle);

            shapes.addShape(tempShape);
            shapes.repaint();
        }
    }

    @Override
    public void pointerUp(Point point) {

        super.pointerUp(point);

        ShapeContainer shapes = app.getShapeContainer();

        if (tempShape != null) {
            shapes.removeShape(tempShape);
            tempShape = null;
        }
        if (startPoint != null) {
            double width = point.getX() - startPoint.getX();
            double height = point.getY() - startPoint.getY();

            Style currentStyle = app.getCurrentStyle();
            shapes.addShape(createShape(startPoint, width, height, currentStyle));
        }

        startPoint = null;
        shapes.repaint();
    }


}

