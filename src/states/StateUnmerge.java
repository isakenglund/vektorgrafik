package states;

import main.ShapeApp;
import main.ShapeContainer;
import shapes.Composite;
import shapes.Point;
import shapes.Shape;

import java.util.List;

public class StateUnmerge extends State {

    public StateUnmerge(ShapeApp app){
        super(app);
    }

    @Override
    public void pointerDown(Point point) {
        app.getShapeContainer().select(point);
        Shape selected = app.getShapeContainer().getSelected();
        ShapeContainer shapeContainer = app.getShapeContainer();

        if(selected instanceof Composite)
        {
            List<Shape> unmergeShape = ((Composite) selected).getChildren();
            for(Shape shape: unmergeShape) {
                shapeContainer.addShape(shape.peel());
            }
            shapeContainer.removeShape(selected);
            app.getShapeContainer().repaint();
        }
    }

}
