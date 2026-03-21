package controller.states;

import model.shapes.Circle;
import model.shapes.CompositeShape;
import model.shapes.Point;
import model.shapes.Shape;
import model.shapes.style.Style;
import model.shapes.style.StyleFactory;
import view.ShapeApp;

import java.awt.*;
import java.util.List;
import java.util.stream.Collectors;

public class StateDrawSelected extends State {

    private Point lastMousePosition;
    private List<Shape> shapes;

    public StateDrawSelected(ShapeApp app) {
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        System.out.println(app.getShapeContainer().getIsMarked().isEmpty());

        if (!app.getShapeContainer().getIsMarked().isEmpty()){
            lastMousePosition = point;
        }

    }

    @Override
    public void pointerMoved(Point point, boolean pointerDown) {
            if (lastMousePosition != null) {
                double dx = point.getX() - lastMousePosition.getX();
                double dy = point.getY() - lastMousePosition.getY();

                double distance = Math.sqrt(dx * dx + dy * dy);
                System.out.println(distance);

                if(distance>10) {
                    List<Shape> clonedShapes = app.getShapeContainer()
                            .getIsMarked()
                            .stream()
                            .map(shape -> {
                                Shape clone = shape.clone();
                                clone.peel();
                                return clone;
                            })
                            .collect(Collectors.toList());

                    System.out.println("adding shapes");
                    app.getShapeContainer().addShape(
                            new CompositeShape(point, 10, 10, clonedShapes,
                                    StyleFactory.getInstance().getStyle(Color.BLACK, 1))
                    );
                    lastMousePosition = point;

                    app.getShapeContainer().repaint();

                }
            }
    }

    @Override
    public void pointerUp(Point point) {
        if(lastMousePosition != null) {
            lastMousePosition = null;
        }
        app.getShapeContainer().repaint();
    }
}
