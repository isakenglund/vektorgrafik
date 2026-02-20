package States;

import Main.ShapeApp;
import Main.ShapeContainer;
import Shapes.Circle;
import Shapes.Point;

import javax.swing.*;
import java.awt.*;

public class StateInsert extends State {

    public StateInsert(ShapeApp app) {
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        ShapeContainer shapes = app.getShapeContainer();
        shapes.addShape(new Circle(point, Math.random() * 50.0));
        app.getShapeContainer().repaint(); // uppmanar swing att måla om
    }
}

