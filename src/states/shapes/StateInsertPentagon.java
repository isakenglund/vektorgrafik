package states.shapes;

import main.ShapeApp;
import shapes.*;

public class StateInsertPentagon extends StateInsert{

    public StateInsertPentagon(ShapeApp app) {
        super(app);
    }

    @Override
    protected Shape createShape(Point start, double width, double height) {
        return new Pentagon(start, width, height);
    }
}
