package shapes;

import shapes.style.Style;

import java.awt.*;

public class Line extends Shape {

    public Line(Point p1, double width, double height, Style style) {
        super(p1.getX(), p1.getY(), width, height, style);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        g.drawLine((int) getPosition().getX(), (int) getPosition().getY(),
                (int) (getPosition().getX() + getWidth()), (int) (getPosition().getY() + getHeight()));

    }
}
