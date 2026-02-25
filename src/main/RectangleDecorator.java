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
        int x = (int)(position.getX()-1);
        int y = (int)(position.getY()-1);
        g.drawRect(x,y,(int)(super.getWidth()+2),(int)(super.getHeight()+2));
    }
}
