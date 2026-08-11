package controller.states.shapes;

import view.ShapeApp;
import model.shapes.Point;
import model.shapes.Shape;
import model.shapes.style.Style;
import model.shapes.Text;

public class StateInsertText extends StateInsert {

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
