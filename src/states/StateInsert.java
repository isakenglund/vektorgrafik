package states;

import main.ShapeApp;
import main.ShapeContainer;
import shapes.Circle;
import shapes.Point;
import shapes.Rectangle;

public abstract class StateInsert extends State {

    public StateInsert(ShapeApp app) {
        super(app);
    }
}

