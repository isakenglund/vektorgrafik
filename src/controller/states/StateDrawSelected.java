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

                if(distance>10&&!app.getShapeContainer().getIsMarked().isEmpty()) {
                    List<Shape> clonedShapes = app.getShapeContainer()
                            .getIsMarked()
                            .stream()
                            .map(shape -> {
                                Shape c = shape.peel().clone();
                                c.setPosition(point);
                                return c;
                            })
                            .collect(Collectors.toList());
                    System.out.println(clonedShapes.size());
                    Shape s = new CompositeShape(point, 50,50, clonedShapes, StyleFactory.getInstance().getStyle(Color.BLACK, 1));
                    s.setPosition(point);
                    app.getShapeContainer().addShape(s);
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
    }
}
