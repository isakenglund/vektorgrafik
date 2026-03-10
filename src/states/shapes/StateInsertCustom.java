package states.shapes;

import main.ShapeApp;
import shapes.CompositeShape;
import shapes.Point;
import shapes.Rectangle;
import shapes.Shape;
import shapes.style.Style;

import java.util.ArrayList;
import java.util.List;

public class StateInsertCustom extends StateInsert {
    List<Shape> selected;

    public StateInsertCustom(ShapeApp app, List<Shape> selected) {
        super(app);
        this.selected = selected;
    }

    @Override
    protected Shape createShape(Point start, double width, double height, Style style) {
        return new CompositeShape(start,width,height,selected,style);

        // hämta alla figurer i markeringen
        // skapa en komposit figur av objekten
        // returnera kopia av figuren med prototypemönstret
    }
}
