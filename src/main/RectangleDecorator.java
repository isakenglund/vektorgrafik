package main;


import shapes.Point;
import shapes.Shape;

import java.awt.*;

public class RectangleDecorator extends ShapeDecorator{


    public RectangleDecorator(Shape decoratee) {
        super(decoratee);
    }

    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
        Point position = super.getPosition();
        double width = super.getWidth();
        double height = super.getHeight();

        int x = (int)(width < 0 ? position.getX() + width - 1 : position.getX() - 1);
        int y = (int)(height < 0 ? position.getY() + height - 1 : position.getY() - 1);
        int w = (int)(Math.abs(width) + 2);
        int h = (int)(Math.abs(height) + 2);

        g.drawRect(x, y, w, h);
    }
}
