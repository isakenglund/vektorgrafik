package controller.states;

import view.ShapeApp;
import view.ShapeContainer;
import model.shapes.ShapeDecorator;
import model.shapes.Point;
import model.shapes.Rectangle;
import model.shapes.Shape;
import model.shapes.style.StyleFactory;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class StateMark extends State {
    private Point pointDown;
    private Point startPoint;
    private Shape tempShape;
    private List<Shape> shapesOnCanvas;


    public StateMark(ShapeApp app) {
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        super.pointerDown(point);
        this.pointDown = point;
        this.startPoint = point;
        shapesOnCanvas = app.getShapeContainer().getShapes();
    }

    @Override
    public void pointerMoved(Point point, boolean pointerDown) {
        if (pointerDown && startPoint != null) {
            ShapeContainer shapes = app.getShapeContainer();

            if (tempShape != null) {
                shapes.removeShapeTemp(tempShape);
            }

            double width = point.getX() - startPoint.getX();
            double height = point.getY() - startPoint.getY();

            tempShape = createShape(startPoint, width, height);

            shapes.addShapeTemp(tempShape);
            shapes.repaint();
        }
    }

    @Override
    public void pointerUp(Point point) {
        app.getShapeContainer().removeShapeTemp(tempShape);
        List<Shape> shapesInMerge = mergeShapes(shapesOnCanvas, pointDown, point);

        for (Shape shape : shapesInMerge) {
            shape.setMarked(true);
            shapesOnCanvas.add(new ShapeDecorator(shape));
        }
        app.getShapeContainer().repaint();
    }

    private Shape createShape(Point start, double width, double height) {
        return new Rectangle(start, width, height, StyleFactory.getInstance().getStyle(Color.BLACK, 1));
    }

    private List<Shape> mergeShapes(List<Shape> list, Point pointDown, Point pointUp) {

        List<Shape> shapesInMerge = new ArrayList<>();

        list.forEach(shape -> {
            if (shape.getPosition().getX() > pointDown.getX() &&
                    shape.getPosition().getX() < pointUp.getX() &&
                    shape.getPosition().getY() > pointDown.getY() &&
                    shape.getPosition().getY() < pointUp.getY()
            ) {
                shapesInMerge.add(shape);
            }
        });


        app.getShapeContainer().getShapes().removeAll(shapesInMerge);

        return shapesInMerge;
    }
}
