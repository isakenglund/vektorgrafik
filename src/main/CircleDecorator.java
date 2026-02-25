package main;

import java.awt.*;

import shapes.Point;
import shapes.Shape;

public class CircleDecorator extends ShapeDecorator{


    public CircleDecorator(Shape decoratee) {
        super(decoratee);
    }

    @Override
    public void draw(Graphics g)
    {
        super.draw(g);
        Point position = super.getPosition();
        int x = (int)(position.getX()-super.getWidth()/2.0) ;
        int y = (int)(position.getY()-super.getHeight()/2.0);
        g.drawOval(x,y,(int)(super.getWidth())+1,(int)(super.getHeight())+1);
    }
}
