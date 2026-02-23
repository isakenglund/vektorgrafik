package States;

import Main.ShapeApp;
import Shapes.Point;

public class StateDelete extends State{

    public StateDelete(ShapeApp app){
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        app.getShapeContainer().select(point);
        Shapes.Shape selected = app.getShapeContainer().getSelected();
        if (selected != null)
            app.getShapeContainer().removeShape(selected);
        app.getShapeContainer().setSelected(null);
        app.getShapeContainer().repaint(); // uppmanar swing att måla om
    }

}
