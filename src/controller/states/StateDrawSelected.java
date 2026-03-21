package controller.states;

import model.shapes.Circle;
import model.shapes.CompositeShape;
import model.shapes.Point;
import model.shapes.Shape;
import model.shapes.style.Style;
import model.shapes.style.StyleFactory;
import view.ShapeApp;

import java.awt.*;
import java.util.ArrayList;

public class StateDrawSelected extends State {

    private Point lastMousePosition;
    private ArrayList<Shape> shapes;

    public StateDrawSelected(ShapeApp app) {
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        System.out.println("pointer down");
        Shape selectedShape = app.getShapeContainer().getSelected();
        System.out.println("selected shape: " + selectedShape);
        if (selectedShape != null){
            lastMousePosition = point;
        }

        shapes = (ArrayList<Shape>) app.getShapeContainer().getIsMarked();
        if (!shapes.isEmpty()){
            lastMousePosition = point;
        }

    }

    @Override
    public void pointerMoved(Point point, boolean pointerDown) {
        System.out.println("pointer moved");
        System.out.println(lastMousePosition);
            if (lastMousePosition != null) {
                double dx = point.getX() - lastMousePosition.getX();
                double dy = point.getY() - lastMousePosition.getY();

                double distance = Math.sqrt(dx * dx + dy * dy);
                System.out.println(distance);

                if(distance>10) {
                    //app.getShapeContainer().addShape(app.getShapeContainer().getSelected().clone());
                    //app.getShapeContainer().addShape(new Circle(point, 50,50, StyleFactory.getInstance().getStyle(Color.BLACK,1)));
                    app.getShapeContainer().addShape(new CompositeShape(point, 10,10, shapes,StyleFactory.getInstance().getStyle(Color.BLACK,1) ));

                    lastMousePosition = point;
                }
                app.getShapeContainer().repaint();
            }
    }

    @Override
    public void pointerUp(Point point) {
        if(lastMousePosition != null) {
            lastMousePosition = null;
        }
    }
}
