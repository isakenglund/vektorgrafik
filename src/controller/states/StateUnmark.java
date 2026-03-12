package controller.states;

import view.ShapeApp;
import view.ShapeContainer;
import model.shapes.Point;
import model.shapes.Shape;

public class StateUnmark extends State {
    public StateUnmark(ShapeApp app) {
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        app.getShapeContainer().select(point);
        Shape selected = app.getShapeContainer().getSelected();
        ShapeContainer shapeContainer = app.getShapeContainer();

        if (selected != null && selected.isMarked()) {
            Shape unmarkedShape = selected.peel();
            unmarkedShape.setMarked(false);
            shapeContainer.removeShape(selected);
            shapeContainer.addShape(unmarkedShape);
            app.getShapeContainer().repaint();
        }
    }
}
