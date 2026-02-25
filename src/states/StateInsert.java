package states;

import main.ShapeApp;
import main.ShapeContainer;
import shapes.Circle;
import shapes.Point;
import shapes.Rectangle;

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

