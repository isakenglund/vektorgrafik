package states.shapes;

import main.ShapeApp;
import main.ShapeContainer;
import shapes.Point;
import shapes.Shape;
import states.State;

public abstract class StateInsert extends State {

    private Point startPoint;
    private Shape tempShape;

    public StateInsert(ShapeApp app) {
        super(app);
    }

    // Abstrakt metod: Subklasserna tvingas definiera VILKEN figur som ritas
    protected abstract Shape createShape(Point start, double width, double height);

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

            // Här anropar vi den abstrakta metoden
            tempShape = createShape(startPoint, width, height);

            shapes.addShape(tempShape);
            shapes.repaint();
        }
    }

    @Override
    public void pointerUp(Point point) {
        // Kör logiken från State-klassen först (avmarkerar eventuella valda figurer)
        super.pointerUp(point);

        ShapeContainer shapes = app.getShapeContainer();

        // Rensa preview-figuren
        if (tempShape != null) {
            shapes.removeShape(tempShape);
            tempShape = null;
        }

        // Skapa den slutgiltiga figuren
        if (startPoint != null) {
            double width = point.getX() - startPoint.getX();
            double height = point.getY() - startPoint.getY();

            shapes.addShape(createShape(startPoint, width, height));
        }

        startPoint = null;
        shapes.repaint();
    }
}

