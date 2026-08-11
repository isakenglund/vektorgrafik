package model.shapes;

import model.shapes.style.Style;

import java.awt.*;

public class Circle extends Shape {


    public Circle(Point p, double width, double height, Style style) {
        super(p, width, height, style);
    }

    public Circle(Circle circle) {
        super(circle);
    }


    @Override
    public void draw(Graphics g) {
        
        super.draw(g);

        int w = (int) Math.abs(getWidth());
        int h = (int) Math.abs(getHeight());

        int x = (int) (getPosition().getX() - w / 2);
        int y = (int) (getPosition().getY() - h / 2);

        g.drawOval(x, y, w, h);
    }

    @Override
    public Shape clone() {
        return new Circle(this);
    }
}