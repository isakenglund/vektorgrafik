package main;

import java.awt.*;

import shapes.Point;
import shapes.Shape;

public class CircleDecorator extends ShapeDecorator{


    public CircleDecorator(Shape decoratee) {
        super(decoratee);
    }

    @Override
    public void draw(Graphics g) {
        super.draw(g);

        Point center = super.getPosition();
        double radius = super.getWidth() / 2.0;

        int padding = 2;

        int x = (int) Math.round(center.getX() - radius) - padding;
        int y = (int) Math.round(center.getY() - radius) - padding;
        int w = (int) Math.round(2 * radius) + 2 * padding;
        int h = (int) Math.round(2 * radius) + 2 * padding;

        g.drawOval(x, y, w, h);
    }
}
