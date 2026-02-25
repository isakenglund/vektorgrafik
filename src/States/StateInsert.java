package States;

import Main.ShapeApp;
import Main.ShapeContainer;
import Shapes.Circle;
import Shapes.Point;
import Shapes.Rectangle;

import javax.swing.*;
import java.awt.*;

public class StateInsert extends State {

    private String shapeType;
    public StateInsert(ShapeApp app, String type) {
        super(app);
        shapeType = type;
    }

    @Override
    public void pointerDown(Point point) {
        ShapeContainer shapes = app.getShapeContainer();
        if (shapeType.equals("RECTANGLE")){
            shapes.addShape(new Rectangle(point, Math.random() * 50.0 + 10,Math.random() * 50.0 + 10));

        }else if( shapeType.equals("CIRCLE")){
            shapes.addShape(new Circle(point, Math.random() * 50.0));
        }
        app.getShapeContainer().repaint();
    }
}

