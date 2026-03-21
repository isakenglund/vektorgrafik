package controller.states;

import model.shapes.Point;
import model.shapes.Shape;
import view.ShapeApp;

public class StateDrawSelected extends State {

    private Point lastMousePosition;

    public StateDrawSelected(ShapeApp app) {
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        System.out.println("pointer down");

        Shape selectedShape = app.getShapeContainer().getSelected();
        System.out.println("selected shape: " + selectedShape);
        if (selectedShape != null){
            System.out.println("pointer down");
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
                    app.getShapeContainer().addShape(app.getShapeContainer().getSelected().clone());
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
