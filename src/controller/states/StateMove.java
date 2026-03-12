package controller.states;

import view.ShapeApp;
import model.shapes.Point;
import model.shapes.Shape;
import model.visitor.MoveMarkedVisitor;

public class StateMove extends State {

    private Point lastMousePosition;
    private boolean isDraggingMarkedGroup;

    public StateMove(ShapeApp app){
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        lastMousePosition = point;

        app.getShapeContainer().select(point);
        Shape selected = app.getShapeContainer().getSelected();

        if (selected != null && selected.peel().isMarked()) {
            isDraggingMarkedGroup = true;
        } else {
            isDraggingMarkedGroup = false;
        }
    }

    @Override
    public void pointerMoved(Point point, boolean pointerDown) {
        if(pointerDown && lastMousePosition != null) {

            double dx = point.getX() - lastMousePosition.getX();
            double dy = point.getY() - lastMousePosition.getY();

            if (isDraggingMarkedGroup) {
                MoveMarkedVisitor visitor = new MoveMarkedVisitor(dx, dy);
                for (Shape shape : app.getShapeContainer().getShapes()) {
                    shape.accept(visitor);
                }
            } else if (app.getShapeContainer().getSelected() != null) {
                app.getShapeContainer().getSelected().move(dx, dy);
            }

            lastMousePosition = point;

            app.getShapeContainer().repaint();
        }
    }

    @Override
    public void pointerUp(Point point) {
        lastMousePosition = null;
    }
}