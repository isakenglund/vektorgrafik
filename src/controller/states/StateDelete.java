package controller.states;

import view.ShapeApp;
import model.shapes.Point;

public class StateDelete extends State {

    public StateDelete(ShapeApp app) {
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        super.pointerDown(point);
        app.getShapeContainer().select(point);
        model.shapes.Shape selected = app.getShapeContainer().getSelected();
        if (selected != null)
            app.getShapeContainer().removeShape(selected);
        app.getShapeContainer().setSelected(null);
        app.getShapeContainer().repaint(); // uppmanar swing att måla om
    }

}
