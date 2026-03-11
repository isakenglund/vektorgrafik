package states.shapes;

import main.ShapeApp;
import shapes.Point;
import shapes.Shape;
import shapes.style.Style;
import shapes.Text;

public class StateInsertText  extends StateInsert {

    private String text;

    public StateInsertText(ShapeApp app, String text) {
        super(app);
        this.text = text;
    }

    @Override
    protected Shape createShape(Point start, double width, double height, Style style) {
        return new Text(start, width, height, style, text);
    }

}
