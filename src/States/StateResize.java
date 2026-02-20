package States;

import Main.ShapeApp;
import Shapes.Point;

import javax.swing.*;
import java.awt.*;

public class StateResize extends State{
    public StateResize(ShapeApp app){
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        app.getShapeContainer().select(point);
    }



    @Override
    public void pointerMoved(Point point, boolean pointerDown) {
        if(app.getShapeContainer().getSelected() != null && pointerDown) {
            app.getShapeContainer().getSelected().resizeTo(point);
            app.getShapeContainer().repaint();
        }
    }
}
