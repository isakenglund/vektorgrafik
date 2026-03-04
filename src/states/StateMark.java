package states;

import main.CircleDecorator;
import main.RectangleDecorator;
import main.ShapeApp;
import main.ShapeContainer;
import shapes.Circle;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;

public class StateMark extends State{


    public StateMark(ShapeApp app){
        super(app);
    }
    @Override
    public void pointerDown(Point point) {
        app.getShapeContainer().select(point);
        Shape selected = app.getShapeContainer().getSelected();
        ShapeContainer shapeContainer = app.getShapeContainer();

        if(selected != null && !selected.isMarked())
        {
            Shape markedShape = new RectangleDecorator(selected);

            markedShape.setMarked(true);
            shapeContainer.removeShape(selected);
            shapeContainer.addShape(markedShape);
            app.getShapeContainer().repaint();
        }
    }
}
