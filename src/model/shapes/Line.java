package model.shapes;

import model.shapes.style.Style;

import java.awt.*;

public class Line extends Shape {

    public Line(Point p, double width, double height, Style style) {
        super(p, width, height, style);
    }

    public Line(Line line) {
        super(line);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);
        int x1 = (int) (getPosition().getX() - getWidth() / 2);
        int y1 = (int) (getPosition().getY() - getHeight() / 2);
        int x2 = (int) (getPosition().getX() + getWidth() / 2);
        int y2 = (int) (getPosition().getY() + getHeight() / 2);

        g.drawLine(x1, y1, x2, y2);
    }

    @Override
    public Shape clone() {
        return new Line(this);
    }
}
