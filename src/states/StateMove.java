package states;

import main.ShapeApp;
import shapes.Point;

public class StateMove extends State {
    public StateMove(ShapeApp app){
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        app.getShapeContainer().select(point);
    }



    @Override
    public void pointerMoved(Point point, boolean pointerDown) {
        if(app.getShapeContainer().getSelected() != null && pointerDown) {
            app.getShapeContainer().getSelected().moveTo(point);
            app.getShapeContainer().repaint();
        }
    }
}
