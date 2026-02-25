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

        System.out.println(selected);
        if(selected instanceof Composite)
        {
            List<Shape> unmergeShape = ((Composite) selected).getChildren();
            System.out.println(unmergeShape.size());
            for(Shape shape: unmergeShape) {
                shapeContainer.addShape(shape);
            }
            shapeContainer.removeShape(selected);
            app.getShapeContainer().repaint();
        }
    }

}
