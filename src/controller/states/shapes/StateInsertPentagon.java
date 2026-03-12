package controller.states.shapes;

import view.ShapeApp;
import model.shapes.*;
import model.shapes.style.Style;

public class StateInsertPentagon extends StateInsert{

    public StateInsertPentagon(ShapeApp app) {
        super(app);    }

    @Override
    protected Shape createShape(Point start, double width, double height, Style style) {
        return new Pentagon(start, width, height,style);
    }
}
