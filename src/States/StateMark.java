package States;

import Main.ShapeApp;
import Main.ShapeContainer;
import Main.ShapeDecorator;
import Shapes.Point;
import Shapes.Shape;

import javax.swing.*;
import java.awt.*;

public class StateMark extends State{


    public StateMark(ShapeApp app){
        super(app);
    }
    @Override
    public void pointerDown(Point point) {
        app.getShapeContainer().select(point);
        Shape selected = app.getShapeContainer().getSelected();
        ShapeContainer shapeContainer = app.getShapeContainer();

        if(selected != null)
        {
            Shape markedShape = new ShapeDecorator(selected);
            shapeContainer.removeShape(selected);
            shapeContainer.addShape(markedShape);
            app.getShapeContainer().repaint();
        }
    }

}
