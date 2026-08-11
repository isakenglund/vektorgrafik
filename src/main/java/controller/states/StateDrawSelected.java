package controller.states;

import model.shapes.CompositeShape;
import model.shapes.Point;
import model.shapes.Shape;
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
                int maxDistance = 10;

                if(distance>maxDistance&&!app.getShapeContainer().getIsMarked().isEmpty()) {


                    List<Shape> clonedShapes = app.getShapeContainer().getIsMarked().stream().map(shape -> {
                                Shape c = shape.peel().clone();
                                c.setPosition(point);
                                return c;
                            }).collect(Collectors.toList());

                    double width = clonedShapes.stream().mapToDouble(Shape::getWidth).max().orElse(0);
                    double height = clonedShapes.stream().mapToDouble(Shape::getHeight).max().orElse(0);

                    Shape s = new CompositeShape(point, width,height, clonedShapes, StyleFactory.getInstance().getStyle(Color.BLACK, 1));
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
